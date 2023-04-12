package com.practice.filetransfer.Util;

/**
 * 上传操作
 */

import com.google.gson.Gson;
import com.practice.filetransfer.Constant.AuthorizeInfo;
import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Exception.FileValidationException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class QiNiuUtil {

	//@Autowired
	//private QiNiuConfig qiniuConfig;

	public String Upload(FileInputStream file, String fileName, String filetype) throws Exception{
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
		String upToken = auth.uploadToken(bucketName, null, 3600, new StringMap().put("force", "true"));//覆盖同名文件

		String key = null;
		if(filetype.equals(FileType.IMAGE))
			key = "Image/" + fileName.toString();
		else if(filetype.equals(FileType.VIDEO))
			key = "Video/" + fileName.toString();
		else
			throw new FileValidationException(MessageInfo.fileTypeError, ErrorCode.fileTypeError);

		Response response = uploadManager.put(file,key,upToken,null,null);
		//解析上传成功结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		return bucketPath+putRet.key;
	}
}
