package com.practice.filetransfer;

import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Service.QueryService;
import com.practice.filetransfer.Util.QiNiuUtil;
import com.practice.filetransfer.config.QiNiuConfig;
import com.qiniu.storage.model.FileInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@SpringBootTest
class FileTransferApplicationTests {

	@Autowired
	QiNiuConfig qiniuConfig;

	@Autowired
	QiNiuUtil qiNiuUtil;

	@Autowired
	QueryService queryService;

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

		String res = qiNiuUtil.Upload(input, "Image/testFile");
		System.out.println("res = " + res);
	}
	
	@Test
	void TestQuery() throws Exception {
		List<FileInfo> strings = qiNiuUtil.QueryList(FileType.VIDEO);
		for (FileInfo item: strings)
		{
			System.out.println(item.mimeType);
		}
	}

}
