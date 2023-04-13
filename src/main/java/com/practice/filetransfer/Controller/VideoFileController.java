package com.practice.filetransfer.Controller;


import com.practice.filetransfer.Constant.FileType;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.DeleteService;
import com.practice.filetransfer.Service.QueryService;
import com.practice.filetransfer.Service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileTransfer/video")
public class VideoFileController {

	@Autowired
	private UpdateService updateService;
	@Autowired
	private DeleteService deleteService;
	@Autowired
	private QueryService queryService;

	@PostMapping("/fileUpload")
	public Message videoUpload(MultipartFile file, @RequestParam String fileName) throws Exception {
		return updateService.Upload(file, fileName, FileType.VIDEO);
	}

	@DeleteMapping("/deleteFile")
	public Message imageDelete(@RequestParam String key,@RequestParam String bucketName) throws Exception {
		return deleteService.Delete(key,bucketName,FileType.VIDEO);
	}

	@GetMapping("/fileList")
	public Message QueryList(@RequestParam int page, @RequestParam int pageSize) throws Exception {
		return queryService.getFileList(page,pageSize,FileType.VIDEO);
	}
}
