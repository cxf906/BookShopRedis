package com.icss.bk.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.bk.biz.BookBiz;
import com.icss.bk.dto.BookComment;
import com.icss.bk.entity.TUser;

public class BookCommentSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookCommentSvl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         
		if(request.getSession().getAttribute("user") != null){
			String isbn = request.getParameter("isbn");
			  if(isbn != null){ 
				 try {
					 BookBiz biz = new BookBiz();
					 List<BookComment> bcList = biz.getBookComments(isbn);					 
					 request.setAttribute("bcList", bcList);
					 request.setAttribute("isbn", isbn);
					 request.getRequestDispatcher("/main/BookComment.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}  
			  }			 
		}else{
			request.getRequestDispatcher("/main/login.jsp").forward(request, response);
		}	  	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
       
		  String bkComm = request.getParameter("bkComm");
		  String isbn = request.getParameter("isbn");
		  BookBiz biz = new BookBiz();
		  TUser user = (TUser)request.getSession().getAttribute("user");
		  try {
			  biz.addBookComment(isbn, user.getUname(), bkComm);
			  List<BookComment> bcList = biz.getBookComments(isbn);					 
			  request.setAttribute("bcList", bcList);
			  request.setAttribute("isbn", isbn);
			  request.getRequestDispatcher("/main/BookComment.jsp").forward(request, response);
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
