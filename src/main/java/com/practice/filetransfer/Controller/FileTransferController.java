package com.practice.filetransfer.Controller;

import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Service.DeleteService;
import com.practice.filetransfer.Service.QueryService;
import com.practice.filetransfer.Service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接口
 */
@RestController
@RequestMapping("/fileTransfer/image")
public class FileTransferController {

	@Autowired
	private UpdateService updateService;
	@Autowired
	private DeleteService deleteService;
	@Autowired
	private QueryService queryService;

	@PostMapping("/fileUpload")
	public Message imageUpload(MultipartFile file, @RequestParam String fileName, @RequestParam String fileType) throws Exception {
		return updateService.Upload(file, fileName, fileType);
	}

	@DeleteMapping("/deleteFile")
	public Message imageDelete(@RequestParam String key,@RequestParam String bucketName, @RequestParam String fileType) throws Exception {
		return deleteService.Delete(key,bucketName,fileType);
	}

	@GetMapping("/fileList")
	public Message QueryList(@RequestParam int page, @RequestParam int pageSize, @RequestParam String fileType) throws Exception {
		return queryService.getFileList(page,pageSize,fileType);
	}


}
