package ocrs.dao;

import ocrs.entity.User;

/**
 * @Description: TODO 用户 DAO 方法接口
 * @author Andrew
 * @date 2017年4月6日
 */
public interface UserDao {
	//添加用户
	void add (User user);
	//根据用户名查找用户
	User findByUsername(String username);
}
