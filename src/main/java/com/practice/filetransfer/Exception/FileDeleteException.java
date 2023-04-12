package com.practice.filetransfer.Exception;

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
