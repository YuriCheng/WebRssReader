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
		//获取web项目的根目录的实际目录
				String path = this.getServletConfig().getServletContext().getRealPath("/");
				System.out.println(path);
				ReadXML readXML = new ReadXML();
				
				//读取目录下的rss文件夹中所有的opml文件
				//将所有的分组信息保存在一个List<RssTeamBean>中
				//将每一个分组下的所有订阅信息,保存在该RssTeamBean中的一个List<RssBean>中
				readXML.ReadRssTeam(path);
				
				//获取装有所有分组信息的列表
				List<RssTeamBean> rssTemBeanList = readXML.getRssTemBeanList();
				
				//将所有分组信息保存在request的属性rssTeamBeanList中
				request.setAttribute("rssTemBeanList", rssTemBeanList);
				
				//转发request
				request.getRequestDispatcher("/left.jsp").forward(request, response);
	}
}
