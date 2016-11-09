package com.lzx2005.tool;

import org.apache.log4j.Logger;

public class Log {
	private static Logger logger = Logger.getLogger(Log.class);
	// 记录error级别的信息 
	public static void e(Object obj,String msg){
		String info;
		if(obj instanceof String){
			info = "Error :"+obj.toString()+" - "+msg;
		}else if(obj instanceof Exception){
			Exception ex = (Exception)obj;
			ex.printStackTrace();
			info = ex.toString();
		}else{
			info = "Error :"+obj.getClass().getName()+" - "+msg;
		}
		System.err.println("--------------------------------------");
		System.err.println(info);
		System.err.println("--------------------------------------");
		// 记录error级别的信息  
	    logger.error(info);
	}
	// 记录error级别的信息 
		public static void e(Object obj,String msg,Exception e){
			String info;
			if(obj instanceof String){
				info = "Error :"+obj.toString()+" - "+msg;
			}else{
				info = "Error :"+obj.getClass().getName()+" - "+msg;
			}
			System.err.println("--------------------------------------");
			System.err.println(info);
			System.err.println("--------------------------------------");
			e.printStackTrace();
			System.err.println("--------------------------------------");
			// 记录error级别的信息  
		    logger.error(info);
		}
	
     
    // 记录info级别信息
	public static void i(Object obj,String msg){
		String info;
		if(obj instanceof String){
			info = "Info :"+obj.toString()+" - "+msg;
		}else{
			info = "Info :"+obj.getClass().getName()+" - "+msg;
		}
		System.out.println("--------------------------------------");
		System.out.println(info);
		System.out.println("--------------------------------------");
		// 记录info级别的信息  
	    logger.info(info); 
	}
	// 记录debug级别的信息  
	public static void d(String obj,String msg){
		String info;
		if(obj instanceof String){
			info = "Debug :"+obj.toString()+" - "+msg;
		}else{
			info = "Debug :"+obj.getClass().getName()+" - "+msg;
		}
		System.out.println("--------------------------------------");
		System.out.println(info);
		System.out.println("--------------------------------------");
		// 记录debug级别的信息  
	    logger.debug(info);  
	}
}
