package com.practice.filetransfer.Exception;

/**
 * 删除失败异常
 */
public class FileDeleteException extends FileTransferException{

	private final int errorCode;

	public FileDeleteException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
