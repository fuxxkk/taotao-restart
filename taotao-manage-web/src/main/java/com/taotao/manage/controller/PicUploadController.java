package com.taotao.manage.controller;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.fileuputils.FastDFSUtils;
import com.taotao.manage.vo.PicUploadResult;

@RequestMapping("pic")
@Controller
public class PicUploadController {
	
	private static final String[] IMAGES_TYPE= {".jpg",".png",".bmp",".jpeg",".gif"};
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Value("${TAOTAO_IMAGE_PATH}")
	private String TAOTAO_IMAGE_PATH;
	
	@RequestMapping(value = "upload",produces= MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String upload(@RequestParam(value="uploadFile")MultipartFile multipartFile) {
		PicUploadResult picUploadResult = new PicUploadResult();
		picUploadResult.setError(1); //非零为失败
		String result = "";
		boolean flag = false;
		
		//判断后缀名
		for (String type : IMAGES_TYPE) {
			if (multipartFile.getOriginalFilename().lastIndexOf(type)>0) {
				flag=true;
				break;
			}
		}
		
		//上传
		if(flag) {
			
			try {
				BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
				String trackConf = this.getClass().getClassLoader().getResource("tracker.conf").toString();
				String url = FastDFSUtils.upload(trackConf,multipartFile, null, TAOTAO_IMAGE_PATH);
				picUploadResult.setUrl(url);
				picUploadResult.setError(0);
				result = mapper.writeValueAsString(picUploadResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
}
