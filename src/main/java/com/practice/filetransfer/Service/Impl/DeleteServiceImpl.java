package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Exception.FileValidationException;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.DeleteService;
import com.practice.filetransfer.Util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 删除服务
 */
@Service
public class DeleteServiceImpl implements DeleteService {

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Override
	public Message Delete(String key, String bucketName, String fileType) throws Exception {

		// 根据接口参数 选择要删除的文件所在文件夹
		if(fileType.equals(FileType.IMAGE))
			key = String.format("Image/%s", key);
		else if (fileType.equals(FileType.VIDEO))
			key = String.format("Video/%s", key);
		else
			throw new FileValidationException(MessageInfo.fileTypeError, ErrorCode.fileTypeError);

		qiNiuUtil.Delete(key,bucketName);

		return new Message(Status.OK, MessageInfo.success);
	}
}
