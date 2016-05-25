/*
 *Project: glorypty-solr
 *File: com.glorypty.solr.job.thread.BaseBulider.java <2015年9月29日>
 ****************************************************************
 * 版权所有@2015 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.bulider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.glorypty.gysolr.common.domain.BaseQuery;
import com.glorypty.gysolr.common.service.BaseIndexImpl;

/**
 *
 * @author liujie 
 * @Date 2015年9月29日 下午3:05:43
 * @version 1.0
 */
public  class BaseBulider<Entrty extends BaseQuery> implements Runnable {
	
	Logger log = Logger.getLogger(this.getClass());
	private final CountDownLatch startSignal;
	
	private BaseIndexImpl<Entrty> solrService;
	
	/**每次查询数量**/
	final Integer pageSize = 5000;

	/**开始查询位置**/
	private Integer start;
	
	/**结束位置**/
	private Integer pageEnd;
	
	
	/** solrCoreName **/
	private String coreName;
	
	
	public BaseBulider(Integer start, Integer pageEnd,CountDownLatch startSignal,BaseIndexImpl<Entrty> baseIndexImpl,String coreName) {
		super();
		this.start = start;
		if (pageEnd == null || pageEnd <= 0) {
			this.pageEnd = -1;
		} else {
			this.pageEnd = pageEnd;
		}
		this.solrService = baseIndexImpl;
		this.coreName = coreName;
		this.startSignal = startSignal;
	}

	public BaseBulider(Integer start,CountDownLatch startSignal,BaseIndexImpl<Entrty> baseIndexImpl,String coreName) {
		super();
		this.start = start;
		this.pageEnd = -1;
		this.solrService = baseIndexImpl;
		this.coreName = coreName;
		this.startSignal = startSignal;
	}

	@Override
	public void run() {
		log.warn(coreName +" bulider start " + new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()));
		Long begin = System.currentTimeMillis();
		try {
			int page = start/pageSize + 1;
			System.err.println(" page = "+ page + ",pageSize = " + pageSize);
			System.err.println(((page - 1) * pageSize + 1)+" <="+" num " + "< "+ (page * pageSize + 1));
			List<Entrty> listEntrty = solrService.getDataByPage(page, pageSize);
			while (listEntrty != null && listEntrty.size() > 0) {
				for (Entrty entrty : listEntrty) {
					solrService.indexEntrty(entrty);
				}
				
				//如果 start == -1则说明是一次性获取的 不需要分页
				//所以可以直接跳出while
				if ( start == -1 || (page * pageSize  >= pageEnd && pageEnd != -1)) {
					listEntrty.clear();
					break;
				}
				page = page + 1;
				listEntrty.clear();
				listEntrty = solrService.getDataByPage(page, pageSize);
			}
			listEntrty = null;
			
		} catch (Exception e) {
			log.error(coreName +" bulider failed " + new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()),e);
			e.printStackTrace();
		}
		/**执行完上报执行完毕**/
		finally{
			startSignal.countDown();
		}
		Long end = System.currentTimeMillis();
		log.warn(coreName +" bulider end " + new SimpleDateFormat("yyyy-MM-dd HH:ss:mm").format(new Date()) + " cost:" + (end-begin)/1000 +" sec!");

	}

}
