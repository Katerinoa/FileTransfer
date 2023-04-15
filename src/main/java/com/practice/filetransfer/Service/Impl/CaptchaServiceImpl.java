package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Cache.RedisCache;
import com.practice.filetransfer.Config.MessageConfig;
import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Entity.CaptchaData;
import com.practice.filetransfer.Exception.FileTransferException;
import com.practice.filetransfer.Exception.RepeatApplyCaptchaException;
import com.practice.filetransfer.Exception.cellPhoneValidationException;
import com.practice.filetransfer.Filter.cellPhoneFilter;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Message.SuccessMessage;
import com.practice.filetransfer.Service.CaptchaService;
import com.practice.filetransfer.Util.TencentCloudSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaptchaServiceImpl implements CaptchaService {

	@Autowired
	RedisCache redisCache;

	@Autowired
	TencentCloudSmsUtil tencentCloudSmsUtil;


	private Random random = new Random();

	//第二次申请间隔 60s
	private final long ReapplyDelay = 60 * 1000;
	private final int maxCount = 3; // 最大验证次数

	/**
	 * 发送验证码
	 * @param cellphone 目标手机号
	 */
	@Override
	public Message applyCaptcha(String cellphone) throws Exception {

		// 手机号格式检查
		if (cellPhoneFilter.cellphoneValidateCheck(cellphone))
			throw new cellPhoneValidationException("手机号格式错误，请重新输入",ErrorCode.invalidCellphoneError);

		// 重复申请检查
		CaptchaData captcha = redisCache.getData(cellphone);
		// 若已经申请过验证码
		if (captcha != null) {
			long delta = captcha.getTime().getTime() + ReapplyDelay - new Date().getTime();
			if (delta > 0) // 未到重新申请时间
				throw new RepeatApplyCaptchaException("验证码重复申请，请于" + delta/1000 + "秒后重试", ErrorCode.repeatApplyCaptchaError);
		}

		// 生成验证码
		String newCaptcha = generateCaptcha();
		redisCache.add(cellphone,newCaptcha);
		tencentCloudSmsUtil.sendCaptcha(cellphone,newCaptcha);

		return new Message(Status.OK, MessageInfo.success);
	}

	@Override
	public Message verifyCaptcha(String cellphone, String captcha) throws Exception{

		// 手机号格式检查
		if (cellPhoneFilter.cellphoneValidateCheck(cellphone))
			throw new cellPhoneValidationException("手机号格式错误，请重新输入",ErrorCode.invalidCellphoneError);

		CaptchaData originCaptcha = redisCache.getData(cellphone);
		if (originCaptcha == null)
			throw new FileTransferException("请先申请验证码",ErrorCode.notApplyCaptcha);
		else if (originCaptcha.getCount() > maxCount) {
			redisCache.delete(cellphone);
			throw new FileTransferException("超过最大验证次数，请重新申请验证码",ErrorCode.verificationExceededError);
		}
		else if (!originCaptcha.getCaptcha().equals(captcha)) {
			redisCache.updateData(cellphone,originCaptcha.getCount()+1);
			throw new FileTransferException("验证码错误",ErrorCode.captchaNotMatchError);
		}

		// 验证成功
		redisCache.delete(cellphone);
		return new Message(Status.OK, MessageInfo.success);

	}

	/**
	 * 生成6位验证码
	 * @return 验证码字符串
	 */
	private String generateCaptcha() {
		random.setSeed(new Date().getTime()); //随机种子
		List<Integer> rs = new ArrayList<>(6);

		for (int i = 0; i < 6; ++i) {
			rs.add(random.nextInt(10));
		}
		Collections.shuffle(rs); //打乱顺序

		StringBuilder builder = new StringBuilder();
		for (Integer r : rs) {
			builder.append(r);
		}

		return builder.toString();
	}
}
