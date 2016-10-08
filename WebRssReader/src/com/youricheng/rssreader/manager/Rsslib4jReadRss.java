package com.youricheng.rssreader.manager;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Handle;

import org.gnu.stealthp.rsslib.RSSChannel;
import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSImage;
import org.gnu.stealthp.rsslib.RSSItem;
import org.gnu.stealthp.rsslib.RSSParser;

import com.sun.org.apache.xml.internal.utils.URI;

public class Rsslib4jReadRss {
	
		//�����ﶨ��һ�����ڵ�rssժҪ�ĵ�ַ(��ѡ�����ײ�����Ϊ����)
		public static final String remoteRss = "http://blog.csdn.net/g_ravity/rss/list";
		
		public static void main(String[] args) throws Exception {
			
			//��RSSParserȥ��������rss��url
			RSSHandler remoteRssHandler = new RSSHandler();
			
			URL url = new URL(remoteRss);
			URLConnection feedUrl = url.openConnection();
			
			RSSParser.parseXmlFile(feedUrl.getURL(), remoteRssHandler, false);
			
			//��ȡrssԪ�ص���Ϣ���Ҵ�ӡ�ڿ���̨��
			String remoteRSSInfo = Rsslib4jReadRss.getRSSInfo(remoteRssHandler);
			System.out.println("-----------------csdn��rss��Ϣ����----------");
			System.out.println(remoteRSSInfo);
			System.out.println("------------------------------------------------");
		}

		private static String getRSSInfo(RSSHandler remoteRssHandler) {
				
			StringBuffer rssInfo = new StringBuffer();
			
			//���rss��Ҫ��Ƶ��Channel
			RSSChannel channel  = remoteRssHandler.getRSSChannel();
			
			//part1: �����rssƵ����Ԫ��Ϣ
			// (1) Ƶ���ı���
			String titleInfo = channel.getTitle();
			// (2) Ƶ����������Ϣ
			String linkInfo = channel.getLink();
			// (3) Ƶ����������Ϣ
			String descriptionInfo = channel.getDescription();
			// (4) Ƶ��ʹ�õ�����
			String languageInfo = channel.getLanguage();
			// (5) Ƶ���İ�Ȩ��Ϣ
			String copyrightInfo = channel.getCopyright();
			// (6) Ƶ����generator����Ϣ
			String generatorInfo = channel.getGenerator();
			// (7) Ƶ����image��Ϣ
			RSSImage channelImage = channel.getRSSImage();
			String channelImageUrl = channelImage.getUrl();
			
			 rssInfo.append("Ƶ�����⣺ " + titleInfo + "\n");
		        rssInfo.append("Ƶ�����Link��Ϣ�� " + linkInfo + "\n");
		        rssInfo.append("Ƶ��������Ϣ: " + descriptionInfo + "\n");
		        rssInfo.append("Ƶ��ʹ�õ����ԣ� " + languageInfo + "\n");
		        rssInfo.append("Ƶ����Ȩ��Ϣ�� " + copyrightInfo + "\n");
		        rssInfo.append("Ƶ����������Ϣ�� " + generatorInfo + "\n");
		        rssInfo.append("Ƶ��ͼƬURL: " + channelImageUrl + "\n");
		     
		     // part2: �����rssƵ��������ժҪ(feed), ������item��ʾ
		       List channelItems = channel.getItems();
		       int itemSize = channelItems.size();
		       if(itemSize >= 1){
		    	   rssInfo.append("\n");
		            rssInfo.append("һ���� " + itemSize + "��ժҪ�����Ƶ����");
		            rssInfo.append("\n");
		            for(int i = 0; i < 1; i++){
		            	int itemNo = i + 1;
		            	RSSItem item = (RSSItem) channelItems.get(i);
		            	rssInfo.append("\n");
		                rssInfo.append("ժҪ" + itemNo + ":");
		                rssInfo.append("\n");
		                // (1)ժҪ������
		                String itemAuthor = item.getAuthor();
		                // (2)ժҪ�ı���
		                String itemTitle = item.getTitle();
		                // (3)ժҪ������
		                String itemDescription = item.getDescription();
		                // (4)ժҪ������
		                String itemLink = item.getLink();
		                // (5)ժҪ�ķ�������
		                String itemPubDate = item.getPubDate();
		                rssInfo.append("���ߣ� " + itemAuthor + "\n");
		                rssInfo.append("���⣺ " + itemTitle + "\n");
		                rssInfo.append("������ " + itemDescription + "\n");
		                rssInfo.append("���ӣ� " + itemLink + "\n");
		                rssInfo.append("��������: " + itemPubDate + "\n");

		                rssInfo.append("\n");
		            }
		       }
				
			return rssInfo.toString();
		}
}
