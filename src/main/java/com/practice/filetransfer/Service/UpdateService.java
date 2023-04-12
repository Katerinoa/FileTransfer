package com.practice.filetransfer.Service;

import com.practice.filetransfer.Message.Message;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateService {
	Message Upload(MultipartFile file, String FileType) throws Exception;
}
