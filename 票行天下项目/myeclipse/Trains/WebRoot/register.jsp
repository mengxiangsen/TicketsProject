<%@page import="com.meng.JDBC"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
  
    //http://192.168.5.160:8080/Trains/register.jsp?loginName='17751777829'&password='12345'
    System.out.println("--name--" + request.getParameter("loginName") );
    System.out.println("--password--" + request.getParameter("password") );

    JDBC jdbc =new JDBC();
    Boolean is= jdbc.insertRegister(request.getParameter("loginName"), request.getParameter("password"));
    if(is){
      out.print("phoneOk");
    }else{
      out.print("Inserterror");
    }
  %>
 
