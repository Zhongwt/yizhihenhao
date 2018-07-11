package com.yzhh.backstage.api.enums;

public enum DeliveryResumeStatusEnum {

	notlook(1, "未查看"),
	look(2, "已查看"),
	pending(3,"待定"),
	not_right(4,"不合适"),
	right(5,"已通知面试"),
    ;
    
    private int id;
    private String name;
    
    DeliveryResumeStatusEnum(int id, String name){
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
    	DeliveryResumeStatusEnum[] enumArray = DeliveryResumeStatusEnum.values();

		for (DeliveryResumeStatusEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
