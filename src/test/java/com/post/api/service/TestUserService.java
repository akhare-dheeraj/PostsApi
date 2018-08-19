package com.post.api.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.posts.api.beans.User;
import com.posts.api.service.UserService;

@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserService {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testPersistUser() {
		User user = new User("Dheeraj", 27, 'M');
		userService.persistUser(user);
		Assert.assertNotNull(user.getUserId());
	}
	
	@Test
	public void testUpdateUser() {
		User user = new User("User 1", 34, 'F');
		userService.persistUser(user);
		user.setUserName("User 2");
		userService.updateUser(user);
		Assert.assertEquals(user.getUserName(), "User 2");
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User("Username", 22, 'M');
		userService.persistUser(user);
		Long id = user.getUserId();
		userService.deleteUser(user.getUserId());
		Assert.assertNull(userService.getUser(id));
	}
	
	@Test
	public void testGetUser() {
		User user = new User("Username", 22, 'M');
		userService.persistUser(user);
		Assert.assertNotNull(userService.getUser(user.getUserId()));
	}
	
	@Test
	public void testGetAllUsers() {
		User u1 = new User("Username", 22, 'M');
		User u2 = new User("Username", 22, 'M');
		
		userService.persistUser(u1);
		userService.persistUser(u2);
		List<User> allUsers = userService.getAllUsers();
		Assert.assertTrue(allUsers!=null && allUsers.size()>=2);
	}
}
