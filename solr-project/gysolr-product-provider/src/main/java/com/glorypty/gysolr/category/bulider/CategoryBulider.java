package com.glorypty.gysolr.category.bulider;

import java.util.concurrent.CountDownLatch;

import com.glorypty.gysolr.category.domain.CategoryData;
import com.glorypty.gysolr.common.bulider.BaseBulider;
import com.glorypty.gysolr.common.service.BaseIndexImpl;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;

public class CategoryBulider  extends BaseBulider<CategoryData>{
	

	public CategoryBulider(Integer start, Integer pageEnd,CountDownLatch single,BaseIndexImpl<CategoryData> baseIndexImpl) {
		super(start,pageEnd,single,baseIndexImpl,SolrCoreCodeEnum.CATEGORY.name());
	}

	public CategoryBulider(Integer start,CountDownLatch single,BaseIndexImpl<CategoryData> baseIndexImpl) {
		super(start,single,baseIndexImpl,SolrCoreCodeEnum.CATEGORY.name());
	}
	
}