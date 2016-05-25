/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.common.util.SerialUtil.java <2016年5月24日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.util;

/**
 *
 * @author liujie 
 * @Date 2016年5月24日 下午5:23:33
 * @version 1.0
 */
public class SerialUtil {
    private static String searialNum = null;

	public static String getSearialNum() {
		return searialNum;
	}

	public static void setSearialNum(String searialNum)  {
		SerialUtil.searialNum = searialNum;
	}
    
}
