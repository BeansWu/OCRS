package ocrs.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
/**
 * @Description: TODO 消息记录实体类
 * @author Andrew
 * @date 2017年4月6日
 */
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="message_record")
public class MessageRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Date date;
	@Type(type="text")
	private String content;
	//多条消息记录对应一个用户
	@ManyToOne(targetEntity=User.class, cascade=CascadeType.ALL)
	@JoinColumn(name="user_account")
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MessageRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageRecord(Date date, String content) {
		this.date = date;
		this.content = content;
	}
	@Override
	public String toString() {
		return "MessageRecord [id=" + id + ", date=" + date + ", content=" + content + ", user=" + user + "]";
	}
}
