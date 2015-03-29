package fr.lordrski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloWorldServlet", urlPatterns = { "/helloworld" })
public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
		PrintWriter out = rs.getWriter();
		String data = "Hello World by get";
		
		out.println(data);
	}
	
	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
		PrintWriter out = rs.getWriter();
		String data = "Hello World by post";
		
		out.println(data);
	}

}
