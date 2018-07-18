package com.yzhh.backstage.api.enums;

public enum IsDefaultEnum {

	is_default(1, "默认"),
	nomal(0, "一般"),
    ;
    
    private int id;
    private String name;
    
    IsDefaultEnum(int id, String name){
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
    	IsDefaultEnum[] enumArray = IsDefaultEnum.values();

		for (IsDefaultEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
