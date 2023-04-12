package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Message.ErrorMessage;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Message.SuccessMessage;
import com.practice.filetransfer.Service.UpdateService;
import com.practice.filetransfer.Filter.fileFormatFilter;
import com.practice.filetransfer.Util.QiNiuUtil;
import com.practice.filetransfer.config.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class UpdateServiceImpl implements UpdateService {

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Autowired
	MessageConfig messageConfig;

	@Override
	public Message Upload(MultipartFile file, String fileType) throws Exception {

		//
		//if (fileName == null) {
		//	System.err.println("传入的文件名不能为空");
		//	return new ErrorMessage(Status.FALSE, messageConfig.fileNameNullError,Status.fileNameNullError);
		//}
		//if (!this.validateFileName(fileName)) {
		//	System.err.println("文件名应仅包含汉字、字母、数字、下划线和点号");
		//	return new ErrorMessage(Status.FALSE, messageConfig.invalidFileNameError, Status.invalidFileNameError);
		//}
		if (fileFormatFilter.checkFileNull(file)) {
			return new ErrorMessage(Status.FALSE,messageConfig.fileNullError,Status.fileNullError);
		}
		if (fileFormatFilter.checkFileNameNull(file)) {
			return new ErrorMessage(Status.FALSE, messageConfig.fileNameNullError,Status.fileNameNullError);
		}
		if (fileFormatFilter.checkFileNameValid(file)) {
			return new ErrorMessage(Status.FALSE,messageConfig.invalidFileNameError,Status.invalidFileNameError);
		}
		if (fileFormatFilter.checkFileSize(file)) {
			return new ErrorMessage(Status.FALSE,messageConfig.fileSizeExceededError,Status.fileSizeExceededError);
		}
		if(fileFormatFilter.checkFileType(file,fileType)) {
			return new ErrorMessage(Status.FALSE,messageConfig.fileTypeError,Status.fileTypeError);
		}


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
		return new SuccessMessage(Status.OK,messageConfig.success,url);
	}

	private boolean validateFileName(String fileName) {
		String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5_\\.]+$";
		return fileName.matches(regex);
	}
}
