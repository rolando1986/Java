package ttps.filter;

//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import ttps.persistence.model.user.impl.UsuarioImpl;
//
//@WebFilter(
//		filterName = "security",
//		urlPatterns = "/secured/*"
//	)
//public class SecurityFilter implements Filter {
//	private final String AUTHORIZATION = "authorization";
//
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

//		HttpServletRequest request = (HttpServletRequest) req;
//		String authHeader = request.getHeader(AUTHORIZATION);
//		HttpSession session = request.getSession();
//		boolean validAccess = false;
//		if(authHeader != null && session != null && session.getAttribute(AUTHORIZATION) != null){
//			 String autValue = (String) session.getAttribute(AUTHORIZATION);
//			 UsuarioImpl user = (UsuarioImpl) session.getAttribute("activeUser");
//			 if(authHeader.equals(autValue) && request.getRequestURI().contains(user.getClass().getName()))
//			 {
//				 validAccess= true;
//			 }
//		}
//
//		if(!validAccess)
//		{
//			HttpServletResponse response = (HttpServletResponse) res;
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//		}
//		else
//			chain.doFilter(req, res);
//	}
//
//	public void init(FilterConfig filterConfig) {}
//
//	public void destroy() {}
//
//}