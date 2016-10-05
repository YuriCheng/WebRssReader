package com.youricheng.rssreader.bean;

import java.util.List;

/**
 * rss分组信息
 * @author Youri
 *
 */
public class RssTeamBean {
	/**
	 * 分组标题
	 */
	private String title;
	
	/**
	 * 分组名称
	 */
	private String text;
	
	/**
	 * 分组列表,当中填充RssBean对象
	 */
	private List<RssBean> rssBeanList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<RssBean> getRssBeanList() {
		return rssBeanList;
	}

	public void setRssBeanList(List<RssBean> rssBeanList) {
		this.rssBeanList = rssBeanList;
	}
	
}
