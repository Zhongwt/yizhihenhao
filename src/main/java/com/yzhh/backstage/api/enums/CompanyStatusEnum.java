package com.yzhh.backstage.api.enums;

public enum CompanyStatusEnum {

	remove(0, "企业审核"),
	pending(1, "查看日志"),
	audited(2,"企业添加"),
    ;
    
    private int id;
    private String name;
    
    CompanyStatusEnum(int id, String name){
        this.id =  id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public static String getValueById(int id)
	{
    	CompanyStatusEnum[] enumArray = CompanyStatusEnum.values();

		for (CompanyStatusEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
