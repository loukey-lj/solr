/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.sys.MakeSerialFacade.java <2016年5月24日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.sys;

import org.springframework.stereotype.Service;

import com.glorypty.gysolr.common.util.SerialUtil;

/**
 *
 * @author liujie 
 * @Date 2016年5月24日 下午4:54:04
 * @version 1.0
 */
@Service
public class MakeSerialImpl implements  MakeSerialFacade{

	@Override
	public String getSerial() {
		
	    return SerialUtil.getSearialNum();
	}
    
}
