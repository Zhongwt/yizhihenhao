package com.yzhh.backstage.api.enums;

public enum IsDeleteEnum {

	delete(0, "已删除"),
	nomal(1, "正常"),
    ;
    
    private int id;
    private String name;
    
    IsDeleteEnum(int id, String name){
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
    	IsDeleteEnum[] enumArray = IsDeleteEnum.values();

		for (IsDeleteEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
	
}
