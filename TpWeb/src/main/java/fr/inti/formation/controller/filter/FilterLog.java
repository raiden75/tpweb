package fr.inti.formation.controller.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Servlet Filter implementation class FilterLog
 */
@WebFilter("/*")
public class FilterLog implements Filter {
	
	private final static Log log = LogFactory.getLog(FilterLog.class);
	private ServletContext context;
    /**
     * Default constructor. 
     */
	
    public FilterLog() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		log.debug("doFilter before");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = req.getParameter(name);
			this.context.log(req.getRemoteAddr() + "::Request Params::{"+name+"="+value+"}");
		}
		
		HttpSession session = req.getSession(false);
		String uri = req.getRequestURI();
		this.context.log("Requested Resource : " + uri);
//		if(session==null && !uri.endsWith("loginServlet") || (uri.endsWith("index.html"))) {
//			this.context.log("Unauthorized connexion request !");
//			res.sendRedirect("index.html");
//		}else {
//			chain.doFilter(request, response);
//		}
		// pass the request along the filter chain
		log.debug("doFilter after");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = fConfig.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}

}
