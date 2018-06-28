package com.yzhh.backstage.api.enums;

/**
 * @description:权限枚举
 * @projectName:backstage-api
 * @className:JurisdictionEnum.java
 * @author:wentao
 * @createTime:2018年6月28日 下午7:59:54
 * @version 1.0.1
 */
public enum JurisdictionEnum {
	search_log(1, "查看日志"),
    add_company(2,"企业添加"),
    edit_company(3, "企业编辑"),
    audit_company(4, "企业审核"),
    add_position(5, "发布职位"),
    edit_position(6,"编辑职位"),
    delete_position(7, "删除职位"),
    audit_position(8, "审核职位"),
    mark_job(9, "标记应聘"),
    notice_interview(10, "通知面试"),
    ;
    
    private int id;
    private String desc;
    
    JurisdictionEnum(int id, String desc){
        this.id =  id;
        this.desc = desc;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDesc() {
        return desc;
    }
}
