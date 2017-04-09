package ocrs.entity;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

/**
 * @Description: TODO 用户详细信息嵌入类
 * @author Andrew
 * @date 2017年4月6日
 */
@Embeddable
public class UserDetail {
	private String gender;
	private Integer age;
	private String city;
	private String hobbies;
	@Type(type="text")
	private String introduction;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDetail(String gender, Integer age, String city, String hobbies, String introduction) {
		this.gender = gender;
		this.age = age;
		this.city = city;
		this.hobbies = hobbies;
		this.introduction = introduction;
	}
}
