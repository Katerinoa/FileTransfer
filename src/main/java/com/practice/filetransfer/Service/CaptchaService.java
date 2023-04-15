package com.practice.filetransfer.Service;

import com.practice.filetransfer.Exception.RepeatApplyCaptchaException;
import com.practice.filetransfer.Exception.cellPhoneValidationException;
import com.practice.filetransfer.Message.Message;

public interface CaptchaService {

	Message applyCaptcha(String cellphone) throws Exception;

	Message verifyCaptcha(String cellphone, String captcha) throws Exception;
}
