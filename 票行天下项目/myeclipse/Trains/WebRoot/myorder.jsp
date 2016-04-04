<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSON"%>
<%@page import="com.meng.Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.meng.JDBC"%>
<%
   
     JDBC jdbc = new JDBC();
     
     List<Order> list = new ArrayList<Order>();
     
     list = jdbc.select_myorder();
     
    JSONArray ja =JSONArray.fromObject(list);
    System.out.print(ja.toString());
    out.print(ja.toString());
%>
