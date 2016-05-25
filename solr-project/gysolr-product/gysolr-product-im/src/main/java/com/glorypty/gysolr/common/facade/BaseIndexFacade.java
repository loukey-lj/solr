/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.common.facade.BaseFacade.java <2016年5月13日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.facade;

import com.glorypty.gysolr.common.domain.BaseQuery;

/**
 *
 * @author liujie 
 * @Date 2016年5月13日 下午3:57:40
 * @version 1.0
 */

public interface BaseIndexFacade<Entrty extends BaseQuery> {
	
	/**
	 * 主键删除
	 */
	public Boolean deleteById(Long id);
	
	/**
	 * 条件删除
	 */
	public void deleteByWhere(Entrty t);
	
	/**
	 * 主键更新
	 */
	public void updateById(Long id);
	
	/**
	 * 批量更新(条件更新)
	 */
	public void updateByWhere(Entrty t);
	
	/**
	 * 删除某个core
	 */
	public void deleteCore(String core);
	
	/**
	 * 覆盖更新某个core
	 */
	public void overloadCore(String core);
	
	/**
	 * 重建某个core
	 */
	public void deleteAndIndexCore(String core);
}
