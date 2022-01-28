package fr.inti.formation.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.inti.formation.entity.Employee;
import fr.inti.formation.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory factory;
	private static EntityManager em;
	private static EntityTransaction tx;
	private static boolean previouslyConnected;
	private static Date date;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		factory = Persistence.createEntityManagerFactory("PERSISTENCE");
		em = factory.createEntityManager();
		tx = em.getTransaction();
		previouslyConnected = false;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String login = request.getParameter("Login");
		String pass = request.getParameter("Password");
		HttpSession session = request.getSession();
		Query query = em.createQuery("select u from User u where u.login = :login and u.password = :password", User.class);
		query.setParameter("login", login);
		query.setParameter("password", pass);
		List<User> liste = query.getResultList();
		if (liste.isEmpty()) {
			request.getRequestDispatcher("error_loginMdp.jsp").forward(request, response);
		}else {
			User usr = em.find(User.class, liste.get(0).getUserID());
			log.debug(usr);
			Employee e = usr.getEmp();
			session.setAttribute("Employee", e);
			request.getRequestDispatcher("Acceuil.jsp").forward(request, response);
		}
	}

}
