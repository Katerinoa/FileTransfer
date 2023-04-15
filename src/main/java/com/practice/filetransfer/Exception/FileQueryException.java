package com.practice.filetransfer.Exception;

/**
 * 查询失败异常
 */
public class FileQueryException extends FileTransferException{

	public FileQueryException(String message, int errorCode) {
		super(message,errorCode);
	}
}
