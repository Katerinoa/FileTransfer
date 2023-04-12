package com.practice.filetransfer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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

}
