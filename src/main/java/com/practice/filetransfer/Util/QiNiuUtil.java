package com.practice.filetransfer.Util;

/**
 * 上传操作
 */

import com.google.gson.Gson;
import com.practice.filetransfer.Constant.AuthorizeInfo;
import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Exception.FileDeleteException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class QiNiuUtil {

	public String Upload(FileInputStream file, String key) throws Exception{
		//构造一个指定地区的配置类
		Configuration cfg = new Configuration(Region.huanan());
		//上传管理器
		UploadManager uploadManager = new UploadManager(cfg);
		//鉴权信息
		Auth auth = Auth.create(AuthorizeInfo.accessKey,AuthorizeInfo.secretKey);
		//获取上传令牌
		String upToken = auth.uploadToken(AuthorizeInfo.bucketName, null, 3600, new StringMap().put("force", "true"));//覆盖同名文件

		Response response = uploadManager.put(file,key,upToken,null,null);

		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		return AuthorizeInfo.bucketPath+putRet.key;
	}

	public void Delete(String key, String bucketName) throws Exception {
		//构造一个指定地区的配置类
		Configuration cfg = new Configuration(Region.huanan());
		//认证管理器
		Auth auth = Auth.create(AuthorizeInfo.accessKey, AuthorizeInfo.secretKey);
		//删除管理器
		BucketManager bucketManager = new BucketManager(auth, cfg);

		try {
			FileInfo fileInfo = bucketManager.stat(bucketName, key);
		} catch (QiniuException e) {
			throw new FileDeleteException(MessageInfo.fileNotExistError,ErrorCode.fileNotExistError);
		}

		Response response = bucketManager.delete(bucketName, key);

		//检查响应状态码，如果成功则不做处理，否则抛出异常
		if (response.statusCode != 200) {
			throw new FileDeleteException(MessageInfo.deleteFalseError,ErrorCode.deleteFalseError);
		}
	}
}
