package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.exception.RestConflictException;
import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    @PostMapping("/api/register")
    @ResponseBody
    public User register(@RequestBody User user, HttpServletResponse response) {
        if(findUserByUsername(user.getUsername()).isPresent()){
            System.out.println("User Already Exists");
            throw new RestConflictException("User Already Exists");
        } else {
            return userRepository.save(user);
        }
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        return null;
    }


    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
        Optional<User> data = userRepository.findById(userId);
        if (data.isPresent()) {
            User user = data.get();
            if(newUser.getUsername()!=null){
                user.setUsername(newUser.getUsername());
            }
            if(newUser.getPassword()!=null){
                user.setPassword(newUser.getPassword());
            }
            if(newUser.getFirstName()!=null){
                user.setFirstName(newUser.getFirstName());
            }
            if(newUser.getLastName()!=null){
                user.setLastName(newUser.getLastName());
            }
            if(newUser.getPhone()!=null){
                user.setPhone(newUser.getPhone());
            }
            if(newUser.getEmail()!=null){
                user.setEmail(newUser.getEmail());
            }
            if(newUser.getRole()!=null){
                user.setRole(newUser.getRole());
            }
            if(newUser.getDateOfBirth()!=null){
                user.setDateOfBirth(newUser.getDateOfBirth());
            }
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }

}
