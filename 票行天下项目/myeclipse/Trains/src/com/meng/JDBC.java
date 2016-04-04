package com.meng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
	
	

    public  List<User> select_Db(String sql){
    	
    	List<User> list =new ArrayList<User>();
   
        try {
			
			//Mysql数据库连接
			//Class.forName("com.mysql.jdbc.Driver");	
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/login?user=root&password=986916");
			
			//sqlserver2008 连接数据库		
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.104:1433;DatabaseName=trainTicket","sa", "sql");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){	
				User u =new User();//实例化用户类型，将三种数据存入list
					u.setPhone(rs.getString("loginName"));
					u.setPassword(rs.getString("password"));		
					list.add(u);				
				
			}
			
			pstmt.close();
			rs.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return list;
		
	}
    
    
public  List<Vehicle> select_DbVehicle(String sql){

        List<Vehicle> list2 =new ArrayList<Vehicle>();
        
        try {
			
			//Mysql数据库连接
			//Class.forName("com.mysql.jdbc.Driver");	
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/login?user=root&password=986916");
			
			//sqlserver2008 连接数据库		
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.104:1433;DatabaseName=trainTicket","sa", "sql");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){	
				
				    Vehicle v =new Vehicle();
					v.setVehicleNum(rs.getString("vehicleNum"));
					v.setStartPlace(rs.getString("startPlace"));
					v.setEndPlace(rs.getString("endPlace"));
					v.setStartTime(rs.getTime("startTime").toString());
					v.setEndTime(rs.getTime("endTime").toString());
					v.setType(rs.getString("type"));
					v.setPrice(rs.getString("price"));
					v.setDate(rs.getString("date"));
					list2.add(v);
					
			}
			pstmt.close();
			rs.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return list2;
		
	}

public  Boolean insert_Db(String sql){
	
	Boolean isInsert = false;
		
   try {
		//sqlserver2008 连接数据库,server2008 设置了自动增长列，不需要设置null
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.104:1433;DatabaseName=trainTicket","sa", "sql");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();			
		
		pstmt.close();
		conn.close();
		
		isInsert = true;
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  return isInsert;
}
	

  public Boolean selectName(String name){
	
	  User u = new User();
	  String sql = "select * from users where loginName='"+name+"'";
	  System.out.println(sql);
	  List<User> list = select_Db(sql);
	  if(list.size()!=0){
		  return true;
	  }
	  return false;
	
  }
  
  public String selectPWD(String name){
 	 
 	 String password =null;
 	 
 	User u = new User();
 	 
 	String sql ="select * from users where loginName='" + name +"'";
 	
 	System.out.println(sql);
 	
 	 List<User> list = select_Db(sql);
 	 
 	 for(int i =0 ;i<list.size();i++){
		
		password = list.get(i).getPassword();
		
		return password;
	 }
 
    return  password;
  }
  
  public  Boolean insertRegister(String name ,String password){
	  
	  
	  Boolean isInsert = false;
		
	  if(name==null || password ==null){
		return false;
	  }
		
	  String sql ="insert into users values('"+name+"','"+password+"')" ;
	  
	  System.out.println(sql);
	  isInsert = insert_Db(sql);
	  
	  return isInsert;
	 
  }
  
  
  
  //查找车次
  public List<Vehicle> select_vehicle(String startPlace,String endPlace,String date,String type){
	  
	  
	  
	  
	  String sql ="select * from vehicle where startPlace='"+startPlace+"' and endPlace='"+endPlace+"' and type='"+type+"' and date ='"+date+"'";
	  
	  if(type.equals("全部")){
		 sql = "select * from vehicle where startPlace='"+startPlace+"' and endPlace='"+endPlace+"'and date ='"+date+"'";
	  }
	  
	  
	  System.out.println(sql);
	  
	  List<Vehicle> list2 = select_DbVehicle(sql);
	  
//	  for(int i=0;i<list2.size();i++){	  
//		  System.out.println(list2.get(i).getStartPlace());
//		  System.out.println(list2.get(i).getEndPlace());
//		  System.out.println(list2.get(i).getEndTime());
//		  System.out.println(list2.get(i).getPrice());
//		  System.out.println(list2.get(i).getStartTime());
//		  System.out.println(list2.get(i).getType());
//		  System.out.println(list2.get(i).getVehicleNum());
//		  System.out.println(list2.get(i).getDate());
//		  
//	  }
	  
	  
	  return list2;
  }
  
  
  public Boolean insert_Order(String name,String phone,String identityCard,String startPlace,String endPlace,String date,String vehicleNum,String price){
	  
	  Boolean ifinsert =false;
	  
	  String sql = "insert into orders values('"+name+"','"+phone+"','"+identityCard+"','"+startPlace+"','"+endPlace+"','"+date+"','"+vehicleNum+"','"+price+"')";
	  
	  System.out.println(sql);
	  
	  ifinsert = insert_Db(sql);
	  
	  return ifinsert;
	  
  }
  
  public List<Order> select_myorder(){
  	List<Order> list =new ArrayList<Order>();
    
    try {	
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.104:1433;DatabaseName=trainTicket","sa", "sql");
		PreparedStatement pstmt = conn.prepareStatement("select * from orders");
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()){	
			    Order o =new Order();//实例化用户类型，将三种数据存入list
				o.setDate(rs.getString("date"));
				o.setEndPlace(rs.getString("endPlace"));
				o.setIdentityCard(rs.getString("identityCard"));
				o.setName(rs.getString("name"));
                o.setPhone(rs.getString("phone"));
                o.setPrice(rs.getString("price"));
                o.setStartPlace(rs.getString("startPlace"));
                o.setVehicleNum(rs.getString("VehicleNum"));
				list.add(o);				
			
		}
		
		pstmt.close();
		rs.close();
		conn.close();
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		return list;
  }
  
}
