package com.practice.filetransfer.Filter;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件格式检查
 * 若不通过检查返回 true
 * 通过返回 false
 */
public class fileFormatFilter {

	private static final int fileMaxSize = 50*1024*1024;

	public static boolean checkFileNull(MultipartFile file) {
		return file==null ||file.isEmpty() ||file.getSize()==0;
	}

	public static boolean checkFileNameNull(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		return fileName == null;
	}

	public static boolean checkFileNameValid(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String regex = "^[a-zA-Z0-9_\\u4e00-\\u9fa5_\\.]+$";
		return !fileName.matches(regex);
	}

	public static boolean checkFileSize(MultipartFile file) {
		return file.getSize() > fileMaxSize;
	}

	public static boolean checkFileType(MultipartFile file, String fileType) {
		String contentType = file.getContentType();
		return !(contentType != null && contentType.startsWith(fileType));
	}

}
