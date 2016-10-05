package com.youricheng.rssreader.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.youricheng.rssreader.bean.RssConfigBean;

/**
 * rss文件列表配置管理器
 * @author Youri
 *
 */
public class RssConfigManager {
	
		public List<RssConfigBean> getRssConfig(){
			
			List<RssConfigBean> list = new ArrayList<RssConfigBean>();
			RssConfigBean rssConfigBean = null;
			InputStream is = Thread.currentThread().getContextClassLoader()
									.getResourceAsStream("rss_config.xml");
			if(is == null){
				throw new RuntimeException("找不到rss_config.xml文件");
			}
			try {
				//读取并解析XML文档
				//SAXReader就是一个管道，用一个流的方式，将xmld读取出来
				SAXReader reader = new SAXReader();
				//解析xml字符串
				Document doc = reader.read(is);
				Element rootElt = doc.getRootElement(); //获取根节点
				System.out.println("根节点： " + rootElt.getName()); //拿到根节点的名称
					
				Iterator<?> iter = rootElt.elementIterator("rss-list");
				
				//遍历rss-list节点
				while(iter.hasNext()){
					
					Element recordEle = (Element) iter.next();
					String name = recordEle.elementTextTrim("rss-name"); //拿到rss-list节点下的子节点name值
					System.out.println("name: " + name);
					
					String path = recordEle.elementTextTrim("rss-path");//拿到rss-list节点下的子节点path
					System.out.println("path: " + path );
					
					rssConfigBean = new RssConfigBean();
					//保存到rssConfigBean中
					rssConfigBean.setName(name);
					rssConfigBean.setPath(path);
					list.add(rssConfigBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
}
