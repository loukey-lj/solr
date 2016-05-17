/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.common.facade.BaseFacade.java <2016年5月13日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.facade;

import com.glorypty.gysolr.common.domain.BaseQuery;
import com.glorypty.gysolr.common.result.BaseResult;

/**
 *
 * @author liujie 
 * @Date 2016年5月13日 下午3:57:40
 * @version 1.0
 */

public interface BaseQueryFacade<Entrty extends BaseQuery,Result extends BaseResult<Entrty>> {
	
	/**
	 * 条件查询
	 */
	public Result queryByWhere(Entrty t);
	
	/**
	 * 主键查询
	 */
	public Result queryById(Long id);
	
}
