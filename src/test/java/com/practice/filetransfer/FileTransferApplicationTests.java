package com.practice.filetransfer;

import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Util.QiNiuUtil;
import com.practice.filetransfer.config.QiNiuConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;

@SpringBootTest
class FileTransferApplicationTests {

	@Autowired
	QiNiuConfig qiniuConfig;

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Test
	void TestGetKeys() {
		System.out.println(qiniuConfig.getAccessKey());
		System.out.println(qiniuConfig.getSecretKey());
		System.out.println(qiniuConfig.getBucketName());
	}

	@Test
	void TestUploadFile() throws Exception{

		File file = new File("C:\\Users\\TingLans\\Desktop\\mouse.png");
		FileInputStream input = new FileInputStream(file);

		String res = qiNiuUtil.Upload(input, "testFile",FileType.IMAGE);
		System.out.println("res = " + res);
	}
}
