package org.javali.util.test;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.gnu.stealthp.rsslib.RSSChannel;
import org.gnu.stealthp.rsslib.RSSException;
import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSItem;
import org.gnu.stealthp.rsslib.RSSParser;


public class RssNewsFetcher {
	/**
	 * rss�����б�
	 */
	private final static String[] rssArr = new String[] {
			/*"http://rss.zol.com.cn/labs.xml",
			"http://cn.engadget.com/rss.xml",
			"http://www.donews.com/GroupFeed.aspx?G=5B1D5178-138D-4D42-B370-5198FDF5AF34",
			"http://www.zaobao.com/zg/zg.xml",
			"http://news.163.com/special/r/00011K6L/rss_newstop.xml",
			"http://rss.zol.com.cn/news.xml",
			"http://www.douban.com/feed/review/movie",
			"http://feeds.feedburner.com/solidot" */
		"http://xiaoxian100.blog.163.com/rss",
        "http://blog.csdn.net/xiaoxian8023/rss/list" 
			
	};
	/**
	 * ���Խ���rss����
	 * @throws IOException
	 */
	public void testFetchRssNews() throws IOException {
		for (int i = 0; i < rssArr.length; i++) {
			System.out.println("STARTING TO FETCH FROM : " + rssArr[i]);
			try {
				
				//��ȡrss���ĵ�ַ
				URL url = new URL(rssArr[i]);
				RSSHandler handler = new RSSHandler();
				
				//����rss
				RSSParser.parseXmlFile(url, handler, false);
				//��ȡrssƵ����Ϣ
				RSSChannel ch = handler.getRSSChannel();
				/*System.out.println(ch.toString());
				
				
				LinkedList<RSSItem> lst = handler.getRSSChannel().getItems();
				for (int j = 0; j < lst.size(); j++) {
				    RSSItem itm = lst.get(j);
				    System.out.println(itm.toString());
				}*/
				System.out.println("---------------------------------------------");
                System.out.println("���ͱ��⣺" + ch.getTitle());
                System.out.println("�������ӣ�" + ch.getLink());
                System.out.println("����������" + ch.getDescription());
                System.out.println("�������ԣ�" + ch.getLanguage());
                System.out.println("����ʱ�䣺" + ch.getPubDate());
                System.out.println("ͼƬ��ַ��" +ch.getRSSImage().getUrl());
                System.out.println("ͼƬָ��" +ch.getRSSImage().getLink());

                System.out.println("����������ĿΪ��" + ch.getItems().size());
//                for(i=0;i<ch.getItems().size();i++){
                for(int j=0;j<1;j++){   //����Ϊ�˷�����ԣ�ֻȡһ��ѭ������ʵʹ��ʱ����ѡ�������Ǿ����
                    RSSItem item = (RSSItem)ch.getItems().get(j);
                    System.out.println("���±��⣺" + item.getTitle());
                    System.out.println("����ժҪ��" + item.getSummary());
                    //System.out.println("�������ģ�" + item.getDescription());
                    //����Ϊ����ʾ���㣬��ȡ���ĵ�ǰ100���ַ�����ʵʹ��ʱ����ѡ�������Ǿ����
                    System.out.println("�������ģ�" + item.getDescription().substring(0, 100));
                    System.out.println("�������ӣ�" + item.getLink());
                    System.out.println("����ʱ�䣺" + item.getPubDate());
                    System.out.println("�������ߣ�" + item.getAuthor());
                    System.out.println("�����޸ģ�" + item.getDate());
                }
				
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RSSException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		RssNewsFetcher fetcher = new RssNewsFetcher();
		fetcher.testFetchRssNews();
	}

}