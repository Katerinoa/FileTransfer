package com.practice.filetransfer.Service.Impl;

import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Message.Message;
import com.practice.filetransfer.Message.QueryResultMessage;
import com.practice.filetransfer.Pagination.FileDTOPagination;
import com.practice.filetransfer.Service.QueryService;
import com.practice.filetransfer.Util.QiNiuUtil;
import com.qiniu.storage.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询服务
 */
@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	QiNiuUtil qiNiuUtil;
	
	@Override
	public Message getFileList(int page, int pageSize, String fileType) throws Exception {

		List<FileInfo> fileList = qiNiuUtil.QueryList(fileType);
		//根据查询页码，取出数据并封装到FileDTOPagination中
		return new QueryResultMessage(Status.OK, MessageInfo.success,new FileDTOPagination(page, pageSize, fileList));
	}
}
