/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.sys.start.ApplicationStart.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.sys.start;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.glorypty.framework.util.PropertyUtil;
import com.glorypty.gysolr.category.index.CategoryIndexService;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.common.util.SerialUtil;
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
	@Autowired
	private CategoryIndexService categoryIndexService;
    
	static Boolean  isFrist = false;
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		//系统启动时 要获取机器序列
		XmlWebApplicationContext applicationContext =  (XmlWebApplicationContext) event.getSource();
		String path = applicationContext.getServletContext().getRealPath("/");
		File serialDir = new File(path + File.separator + "serial");
		if(serialDir.exists()){
			makeFile(serialDir);
		}else{
			serialDir.mkdir();
			makeFile(serialDir);
		}
		
		System.out.println(SerialUtil.getSearialNum());
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
			
			String category_reIndex = PropertyUtil.getProperty("category.reIndex");
			
			if("1".equals(category_reIndex)){
				new Thread( new Runnable() {
					@Override
					public void run() {
						categoryIndexService.deleteAndIndexCore(SolrCoreCodeEnum.CATEGORY.name());
						
					}
				}).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void makeFile(File serialDir) {
		File[] files = serialDir.listFiles();
		if(files != null && files.length > 0){
			SerialUtil.setSearialNum(files[0].getName());
		}else{
			String tem = System.currentTimeMillis() + "";
			File f = new File(serialDir.getPath() + File.separator + tem);
			try {
				f.createNewFile();
				SerialUtil.setSearialNum(tem);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
   
}
