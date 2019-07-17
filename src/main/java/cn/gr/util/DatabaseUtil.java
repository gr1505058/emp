package cn.gr.util;


import cn.gr.entity.*;
import cn.gr.mapper.*;
import cn.gr.mymapper.MyProductMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class DatabaseUtil {
	@Autowired
	private NmCatalogMapper nmCatalogMapper;
	@Autowired
	private NmBrandMapper nmBrandMapper;
	@Autowired
	private NmSizeMapper nmSizeMapper;
	@Autowired
	private NmProductMapper nmProductMapper;
	@Autowired
	private MyProductMapper myProductMapper;
	@Autowired
	private NmOrderMapper nmOrderMapper;
	@Value("${num.page}")
	private int numOfEachPage;

	// catalog
	public List<NmCatalog> getAllCatalog() throws Exception{
		NmCatalogExample example = new NmCatalogExample();
		NmCatalogExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		return nmCatalogMapper.selectByExample(example);
	}
	public Map<String,String> getCatalogMap() throws Exception{
		List<NmCatalog> list = getAllCatalog();
		Map<String,String> map = Maps.newHashMap();
		for(NmCatalog nmCatalog : list){
			map.put(nmCatalog.getMsort()+"-"+nmCatalog.getMisort(),nmCatalog.getName());
		}
		return map;
	}
	public void updateCatalog(boolean isUpdate,String title,int msort,int misort) throws Exception{
		NmCatalog nmCatalog = new NmCatalog();
		nmCatalog.setName(title);
		nmCatalog.setMsort(msort);
		nmCatalog.setMisort(misort);
		if(isUpdate){
			nmCatalogMapper.updateByPrimaryKey(nmCatalog);
		}else{
			nmCatalogMapper.insert(nmCatalog);
		}
	}
	public void deleteCatalog(int msort,int misort) throws Exception{
		NmCatalogExample example = new NmCatalogExample();
		NmCatalogExample.Criteria criteria = example.createCriteria();
		criteria.andMsortEqualTo(msort);
		criteria.andMisortEqualTo(misort);
		nmCatalogMapper.deleteByExample(example);
	}


	// brand
	public List<NmBrand> getAllBrand() throws Exception{
		NmBrandExample example = new NmBrandExample();
		NmBrandExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		return nmBrandMapper.selectByExample(example);
	}
	public Map<Integer,String> getBrandMap() throws Exception{
		List<NmBrand> list = getAllBrand();
		Map<Integer,String> map = Maps.newHashMap();
		for(NmBrand nmBrand : list){
			map.put(nmBrand.getId(),nmBrand.getName());
		}
		return map;
	}
	public void updateBrand(boolean isUpdate,String title,int brandId,String firstWord) throws Exception{
		NmBrand nmBrand = new NmBrand();
		nmBrand.setName(title);
		nmBrand.setId(brandId);
		nmBrand.setFirstWord(firstWord);
		if(isUpdate){
			nmBrandMapper.updateByPrimaryKey(nmBrand);
		}else{
			nmBrandMapper.insert(nmBrand);
		}
	}
	public void deleteBrand(int brandId) throws Exception{
		nmBrandMapper.deleteByPrimaryKey(brandId);
	}
	public List<NmBrand> getBrand(int msort,int misort,int size,byte isFlaw) throws Exception{
		List<Integer> brandIds = myProductMapper.getBrandId(msort,misort,size,isFlaw);
		NmBrandExample example = new NmBrandExample();
		NmBrandExample.Criteria criteria = example.createCriteria();
		if(brandIds.size()>0){
			criteria.andIdIn(brandIds);
		}else{
			criteria.andIdIsNotNull();
		}
		return nmBrandMapper.selectByExample(example);
	}

	// size
	public List<NmSize> getAllSize() throws Exception{
		NmSizeExample example = new NmSizeExample();
		NmSizeExample.Criteria criteria = example.createCriteria();
		criteria.andIdIsNotNull();
		return nmSizeMapper.selectByExample(example);
	}
	public Map<Integer,String> getSizeMap() throws Exception{
		List<NmSize> list = getAllSize();
		Map<Integer,String> map = Maps.newHashMap();
		for(NmSize nmSize : list){
			map.put(nmSize.getId(),nmSize.getName());
		}
		return map;
	}
	public void updateSize(boolean isUpdate ,String title,int brandId) throws Exception{
		NmSize nmSize = new NmSize();
		nmSize.setName(title);
		nmSize.setId(brandId);
		if(isUpdate){
			nmSizeMapper.updateByPrimaryKey(nmSize);
		}else{
			nmSizeMapper.insert(nmSize);
		}
	}
	public void deleteSize(int sizeId) throws Exception{
		nmSizeMapper.deleteByPrimaryKey(sizeId);
	}
	public List<NmSize> getSize(int msort,int misort,int brand,byte isFlaw) throws Exception{
		List<Integer> sizeIds = myProductMapper.getSizeId(msort,misort,brand,isFlaw);
		NmSizeExample example = new NmSizeExample();
		NmSizeExample.Criteria criteria = example.createCriteria();
		if(sizeIds.size()>0){
			criteria.andIdIn(sizeIds);
		}else{
			criteria.andIdIsNotNull();
		}
		return nmSizeMapper.selectByExample(example);
	}
	public NmSize getSizeById(int id) throws Exception{
		return nmSizeMapper.selectByPrimaryKey(id);
	}

	// product
	public List<NmProduct> getAllProduct() throws Exception{
		NmProductExample example = new NmProductExample();
		NmProductExample.Criteria criteria = example.createCriteria();
		criteria.andNmSkuIsNotNull();
		return nmProductMapper.selectByExample(example);
	}
	public Map<Integer,String> getProductMap() throws Exception{
		List<NmProduct> list = getAllProduct();
		Map<Integer,String> map = Maps.newHashMap();
		for(NmProduct nmProduct : list){
			map.put(nmProduct.getNmSku(),nmProduct.getSku());
		}
		return map;
	}
	public void updateProduct(boolean isUpdate,NmProduct nmProduct) throws Exception{
		if(isUpdate){
			nmProductMapper.updateByPrimaryKey(nmProduct);
		}else{
			nmProductMapper.insert(nmProduct);
		}
	}
	public JSONObject getProduct(int msort,int misort,int brand,int size,int salePriceSort,byte isFlaw,int page) throws Exception{
		JSONObject res = new JSONObject();
		NmProductExample example = new NmProductExample();
		NmProductExample.Criteria criteria = example.createCriteria();
		criteria.andStockNumNotEqualTo(0);
		if(msort > 0){
			criteria.andMsortEqualTo(msort);
		}
		if(misort > 0){
			criteria.andMisortEqualTo(misort);
		}
		if(brand > 0){
			criteria.andBrandEqualTo(brand);
		}
		if(size > 0){
			criteria.andSizeEqualTo(size);
		}
		if(isFlaw == 0){
			criteria.andIsFlawEqualTo(isFlaw);
		}
		int totalCount = nmProductMapper.countByExample(example);
		int totalPage = (int)Math.ceil(totalCount*1.0/numOfEachPage);
		totalPage = totalPage==0?1:totalPage;
		res.put("totalPage",totalPage);
		if(salePriceSort==0){
			example.setOrderByClause("nm_sku limit "+(page-1)*numOfEachPage+","+numOfEachPage);
		}
		if(salePriceSort==1){
			example.setOrderByClause("my_sale_price asc limit "+(page-1)*numOfEachPage+","+numOfEachPage);
		}
		if(salePriceSort==2){
			example.setOrderByClause("my_sale_price desc limit "+(page-1)*numOfEachPage+","+numOfEachPage);
		}
		List<NmProduct> list= nmProductMapper.selectByExample(example);
		JSONArray prdArr = new JSONArray();
		for(NmProduct nmProduct : list){
			JSONObject object = new JSONObject();
			object.put("nmSku",nmProduct.getNmSku());
			object.put("imgMain",nmProduct.getImgMain());
			object.put("title",nmProduct.getTitle());
			object.put("flawDescribe",nmProduct.getFlawDescribe());
			object.put("marketPrice",nmProduct.getMarketPrice());
			object.put("mySalePrice",nmProduct.getMySalePrice());
			prdArr.add(object);
		}
		res.put("list",prdArr);
		return res;
	}
	public NmProduct getProductBySku(int nmSku) throws Exception{
		return nmProductMapper.selectByPrimaryKey(nmSku);
	}

	// order
	public int insertOrder(String name,String address,String phoneNumber,String sku,String totalPrice) throws Exception{
		NmOrder nmOrder = new NmOrder();
		nmOrder.setReceiveName(name);
		nmOrder.setReceiveAddress(address);
		nmOrder.setReceivePhoneNumber(phoneNumber);
		nmOrder.setSku(sku);
		nmOrder.setTotalPrice(totalPrice);
		nmOrder.setExpressInfo("[]");
		nmOrder.setAddTime(new Date());
		nmOrder.setExpressNumber("");
		nmOrder.setStatus(0);
		myProductMapper.insertOrderGetId(nmOrder);
		return nmOrder.getId();
	}
	public Object[] getOrderByPhoneNumber(String phoneNumber,int page,int numOfEachPage) throws Exception{
		Object[] ret = new Object[2];
		NmOrderExample example = new NmOrderExample();
		NmOrderExample.Criteria criteria = example.createCriteria();
		// 密码 可以查看全部订单
		if(phoneNumber.equals("#whosYourDaddy")){
			criteria.andIdIsNotNull();
		}else{
			if(phoneNumber.startsWith("5")){
				criteria.andIdEqualTo(Integer.parseInt(phoneNumber));
			}else{
				criteria.andReceivePhoneNumberEqualTo(phoneNumber);
			}
		}
		int totalCount = nmOrderMapper.countByExample(example);
		int totalPage = (int)Math.ceil(totalCount*1.0/numOfEachPage);
		totalPage = totalPage==0?1:totalPage;
		ret[0] = totalPage;
		example.setOrderByClause("id desc limit "+(page-1)*numOfEachPage+","+numOfEachPage);
		ret[1] = nmOrderMapper.selectByExample(example);
		return ret;
	}
	public Object[] getOrderByIdList(List<Integer> id,int page,int numOfEachPage) throws Exception{
		Object[] ret = new Object[2];
		NmOrderExample example = new NmOrderExample();
		NmOrderExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		int totalCount = nmOrderMapper.countByExample(example);
		int totalPage = (int)Math.ceil(totalCount*1.0/numOfEachPage);
		totalPage = totalPage==0?1:totalPage;
		ret[0] = totalPage;
		example.setOrderByClause("id desc limit "+(page-1)*numOfEachPage+","+numOfEachPage);
		ret[1] = nmOrderMapper.selectByExample(example);
		return ret;
	}
	public NmOrder getOrderById(int id) throws Exception{
		return nmOrderMapper.selectByPrimaryKey(id);
	}
	public int updateOrder(NmOrder nmOrder) throws Exception{
		return nmOrderMapper.updateByPrimaryKey(nmOrder);
	}
}
