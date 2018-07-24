package com.yzhh.backstage.api.enums;

public enum AccountSettingEnum {

	education_master(1, "硕士及以上"),
	education_undergraduate(2, "本科"),
	education_specialty(3, "大专"),
	education_high_school(4, "高中"),
	resumes(5, "简历投递"),
	recharge(6, "充值赠送倍率"),
	job_seeker_first_gear(7,"客户第一档"),
	job_seeker_second_gear(8,"客户第二档"),
	job_seeker_third_gear(9,"客户第三档"),
	job_seeker_fourth_gear(10,"客户第四档"),
	job_seeker_fifth_gear(11,"客户第五档"),
	job_seeker_sixth_gear(12,"客户第六档"),
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
