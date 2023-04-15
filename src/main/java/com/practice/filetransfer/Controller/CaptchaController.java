package com.practice.filetransfer.Controller;


import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fileTransfer")
public class CaptchaController {

	@Autowired
	CaptchaService captchaService;

	@GetMapping("/login")
	public Message ApplyCaptcha(@RequestParam String cellphone) throws Exception {
		return captchaService.applyCaptcha(cellphone);
	}

	@PostMapping("/login")
	public Message VerifyCaptcha(@RequestParam String cellphone,@RequestParam String captcha) throws Exception {
		return captchaService.verifyCaptcha(cellphone,captcha);
	}
}
