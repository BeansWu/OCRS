package ocrs.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ocrs.dao.MessageRecordDao;
import ocrs.dao.UserDao;
import ocrs.entity.MessageRecord;
import ocrs.entity.User;

public class TestDao {

	static ClassPathXmlApplicationContext ctx = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testAdd() {
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = new User();
		user.setUsername("Tom");
		user.setPassword("123456");
		user.getUserDetail().setGender("男");
		user.getUserDetail().setAge(20);
		userDao.add(user);
	}
	
	@Test
	public void testFindByUsername() {
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = userDao.findByUsername("Andrew");
		System.out.println(user.getPassword());
	}
	
	@Test
	public void testInsertMessage() {
		MessageRecordDao mrd = ctx.getBean(MessageRecordDao.class);
		MessageRecord mr = new MessageRecord();
		UserDao userDao = ctx.getBean(UserDao.class);
		User user = userDao.findByUsername("Tom");
		mr.setUser(user);
		mr.setContent("只是近黄昏");
		mr.setDate(new Date());
		mrd.add(mr);
	}
	@Test
	public void testFindALlMessages() {
		MessageRecordDao mrd = ctx.getBean(MessageRecordDao.class);
		List<MessageRecord> mrs = mrd.getAllMessageRecords();
		for (MessageRecord mr : mrs) {
			System.out.println(mr.getContent());
		}
	}
}
