package ocrs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
/**
 * @Description: TODO 讨论组实体类
 * @author Andrew
 * @date 2017年4月6日
 */
@Component
@Entity
@Table(name="discussion_group")
public class DiscussionGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	//讨论组与用户为多对多关系，将主控方交给用户来控制
	@ManyToMany(mappedBy="discussionGroups")
	private List<User> members = new ArrayList<User>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
}
