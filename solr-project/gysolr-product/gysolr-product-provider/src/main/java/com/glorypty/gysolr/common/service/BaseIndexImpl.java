/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.base.BaseSolrService.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.glorypty.gysolr.common.bulider.BaseBulider;
import com.glorypty.gysolr.common.domain.BaseQuery;
import com.glorypty.gysolr.common.facade.BaseIndexFacade;

/**
 *
 * @author liujie 
 * @Date 2016年5月14日 下午5:42:07
 * @version 1.0
 */
public abstract class BaseIndexImpl<Entrty extends BaseQuery> implements BaseIndexFacade<Entrty>{
	protected  Logger log = Logger.getLogger(this.getClass());

	@Override
	public Boolean deleteById(Long id) {
		try {
			getHttpSolrClient().deleteById(id+"");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this + "deleteById failed id:" + id);
		}
		return false;
	}

	@Override
	public void deleteByWhere(Entrty t) {
		
	}

	@Override
	public void updateById(Long id) {
		
	}

	@Override
	public void updateByWhere(Entrty t) {
		
	}

	@Override
	public void deleteCore(String core) {
		
	}

	@Override
	public void overloadCore(String core) {
		//获取总条数
		Integer total = getTotal();
		if((total == null || total <= 0) && total != -1){
			log.warn("core :"+core+ ", there is none data to index");
			return;
		}
		//获取单线程处理数量
		int bathNum = getBathNum();
		//计算线程数
		int threadSizeInt = (total%bathNum == 0) ? (total/bathNum) : (total/bathNum + 1);
		ExecutorService ex = Executors.newFixedThreadPool(threadSizeInt);
		CountDownLatch startSignal = new CountDownLatch(threadSizeInt);
		for(int i = 0; i < threadSizeInt; i ++){
			if(i == threadSizeInt-1 ){
				//代表不分页
				if(total == -1){
					ex.execute(getBulider(-1,-1,startSignal));
					break;
				}else{
					ex.execute(getBulider(i*bathNum,-1,startSignal));
				}
			}else{
				ex.execute(getBulider(i*bathNum, (i+1)*bathNum,startSignal));
			}
		}
		
		try {
			//资源回收
			while(true){
				Long  size = startSignal.getCount();
				if(size == 0){
					ex.shutdown();
					//休息5分钟等待都commit完毕
					Thread.sleep(60*5*1000);
					//提交完毕优化索引
					getHttpSolrClient().optimize();
					break;
				}else{
					Thread.sleep(60*1000);
				}
			}
		} catch (Exception e) {
			log.error("core :"+core+ ", ExecutorService shutdown failed");
			e.printStackTrace();
		}
	}
		

	@Override
	public void deleteAndIndexCore(String core)  {
		try {
			//第一步删除数据
			getHttpSolrClient().deleteByQuery("*:*");
			overloadCore(core);
		} catch (Exception e) {
			log.error("core :"+core+ ", deleteAndIndexCore failed");
			e.printStackTrace();
		}
	}
    
	public abstract BaseBulider<Entrty> getBulider(int start, int end,CountDownLatch startSignal);
	public abstract List<Entrty> getDataByPage(Integer page, Integer pageSize);
	public abstract Boolean indexEntrty(Entrty entrty);
	public abstract Integer getTotal();
	public abstract Integer getBathNum();
	public abstract HttpSolrClient getHttpSolrClient();

}
