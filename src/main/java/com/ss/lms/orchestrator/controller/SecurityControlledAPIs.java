package com.ss.lms.orchestrator.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.db.UserDAO;
import com.ss.lms.orchestrator.entity.BookCopies;
import com.ss.lms.orchestrator.entity.BookCopiesKey;
import com.ss.lms.orchestrator.entity.User;
import com.ss.lms.orchestrator.entity.BookLoansKey;
import com.ss.lms.orchestrator.entity.LibraryBranch;

@RestController
public class SecurityControlledAPIs {
	private UserDAO userDao;

	@Autowired
	RestTemplate restTemplate;

	public SecurityControlledAPIs(UserDAO userDao) {
		this.userDao = userDao;
	}

	// Available to ROLE_ADMIN
	@GetMapping("admin/users")
	public List<User> users() {
		return this.userDao.findAll();
	}

	// ------------Requests for library-----------------------
	// read all branches
	@GetMapping(path = "/lms/public/library/branches")
	public ResponseEntity<String> branch1() throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/", String.class);
	}

	// read by branch Id
	@GetMapping("/lms/public/library/branches/branch/{branchId}")
	public ResponseEntity<String> branch2(@PathVariable Long branchId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/" + branchId,
				String.class);
	}

	// update all branches
	@PutMapping("/lms/public/library/branches/branch/{branchId}")
	public void branch3(@RequestBody LibraryBranch libraryBranch, @PathVariable Long branchId) throws SQLException {
		restTemplate.put("http://LIBRARY-SERVICE/lms/public/library/branches/branch/" + branchId, libraryBranch,
				String.class);
	}

	// read all book copies by branchId
	@GetMapping("/lms/public/library/branches/branch/{branchId}/bookCopies")
	public ResponseEntity<String> copies(@PathVariable Long branchId) throws SQLException {
		return restTemplate.getForEntity(
				"http://LIBRARY-SERVICE/lms/public/library/branches/branch/" + branchId + "/bookCopies", String.class);
	}

	// read all book copies by bookCopiesKey
	@GetMapping("/lms/public/library/branches/branch/{branchId}/bookCopies/bookcopy/{bookId}")
	public ResponseEntity<String> copy1(@PathVariable Long branchId, @PathVariable Long bookId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/" + branchId
				+ "/bookCopies/bookcopy/" + bookId, String.class);
	}

	// update number of book copies
	@PutMapping("/lms/public/library/branches/branch/{branchId}/bookCopies/bookcopy/{bookId}")
	public void copy2(@RequestBody BookCopies bookCopies, @PathVariable Long branchId, @PathVariable Long bookId)
			throws SQLException {
		restTemplate.put("http://LIBRARY-SERVICE/lms/public/library/branches/branch/" + branchId
				+ "/bookCopies/bookcopy/" + bookId, bookCopies, String.class);
	}

	/// ------------ Requests for Borrower --------------------------------

	@GetMapping(path = "/lms/public/borrower/borrowers")
	public ResponseEntity<String> b1() throws SQLException {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/borrowers", String.class);
	}

	@GetMapping(path = "/lms/public/borrower/borrowers/{cardNo}")
	public ResponseEntity<String> b2(@PathVariable Long cardNo) throws SQLException {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/borrowers/" + cardNo,
				String.class);
	}

	@GetMapping(path = "/lms/public/borrower/borrowers/{cardNo}/loans")
	public ResponseEntity<String> b3(@PathVariable Long cardNo) {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/borrowers/" + cardNo + "/loans",
				String.class);
	}

	@GetMapping(path = "/lms/public/borrower/branches")
	public ResponseEntity<String> b4() {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/branches", String.class);
	}

	@GetMapping(path = "/lms/public/borrower/branches/{branchId}")
	public ResponseEntity<String> b5(@PathVariable Long branchId) {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/branches/" + branchId,
				String.class);
	}

	@GetMapping(path = "/lms/public/borrower/branches/{branchId}/books")
	public ResponseEntity<String> b6(@PathVariable Long branchId) {
		return restTemplate.getForEntity("http://BORROWER-SERVICE/lms/public/borrower/branches/" + branchId + "/books",
				String.class);
	}

	@PutMapping(path = "/lms/public/borrower/borrowers/{cardNo}/loans/return")
	public ResponseEntity<String> b7(@RequestBody BookLoansKey id, @PathVariable Long cardNo) {
		return restTemplate.postForEntity(
				"http://BORROWER-SERVICE/lms/public/borrower/borrowers/" + cardNo + "/loans/return", id, String.class);
	}

	@PostMapping(path = "/lms/public/borrower/borrowers/{cardNo}/borrow")
	public ResponseEntity<String> b8(@RequestBody BookCopiesKey id, @PathVariable Long cardNo) {
		return restTemplate.postForEntity("http://BORROWER-SERVICE/lms/public/borrower/borrowers/" + cardNo + "/borrow",
				id, String.class);
	}

}
