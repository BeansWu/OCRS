package ocrs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ocrs.entity.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		description = "防止用户跳过登陆", 
		urlPatterns = { 
				"/LoginFilter",
				"/*"
				},
		initParams = {
				@WebInitParam(name = "noLoginPath", value = "login.html;loginCheck;.js;.css;toRegister;register"), 
				@WebInitParam(name = "charset", value = "utf-8")
		}
		
)
public class LoginFilter implements Filter {

	//初始化参数
	private FilterConfig config;
	
    /**
     * Default constructor. 
     */
    public LoginFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		String noLoginPath = config.getInitParameter("noLoginPath");
		String[] noLoginPathArray = noLoginPath.split(";");
		String uri = req.getRequestURI();
		
		for (String str : noLoginPathArray) {
			if (str == null || str.equals("")) {
				continue;
			}
			//若 uri 中包含允许通过的字段，则放行
			if (uri.indexOf(str) != -1) {
				// pass the request along the filter chain
				chain.doFilter(request, response);
				return;
			}
		}
		
		//session 中不存在用户对象（未登录）则返回登陆页面
		User user = (User) session.getAttribute("user");
		if (user != null && !user.equals("")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config = fConfig;
	}

}
