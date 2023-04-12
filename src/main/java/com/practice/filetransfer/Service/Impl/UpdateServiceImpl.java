package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Filter.fileFormatFilter;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Message.SuccessMessage;
import com.practice.filetransfer.Service.UpdateService;
import com.practice.filetransfer.Util.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Override
	public Message Upload(MultipartFile file, String fileType) throws Exception {

		fileFormatFilter.fileValidateCheck(file,fileType);

		String fileName = file.getOriginalFilename();
		FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
		String url = "";
		if (fileType.equals(FileType.IMAGE)) {
			url = new QiNiuUtil().Upload(fileInputStream,fileName, FileType.IMAGE);
		}
		else if (fileType.equals(FileType.VIDEO)) {
			url = new QiNiuUtil().Upload(fileInputStream,fileName, FileType.VIDEO);
		}
		fileInputStream.close();
		return new SuccessMessage(Status.OK, MessageInfo.success,url);
	}

	private boolean validateFileName(String fileName) {
		String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5_\\.]+$";
		return fileName.matches(regex);
	}
}
