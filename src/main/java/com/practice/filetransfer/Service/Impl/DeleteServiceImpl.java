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

@Service
public class DeleteServiceImpl implements DeleteService {

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Override
	public Message Delete(String key, String bucketName, String fileType) throws Exception {

		if(fileType.equals(FileType.IMAGE))
			key = "Image/" + key.toString();
		else if (fileType.equals(FileType.VIDEO))
			key = "Video/" + key.toString();
		else
			throw new FileValidationException(MessageInfo.fileTypeError, ErrorCode.fileTypeError);

		qiNiuUtil.Delete(key,bucketName);
		return new Message(Status.OK, MessageInfo.success);
	}
}
