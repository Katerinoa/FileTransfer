package com.practice.filetransfer.Util;

/**
 * 上传操作
 */

import com.google.gson.Gson;
import com.practice.filetransfer.Constant.AuthorizeInfo;
import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.FileType;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 七牛云接口
 */
@Component
public class QiNiuUtil {

	public String Upload(FileInputStream file, String key) throws Exception{
		// 获取配置信息
		Configuration cfg = new Configuration(Region.huanan());
		// 获取上传管理器
		UploadManager uploadManager = new UploadManager(cfg);
		// 获取鉴权信息
		Auth auth = Auth.create(AuthorizeInfo.accessKey,AuthorizeInfo.secretKey);
		// 获取上传令牌
		String upToken = auth.uploadToken(AuthorizeInfo.bucketName, null, 3600, new StringMap().put("force", "true"));// put("force", "true") 覆盖同名文件

		Response response = uploadManager.put(file,key,upToken,null,null);

		// 解析上传信息
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		return AuthorizeInfo.bucketPath+putRet.key; //返回上传后的文件路径
	}

	public void Delete(String key, String bucketName) throws Exception {
		Configuration cfg = new Configuration(Region.huanan());
		Auth auth = Auth.create(AuthorizeInfo.accessKey, AuthorizeInfo.secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		//检查目标文件是否存在
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

	public List<FileInfo> QueryList(String fileType) throws Exception {
		Configuration cfg = new Configuration(Region.huanan());
		Auth auth = Auth.create(AuthorizeInfo.accessKey, AuthorizeInfo.secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);

		// 根据接口参数选择查询前缀，即查询的文件夹
		String prefix = "";
		if(fileType.equals(FileType.IMAGE))
			prefix = "Image/";
		else if(fileType.equals(FileType.VIDEO))
			prefix = "Video/";

		String marker = ""; //保存上一次查询的末尾标识，便于下次查询定位
		int limit = 1000;
		BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(AuthorizeInfo.bucketName, prefix, limit, marker);
		List<FileInfo> allFiles = new ArrayList<>();
		while (fileListIterator.hasNext()) {
			FileInfo[] items = fileListIterator.next();
			if (items.length > 0) {
				for (FileInfo item : items) {
					if(!item.key.endsWith("/")) //去除目录
						allFiles.add(item);
				}
				marker = items[items.length - 1].key;
			}
		}
		return allFiles;
	}
}
