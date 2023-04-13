package com.practice.filetransfer.Exception;

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
