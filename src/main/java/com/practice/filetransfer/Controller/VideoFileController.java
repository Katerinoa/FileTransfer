package com.practice.filetransfer.Controller;


import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileTransfer/video")
public class VideoFileController {

	@Autowired
	private UpdateService updateService;

	@PostMapping("/fileUpload")
	public Message videoUpload(MultipartFile file, @RequestParam("fileName") String fileName) throws Exception {
		return updateService.Upload(file, fileName, FileType.VIDEO);
	}
}
