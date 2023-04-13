package com.practice.filetransfer.Message;

import com.practice.filetransfer.Pagination.Pagination;
import lombok.Data;

@Data
public class QueryResultMessage extends Message{

	private Pagination pagination;

	public QueryResultMessage(int status, String message, Pagination pagination) {
		super(status,message);
		this.pagination = pagination;
	}


}
