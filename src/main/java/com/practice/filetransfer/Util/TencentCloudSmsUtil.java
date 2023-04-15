package com.practice.filetransfer.Util;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TencentCloudSmsUtil {

	@Value("${sms.tencentcloud.SECRET_ID}")
	private String SECRET_ID;
	@Value("${sms.tencentcloud.SECRET_KEY}")
	private String SECRET_KEY;

	private static String REGION = "ap-guangzhou"; // 发送短信所在的区域
	private static String sdkAppId = "1400724204";
	private static String signName = "一苇智联科技";
	private static String templateId = "1524530";

	public void sendCaptcha(String cellphone, String captcha) throws TencentCloudSDKException {

		try {
			/* 必要步骤：
			 * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
			 * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
			 * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
			 * 以免泄露密钥对危及你的财产安全。
			 * SecretId、SecretKey 查询: https://console.cloud.tencent.com/cam/capi */
			Credential cred = new Credential(SECRET_ID, SECRET_KEY);

			// 配置客户端
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setSignMethod("HmacSHA256");// 使用该算法进行签名

			SmsClient client = new SmsClient(cred, REGION, clientProfile);

			SendSmsRequest req = new SendSmsRequest();

			// 配置短信类型
			req.setSmsSdkAppId(sdkAppId);
			req.setSignName(signName);
			req.setTemplateId(templateId);

			String[] templateParamSet = {captcha};
			req.setTemplateParamSet(templateParamSet);

			String s = "+86" + cellphone;
			String[] phoneNumberSet = {s};
			req.setPhoneNumberSet(phoneNumberSet);

			client.SendSms(req); //发送短信

		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}


	}
}
