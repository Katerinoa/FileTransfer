package com.practice.filetransfer.Filter;

import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Exception.FileValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件合法检查
 */
@Component
public class fileFormatFilter {

	private static final int fileMaxSize = 50*1024*1024;

	private static boolean checkFileNull(MultipartFile file) {
		return file==null ||file.isEmpty() ||file.getSize()==0;
	}

	private static boolean checkFileNameNull(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		return fileName == null;
	}

	private static boolean checkFileNameValid(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5_\\.]+$";
		return !fileName.matches(regex);
	}

	private static boolean checkFileSize(MultipartFile file) {
		return file.getSize() > fileMaxSize;
	}

	private static boolean checkFileType(MultipartFile file, String fileType) {
		String contentType = file.getContentType();
		return !(contentType != null && contentType.startsWith(fileType));
	}

	public static void fileValidateCheck(MultipartFile file, String fileType) throws FileValidationException {
		if(checkFileNull(file))
			throw new FileValidationException(MessageInfo.fileNullError, ErrorCode.fileNullError);
		if(checkFileNameNull(file))
			throw new FileValidationException(MessageInfo.fileNameNullError,ErrorCode.fileNameNullError);
		if(checkFileNameValid(file))
			throw new FileValidationException(MessageInfo.invalidFileNameError,ErrorCode.invalidFileNameError);
		if(checkFileSize(file))
			throw new FileValidationException(MessageInfo.fileSizeExceededError,ErrorCode.fileSizeExceededError);
		if(checkFileType(file,fileType))
			throw new FileValidationException(MessageInfo.fileTypeError,ErrorCode.fileTypeError);
	}

}
