package com.ss.lms.orchestrator.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping(path="/lms/public/admin/authors}")
	public ResponseEntity<String> author() throws SQLException {
		return restTemplate.getForEntity("http://ADMIN-SERVICE/lms/public/admin/authors", String.class);
	}
}