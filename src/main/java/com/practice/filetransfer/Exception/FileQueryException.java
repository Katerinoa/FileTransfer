package com.practice.filetransfer.Exception;

/**
 * 查询失败异常
 */
public class FileQueryException extends FileTransferException{

	private final int errorCode;

	public FileQueryException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
