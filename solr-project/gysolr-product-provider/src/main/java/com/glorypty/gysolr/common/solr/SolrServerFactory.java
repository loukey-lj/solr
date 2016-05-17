package com.glorypty.gysolr.common.solr;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.glorypty.framework.util.PropertyUtil;

public class SolrServerFactory {

	private static Map<SolrCoreCodeEnum, HttpSolrClient> searchServerMap = Collections.synchronizedMap(new HashMap<SolrCoreCodeEnum, HttpSolrClient>());

	public static synchronized HttpSolrClient getSearchServer(SolrCoreCodeEnum searchName) {
		String coreUrl = null;
		try {
			coreUrl = PropertyUtil.getProperty("solr.base");
		} catch (Exception e) {
		    System.exit(0);
		}
		
		if (coreUrl.endsWith("/")) {
			coreUrl = coreUrl + searchName.name().toLowerCase(Locale.ENGLISH);
		} else {
			coreUrl = coreUrl + "/"
					+ searchName.name().toLowerCase(Locale.ENGLISH);
		}
		HttpSolrClient solrServer = null;
		if (!searchServerMap.containsKey(searchName)) {
			solrServer = new HttpSolrClient(coreUrl);

			solrServer.setSoTimeout(10000);
			solrServer.setConnectionTimeout(10000);
			solrServer.setMaxTotalConnections(100);
			solrServer.setDefaultMaxConnectionsPerHost(100);
//			solrServer.setMaxRetries(2);
			// solrServer.setRequestWriter(new BinaryRequestWriter());
			// //打开注释后会报错
			searchServerMap.put(searchName, solrServer);
		}
		return searchServerMap.get(searchName);
	}

}
