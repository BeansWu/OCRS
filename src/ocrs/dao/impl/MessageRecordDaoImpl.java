package ocrs.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ocrs.dao.MessageRecordDao;
import ocrs.entity.MessageRecord;
import ocrs.util.HibernateUtil;
/**
 * @Description: TODO 消息记录 dao 方法的实现类
 * @author Andrew
 * @date 2017年4月7日
 */
@Repository("messageRecordDao")
public class MessageRecordDaoImpl implements MessageRecordDao {
	
	@Override
	public void add(MessageRecord messageRecord) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(messageRecord);
		session.getTransaction().commit();
	}

	@Override
	public List<MessageRecord> getAllMessageRecords() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		String hql = "from MessageRecord mr";
		List<MessageRecord> messageRecords = session.createQuery(hql, MessageRecord.class).list();
		return messageRecords;
	}
}
