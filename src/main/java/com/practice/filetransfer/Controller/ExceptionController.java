package com.practice.filetransfer.Controller;

import com.practice.filetransfer.Constant.Status;
import com.practice.filetransfer.Exception.FileValidationException;
import com.practice.filetransfer.Message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class ExceptionController {

	@ExceptionHandler(FileValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleFileValidationException(FileValidationException ex) {
		return new ErrorMessage(Status.FALSE, ex.getMessage(), ex.getErrorCode());
	}
}
