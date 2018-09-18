package com.icss.bk.controller.back;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Tuple;

import com.icss.bk.biz.BookBiz;
import com.icss.bk.dto.BookJson;

public class BookPageViewSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookPageViewSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		BookBiz biz = new BookBiz();
		try {
			List<BookJson> bkpv =  biz.getBookPageView();	
			request.setAttribute("bkpv", bkpv);			
			request.getRequestDispatcher("/back/BookPageView.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
