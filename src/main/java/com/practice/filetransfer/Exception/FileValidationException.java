package com.practice.filetransfer.Exception;

/**
 * 无效文件异常
 */
public class FileValidationException  extends FileTransferException{

	public FileValidationException(String message, int errorCode) {
		super(message,errorCode);
	}
}
