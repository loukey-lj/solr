/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.sys.start.ApplicationStart.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.sys.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.glorypty.framework.util.PropertyUtil;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.product.index.GoodsIndexService;

/**
 * 容器启动
 * @author liujie 
 * @Date 2016年5月14日 下午5:34:22
 * @version 1.0
 */
@Service
public class ApplicationStart implements ApplicationListener<ApplicationEvent>{
	
	@Autowired
	private GoodsIndexService goodsIndexService;
    
	static Boolean  isFrist = false;
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(isFrist){
			return;
		}
		isFrist = true;
		try {
			String goods_reIndex = PropertyUtil.getProperty("goods.reIndex");
			
			if("1".equals(goods_reIndex)){
				new Thread( new Runnable() {
					@Override
					public void run() {
						goodsIndexService.deleteAndIndexCore(SolrCoreCodeEnum.GOODS.name());
						
					}
				}).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
