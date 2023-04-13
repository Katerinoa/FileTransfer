package com.practice.filetransfer.config;

/**
 * 用于获取七牛云的配置信息
 */

import com.practice.filetransfer.Constant.AuthorizeInfo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class QiNiuConfig {

	@Value("${oss.qiniu.accessKey}")
	private String accessKey;

	@Value("${oss.qiniu.secretKey}")
	private String secretKey;

	@Value("${oss.qiniu.bucketName}")
	private String bucketName;

	@Value("${oss.qiniu.bucketPath}")
	private String bucketPath;


	/**
	 * 初始化配置信息常量类
	 */
	@Bean
	public void qiniuInit() {
		AuthorizeInfo.accessKey = accessKey;
		AuthorizeInfo.secretKey = secretKey;
		AuthorizeInfo.bucketName = bucketName;
		AuthorizeInfo.bucketPath = bucketPath;
	}

}
