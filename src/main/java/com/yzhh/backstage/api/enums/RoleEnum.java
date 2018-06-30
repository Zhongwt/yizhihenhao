package com.yzhh.backstage.api.enums;

public enum RoleEnum {

		admin(1, "管理原"),
		company(2, "公司"),
		jobSeeker(3,"用户"),
	    ;
	    
	    private int id;
	    private String name;
	    
	    RoleEnum(int id, String name){
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
	    	RoleEnum[] enumArray = RoleEnum.values();

			for (RoleEnum enums : enumArray)
			{
				if (id == enums.getId())
				{
					return enums.getName();
				}
			}

			return null;
		}
}
