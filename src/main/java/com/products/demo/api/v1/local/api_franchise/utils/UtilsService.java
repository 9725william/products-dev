package com.products.demo.api.v1.local.api_franchise.utils;

public class UtilsService {

	public static boolean isErrorService( Object object ){
		String typeData = (object != null) ? object.getClass().getSimpleName() : "";
		if( typeData.equals("ErrorService") )
			return true;
		return false;
	}

}
