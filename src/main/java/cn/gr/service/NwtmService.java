package cn.gr.service;

import cn.gr.entity.*;
import cn.gr.util.AsyncExecutorUtil;
import cn.gr.util.DatabaseUtil;
import cn.gr.util.JsoupUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rong.gao on 2018/3/6.
 */
@Service
@Transactional
public class NwtmService {

    @Autowired
    private AsyncExecutorUtil aeu;
    @Autowired
    private DatabaseUtil dbu;
    @Autowired
    private JsoupUtil ju;

    public JSONObject update(){
        JSONObject res = new JSONObject();
        aeu.doExecute(new Runnable() {
            @Override
            public void run() {
                try {
                    updateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        res.put("success", true);
        res.put("data", "start update data");
        return res;
    }
    // 更新数据
    private void updateData() throws Exception{
        // 更新品类、品牌、尺码
        System.out.println("Start Update! 更新品类、品牌、尺码");
//        ju.updateCatalogAndBrandAndSize();
        // 更新商品
        System.out.println("更新商品");
//        ju.updateProduct();
        // 更新图片
        System.out.println("更新图片");
//        ju.updatePicture();
        System.out.println("All Done");
    }

    public JSONObject getCatalog() throws Exception{
        JSONObject res = new JSONObject();
        List<NmCatalog> list = dbu.getAllCatalog();
        JSONArray arr = new JSONArray();
        for(NmCatalog nmCatalog : list){
            if(nmCatalog.getMisort() == 0){
                JSONObject objectL1 = new JSONObject();
                objectL1.put("name",nmCatalog.getName());
                JSONArray children = new JSONArray();
                for(NmCatalog nmCatalog2 : list){
                    if(nmCatalog2.getMsort().equals(nmCatalog.getMsort())){
                        JSONObject objectL2 = new JSONObject();
                        objectL2.put("msort",nmCatalog2.getMsort());
                        objectL2.put("misort",nmCatalog2.getMisort());
                        if(nmCatalog2.getMisort() == 0){
                            objectL2.put("name","全部"+nmCatalog2.getName());
                        }else{
                            objectL2.put("name",nmCatalog2.getName());
                        }
                        children.add(objectL2);
                    }
                }
                objectL1.put("children",children);
                arr.add(objectL1);
            }
        }
        res.put("success", true);
        res.put("data", arr);
        return res;
    }

    public JSONObject getBrand(String msort,String misort,String size,String flaw) throws Exception{
        JSONObject res = new JSONObject();
        List<NmBrand> list = dbu.getBrand(Integer.parseInt(msort),Integer.parseInt(misort),Integer.parseInt(size),Byte.parseByte(flaw));
        NmBrand all = new NmBrand();
        all.setId(0);
        all.setName("全部");
        all.setFirstWord("");
        list.add(0,all);
        res.put("success", true);
        res.put("data", list);
        return res;
    }
    public JSONObject getSize(String msort,String misort,String brand,String flaw) throws Exception{
        JSONObject res = new JSONObject();
        List<NmSize> list = dbu.getSize(Integer.parseInt(msort),Integer.parseInt(misort),Integer.parseInt(brand),Byte.parseByte(flaw));
        NmSize all = new NmSize();
        all.setId(0);
        all.setName("全部");
        list.add(0,all);
        res.put("success", true);
        res.put("data", list);
        return res;
    }

    public JSONObject getProduct(String msort,String misort,String brand,String size,String salePriceSort,String flaw,String page) throws Exception{
        JSONObject res = new JSONObject();
        JSONObject ret = dbu.getProduct(Integer.parseInt(msort),Integer.parseInt(misort),Integer.parseInt(brand),
                Integer.parseInt(size),Integer.parseInt(salePriceSort),Byte.parseByte(flaw),Integer.parseInt(page));
        res.put("success", true);
        res.put("data", ret.getJSONArray("list"));
        res.put("totalPage",ret.getIntValue("totalPage"));
        return res;
    }

    public JSONObject getProductBySku(String nmSKu) throws Exception{
        JSONObject res = new JSONObject();
        NmProduct product = dbu.getProductBySku(Integer.parseInt(nmSKu));
        JSONObject object = (JSONObject)JSONObject.toJSON(product);
        NmSize nmSize = dbu.getSizeById(product.getSize());
        object.put("sizeName",nmSize.getName());
        JSONArray arr = JSONArray.parseArray(product.getDetail());
        JSONArray arrRet = new JSONArray();
        for(int i=0;i<arr.size();i++){
            String s = arr.getString(i);
            String flag = s.substring(0,1);
            String ss = s.substring(1);
            JSONObject object1 = new JSONObject();
            object1.put("flag",flag);
            object1.put("val",ss);
            arrRet.add(object1);
        }
        object.put("detailObject",arrRet);
        res.put("success", true);
        res.put("data", object);
        return res;
    }
    public JSONObject getShoppingCart(String sc) throws Exception{
        JSONObject res = new JSONObject();
        List<JSONObject> list = Lists.newArrayList();
        JSONArray scArr = JSONArray.parseArray(sc);
        for(int i=0;i<scArr.size();i++){
            int sku = scArr.getIntValue(i);
            if(sku > 0){
                NmProduct product = dbu.getProductBySku(sku);
                if(product != null){
                    JSONObject object = new JSONObject();
                    object.put("nmSku",product.getNmSku());
                    object.put("imgMain",product.getImgMain());
                    object.put("title",product.getTitle());
                    object.put("flawDescribe",product.getFlawDescribe());
                    object.put("marketPrice",product.getMarketPrice());
                    object.put("mySalePrice",product.getMySalePrice());
                    NmSize nmSize = dbu.getSizeById(product.getSize());
                    object.put("sizeName",nmSize.getName());
                    object.put("check",true);
                    list.add(object);
                }
            }
        }
        res.put("success", true);
        res.put("data", list);
        return res;
    }
    public JSONObject submitOrder(String name,String address,String phoneNumber,String sku,String totalPrice) throws Exception{
        JSONObject res = new JSONObject();
        int id = dbu.insertOrder(name.trim(), address.trim(), phoneNumber.trim(),sku.trim(),totalPrice.trim());
        res.put("success", true);
        res.put("data", id);
        return res;
    }
    public JSONObject getOrderList(String orderId,String isSearch,String searchData,String page,String numOfEachPage) throws Exception{
        JSONObject res = new JSONObject();
        JSONArray resArr = new JSONArray();
        List<NmOrder> list = null;
        int totalPage = 1;
        int pageInt = Integer.parseInt(page);
        int numOfEachPageInt = Integer.parseInt(numOfEachPage);
        boolean isSearchBool = Boolean.parseBoolean(isSearch);
        if(isSearchBool){
            searchData = searchData.trim();
            if(!searchData.equals("")){
                Object[] dbRes = dbu.getOrderByPhoneNumber(searchData.trim(),pageInt,numOfEachPageInt);
                totalPage = (int)dbRes[0];
                list = (List<NmOrder>)dbRes[1];
            }
        }else{
            JSONArray orderIdArr = JSONArray.parseArray(orderId);
            List<Integer> idList = Lists.newArrayList();
            for(int i=0;i<orderIdArr.size();i++){
                idList.add(orderIdArr.getIntValue(i));
            }
            if(idList.size() > 0){
                Object[] dbRes = dbu.getOrderByIdList(idList,pageInt,numOfEachPageInt);
                totalPage = (int)dbRes[0];
                list = (List<NmOrder>)dbRes[1];
            }
        }
        if(list != null){
            for(NmOrder nmOrder : list){
                JSONObject object = (JSONObject)JSONObject.toJSON(nmOrder);
                String sku = nmOrder.getSku();
                JSONArray skuArr = JSONArray.parseArray(sku);
                int productNum = skuArr.size();
                object.put("productNum",productNum);
                if(productNum > 0){
                    int skuFirst = skuArr.getIntValue(0);
                    NmProduct product = dbu.getProductBySku(skuFirst);
                    if(product != null){
                        JSONObject object2 = (JSONObject)JSONObject.toJSON(product);
                        NmSize nmSize = dbu.getSizeById(product.getSize());
                        object2.put("sizeName",nmSize.getName());
                        object.put("prd",object2);
                    }
                }
                resArr.add(object);
            }
        }
        res.put("success", true);
        res.put("data", resArr);
        res.put("totalPage",totalPage);
        return res;
    }
    public JSONObject getOrderByOid(String orderId) throws Exception{
        JSONObject res = new JSONObject();
        NmOrder nmOrder = dbu.getOrderById(Integer.parseInt(orderId));
        JSONArray expressInfoList = JSONArray.parseArray(nmOrder.getExpressInfo());
        res.put("success", true);
        res.put("data", nmOrder);
        res.put("expressInfoList", expressInfoList);
        return res;
    }
    public JSONObject updateExpress(String orderId,String orderStatus,String expressNumber,String expressInfo) throws Exception{
        JSONObject res = new JSONObject();
        NmOrder nmOrder = dbu.getOrderById(Integer.parseInt(orderId));
        nmOrder.setStatus(Integer.parseInt(orderStatus));
        nmOrder.setExpressNumber(expressNumber);
        JSONArray expressInfoList = new JSONArray();
        for(String s : expressInfo.split("\n")){
            expressInfoList.add(s.trim());
        }
        nmOrder.setExpressInfo(expressInfoList.toJSONString());
        dbu.updateOrder(nmOrder);
        res.put("success", true);
        res.put("data", nmOrder);
        res.put("expressInfoList", expressInfoList);
        return res;
    }
}
