package com.icss.bk.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.bk.biz.BookBiz;
import com.icss.bk.dto.BookJson;
import com.icss.bk.entity.TBook;

public class MainSvl2 extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MainSvl2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BookBiz biz = new BookBiz();
		try {
			List<BookJson> books = biz.getTimeSortBooks();
			request.setAttribute("books", books);
			request.getRequestDispatcher("/main/main2.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "网络异常，请和管理员联系");
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
