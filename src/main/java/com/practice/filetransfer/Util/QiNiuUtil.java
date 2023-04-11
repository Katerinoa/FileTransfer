package com.practice.filetransfer.Util;

/**
 * 上传操作
 */

import com.google.gson.Gson;
import com.practice.filetransfer.Constant.AuthorizeInfo;
import com.practice.filetransfer.Constant.FileType;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class QiNiuUtil {

	//@Autowired
	//private QiNiuConfig qiniuConfig;

	public String Upload(FileInputStream file, String fileName, int filetype) throws Exception{
		//构造一个指定地区的配置类
		Configuration cfg = new Configuration(Region.huanan());
		//上传管理器
		UploadManager uploadManager = new UploadManager(cfg);
		//获取上传凭证
		String accessKey = AuthorizeInfo.accessKey;
		String secretKey = AuthorizeInfo.secretKey;
		String bucketName = AuthorizeInfo.bucketName;
		String bucketPath = AuthorizeInfo.bucketPath;
		//鉴权信息
		Auth auth = Auth.create(accessKey,secretKey);
		String upToken = auth.uploadToken(bucketName);

		String key = null;
		if(filetype == FileType.IMAGE)
			key = "Image/" + fileName.toString();
		else if(filetype == FileType.VIDEO)
			key = "Video/" + fileName.toString();
		else
			System.err.println("文件类型错误");  //TODO: 此处需抛出异常

		Response response = uploadManager.put(file,key,upToken,null,null);
		//解析上传成功结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		return bucketPath+putRet.key;
	}
}
