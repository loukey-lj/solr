package com.glorypty.gysolr.common.util;

import java.util.List;

public class CheckData {

	public static boolean isEmptyForList(List<?> list){
		if(list != null  && list.size() > 0){
			return false;
		}
		return true;
	}
	
	public static boolean isEmptyForString(String src){
		if(src == null  || "".equals(src) || "null".equals(src) || "NULL".equals(src) ){
			return true;
		}
		return false;
	}
}