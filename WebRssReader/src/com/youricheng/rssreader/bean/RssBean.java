package com.youricheng.rssreader.bean;
/**
 * ��ϸ�Ķ�����Ϣ
 * @author Youri
 *
 */
public class RssBean {
	/**
	 * ��ʾ����
	 */
	private String text;
	/**
	 * ����
	 */
	private String title;
	/**
	 * rss���ĵ�ַ
	 */
	private String htmlUrl;
	/**
	 * rss���ĵ�ַ
	 */
	private String xmlUrl;
	/**
	 * �汾
	 */
	private String version;
	/**
	 * ����
	 */
	private String type;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public String getXmlUrl() {
		return xmlUrl;
	}
	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
