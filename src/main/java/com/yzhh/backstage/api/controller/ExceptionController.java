package com.yzhh.backstage.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import com.yzhh.backstage.api.commons.ApiResponse;
import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.error.CommonError;


/**
 * @description:统一异常处理
 * @projectName:teacher-api
 * @className:ExceptionController.java
 * @author:衷文涛
 * @createTime:2017年10月10日 下午2:13:20
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionController {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)	//400
    @ResponseBody
    public ApiResponse processResponseException(BizException ex) {
    	ApiResponse response = new ApiResponse(ex.getCode(), ex.getMsg(), ex.getData());
        return response;
    }
    
    @ExceptionHandler(value = {
            MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processMultipartExcetption(Exception ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
    	ApiResponse apiReponse = new ApiResponse();
        
        apiReponse.setCode(CommonError.UPLOAD_FILE_ERROR.getId());
        apiReponse.setMsg(CommonError.UPLOAD_FILE_ERROR.getMsg());

        return apiReponse;
    }

    //入参错误
    @ExceptionHandler(value = {
            IllegalStateException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)	//400
    @ResponseBody
    public ApiResponse processInvalidInput(Exception ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
    	ApiResponse apiReponse = new ApiResponse();
        apiReponse.setCode(CommonError.REQUEST_PARAMETER_ERROR.getId());
        apiReponse.setMsg(CommonError.REQUEST_PARAMETER_ERROR.getMsg());
        //apiReponse.setMsg(ex.getMessage());
        return apiReponse;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)	//400
    @ResponseBody
    protected ApiResponse addValidationError(ConstraintViolationException e){
        Map<String, String> errorMsg = new HashMap<>();

        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            // 获得验证失败的类 constraintViolation.getLeafBean()
            // 获得验证失败的值 constraintViolation.getInvalidValue()
            // 获取参数值 constraintViolation.getExecutableParameters()
            // 获得返回值 constraintViolation.getExecutableReturnValue()
            errorMsg.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }

        List<Map<String,Object>> fieldErrorDtoList = new ArrayList<Map<String,Object>>();
        for (Map.Entry<String, String> entry : errorMsg.entrySet()) {

            Map<String,Object> map = new HashMap<>();
            map.put("name", entry.getKey());
            map.put("error", entry.getValue());

            fieldErrorDtoList.add(map);
        }
        
        Map<String,Object> map = new HashMap<>();
        map.put("errors", fieldErrorDtoList);

        ApiResponse apiReponse = new ApiResponse();
        apiReponse.setCode(CommonError.REQUEST_PARAMETER_ERROR.getId());
        apiReponse.setMsg("请求参数错误");
        apiReponse.setData(map);
        return apiReponse;
    }

}