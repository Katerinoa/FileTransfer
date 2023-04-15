package com.practice.filetransfer.Entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Builder
public class CaptchaData implements Serializable {
	private static final long serialVersionUID = 7459111785784187182L;

	public static int Expire = 5;  //有效时间为5
	public static TimeUnit Unit = TimeUnit.MINUTES;  //单位为分钟

	private String captcha;
	private Date time;
	private Integer count; //验证次数

	public CaptchaData(String captcha, Date time, Integer count) {
		this.captcha = captcha;
		this.time = time;
		this.count = count;
	}

	public String getCaptcha() {
		return captcha;
	}

	public Date getTime() {
		return time;
	}

	public Integer getCount() {
		return count;
	}
}
