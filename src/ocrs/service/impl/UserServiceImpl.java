package ocrs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ocrs.dao.MessageRecordDao;
import ocrs.dao.UserDao;
import ocrs.entity.MessageRecord;
import ocrs.entity.User;
import ocrs.service.UserService;

/**
 * @Description: TODO 用户 service 方法实现类
 * @author Andrew
 * @date 2017年4月7日
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private MessageRecordDao messageRecordDao;
	
	@Transactional
	@Override
	public User loginCheck(String username, String password) {
		User user = userDao.findByUsername(username);
		return user;
	}
	
	@Transactional
	@Override
	public List<MessageRecord> getAllMessageRecords() {
		List<MessageRecord> messageRecords = messageRecordDao.getAllMessageRecords();
		return messageRecords;
	}

	@Transactional
	@Override
	public void recordMessage(MessageRecord messageRecord) {
		messageRecordDao.add(messageRecord);
	}

	@Transactional
	@Override
	public void register(User user) {
		userDao.add(user);
	}
}
