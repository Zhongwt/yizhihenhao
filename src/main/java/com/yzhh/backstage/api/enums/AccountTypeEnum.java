package com.yzhh.backstage.api.enums;

public enum AccountTypeEnum {

	company(1, "Company"),
	job_seeker(1, "JobSeeker"),
    ;
    
    private int id;
    private String name;
    
    AccountTypeEnum(int id, String name){
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
    	AccountTypeEnum[] enumArray = AccountTypeEnum.values();

		for (AccountTypeEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
