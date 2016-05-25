/*
 *Project: glorypty-manage
 *File: com.glorypty.scheduler.BaseJob.java <2015年9月28日>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.job;

import org.apache.log4j.Logger;

/**
 *
 * @author hardy 
 * @Date 2015年9月28日 下午4:29:56
 * @version 1.0
 */
public abstract class BaseJob {
	protected final Logger logger = Logger.getLogger(this.getClass());	
	
	/**
	 * Job入口
	 * @author hardy<2015年12月3日>
	 */
	public abstract void main();
	
}
