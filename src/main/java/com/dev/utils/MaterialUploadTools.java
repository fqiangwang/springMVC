package com.dev.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
/*import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class MaterialUploadTools {
	
	///private static Logger logger = LoggerFactory.getLogger(MaterialUploadTools.class);
	//浣跨敤鍗曚緥妯″紡
	private static volatile OSSClient client ;
	private static volatile MaterialUploadTools tool;
	private MaterialUploadTools(){
		if(client==null){
			
			client = new OSSClient(ApplicationConfig.getProperty("aliyun.url"), 
					ApplicationConfig.getProperty("aliyun.accessid"), ApplicationConfig.getProperty("aliyun.accesskey"));
		}
	}
	
	public static MaterialUploadTools getInstance() {  
	        if(tool==null){
	        	synchronized(MaterialUploadTools.class){
	        		if(tool==null){
	        			return tool=new MaterialUploadTools();
	        		}
	        	}
	        }
	        return tool;
	}  
	 
	public  String uploadAdvertFile(String fileName,InputStream in) throws Exception {

		// 浣跨敤榛樿鐨凮SS鏈嶅姟鍣ㄥ湴鍧�鍒涘缓OSSClient瀵硅薄銆�
		String key =ApplicationConfig.getProperty("aliyun.path") + fileName;
		try {
			uploadFile(ApplicationConfig.getProperty("aliyun.bucketName"), key, in, fileName);
			return fileName;//
		} catch (Exception e) {
			//logger.error("涓婁紶鏂囦欢澶辫触...");
			e.printStackTrace();
		}
		return null;
		
	}	
	// 涓婁紶鏂囦欢	
	private  void uploadFile( String bucketName, String key, InputStream in,String fileName) throws Exception {
		try{
			ObjectMetadata objectMeta = new ObjectMetadata();
			objectMeta.setContentLength(in.available());
			objectMeta.setCacheControl("no-cache");
			objectMeta.setHeader("Pragma", "no-cache");
			objectMeta.setContentDisposition("inline;filename=" + fileName);
			client.putObject(bucketName, key, in,objectMeta);
		}
		catch(Exception ex){
			//logger.error("涓婁紶鏂囦欢澶辫触(uploadFile):"+ex.getMessage());
			throw new Exception("涓婁紶鏂囦欢澶辫触(uploadFile):"+ex.getMessage());
		}
	}
	//OSS鍒犻櫎鏂囦欢
	public  void deleteFile(String fileName)throws Exception{
		try{
			String key = ApplicationConfig.getProperty("aliyun.path") + fileName;
			client.deleteObject(ApplicationConfig.getProperty("aliyun.bucketName"), key);
		}
		catch(Exception ex){
			//logger.error("OSS鍒犻櫎鏂囦欢(deleteFile):"+ex.getMessage());
			throw new Exception("OSS鍒犻櫎鏂囦欢(deleteFile):"+ex.getMessage());
		}
	}
	
	/**
	 * MD5璇诲彇鏂囦欢,鐢熸垚Md5楠岃瘉
	 * 娴嬭瘯
	 * @throws Exception 
	 * */
	/*public  static String getMd5ByFileTwo(InputStream inputStream) throws Exception {  
		try {
			String FileMD5 = DigestUtils.md5Hex(IOUtils.toByteArray(inputStream));    
		    IOUtils.closeQuietly(inputStream);
		    return FileMD5;
		} catch (Exception e) {
			logger.error(ExceptionUtils.printDetailException(e));
			throw new Exception(ExceptionUtils.printDetailException(e));
		}
	 } */
	
}
