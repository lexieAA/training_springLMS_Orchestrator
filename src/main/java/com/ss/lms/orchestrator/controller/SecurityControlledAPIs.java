package com.ss.lms.orchestrator.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.lms.orchestrator.db.UserDAO;
import com.ss.lms.orchestrator.entity.User;

@RestController
public class SecurityControlledAPIs {
    private UserDAO userDao;

    @Autowired
	RestTemplate restTemplate;
    
    public SecurityControlledAPIs(UserDAO userDao){
        this.userDao = userDao;
    }
    
    // Available to ROLE_ADMIN
    @GetMapping("admin/users")
    public List<User> users(){
        return this.userDao.findAll();
    }
    
    //------------Requests for library-----------------------
    //read all branches
    @GetMapping(path="/lms/public/library/branches")
	public ResponseEntity<String> branch1() throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/", String.class);
	}
    
    //read by branch Id
    @GetMapping("/lms/public/library/branches/branch/{branchId}")
	public ResponseEntity<String> branch2(@PathVariable Long branchId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/"+branchId, String.class);
	}
    
    // update all branches
 	@PutMapping("/lms/public/library/branches/branch/{branchId}")
 	public ResponseEntity<String> branch3(@PathVariable Long branchId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/"+branchId, String.class);
	}
    
 	// read all book copies by branchId
 	@GetMapping("/lms/public/library/branches/branch/{branchId}/bookCopies")
 	public ResponseEntity<String> copies(@PathVariable Long branchId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/"+branchId+"/bookCopies", String.class);
	}

 	// read all book copies by bookCopiesKey
 	@GetMapping("/lms/public/library/branches/branch/{branchId}/bookCopies/bookcopy/{bookId}")
 	public ResponseEntity<String> copy1(@PathVariable Long branchId,@PathVariable Long bookId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/"+branchId+"/bookCopies/bookcopy/"+bookId, String.class);
	}


 	// update number of book copies
 	@PutMapping("/lms/public/library/branches/branch/{branchId}/bookCopies/bookcopy/{bookId}")
 	public ResponseEntity<String> copy2(@PathVariable Long branchId,@PathVariable Long bookId) throws SQLException {
		return restTemplate.getForEntity("http://LIBRARY-SERVICE/lms/public/library/branches/branch/"+branchId+"/bookCopies/bookcopy/"+bookId, String.class);
	}

    
    //------------Requests for admin-----------------------

      
    /**
	 * This method handles read all requests
	 */
    @GetMapping(path = "/lms/public/authors")
	public ResponseEntity<String> aa() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/authors/id/{authorId}")
	public ResponseEntity<String> ab(@PathVariable Long authorId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/id/"+authorId, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/authors/name/{authorName}")
	public ResponseEntity<String> ac(@PathVariable String authorName) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/name/"+authorName, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/authors")
	public ResponseEntity<String> ad() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/name/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/authors")
	public ResponseEntity<String> ae() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/name/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/authors/id/{authorId}")
	public ResponseEntity<String> af(@PathVariable Long authorId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/authors/id/"+authorId, String.class);
	}

	/**
	 * This method handles read all requests
	 */
	@GetMapping(path = "/lms/public/publishers")
	public ResponseEntity<String> pa() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/publishers/id/{publisherId}")
	public ResponseEntity<String> pb(@PathVariable Long publisherId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/id/"+publisherId, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/publishers/name/{publisherName}")
	public ResponseEntity<String> pc(@PathVariable String publisherName) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/name/"+publisherName, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/publishers")
	public ResponseEntity<String> pd() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/publishers")
	public ResponseEntity<String> pe() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/publishers/id/{publisherId}")
	public ResponseEntity<String> pf(@PathVariable Long publisherId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/publishers/id/"+publisherId, String.class);
	}

	/**
	 * This method handles read all requests
	 */
	@GetMapping(path = "/lms/public/borrowers")
	public ResponseEntity<String> boa() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/borrowers/cardno/{cardNo}")
	public ResponseEntity<String> bob(@PathVariable Long cardNo) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/cardno/"+cardNo, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/borrowers/name/{borrowerName}")
	public ResponseEntity<String> boc(@PathVariable String borrowerName) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/name/"+borrowerName, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/borrowers")
	public ResponseEntity<String> bod() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/borrowers")
	public ResponseEntity<String> boe() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/borrowers/cardno/{cardNo}")
	public ResponseEntity<String> bof(@PathVariable Long cardNo) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/borrowers/cardno/"+cardNo, String.class);
	}

	/**
	 * This method handles read all requests
	 */
	@GetMapping(path = "/lms/public/genres")
	public ResponseEntity<String> ga() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/genres/id/{genreId}")
	public ResponseEntity<String> gb(@PathVariable Long genreId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/id/"+genreId, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/genres/name/{genreName}")
	public ResponseEntity<String> gc(@PathVariable String genreName) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/name/"+genreName, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/genres")
	public ResponseEntity<String> gd() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/genres")
	public ResponseEntity<String> ge() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/genres/id/{genreId}")
	public ResponseEntity<String> gf(@PathVariable Long genreId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/genres/id/"+genreId, String.class);
	}

	/**
	 * This method handles read all requests
	 */
	@GetMapping(path = "/lms/public/branches")
	public ResponseEntity<String> bra() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/branches/id/{branchId}")
	public ResponseEntity<String> brb(@PathVariable Long branchId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/id/"+branchId, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/branches/name/{branchName}")
	public ResponseEntity<String> brc(@PathVariable String branchName) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/name/"+branchName, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/branches")
	public ResponseEntity<String> brd() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/branches")
	public ResponseEntity<String> bre() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/branches/id/{branchId}")
	public ResponseEntity<String> brf(@PathVariable Long branchId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/branches/id/"+branchId, String.class);
	}

	/**
	 * This method handles read all requests
	 */
	@GetMapping(path = "/lms/public/books")
	public ResponseEntity<String> ba() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/", String.class);
	}

	/**
	 * This method handles read requests for the specified id
	 */
	@GetMapping(path = "/lms/public/books/id/{bookId}")
	public ResponseEntity<String> bb(@PathVariable Long bookId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/id/"+bookId, String.class);
	}

	/**
	 * This method handles read requests for the specified name
	 */
	@GetMapping(path = "/lms/public/books/title/{title}")
	public ResponseEntity<String> bc(@PathVariable String title) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/title/"+title, String.class);
	}

	/**
	 * This method handles update requests for the specified id
	 */
	@PutMapping(path = "/lms/public/books")
	public ResponseEntity<String> bd() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/", String.class);
	}

	/**
	 * This method handles post requests for the passed object type. This case is
	 * for the creation of a new object. The primary key value should be null in the
	 * passed request object.
	 */
	@PostMapping(path = "/lms/public/books")
	public ResponseEntity<String> be() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/", String.class);
	}

	/**
	 * This method handles delete requests for the specified id.
	 */
	@DeleteMapping(path = "/lms/public/books/id/{bookId}")
	public ResponseEntity<String> bf(@PathVariable Long bookId) {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/books/id/"+bookId, String.class);
	}

	/**
	 * This method takes an open loan and extends its due date by 7 days
	 */
	@PutMapping(path = "/lms/public/loans")
	public ResponseEntity<String> la() {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/loans/", String.class);
	}
}