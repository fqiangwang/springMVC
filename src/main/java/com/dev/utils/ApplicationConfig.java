package com.dev.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {

	private static Properties pps;
	//
	public static String test;
	
	static {
		pps = new Properties();
		try {
			pps.load(new FileInputStream("D:/workdir/cias-dev/cias-dev/src/main/resources/application.properties"));
			test=pps.getProperty("test");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return pps.getProperty(key);
	}
}
