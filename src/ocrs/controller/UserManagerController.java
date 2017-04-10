package ocrs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import ocrs.entity.MessageRecord;
import ocrs.entity.User;
import ocrs.entity.UserDetail;
import ocrs.service.UserService;
import ocrs.util.MessageRecordTransform;

/**
 * @Description: TODO 用户管理
 * @author Andrew
 * @date 2017年4月7日
 */
@Controller
@RequestMapping("/user")
public class UserManagerController {
	
	@Autowired
	private UserService userService;
	
	//登陆验证
	@ResponseBody
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public int loginCheck (String username, String password, HttpServletRequest request) throws IOException {
		User user = userService.loginCheck(username, password);
		//从 servletContext 获取用户名的集合
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) request.getServletContext().getAttribute("usernames");
		int result;
		if (user == null || !password.equals(user.getPassword())) {
			result = 0;
		} else {
			User userTemp = (User) request.getSession().getAttribute("user");
			//防止用户重复登陆
			if (userTemp != null && username.equals(userTemp.getUsername())) {
				result = 1;
			} else if(usernames != null && usernames.contains(username)) {
				result = 1;
			} else {
				result = 2;
				request.getSession().setAttribute("user", user);
				if (usernames == null) {
					//若用户名集合不存在则新建
					usernames = new ArrayList<String>();
				}
				//添加新登陆的用户名到集合
				if (!usernames.contains(username)) {
					usernames.add(username);
				}
				//更新 servletContext 中的用户名集合
				request.getServletContext().setAttribute("usernames", usernames);
			}
		}
		return result;
	}
	
	//登陆
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView login (HttpServletRequest request) {
		ModelAndView modelAdnView = new ModelAndView("index");
		return modelAdnView;
	}
	
	//用户登录成功后获得自己的信息
	@ResponseBody
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET, produces="application/json")
	public User getUserInfo (HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		return user;
	}
	
	//聊天
	@ResponseBody
	@RequestMapping(value = "/chat", method = RequestMethod.GET, produces="application/json")
	public List<MessageRecordTransform> chat (String username, String content, Date date, HttpServletRequest request) throws JsonProcessingException {
		//将要保存到数据库中的实体类对象
		MessageRecord messageRecord = new MessageRecord(date, content);
		//将要放到 servlet 的全局变量中的临时对象
		MessageRecordTransform messageRecordTransform= new MessageRecordTransform(username, content, date);
		//从会话中找到 user 对象
		User user = (User)request.getSession().getAttribute("user");
		//建立级联关系
		messageRecord.setUser(user);
		//将消息记录保存到数据库
		userService.recordMessage(messageRecord);
		//获得全局变量中的消息记录集合
		@SuppressWarnings("unchecked")
		List<MessageRecordTransform> messageRecords = (List<MessageRecordTransform>) request.getServletContext().getAttribute("messageRecords");
		//若不存在则新建
		if (messageRecords == null) {
			messageRecords = new ArrayList<MessageRecordTransform>();
		}
		//添加新的消息记录
		messageRecords.add(messageRecordTransform);
		//将更新后的消息记录集合再保存到 servletContext 中
		request.getServletContext().setAttribute("messageRecords", messageRecords);
		return messageRecords;
	}
	
	//定时刷新消息记录
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces="application/json")
	public List<MessageRecordTransform> updateMessageContent (HttpServletRequest request) throws JsonProcessingException {
		//获得全局变量中的消息记录集合
		@SuppressWarnings("unchecked")
		List<MessageRecordTransform> messageRecords = (List<MessageRecordTransform>) request.getServletContext().getAttribute("messageRecords");
		//若不存在则新建
		if (messageRecords == null) {
			messageRecords = new ArrayList<MessageRecordTransform>();
		}
		return messageRecords;
	}
	
	//定时刷新在线人员信息
	@ResponseBody
	@RequestMapping(value = "/updateOnlineUserInfo", method = RequestMethod.GET, produces="application/json")
	public List<String> updateOnlineUserInfo (HttpServletRequest request) throws JsonProcessingException {
		//获得全局变量中的用户名集合
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) request.getServletContext().getAttribute("usernames");
		return usernames;
	}
	
	//跳转到用户注册界面
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET)
	public ModelAndView toRegister() {
		ModelAndView modelAdnView = new ModelAndView("register");
		return modelAdnView;
	}
	
	//用户注册
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Integer register(HttpServletRequest request) {
		//获取用户信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String[] hobbies = request.getParameterValues("hobbies[]");
		StringBuilder hobbiesStr = new StringBuilder();
		if (hobbies != null) {
			for (String hobby : hobbies) {
				hobbiesStr.append(hobby + ";");
			}
		}
		String introduction = request.getParameter("introduction");
		//新建用户对象
		User user = new User(username, password, new UserDetail(gender, Integer.parseInt(age), city, hobbiesStr.toString(), introduction));
		//将用户信息保存到数据库
		userService.register(user);
		//将用户对象信息放到 session 中
		request.getSession().setAttribute("user", user);
		//从 servletContext 获取用户名的集合
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) request.getServletContext().getAttribute("usernames");
		if (usernames == null) {
			//若用户名集合不存在则新建
			usernames = new ArrayList<String>();
		}
		//添加新登陆的用户名到集合
		if (!usernames.contains(username)) {
			usernames.add(username);
		}
		//更新 servletContext 中的用户名集合
		request.getServletContext().setAttribute("usernames", usernames);
		return 1;
	}
	
	//用户退出
	@RequestMapping(value = "/quit", method = RequestMethod.GET)
	public void quit (HttpServletRequest request, HttpServletResponse response) throws IOException {
		//将 session 中的用户对象清除
		User user = (User) request.getSession().getAttribute("user");
		String username = user.getUsername();
		request.getSession().removeAttribute("user");
		//获得全局变量中的用户名集合
		@SuppressWarnings("unchecked")
		List<String> usernames = (List<String>) request.getServletContext().getAttribute("usernames");
		usernames.remove(username);
		response.sendRedirect(request.getContextPath() + "/login.html");
	}
	
	//直接通过 uri 访问时跳转到登陆页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void returnToLogin (HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login.html");
	}
}
