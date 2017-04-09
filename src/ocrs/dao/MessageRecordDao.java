package ocrs.dao;

import java.util.List;

import ocrs.entity.MessageRecord;

/**
 * @Description: TODO 消息记录 dao 方法的接口
 * @author Andrew
 * @date 2017年4月7日
 */
public interface MessageRecordDao {
	//添加消息记录
	void add(MessageRecord messageRecord);
	//获取所有的消息记录
	List<MessageRecord> getAllMessageRecords ();
}
