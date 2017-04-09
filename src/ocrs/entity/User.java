package ocrs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @Description: TODO 用户实体类
 * @author Andrew
 * @date 2017年4月6日
 */
@Component
@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer account;
	@Column(length=20, nullable=false)
	private String username;
	@Column(length=40, nullable=false)
	private String password;
	private UserDetail userDetail = new UserDetail();
	//一个用户对应多条消息记录
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, targetEntity=MessageRecord.class, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<MessageRecord> messageRecords = new ArrayList<MessageRecord>();
	//多对多
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<DiscussionGroup> discussionGroups = new ArrayList<DiscussionGroup>();
	public Integer getAccount() {
		return account;
	}
	public void setAccount(Integer account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	public List<MessageRecord> getMessageRecords() {
		return messageRecords;
	}
	public void setMessageRecords(List<MessageRecord> messageRecords) {
		this.messageRecords = messageRecords;
	}
	public List<DiscussionGroup> getDiscussionGroups() {
		return discussionGroups;
	}
	public void setDiscussionGroups(List<DiscussionGroup> discussionGroups) {
		this.discussionGroups = discussionGroups;
	}
	public User() {
		super();
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(Integer account, String username, String password) {
		this.account = account;
		this.username = username;
		this.password = password;
	}
	public User(Integer account, String username, String password, UserDetail userDetail,
			List<MessageRecord> messageRecords, List<DiscussionGroup> discussionGroups) {
		this.account = account;
		this.username = username;
		this.password = password;
		this.userDetail = userDetail;
		this.messageRecords = messageRecords;
		this.discussionGroups = discussionGroups;
	}
	public User(String username, String password, UserDetail userDetail) {
		super();
		this.username = username;
		this.password = password;
		this.userDetail = userDetail;
	}
}
