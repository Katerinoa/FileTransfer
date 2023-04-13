package com.practice.filetransfer.Exception;

/**
 * 无效文件异常
 */
public class FileValidationException  extends FileTransferException{

	private final int errorCode;

	public FileValidationException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
