package com.yzhh.backstage.api.enums;

public enum CompanyIdentificationEnum {

	Uncertified(1, "未认证"),
	certified(2,"已认证"),
    ;
    
    private int id;
    private String name;
    
    CompanyIdentificationEnum(int id, String name){
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
    	CompanyIdentificationEnum[] enumArray = CompanyIdentificationEnum.values();

		for (CompanyIdentificationEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
