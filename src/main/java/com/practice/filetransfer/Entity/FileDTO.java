package com.practice.filetransfer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询文件封装实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO implements Serializable {
	private static final long serialVersionUID = 7459111785784187182L;

	private String name;
	private String url;
	private String type;

}
