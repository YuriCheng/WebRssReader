package com.youricheng.rssreader.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.youricheng.rssreader.bean.RssBean;
import com.youricheng.rssreader.bean.RssConfigBean;
import com.youricheng.rssreader.bean.RssTeamBean;

/**
 * ��ȡxml�ļ�
 * @author Youri
 *
 */
public class ReadXML {
	
	//rss���鶩���б�
	private List<RssTeamBean> rssTeamBeanList = new ArrayList<RssTeamBean>();
	
	/**
	 * ��ȡrss�ļ��б�
	 */
	public void ReadRss(){
		// rss�ļ��б�������Ϣʵ��
		RssConfigManager rssConfigManager = new RssConfigManager();
		List<RssConfigBean> list = rssConfigManager.getRssConfig();
		
		String errText = ""; //��¼������Ϣ
		
		for(RssConfigBean rssConfig : list){
			try {
				System.out.println(System.getProperty("user.dir") + rssConfig.getPath());
				ReadRss(System.getProperty("user.dir") + rssConfig.getPath());
				
			} catch (Exception e) {
				errText += e.getMessage();
			}
		}
	}
	
	/**
     * ��ȡompl�ļ�
     * 
     * @param filePath
     */
	public void ReadRss(String filePath){
		
		File file = new File(filePath);
		
		if(!file.exists()){
			throw new RuntimeException("�Ҳ��� "+ filePath+" �ļ�");
		}
		try {

            // ��ȡ������XML�ĵ�
            // SAXReader����һ���ܵ�����һ�����ķ�ʽ����xml�ļ�������
            SAXReader reader = new SAXReader();
            FileInputStream fis = new FileInputStream(file);

            // �������ͨ������xml�ַ�����
            Document doc = reader.read(fis);

            // ��ȡ���ڵ�
            Element rootElt = doc.getRootElement(); // ��ȡ���ڵ�
            // System.out.println("���ڵ㣺" + rootElt.getName()); // �õ����ڵ������

            // ��ȡhead/title�ڵ�
            Element titleElt = (Element) rootElt.selectSingleNode("head/title");// ��ȡhead�ڵ��µ��ӽڵ�title

            // ��ȡ��������
            String title = titleElt.getTextTrim();

            // ��ȡbody�ڵ�
            Element bodyElt = (Element) rootElt.selectSingleNode("body");

            // ��ȡbody�µĵ�һ��outline�ڵ�
            Element outlineElt = (Element) bodyElt.selectSingleNode("outline");

            // �жϸ�outlineElt�ڵ������������>2˵������ϸ���Ͷ�����Ϣ���������Ƿ�����Ϣ��
            if (outlineElt.attributes().size() > 2) { // ��ϸ���Ͷ�����Ϣ

                // ʵ���� RSS����ʵ��
                RssTeamBean rssTeamBean = new RssTeamBean();

                // ��ȡbody�ڵ��µ�outline�ڵ�
                Iterator<?> iter = bodyElt.elementIterator("outline");

                // �����������
                // System.out.println("��������:" + title);

                // ��¼��������
                rssTeamBean.setTitle(title);
                rssTeamBean.setText(title);

                // ʵ���������б�
                List<RssBean> rssBeanList = new ArrayList<RssBean>();

                // ��ȡ��ϸ���Ͷ�����Ϣ
                ReadBlogsInfo(iter, rssBeanList);

                // ���ö����б�������
                rssTeamBean.setRssBeanList(rssBeanList);

                // ��ӷ��鵽rss���鶩���б�
                rssTeamBeanList.add(rssTeamBean);

            } else { // ������Ϣ

                // ��ȡbody�ڵ��µ�outline�ڵ�
                Iterator<?> iter = bodyElt.elementIterator("outline");

                while (iter.hasNext()) {

                    // ��ȡoutline�ڵ��µ�����outline��Ϣ��ÿ����Ϣ����һ�����ļ�¼
                    Element TeamElt = (Element) iter.next();

                    // ʵ���� RSS����ʵ��
                    RssTeamBean rssTeamBean = new RssTeamBean();

                    // ���»�ȡ��������
                    title = TeamElt.attributeValue("title");
                    String text = TeamElt.attributeValue("text");
                    // System.out.println("����title:" + title + "   text:" +
                    // text);

                    // ��¼�������ƺ���ʾ����
                    rssTeamBean.setTitle(title);
                    rssTeamBean.setText(text);

                    // ʵ���������б�
                    List<RssBean> rssBeanList = new ArrayList<RssBean>();

                    // ��ȡbody�ڵ��µ�outline�ڵ�
                    Iterator<?> iterRss = TeamElt.elementIterator("outline");

                    // ��ȡ��ϸ���Ͷ�����Ϣ
                    ReadBlogsInfo(iterRss, rssBeanList);

                    // ���ö����б�������
                    rssTeamBean.setRssBeanList(rssBeanList);

                    // ��ӷ��鵽rss���鶩���б�
                    rssTeamBeanList.add(rssTeamBean);
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		
		
	}
	/**
	 * ��ȡ��ǰ�鲩�Ͷ�����Ϣ
	 * @param iter
	 * 				��ǰ�ڵ���ӽڵ������
	 * @param rssBeanList
	 * 				�����б�
	 */
	private void ReadBlogsInfo(Iterator<?> iter, List<RssBean> rssBeanList) {
		
		//��������outline�ڵ㣬ÿ������һ��������Ϣ
		while(iter.hasNext()){
			
			RssBean rssBean = new RssBean();
			Element outlineElt = (Element) iter.next();
			String htmlUrl = outlineElt.attributeValue("htmlUrl");//�õ���ǰ�ڵ��htmlUrl����ֵ
			String xmlUrl = outlineElt.attributeValue("xmlUrl"); //�õ���ǰ�ڵ��xmlUrl����ֵ
			String version = outlineElt.attributeValue("version"); //�õ���ǰ�ڵ��version����ֵ
			String type = outlineElt.attributeValue("type"); //�õ���ǰ�ڵ��type����ֵ
			String outlineTitle = outlineElt.attributeValue("title"); //�õ���ǰ�ڵ�title����ֵ
			String outlineText = outlineElt.attributeValue("text"); //�õ���ǰ�ڵ��text����ֵ
			rssBean.setHtmlUrl(htmlUrl);
			rssBean.setXmlUrl(xmlUrl);
			rssBean.setVersion(version);
			rssBean.setType(type);
			rssBean.setTitle(outlineTitle);
			rssBean.setText(outlineText);
			
			// ��ÿ��������Ϣ����ŵ������б���
			rssBeanList.add(rssBean);
		}
	}
	/**
	 * ��ȡRss���鶩���б�
	 * @return
	 */
	public List<RssTeamBean> getRssTemBeanList(){
		return rssTeamBeanList;
	}
	
	public static void main(String[] args) {
		ReadXML readXML = new ReadXML();
		readXML.ReadRss();
		List<RssTeamBean> rssTemBeanList = readXML.getRssTemBeanList();
		for(RssTeamBean rssTeamBean : rssTemBeanList){
			System.out.println("[����title: "+ rssTeamBean.getTitle() +"  text:"+ rssTeamBean.getText() +"]");
			for (RssBean rssBean : rssTeamBean.getRssBeanList()) {
                System.out.print("<outline htmlUrl=\"" + rssBean.getHtmlUrl() + "\" ");
                //System.out.print("xmlUrl=\"" + rssBean.getXmlUrl() + "\" ");
                System.out.print("version=\"" + rssBean.getVersion() + "\" ");
                System.out.print("type=\"" + rssBean.getType() + "\" ");
                System.out.print("title=\"" +  rssBean.getTitle() + "\" ");
                System.out.println("text=\"" + rssBean.getText() + "\" />");
           }
			System.out.println("-------------------------------------------------");
		}
	}
	
}
