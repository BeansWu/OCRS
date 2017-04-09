package ocrs.service;

import java.util.List;

import ocrs.entity.MessageRecord;
import ocrs.entity.User;

/**
 * @Description: TODO 用户 service 方法接口
 * @author Andrew
 * @date 2017年4月7日
 */
public interface UserService {
	//用户登录验证
	User loginCheck (String username, String password);
	//获取所有用户的消息记录
	List<MessageRecord> getAllMessageRecords ();
	//记录消息记录
	void recordMessage (MessageRecord messageRecord);
	//用户注册
	void register (User user);
}
