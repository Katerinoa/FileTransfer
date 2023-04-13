package com.practice.filetransfer.Service;


import com.practice.filetransfer.Message.Message;

public interface QueryService {

	Message getFileList(int page, int pageSize, String fileType) throws Exception;
}
