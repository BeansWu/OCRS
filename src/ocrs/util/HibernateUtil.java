package ocrs.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: TODO Hibernate 工具类
 * @author Andrew
 * @date 2017年3月28日
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	private static ClassPathXmlApplicationContext ctx;
	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory = ctx.getBean(SessionFactory.class);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession (){
		session = sessionFactory.getCurrentSession();
		return session;
	}

	public static void closeSession (){
		if (session != null) {
			session.close();
		}
	}
}
