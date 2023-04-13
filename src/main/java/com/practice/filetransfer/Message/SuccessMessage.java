package com.practice.filetransfer.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 上传成功消息
 */
@Data
@NoArgsConstructor
public class SuccessMessage extends Message{

	private String downloadUrl;

	public SuccessMessage(int status, String message, String downloadUrl) {
		super(status,message);
		this.downloadUrl = downloadUrl;
	}

}
