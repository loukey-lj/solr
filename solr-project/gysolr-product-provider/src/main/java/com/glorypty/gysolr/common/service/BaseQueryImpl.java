/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.base.BaseSolrService.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.service;

import com.glorypty.gysolr.common.domain.BaseQuery;
import com.glorypty.gysolr.common.facade.BaseQueryFacade;
import com.glorypty.gysolr.common.result.BaseResult;

/**
 *
 * @author liujie 
 * @Date 2016年5月14日 下午5:42:07
 * @version 1.0
 */
public class BaseQueryImpl<Entrty extends BaseQuery,Result extends BaseResult<Entrty>> implements BaseQueryFacade<Entrty, Result>{

	@Override
	public Result queryByWhere(Entrty t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result queryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
