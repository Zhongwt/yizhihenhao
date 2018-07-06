package com.yzhh.backstage.api.enums;

public enum IsReadEnum {

	read(1, "已读"),
	not_read(0, "未读"),
    ;
    
    private int id;
    private String name;
    
    IsReadEnum(int id, String name){
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
    	IsReadEnum[] enumArray = IsReadEnum.values();

		for (IsReadEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
    
}
