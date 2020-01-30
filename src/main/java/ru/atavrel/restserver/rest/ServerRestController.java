package ru.atavrel.restserver.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.atavrel.restserver.dao.RoleRepository;
import ru.atavrel.restserver.dao.UserRepository;
import ru.atavrel.restserver.model.Role;
import ru.atavrel.restserver.model.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServerRestController {


    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // GET: http://localhost:8075/api/users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    //GET: http://localhost:8075/api/users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //GET: http://localhost:8075/api/users/email/{email}
    @GetMapping("/users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //POST: http://localhost:8075/api/users/
    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    //PUT: http://localhost:8075/api/users/
    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user) {
        User userFromDB = userRepository.findById(user.getId()).orElse(null);
        if (userFromDB == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE: http://localhost:8075/api/users/{id}
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // GET: http://localhost:8075/api/roles
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<> (roleRepository.findAll(), HttpStatus.OK);
    }
}
