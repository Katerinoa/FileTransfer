package com.practice.filetransfer.Pagination;

import com.practice.filetransfer.Constant.AuthorizeInfo;
import com.practice.filetransfer.Constant.ErrorCode;
import com.practice.filetransfer.Constant.MessageInfo;
import com.practice.filetransfer.Entity.FileDTO;
import com.practice.filetransfer.Exception.FileQueryException;
import com.qiniu.storage.model.FileInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件查询分页
 */
@Data
@NoArgsConstructor
public class FileDTOPagination extends Pagination {


	private List<FileDTO> fileDTOList;

	public FileDTOPagination(int currentPage, int pageSize, List<FileInfo> fileInfoList) throws Exception{
		super(currentPage, pageSize);
		this.fileDTOList = getFileDTOListList(currentPage,pageSize,fileInfoList);
	}

	/**
	 *
	 * @param currentPage 查询的页码
	 * @param pageSize 每页的文件数量
	 * @param fileInfoList 查到的所有文件列表
	 * @return 要查询的文件列表
	 * @throws Exception 页码错误异常
	 */
	private List<FileDTO> getFileDTOListList(int currentPage,int pageSize,List<FileInfo> fileInfoList) throws Exception{

		int totalFiles = fileInfoList.size();
		int totalPages = (int) Math.ceil((double) totalFiles / pageSize);
		if(currentPage<1 || currentPage > totalPages)
			throw new FileQueryException(MessageInfo.queryPageError, ErrorCode.queryPageError);
		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalFiles);
		List<FileDTO> fileDTOs = new ArrayList<>();
		for (int i = startIndex; i < endIndex; i++) {
			FileInfo file = fileInfoList.get(i);
			String url = String.format("%s/%s", AuthorizeInfo.bucketPath, file.key);
			String mimeType = file.mimeType;
			FileDTO fileDTO = new FileDTO(file.key, url, mimeType);
			fileDTOs.add(fileDTO);
		}
		return fileDTOs;
	}

}

