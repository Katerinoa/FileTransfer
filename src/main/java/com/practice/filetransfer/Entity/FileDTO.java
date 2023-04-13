package com.practice.filetransfer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询文件封装实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

	private String name;
	private String url;
	private String type;

}
