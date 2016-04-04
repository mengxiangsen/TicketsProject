<%@page import="com.meng.JDBC"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
//登录的后台验证
   
     JDBC jdbc =new JDBC();
     
     //验证账号是否存在
        Boolean IsName = jdbc.selectName(request.getParameter("loginName"));
       // System.out.print(IsName);
        
     //账号存在后，通过数据库获取密码

        String password = jdbc.selectPWD(request.getParameter("loginName"));//通过android studio传递过来的账号  
        //System.out.print(password);
 
 
     if(IsName ){
          
       if(request.getParameter("password").equals(password)){
          out.println("OK");
       }else{
          out.println("passwordError");
          System.out.print("passwordError");
       }
     }else{
         out.println("nameError");
         System.out.print("nameError");
     }
     
%>
