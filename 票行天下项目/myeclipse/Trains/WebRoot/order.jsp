<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.meng.JDBC"%>
<%

    request.setCharacterEncoding("UTF-8");
    System.out.println("联系人" + request.getParameter("name").toString());
    System.out.println("手机" + request.getParameter("phone").toString());
    System.out.println("身份证" + request.getParameter("identityCard").toString());
    System.out.println("开始地" +request.getParameter("startPlace").toString());
    System.out.println("目的地" + request.getParameter("endPlace").toString());
    System.out.println("开始日期" + request.getParameter("date").toString());
    System.out.println("车次编号" + request.getParameter("vehicleNum").toString());
    System.out.println("价格" + request.getParameter("price").toString());
    
    
     JDBC jdbc = new JDBC();
     Boolean ifinsert ;
     
     ifinsert = jdbc.insert_Order(request.getParameter("name").toString(),request.getParameter("phone").toString(),request.getParameter("identityCard").toString(),request.getParameter("startPlace").toString(),request.getParameter("endPlace").toString(),request.getParameter("date").toString(),request.getParameter("vehicleNum").toString(),request.getParameter("price"));
     
     if(ifinsert){
       out.println("OK");
     }
    
%>
