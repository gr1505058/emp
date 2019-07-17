package cn.gr.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NmProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NmProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andNmSkuIsNull() {
            addCriterion("nm_sku is null");
            return (Criteria) this;
        }

        public Criteria andNmSkuIsNotNull() {
            addCriterion("nm_sku is not null");
            return (Criteria) this;
        }

        public Criteria andNmSkuEqualTo(Integer value) {
            addCriterion("nm_sku =", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuNotEqualTo(Integer value) {
            addCriterion("nm_sku <>", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuGreaterThan(Integer value) {
            addCriterion("nm_sku >", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuGreaterThanOrEqualTo(Integer value) {
            addCriterion("nm_sku >=", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuLessThan(Integer value) {
            addCriterion("nm_sku <", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuLessThanOrEqualTo(Integer value) {
            addCriterion("nm_sku <=", value, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuIn(List<Integer> values) {
            addCriterion("nm_sku in", values, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuNotIn(List<Integer> values) {
            addCriterion("nm_sku not in", values, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuBetween(Integer value1, Integer value2) {
            addCriterion("nm_sku between", value1, value2, "nmSku");
            return (Criteria) this;
        }

        public Criteria andNmSkuNotBetween(Integer value1, Integer value2) {
            addCriterion("nm_sku not between", value1, value2, "nmSku");
            return (Criteria) this;
        }

        public Criteria andMsortIsNull() {
            addCriterion("msort is null");
            return (Criteria) this;
        }

        public Criteria andMsortIsNotNull() {
            addCriterion("msort is not null");
            return (Criteria) this;
        }

        public Criteria andMsortEqualTo(Integer value) {
            addCriterion("msort =", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortNotEqualTo(Integer value) {
            addCriterion("msort <>", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortGreaterThan(Integer value) {
            addCriterion("msort >", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortGreaterThanOrEqualTo(Integer value) {
            addCriterion("msort >=", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortLessThan(Integer value) {
            addCriterion("msort <", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortLessThanOrEqualTo(Integer value) {
            addCriterion("msort <=", value, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortIn(List<Integer> values) {
            addCriterion("msort in", values, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortNotIn(List<Integer> values) {
            addCriterion("msort not in", values, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortBetween(Integer value1, Integer value2) {
            addCriterion("msort between", value1, value2, "msort");
            return (Criteria) this;
        }

        public Criteria andMsortNotBetween(Integer value1, Integer value2) {
            addCriterion("msort not between", value1, value2, "msort");
            return (Criteria) this;
        }

        public Criteria andMisortIsNull() {
            addCriterion("misort is null");
            return (Criteria) this;
        }

        public Criteria andMisortIsNotNull() {
            addCriterion("misort is not null");
            return (Criteria) this;
        }

        public Criteria andMisortEqualTo(Integer value) {
            addCriterion("misort =", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortNotEqualTo(Integer value) {
            addCriterion("misort <>", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortGreaterThan(Integer value) {
            addCriterion("misort >", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortGreaterThanOrEqualTo(Integer value) {
            addCriterion("misort >=", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortLessThan(Integer value) {
            addCriterion("misort <", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortLessThanOrEqualTo(Integer value) {
            addCriterion("misort <=", value, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortIn(List<Integer> values) {
            addCriterion("misort in", values, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortNotIn(List<Integer> values) {
            addCriterion("misort not in", values, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortBetween(Integer value1, Integer value2) {
            addCriterion("misort between", value1, value2, "misort");
            return (Criteria) this;
        }

        public Criteria andMisortNotBetween(Integer value1, Integer value2) {
            addCriterion("misort not between", value1, value2, "misort");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(Integer value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(Integer value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(Integer value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(Integer value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(Integer value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<Integer> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<Integer> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(Integer value1, Integer value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(Integer value1, Integer value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andIsFlawIsNull() {
            addCriterion("is_flaw is null");
            return (Criteria) this;
        }

        public Criteria andIsFlawIsNotNull() {
            addCriterion("is_flaw is not null");
            return (Criteria) this;
        }

        public Criteria andIsFlawEqualTo(Byte value) {
            addCriterion("is_flaw =", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawNotEqualTo(Byte value) {
            addCriterion("is_flaw <>", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawGreaterThan(Byte value) {
            addCriterion("is_flaw >", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_flaw >=", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawLessThan(Byte value) {
            addCriterion("is_flaw <", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawLessThanOrEqualTo(Byte value) {
            addCriterion("is_flaw <=", value, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawIn(List<Byte> values) {
            addCriterion("is_flaw in", values, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawNotIn(List<Byte> values) {
            addCriterion("is_flaw not in", values, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawBetween(Byte value1, Byte value2) {
            addCriterion("is_flaw between", value1, value2, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andIsFlawNotBetween(Byte value1, Byte value2) {
            addCriterion("is_flaw not between", value1, value2, "isFlaw");
            return (Criteria) this;
        }

        public Criteria andImgMainIsNull() {
            addCriterion("img_main is null");
            return (Criteria) this;
        }

        public Criteria andImgMainIsNotNull() {
            addCriterion("img_main is not null");
            return (Criteria) this;
        }

        public Criteria andImgMainEqualTo(String value) {
            addCriterion("img_main =", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainNotEqualTo(String value) {
            addCriterion("img_main <>", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainGreaterThan(String value) {
            addCriterion("img_main >", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainGreaterThanOrEqualTo(String value) {
            addCriterion("img_main >=", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainLessThan(String value) {
            addCriterion("img_main <", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainLessThanOrEqualTo(String value) {
            addCriterion("img_main <=", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainLike(String value) {
            addCriterion("img_main like", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainNotLike(String value) {
            addCriterion("img_main not like", value, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainIn(List<String> values) {
            addCriterion("img_main in", values, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainNotIn(List<String> values) {
            addCriterion("img_main not in", values, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainBetween(String value1, String value2) {
            addCriterion("img_main between", value1, value2, "imgMain");
            return (Criteria) this;
        }

        public Criteria andImgMainNotBetween(String value1, String value2) {
            addCriterion("img_main not between", value1, value2, "imgMain");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("market_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Integer value) {
            addCriterion("market_price =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Integer value) {
            addCriterion("market_price <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Integer value) {
            addCriterion("market_price >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_price >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Integer value) {
            addCriterion("market_price <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Integer value) {
            addCriterion("market_price <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Integer> values) {
            addCriterion("market_price in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Integer> values) {
            addCriterion("market_price not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Integer value1, Integer value2) {
            addCriterion("market_price between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("market_price not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Integer value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Integer value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Integer value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Integer value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Integer value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Integer> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Integer> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Integer value1, Integer value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceIsNull() {
            addCriterion("my_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andMySalePriceIsNotNull() {
            addCriterion("my_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andMySalePriceEqualTo(Integer value) {
            addCriterion("my_sale_price =", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceNotEqualTo(Integer value) {
            addCriterion("my_sale_price <>", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceGreaterThan(Integer value) {
            addCriterion("my_sale_price >", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("my_sale_price >=", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceLessThan(Integer value) {
            addCriterion("my_sale_price <", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceLessThanOrEqualTo(Integer value) {
            addCriterion("my_sale_price <=", value, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceIn(List<Integer> values) {
            addCriterion("my_sale_price in", values, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceNotIn(List<Integer> values) {
            addCriterion("my_sale_price not in", values, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceBetween(Integer value1, Integer value2) {
            addCriterion("my_sale_price between", value1, value2, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andMySalePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("my_sale_price not between", value1, value2, "mySalePrice");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeIsNull() {
            addCriterion("flaw_describe is null");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeIsNotNull() {
            addCriterion("flaw_describe is not null");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeEqualTo(String value) {
            addCriterion("flaw_describe =", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeNotEqualTo(String value) {
            addCriterion("flaw_describe <>", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeGreaterThan(String value) {
            addCriterion("flaw_describe >", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("flaw_describe >=", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeLessThan(String value) {
            addCriterion("flaw_describe <", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeLessThanOrEqualTo(String value) {
            addCriterion("flaw_describe <=", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeLike(String value) {
            addCriterion("flaw_describe like", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeNotLike(String value) {
            addCriterion("flaw_describe not like", value, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeIn(List<String> values) {
            addCriterion("flaw_describe in", values, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeNotIn(List<String> values) {
            addCriterion("flaw_describe not in", values, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeBetween(String value1, String value2) {
            addCriterion("flaw_describe between", value1, value2, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andFlawDescribeNotBetween(String value1, String value2) {
            addCriterion("flaw_describe not between", value1, value2, "flawDescribe");
            return (Criteria) this;
        }

        public Criteria andSkuIsNull() {
            addCriterion("sku is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("sku is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(String value) {
            addCriterion("sku =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(String value) {
            addCriterion("sku <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(String value) {
            addCriterion("sku >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(String value) {
            addCriterion("sku >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(String value) {
            addCriterion("sku <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(String value) {
            addCriterion("sku <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLike(String value) {
            addCriterion("sku like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotLike(String value) {
            addCriterion("sku not like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<String> values) {
            addCriterion("sku in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<String> values) {
            addCriterion("sku not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(String value1, String value2) {
            addCriterion("sku between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(String value1, String value2) {
            addCriterion("sku not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSknIsNull() {
            addCriterion("skn is null");
            return (Criteria) this;
        }

        public Criteria andSknIsNotNull() {
            addCriterion("skn is not null");
            return (Criteria) this;
        }

        public Criteria andSknEqualTo(String value) {
            addCriterion("skn =", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknNotEqualTo(String value) {
            addCriterion("skn <>", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknGreaterThan(String value) {
            addCriterion("skn >", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknGreaterThanOrEqualTo(String value) {
            addCriterion("skn >=", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknLessThan(String value) {
            addCriterion("skn <", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknLessThanOrEqualTo(String value) {
            addCriterion("skn <=", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknLike(String value) {
            addCriterion("skn like", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknNotLike(String value) {
            addCriterion("skn not like", value, "skn");
            return (Criteria) this;
        }

        public Criteria andSknIn(List<String> values) {
            addCriterion("skn in", values, "skn");
            return (Criteria) this;
        }

        public Criteria andSknNotIn(List<String> values) {
            addCriterion("skn not in", values, "skn");
            return (Criteria) this;
        }

        public Criteria andSknBetween(String value1, String value2) {
            addCriterion("skn between", value1, value2, "skn");
            return (Criteria) this;
        }

        public Criteria andSknNotBetween(String value1, String value2) {
            addCriterion("skn not between", value1, value2, "skn");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNull() {
            addCriterion("stock_num is null");
            return (Criteria) this;
        }

        public Criteria andStockNumIsNotNull() {
            addCriterion("stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andStockNumEqualTo(Integer value) {
            addCriterion("stock_num =", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotEqualTo(Integer value) {
            addCriterion("stock_num <>", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThan(Integer value) {
            addCriterion("stock_num >", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_num >=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThan(Integer value) {
            addCriterion("stock_num <", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("stock_num <=", value, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumIn(List<Integer> values) {
            addCriterion("stock_num in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotIn(List<Integer> values) {
            addCriterion("stock_num not in", values, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumBetween(Integer value1, Integer value2) {
            addCriterion("stock_num between", value1, value2, "stockNum");
            return (Criteria) this;
        }

        public Criteria andStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_num not between", value1, value2, "stockNum");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}