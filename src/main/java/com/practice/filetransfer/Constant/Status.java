package com.practice.filetransfer.Constant;

public interface Status {

	int OK = 0;
	int FALSE = 1;

	int fileNullError = 301;
	int fileNameNullError = 302;
	int invalidFileNameError = 303;
	int fileSizeExceededError = 304;
	int fileTypeError = 305;
}
