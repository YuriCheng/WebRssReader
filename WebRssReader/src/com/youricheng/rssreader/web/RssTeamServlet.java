package com.youricheng.rssreader.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youricheng.rssreader.bean.RssTeamBean;
import com.youricheng.rssreader.manager.ReadXML;

@SuppressWarnings("serial")
public class RssTeamServlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡweb��Ŀ�ĸ�Ŀ¼��ʵ��Ŀ¼
				String path = this.getServletConfig().getServletContext().getRealPath("/");
				System.out.println(path);
				ReadXML readXML = new ReadXML();
				
				//��ȡĿ¼�µ�rss�ļ��������е�opml�ļ�
				//�����еķ�����Ϣ������һ��List<RssTeamBean>��
				//��ÿһ�������µ����ж�����Ϣ,�����ڸ�RssTeamBean�е�һ��List<RssBean>��
				readXML.ReadRssTeam(path);
				
				//��ȡװ�����з�����Ϣ���б�
				List<RssTeamBean> rssTemBeanList = readXML.getRssTemBeanList();
				
				//�����з�����Ϣ������request������rssTeamBeanList��
				request.setAttribute("rssTemBeanList", rssTemBeanList);
				
				//ת��request
				request.getRequestDispatcher("/left.jsp").forward(request, response);
	}
}
