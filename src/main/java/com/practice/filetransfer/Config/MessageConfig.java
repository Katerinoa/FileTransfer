package com.practice.filetransfer.Config;

import com.practice.filetransfer.Constant.MessageInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MessageConfig {

	@Value("${message.success}")
	public String success;

	@Value("${message.fileNullError}")
	public String fileNullError;

	@Value("${message.fileNameNullError}")
	public String fileNameNullError;

	@Value("${message.invalidFileNameError}")
	public String invalidFileNameError;

	@Value("${message.fileSizeExceededError}")
	public String fileSizeExceededError;

	@Value("${message.fileTypeError}")
	public String fileTypeError;

	@Value("${message.deleteFalseError}")
	public String deleteFalseError;

	@Value("${message.fileNotExistError}")
	public String fileNotExistError;

	@Value("${message.queryPageError}")
	public String queryPageError;

	/**
	 * 初始化消息常量类
	 */
	@Bean
	public void messageInit() {
		MessageInfo.success = success;
		MessageInfo.fileNullError = fileNullError;
		MessageInfo.fileNameNullError = fileNameNullError;
		MessageInfo.invalidFileNameError = invalidFileNameError;
		MessageInfo.fileSizeExceededError = fileSizeExceededError;
		MessageInfo.fileTypeError = fileTypeError;
		MessageInfo.deleteFalseError = deleteFalseError;
		MessageInfo.fileNotExistError = fileNotExistError;
		MessageInfo.queryPageError = queryPageError;
	}

}
