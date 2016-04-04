<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.meng.JDBC"%>
<%@page import="com.meng.Vehicle"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="sun.misc.BASE64Decoder"%>
<%

    request.setCharacterEncoding("UTF-8");

    System.out.println("出发城市" +request.getParameter("startPlace"));
    System.out.println("抵达城市" + request.getParameter("endPlace"));
    System.out.println("开始行车日期" + request.getParameter("date"));
    System.out.println("车类型" + request.getParameter("type"));

    JDBC jdbc = new JDBC();
    List<Vehicle> list2 = new ArrayList<Vehicle>();
    
    list2 = jdbc.select_vehicle(request.getParameter("startPlace"),request.getParameter("endPlace"),request.getParameter("date"),request.getParameter("type"));
    
 //   for(int i=0;i<list2.size();i++){
    
 //       System.out.println(list2.get(i).getStartPlace());
 //       System.out.println(list2.get(i).getEndPlace());
 //       System.out.println(list2.get(i).getPrice());
 //       System.out.println(list2.get(i).getType());
 //       System.out.println(list2.get(i).getVehicleNum());
        
 //   }
    
    JSONArray ja =JSONArray.fromObject(list2);
    System.out.print(ja.toString());
    out.print(ja.toString());
   
%>


