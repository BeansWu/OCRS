package ocrs.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ocrs.dao.UserDao;
import ocrs.entity.User;
import ocrs.util.HibernateUtil;
/**
 * @Description: TODO 用户 DAO 方法实现类
 * @author Andrew
 * @date 2017年4月6日
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Override
	public void add(User user) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	@Override
	public User findByUsername(String username) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		User user = session.createQuery("select new User(u.account as account, u.username as username, u.password as password) " + 
				"from User u " + "where u.username =:username", User.class).setParameter("username", username).uniqueResult();
		tx.commit();
		return user;
	}
}
