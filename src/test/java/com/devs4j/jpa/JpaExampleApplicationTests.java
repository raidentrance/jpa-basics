package com.devs4j.jpa;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devs4j.jpa.entities.User;
import com.devs4j.jpa.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaExampleApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
		
	
	@Test
	public void contextLoads() {
		Iterable<User> users = userRepository.findAll();
		assertNotNull(users);
	}

}
