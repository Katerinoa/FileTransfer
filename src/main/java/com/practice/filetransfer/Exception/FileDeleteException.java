package com.practice.filetransfer.Exception;

/**
 * 删除失败异常
 */
public class FileDeleteException extends FileTransferException{

	public FileDeleteException(String message, int errorCode) {
		super(message,errorCode);
	}
}
