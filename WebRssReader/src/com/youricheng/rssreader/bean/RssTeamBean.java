package com.youricheng.rssreader.bean;

import java.util.List;

/**
 * rss������Ϣ
 * @author Youri
 *
 */
public class RssTeamBean {
	/**
	 * �������
	 */
	private String title;
	
	/**
	 * ��������
	 */
	private String text;
	
	/**
	 * �����б�,�������RssBean����
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
