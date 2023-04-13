package com.practice.filetransfer.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误消息
 */
@Data
@NoArgsConstructor
public class ErrorMessage extends Message{

	private int errorCode;

	public ErrorMessage(int status, String message, int errorCode) {
		super(status,message);
		this.errorCode = errorCode;
	}
}
