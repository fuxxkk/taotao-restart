package demo.test;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastdfsTest {

	@Test
	public void test() throws Exception {
		//获取配置文件路径
		String path = ClassLoader.getSystemResource("tracker.conf").toString();
		//判断操作系统
		if(path.split(":").length>2) {
			path = path.replace("file:/", "");
		}else {
			path = path.replace("file:", "");
		}
		
		ClientGlobal.init(path);
		
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackServer = trackerClient.getConnection();
		
		StorageServer storageServer = null;
		StorageClient storageClient =new StorageClient(trackServer, storageServer);
		
		//上传文件
		String[] filepath = storageClient.upload_file("f:\\a.txt", "txt", null);
		for (String string : filepath) {
			System.out.println(string);
		}
	}

}
