package com.yzhh.backstage.api.enums;

public enum CourseStatusEnum {
    PLANED(1, "Already Plan"),
    CONFIRM(2,"Confirm"),
    COMPLETED(3, "Completed"),
    OTHER(4, "Other");
    
    private int id;
    private String desc;
    
    CourseStatusEnum(int id, String desc){
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
