package fr.inti.formation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UserName="root";
	
	private static final String password="123456";
	
//	private final static Log log = LogFactory.getLog(LoginServlet.class);
	
	private static boolean previouslyConnected = false;
	
	private static Date date;
	
	private static String login;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String message = (String)session.getAttribute("message");
		
		if (previouslyConnected && message!=null) {
			ServletOutputStream out = response.getOutputStream();
			out.println("<html>");
			out.println("<head><title>Login Servlet</title></head>");
			out.println("<h1>Hello " + login + "</h1><br>");
			out.println("<h1>"+message+"</h1><br>");
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			out.println("<h1>Date de connexion : " + formatter.format(date) + "</h1>");
		}else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = request.getServletContext();
		login = request.getParameter("Login");
		String pass = request.getParameter("Password");
		HttpSession session = request.getSession();
		if(UserName.equals(login) && password.equals(pass) ) {
			previouslyConnected = true;
			date = new Date();
			session.setAttribute("message", "You are connected");
			session.setAttribute("dataConnection", date);
			ServletOutputStream out = response.getOutputStream();
			out.println("<html>");
			out.println("<head><title>Login Servlet</title></head>");
			out.println("<h1>Hello " + login + "</h1><br>");
			out.println("<h1>You are connected</h1><br>");
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			out.println("<h1>Date de connexion : " + formatter.format(date) + "</h1>");
			
		}else {
			response.sendRedirect(context.getContextPath());
		}
	}

}
