package com.ss.lms.orchestrator.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ss.lms.orchestrator.entity.User;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
	private UserDAO userDao;
	private PasswordEncoder passwordEncoder;

	public DbInit(UserDAO userDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		// Delete all
		this.userDao.deleteAll();

		// Create users
		User dan = new User("tom", passwordEncoder.encode("123"), "LIBRARY",
				"ACCESS_BRANCH1, ACCESS_BRANCH2, ACCESS_BRANCH3, ACCESS_COPIES,ACCESS_COPY1,ACCESS_COPY2");
		User admin = new User("kim", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_USERS, ACCESS_ADMIN");
		User manager = new User("borrower", passwordEncoder.encode("borrower123"), "BORROWER", "ACCESS_USERS");

		List<User> users = Arrays.asList(dan, admin, manager);

		// Save to database
		this.userDao.saveAll(users);
	}
}