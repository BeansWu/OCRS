package ocrs.util;

import java.util.Date;

/**
 * @Description: TODO 将消息记录转换为 用户名 + 内容 + 时间 的形式，解决 JSON 传输时的问题（一对多双向映射导致指针指来指去）
 * @author Andrew
 * @date 2017年4月8日
 */
public class MessageRecordTransform {
	private String username;
	private String content;
	private Date date;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MessageRecordTransform(String username, String content, Date date) {
		super();
		this.username = username;
		this.content = content;
		this.date = date;
	}
}
