package cn.gr.controller;

import cn.gr.service.NwtmService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yoho on 2016/11/30.
 */

@Controller
@RequestMapping("/api")
public class GeneralController {

    @Autowired
    NwtmService ns;

    private void onException(HttpServletResponse response,Exception e){
        e.printStackTrace();
        JSONObject res = new JSONObject();
        res.put("success", false);
        res.put("data", e.toString());
        try {
            response.getWriter().write(res.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void update(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(ns.update().toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }

    @RequestMapping(value = "/getCatalog", method = RequestMethod.POST)
    public void getCatalog(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(ns.getCatalog().toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getBrand", method = RequestMethod.POST)
    public void getBrand(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String msort = request.getParameter("msort");
            String misort = request.getParameter("misort");
            String size = request.getParameter("size");
            String flaw = request.getParameter("flaw");
            response.getWriter().write(ns.getBrand(msort,misort,size,flaw).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getSize", method = RequestMethod.POST)
    public void getSize(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String msort = request.getParameter("msort");
            String misort = request.getParameter("misort");
            String brand = request.getParameter("brand");
            String flaw = request.getParameter("flaw");
            response.getWriter().write(ns.getSize(msort,misort,brand,flaw).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getProduct", method = RequestMethod.POST)
    public void getProduct(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String msort = request.getParameter("msort");
            String misort = request.getParameter("misort");
            String brand = request.getParameter("brand");
            String size = request.getParameter("size");
            String salePriceSort = request.getParameter("salePriceSort");
            String flaw = request.getParameter("flaw");
            String page = request.getParameter("page");
            response.getWriter().write(ns.getProduct(msort,misort,brand,size,salePriceSort,flaw,page).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getProductBySku", method = RequestMethod.POST)
    public void getProductBySku(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String nmSku = request.getParameter("nmSku");
            response.getWriter().write(ns.getProductBySku(nmSku).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getShoppingCart", method = RequestMethod.POST)
    public void getShoppingCart(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String sc = request.getParameter("sc");
            response.getWriter().write(ns.getShoppingCart(sc).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public void submitOrder(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phone_number");
            String sku = request.getParameter("sku");
            String totalPrice = request.getParameter("total_price");
            response.getWriter().write(ns.submitOrder(name,address,phoneNumber,sku,totalPrice).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    public void getOrderList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String orderId = request.getParameter("orderId");
            String isSearch = request.getParameter("isSearch");
            String searchData = request.getParameter("searchData");
            String page = request.getParameter("page");
            String numOfEachPage = request.getParameter("numOfEachPage");
            response.getWriter().write(ns.getOrderList(orderId,isSearch,searchData,page,numOfEachPage).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/getOrderByOid", method = RequestMethod.POST)
    public void getOrderByOid(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String orderId = request.getParameter("orderId");
            response.getWriter().write(ns.getOrderByOid(orderId).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }
    @RequestMapping(value = "/updateExpress", method = RequestMethod.POST)
    public void updateExpress(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String orderId = request.getParameter("orderId");
            String orderStatus = request.getParameter("orderStatus");
            String expressNumber = request.getParameter("expressNumber");
            String expressInfo = request.getParameter("expressInfo");
            response.getWriter().write(ns.updateExpress(orderId,orderStatus,expressNumber,expressInfo).toString());
        } catch (Exception e) {
            onException(response,e);
        }
    }

}
