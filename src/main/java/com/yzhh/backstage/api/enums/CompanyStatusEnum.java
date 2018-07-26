package com.yzhh.backstage.api.enums;

public enum CompanyStatusEnum {

	remove(0, "已移除"),
	pending(1, "未审核"),
	audited(2,"已审核"),
	reject(3, "已驳回"),
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
