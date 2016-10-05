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
 * rss�ļ��б����ù�����
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
				throw new RuntimeException("�Ҳ���rss_config.xml�ļ�");
			}
			try {
				//��ȡ������XML�ĵ�
				//SAXReader����һ���ܵ�����һ�����ķ�ʽ����xmld��ȡ����
				SAXReader reader = new SAXReader();
				//����xml�ַ���
				Document doc = reader.read(is);
				Element rootElt = doc.getRootElement(); //��ȡ���ڵ�
				System.out.println("���ڵ㣺 " + rootElt.getName()); //�õ����ڵ������
					
				Iterator<?> iter = rootElt.elementIterator("rss-list");
				
				//����rss-list�ڵ�
				while(iter.hasNext()){
					
					Element recordEle = (Element) iter.next();
					String name = recordEle.elementTextTrim("rss-name"); //�õ�rss-list�ڵ��µ��ӽڵ�nameֵ
					System.out.println("name: " + name);
					
					String path = recordEle.elementTextTrim("rss-path");//�õ�rss-list�ڵ��µ��ӽڵ�path
					System.out.println("path: " + path );
					
					rssConfigBean = new RssConfigBean();
					//���浽rssConfigBean��
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
