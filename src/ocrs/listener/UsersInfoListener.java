package ocrs.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ocrs.entity.User;
import ocrs.util.MessageRecordTransform;

/**
 * Application Lifecycle Listener implementation class UsersInfoListener
 *
 */
@WebListener
public class UsersInfoListener implements ServletContextListener, HttpSessionListener {

    /**
     * Default constructor. 
     */
    public UsersInfoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	//在 session 被销毁时移除 user 对象,以及存放在 servletContext 中的相关信息
    	User user = (User) arg0.getSession().getAttribute("user");
    	arg0.getSession().removeAttribute("user");
    	if (user != null) {
    		@SuppressWarnings("unchecked")
    		List<String> usernames = (List<String>) arg0.getSession().getServletContext().getAttribute("usernames");
    		usernames.remove(user.getUsername());
    		arg0.getSession().getServletContext().setAttribute("usernames", usernames);
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//在 servletContext 初始化时创建需要的变量
    	List<String> usernames = new ArrayList<String>();
    	List<MessageRecordTransform> messageRecords = new ArrayList<MessageRecordTransform>();
    	arg0.getServletContext().setAttribute("usernames", usernames);
    	arg0.getServletContext().setAttribute("messageRecords", messageRecords);
    }

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
