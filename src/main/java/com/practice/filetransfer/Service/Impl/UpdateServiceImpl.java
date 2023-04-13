package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Exception.FileValidationException;
import com.practice.filetransfer.Filter.fileFormatFilter;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Message.SuccessMessage;
import com.practice.filetransfer.Service.UpdateService;
import com.practice.filetransfer.Util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Override
	public Message Upload(MultipartFile file,String fileName, String fileType) throws Exception {

		fileFormatFilter.fileValidateCheck(file,fileType);

		FileInputStream fileInputStream = (FileInputStream) file.getInputStream();

		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());//自动添加拓展名

		String key = null;
		if(fileType.equals(FileType.IMAGE)) {
			key = String.format("Image/%s.%s", fileName,extension);
		}
		else if(fileType.equals(FileType.VIDEO)) {
			key = String.format("Video/%s.%s", fileName,extension);
		}
		else
			throw new FileValidationException(MessageInfo.fileTypeError, ErrorCode.fileTypeError);

		String url = new QiNiuUtil().Upload(fileInputStream,key);

		return new SuccessMessage(Status.OK, MessageInfo.success, url);
	}

}
