package com.glorypty.gysolr.product.bulider;

import java.util.concurrent.CountDownLatch;

import com.glorypty.gysolr.common.bulider.BaseBulider;
import com.glorypty.gysolr.common.service.BaseIndexImpl;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.product.domain.GoodsData;

public class GoodsBulider  extends BaseBulider<GoodsData>{
	

	public GoodsBulider(Integer start, Integer pageEnd,CountDownLatch single,BaseIndexImpl<GoodsData> baseIndexImpl) {
		super(start,pageEnd,single,baseIndexImpl,SolrCoreCodeEnum.GOODS.name());
	}

	public GoodsBulider(Integer start,CountDownLatch single,BaseIndexImpl<GoodsData> baseIndexImpl) {
		super(start,single,baseIndexImpl,SolrCoreCodeEnum.GOODS.name());
	}
	
}