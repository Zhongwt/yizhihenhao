package com.yzhh.backstage.api.error;

public enum CommonError implements ICommonError {

    REQUEST_PARAMETER_ERROR(10000, "Request parameter error"),
    // 用户错误
    USER_AUTH_ERROR(10001, "User authentication failure"),
    USER_LOGIN_ACCOUNTPWD_ERROR(10002, "ERROR Incorrect username or password"),
  
    SYSTEM_ERROR(11000,"System error"),
    UPLOAD_FILE_ERROR(12000,"File upload error"),
    EXCEL_ERROR(13000,"tile & value length is not the same length "),
    ;

    final private int id;
    final private String msg;

    CommonError(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }
}
