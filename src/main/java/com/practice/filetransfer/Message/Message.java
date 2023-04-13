package com.practice.filetransfer.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	private int status;
	private String message;

}
