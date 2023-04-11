package com.practice.filetransfer.Controller;


import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileTransfer/video")
public class VideoFileController {

	@Autowired
	private UpdateService updateService;

	@PostMapping("/upload")
	public Message videoUpload(MultipartFile file) throws Exception {
		return updateService.Upload(file, FileType.VIDEO);
	}
}
