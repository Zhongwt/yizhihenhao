package com.yzhh.backstage.api.enums;

public enum PositionStatusEnum {
	remove(0, "已下线"),
	pending(1, "未审核"),
	audited(2,"已审核"),
	delete(3,"已删除"),
	reject(4, "已驳回"),
    ;
    
    private int id;
    private String name;
    
    PositionStatusEnum(int id, String name){
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
    	PositionStatusEnum[] enumArray = PositionStatusEnum.values();

		for (PositionStatusEnum enums : enumArray)
		{
			if (id == enums.getId())
			{
				return enums.getName();
			}
		}

		return null;
	}
}
