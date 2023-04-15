package com.practice.filetransfer.Constant;

/**
 * 错误码
 */
public interface ErrorCode {

	int fileNullError = 301;
	int fileNameNullError = 302;
	int invalidFileNameError = 303;
	int fileSizeExceededError = 304;
	int fileTypeError = 305;
	int deleteFalseError = 306;
	int fileNotExistError = 307;
	int queryPageError = 308;
	int repeatApplyCaptchaError = 309;
	int invalidCellphoneError = 310;
	int notApplyCaptcha = 311;
	int verificationExceededError = 312;
	int captchaNotMatchError = 313;
}
