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
	
		//在这里定义一个现在的rss摘要的地址(先选择网易博客作为测试)
		public static final String remoteRss = "http://blog.csdn.net/g_ravity/rss/list";
		
		public static void main(String[] args) throws Exception {
			
			//让RSSParser去解析在线rss的url
			RSSHandler remoteRssHandler = new RSSHandler();
			
			URL url = new URL(remoteRss);
			URLConnection feedUrl = url.openConnection();
			
			RSSParser.parseXmlFile(feedUrl.getURL(), remoteRssHandler, false);
			
			//获取rss元素的信息并且打印在控制台上
			String remoteRSSInfo = Rsslib4jReadRss.getRSSInfo(remoteRssHandler);
			System.out.println("-----------------csdn的rss信息如下----------");
			System.out.println(remoteRSSInfo);
			System.out.println("------------------------------------------------");
		}

		private static String getRSSInfo(RSSHandler remoteRssHandler) {
				
			StringBuffer rssInfo = new StringBuffer();
			
			//获得rss提要的频道Channel
			RSSChannel channel  = remoteRssHandler.getRSSChannel();
			
			//part1: 分离出rss频道的元消息
			// (1) 频道的标题
			String titleInfo = channel.getTitle();
			// (2) 频道的连接消息
			String linkInfo = channel.getLink();
			// (3) 频道的描述信息
			String descriptionInfo = channel.getDescription();
			// (4) 频道使用的语言
			String languageInfo = channel.getLanguage();
			// (5) 频道的版权消息
			String copyrightInfo = channel.getCopyright();
			// (6) 频道的generator的消息
			String generatorInfo = channel.getGenerator();
			// (7) 频道的image消息
			RSSImage channelImage = channel.getRSSImage();
			String channelImageUrl = channelImage.getUrl();
			
			 rssInfo.append("频道标题： " + titleInfo + "\n");
		        rssInfo.append("频道相关Link信息： " + linkInfo + "\n");
		        rssInfo.append("频道描述信息: " + descriptionInfo + "\n");
		        rssInfo.append("频道使用的语言： " + languageInfo + "\n");
		        rssInfo.append("频道版权信息： " + copyrightInfo + "\n");
		        rssInfo.append("频道产生器信息： " + generatorInfo + "\n");
		        rssInfo.append("频道图片URL: " + channelImageUrl + "\n");
		     
		     // part2: 分离出rss频道的所有摘要(feed), 这里用item表示
		       List channelItems = channel.getItems();
		       int itemSize = channelItems.size();
		       if(itemSize >= 1){
		    	   rssInfo.append("\n");
		            rssInfo.append("一共有 " + itemSize + "个摘要在这个频道中");
		            rssInfo.append("\n");
		            for(int i = 0; i < 1; i++){
		            	int itemNo = i + 1;
		            	RSSItem item = (RSSItem) channelItems.get(i);
		            	rssInfo.append("\n");
		                rssInfo.append("摘要" + itemNo + ":");
		                rssInfo.append("\n");
		                // (1)摘要的作者
		                String itemAuthor = item.getAuthor();
		                // (2)摘要的标题
		                String itemTitle = item.getTitle();
		                // (3)摘要的描述
		                String itemDescription = item.getDescription();
		                // (4)摘要的连接
		                String itemLink = item.getLink();
		                // (5)摘要的发布日期
		                String itemPubDate = item.getPubDate();
		                rssInfo.append("作者： " + itemAuthor + "\n");
		                rssInfo.append("标题： " + itemTitle + "\n");
		                rssInfo.append("描述： " + itemDescription + "\n");
		                rssInfo.append("链接： " + itemLink + "\n");
		                rssInfo.append("发布日期: " + itemPubDate + "\n");

		                rssInfo.append("\n");
		            }
		       }
				
			return rssInfo.toString();
		}
}
