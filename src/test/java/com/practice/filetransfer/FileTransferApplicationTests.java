package com.practice.filetransfer;

import com.practice.filetransfer.Cache.RedisCache;
import com.practice.filetransfer.Entity.CaptchaData;
import com.practice.filetransfer.Util.TencentCloudSmsUtil;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileTransferApplicationTests {

	@Autowired
	RedisCache redisCache;

	@Autowired
	TencentCloudSmsUtil tencentCloudSmsUtil;

	@Test
	void TestRedis() {
		//redisCache.add("17346255544","123456");
		//redisCache.updateData("17346255544", data.getCount()+1);
		redisCache.delete("17346255544");

		CaptchaData data = redisCache.getData("17346255544");
		System.out.println("data = " + data);
	}

	@Test
	void TestSms() throws TencentCloudSDKException {
		tencentCloudSmsUtil.sendCaptcha("17346255544","123456");
	}
}
