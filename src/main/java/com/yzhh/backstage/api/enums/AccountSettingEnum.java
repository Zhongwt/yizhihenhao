package com.yzhh.backstage.api.enums;

public enum AccountSettingEnum {

	education_master(1, "硕士及以上"),
	education_undergraduate(2, "本科"),
	education_specialty(3, "大专"),
	education_high_school(4, "高中"),
	resumes(5, "简历投递"),
	recharge(6, "充值赠送倍率"),
    ;
    
    private int id;
    private String name;
    
    AccountSettingEnum(int id, String name){
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
    	AccountSettingEnum[] enumArray = AccountSettingEnum.values();

		for (AccountSettingEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
