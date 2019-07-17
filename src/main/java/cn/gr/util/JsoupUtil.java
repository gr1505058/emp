package cn.gr.util;

import cn.gr.entity.NmCatalog;
import cn.gr.entity.NmProduct;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by rong.gao on 2018/8/22.
 */
@Component
public class JsoupUtil {
    @Value("${nm.url.home}")
    private String homeUrl;
    @Value("${nm.url.erp2goods}")
    private String erp2goodsUrl;
    @Autowired
    private DatabaseUtil dbu;
    @Autowired
    private HttpClientUtil hu;

    private final String IMAGE_DIR = "D:\\nmImg\\";

    private final String IMAGE_SIZE_1 = "75x100";
    private final String IMAGE_SIZE_2 = "420x560";
    private final String IMAGE_SIZE_3 = "original";


    public void updateCatalogAndBrandAndSize() throws Exception{
        Document doc = Jsoup.connect(homeUrl).get();
        // catalog
        Map<String,String> catalogMap = dbu.getCatalogMap();
        Elements sortChildList = doc.getElementsByClass("sort-child-list");
        for(Element element : sortChildList){
            Elements elements = element.getElementsByTag("a");
            for(Element e : elements){
                String title = e.attr("title");
                String href = e.attr("href");
                int msort;
                int misort;
                if(href.contains("misort")){
                    String[] str = href.split("&");
                    msort = Integer.parseInt(str[0].split("msort=")[1]);
                    misort = Integer.parseInt(str[1].split("misort=")[1]);
                }else{
                    msort = Integer.parseInt(href.split("msort=")[1]);
                    misort = 0;
                    title = title.substring(2);
                }
                if(catalogMap.containsKey(msort+"-"+misort)){
                    catalogMap.remove(msort+"-"+misort);
                    dbu.updateCatalog(true,title,msort,misort);
                }else{
                    dbu.updateCatalog(false,title,msort,misort);
                }
            }
        }
        for(String k : catalogMap.keySet()){
            String[] ss = k.split("-");
            dbu.deleteCatalog(Integer.parseInt(ss[0]),Integer.parseInt(ss[1]));
        }
        // brand
        Map<Integer,String> brandMap = dbu.getBrandMap();
        Element checkContainer = doc.getElementsByClass("check-container").first();
        Elements liElements = checkContainer.getElementsByTag("li");
        for(Element element : liElements){
            String title = element.attr("data-key").trim();
            if(!title.equals("")){
                String firstWord = PinyinUtil.getFirstWord(title);
                String href = element.child(0).attr("href");
                int brandId = Integer.parseInt(href.split("brand=")[1]);
                if(brandMap.containsKey(brandId)){
                    brandMap.remove(brandId);
                    dbu.updateBrand(true,title,brandId,firstWord);
                }else{
                    dbu.updateBrand(false,title,brandId,firstWord);
                }
            }
        }
        for(int k : brandMap.keySet()){
            dbu.deleteBrand(k);
        }
        // size
        Map<Integer,String> sizeMap = dbu.getSizeMap();
        Element size = doc.getElementsByClass("size").first();
        Elements aElements = size.getElementsByTag("a");
        for(Element element : aElements){
            String title = element.text();
            String href = element.attr("href");
            int sizeId = Integer.parseInt(href.split("size=")[1]);
            if(sizeMap.containsKey(sizeId)){
                sizeMap.remove(sizeId);
                dbu.updateSize(true,title,sizeId);
            }else{
                dbu.updateSize(false,title,sizeId);
            }
        }
        for(int k : sizeMap.keySet()){
            dbu.deleteSize(k);
        }
    }

    public void updateProduct() throws Exception{
        Map<Integer,String> productList = dbu.getProductMap();
        List<NmCatalog> catalogList = dbu.getAllCatalog();
        for(NmCatalog nmCatalog : catalogList){
            int msort = nmCatalog.getMsort();
            int misort = nmCatalog.getMisort();
            if(misort != 0){
                Document doc = Jsoup.connect(homeUrl+"/?msort="+msort+"&misort="+misort).get();
                Elements brandElements = doc.getElementsByClass("check-container").first().getElementsByTag("li");
                for(Element brandElement : brandElements){
                    int brandId = Integer.parseInt(brandElement.child(0).attr("href").split("brand=")[1]);
                    Document doc2 = Jsoup.connect(homeUrl+"/?msort="+msort+"&misort="+misort+"&brand="+brandId).get();
                    Elements sizeElements = doc2.getElementsByClass("size").first().getElementsByTag("a");
                    for(Element sizeElement : sizeElements){
                        int sizeId = Integer.parseInt(sizeElement.attr("href").split("size=")[1]);
                        Document doc3 = Jsoup.connect(homeUrl+"/?msort="+msort+"&misort="+misort+"&brand="+brandId+"&size="+sizeId).get();
                        int maxPage = 1;
                        Element firstAElement = doc3.getElementsByClass("pager").first();
                        if(firstAElement != null){
                            Elements aElements = firstAElement.getElementsByTag("a");
                            if(aElements.size() > 0){
                                maxPage = Integer.parseInt(aElements.get(aElements.size()-2).text());
                            }
                        }
                        for(int page=1;page<=maxPage;page++){
                            Document doc4 = Jsoup.connect(homeUrl+"/?msort="+msort+"&misort="+misort+"&brand="+brandId+"&size="+sizeId+"&page="+page).get();
                            Elements goodDetailText = doc4.getElementsByClass("good-detail-text");
                            for(Element productElement : goodDetailText){
                                int nmSku = Integer.parseInt(productElement.child(0).attr("href").split("sku=")[1]);
                                NmProduct nmProduct = null;
                                try{
                                    nmProduct = getProductByHtml(nmSku,msort,misort,brandId,sizeId);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                if(nmProduct != null){
                                    dbu.updateProduct(productList.containsKey(nmSku),nmProduct);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private NmProduct getProductByHtml(int nmSku,int msort,int misort,int brand,int size) throws Exception{
        NmProduct nmProduct = new NmProduct();
        nmProduct.setNmSku(nmSku);
        nmProduct.setMsort(msort);
        nmProduct.setMisort(misort);
        nmProduct.setBrand(brand);
        nmProduct.setSize(size);
        Document doc = Jsoup.connect(homeUrl+"/goods?sku="+nmSku).get();
        Elements thumb = doc.getElementsByClass("thumb");
        JSONArray imgArr = new JSONArray();
        for(Element thumbElement : thumb){
            // 75x100
            String imgUrl = thumbElement.attr("src");
            imgArr.add(imgUrl);
        }
        nmProduct.setImgMain(imgArr.toJSONString());
        Element infosElement = doc.getElementsByClass("infos").first();
        nmProduct.setTitle(infosElement.child(0).text().trim());
        int marketPrice = Integer.parseInt(infosElement.child(1).child(1).text().trim().substring(1));
        nmProduct.setMarketPrice(marketPrice);
        int salePrice = Integer.parseInt(infosElement.child(2).child(1).text().trim().substring(1));
        nmProduct.setSalePrice(salePrice);
        nmProduct.setMySalePrice((int)(salePrice*1.1+10));
        Element detailElement = doc.getElementById("details-html");
        String flawDescribe = detailElement.child(0).text().trim().substring(5);
        nmProduct.setFlawDescribe(flawDescribe);
        nmProduct.setIsFlaw(testIsFlaw(flawDescribe));
        String sku = detailElement.child(1).getElementsByTag("a").first().text().split("sku=")[1];
        nmProduct.setSku(sku);
        nmProduct.setSkn(getSknBySku(sku));
        JSONArray detailArr = new JSONArray();
        Elements pElements = detailElement.getElementsByTag("p");
        for(Element pElement : pElements){
            Elements imgElements = pElement.getElementsByTag("img");
            Elements spanElements = pElement.getElementsByTag("span");
            if(imgElements.size()>0){
                for(Element e : imgElements){
                    detailArr.add("1"+e.attr("src"));
                }
            }else{
                if(spanElements.size()>0){
                    for(Element e : spanElements){
                        detailArr.add("0"+e.text().trim());
                    }
                }else{
                    detailArr.add("0"+pElement.text().trim());
                }
            }
        }
        nmProduct.setDetail(detailArr.toJSONString());
        boolean isHide = doc.getElementById("add-to-cart").hasClass("hide");
        nmProduct.setStockNum(isHide?0:1);
        nmProduct.setUpdateTime(new Date());
        return nmProduct;
    }
    private byte testIsFlaw(String flawDescribe) throws Exception{
        if(flawDescribe.contains("未穿过") || flawDescribe.contains("全新") || flawDescribe.contains("存货")
                || flawDescribe.contains("包装破旧") || flawDescribe.contains("好的") || flawDescribe.contains("新的")
                || flawDescribe.contains("尺寸不符") || flawDescribe.contains("无质量问题") || flawDescribe.contains("新的")){
            return 0;
        }
        return 1;
    }
    private String getSknBySku(String sku) throws Exception{
        String res = hu.doGetFromHeader(erp2goodsUrl+"?sku="+sku,"Location");
        if(res != null){
            int idx1 = res.lastIndexOf("/")+1;
            int idx2 = res.lastIndexOf(".");
            if(idx1 > 0 && idx2 > 0){
                return res.substring(idx1,idx2);
            }
        }
        return "";
    }


    public void updatePicture() throws Exception{
        File dir1 = new File(IMAGE_DIR+IMAGE_SIZE_1);
        File dir2 = new File(IMAGE_DIR+IMAGE_SIZE_2);
        File dir3 = new File(IMAGE_DIR+IMAGE_SIZE_3);
        if(!dir1.exists()){
            dir1.mkdirs();
        }
        if(!dir2.exists()){
            dir2.mkdirs();
        }
        if(!dir3.exists()){
            dir3.mkdirs();
        }
        Map<String , Integer> localImgMap1 = Maps.newHashMap();
        Map<String , Integer> localImgMap2 = Maps.newHashMap();
        Map<String , Integer> localImgMap3 = Maps.newHashMap();

        for(String s : dir1.list()){
            localImgMap1.put(s,0);
        }
        for(String s : dir2.list()){
            localImgMap2.put(s,0);
        }
        for(String s : dir3.list()){
            localImgMap3.put(s,0);
        }
        System.out.println("localImgMapSize:"+(localImgMap1.size()+localImgMap2.size()+localImgMap3.size()));
        List<NmProduct> list = dbu.getAllProduct();
        int size = list.size();
        int k = 1;
        for(NmProduct nmProduct : list){
            String imgMain = nmProduct.getImgMain();
            JSONArray imgMainArr = JSONArray.parseArray(imgMain);
            for(int i=0;i<imgMainArr.size();i++){
                String img = imgMainArr.getString(i);
                String imgUrl = img.split("\\?")[0];
                int idx = imgUrl.lastIndexOf("/");
                String imgName = imgUrl.substring(idx+1);
                if(!localImgMap1.containsKey(imgName)){
                    downLoadImg(IMAGE_SIZE_1,imgName,img);
                    // upload
                }
                if(!localImgMap2.containsKey(imgName)){
                    downLoadImg(IMAGE_SIZE_2,imgName,img.replaceAll(IMAGE_SIZE_1,IMAGE_SIZE_2));
                    // upload
                }
            }
            String detail = nmProduct.getDetail();
            JSONArray detailArr = JSONArray.parseArray(detail);
            for(int i=0;i<detailArr.size();i++){
                String img = detailArr.getString(i);
                if(img.startsWith("1")){
                    String imgUrl = img.substring(1);
                    if(imgUrl.contains("?")){
                        imgUrl = imgUrl.split("\\?")[0];
                    }
                    int idx = imgUrl.lastIndexOf("/");
                    String imgName = imgUrl.substring(idx+1);
                    if(!localImgMap3.containsKey(imgName)){
                        downLoadImg(IMAGE_SIZE_3,imgName,imgUrl+"?imageMogr2/thumbnail/420x560/extent/420x560/background/d2hpdGU=/position/center/quality/80");
                        // upload
                    }
                }
            }

            System.out.println("process:"+(k++)+"/"+size);
        }
    }

    private void downLoadImg(String imgSize , String imgName,String imgUrl) {
        if(imgUrl.contains("product")){return;}
        try{
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 创建文件
            File file = new File(IMAGE_DIR+imgSize+"\\"+imgName);
            FileOutputStream out = new FileOutputStream(file);
            int i = 0;
            while((i = is.read()) != -1){
                out.write(i);
            }
            is.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
