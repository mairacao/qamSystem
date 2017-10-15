package com.xiaoyu.commons.scan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xiaoyu.commons.result.Result;


/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

	protected static Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processValidationError(MethodArgumentNotValidException ex) {
		logger.error(ex.getMessage(), ex);
		BindingResult result = ex.getBindingResult();
		FieldError error = result.getFieldError();
		return getFieldErrorResult(error);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Result processException(BindException ex) {
		logger.error(ex.getMessage(), ex);
		FieldError error = ex.getFieldError();
		return getFieldErrorResult(error);
	}
	
	/**
	 * 对hibernate-validator异常错误信息简单处理
	 * @param error
	 * @return
	 */
	private Result getFieldErrorResult(FieldError error) {
		StringBuilder errorMsg = new StringBuilder(100);
		errorMsg.append("$(form).find(\"[name=\\\"");
		errorMsg.append(error.getField());
		errorMsg.append("\\\"]\").closest(\"td\").prev().text() + \"，");
		errorMsg.append(error.getDefaultMessage());
		errorMsg.append("\"");
		Result _result = new Result();
		_result.setMsg(errorMsg.toString());
		return _result;
	}
	
	/**
	 * 普通的异常交给 {@link ExceptionResolver} 处理
	 * 兼容 页面 异常和ajax异常
	 */
//	@ExceptionHandler(Exception.class)
//	@ResponseBody
//	public Result processException(Exception ex) {
//		log.error(ex.getMessage(), ex);
//		Result result = new Result();
//		result.setMsg(ex.getMessage());
//		return result;
//	}
}
