package com.taotao.manage.fileuputils;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

public class FastDFSUtils {
	
	
	public  static String upload(String trackConf,MultipartFile multipartFile,NameValuePair[] msg,String url) throws Exception {
		
		
		if(trackConf.split(":").length>2) {
			trackConf = trackConf.replace("file:/", "");
		}else {
			trackConf = trackConf.replace("file:", "");
		}
		
		ClientGlobal.init(trackConf);
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
		//获取后缀名
		String file_ext_name = StringUtils.substringAfter(multipartFile.getOriginalFilename(), ".");
		String[] upload_filePath = storageClient.upload_file(multipartFile.getBytes(), file_ext_name, null);
		
		for (String string : upload_filePath) {
			url += "/"+string;
		}
		
		return url;
	}
}
