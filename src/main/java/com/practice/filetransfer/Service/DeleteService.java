package com.practice.filetransfer.Service;

import com.practice.filetransfer.Message.Message;

public interface DeleteService {

	Message Delete(String key, String bucketName, String fileType) throws Exception;
}
