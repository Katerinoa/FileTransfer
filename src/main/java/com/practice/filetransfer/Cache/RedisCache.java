package com.practice.filetransfer.Cache;

import com.practice.filetransfer.Entity.CaptchaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Redis缓存
 */

@Repository
public class RedisCache {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	private ValueOperations<String, Object> valueOperations;

	@PostConstruct
	private void init() {
		valueOperations = redisTemplate.opsForValue();
	}

	public void add(final String cellphone, final String captcha) {
		valueOperations.set(cellphone, new CaptchaData(captcha, new Date(),0), CaptchaData.Expire, CaptchaData.Unit);
	}

	public void delete(final String cellphone) {
		redisTemplate.delete(cellphone);
	}

	public CaptchaData getData(final String cellphone) {
		return ((CaptchaData) valueOperations.get(cellphone));
	}

	public void updateData(final String cellphone, final int count) {
		CaptchaData captchaData = (CaptchaData) valueOperations.get(cellphone);
		if (captchaData != null) {
			captchaData.setCount(count);
			valueOperations.set(cellphone, captchaData);
		}
	}

}