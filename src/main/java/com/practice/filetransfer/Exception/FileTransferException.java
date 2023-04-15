package com.practice.filetransfer.Exception;

/**
 * 文件传输异常
 */
public class FileTransferException extends Exception{

	private final int errorCode;

	public FileTransferException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
