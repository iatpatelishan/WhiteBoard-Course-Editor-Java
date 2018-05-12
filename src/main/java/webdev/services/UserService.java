package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.User;
import webdev.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PutMapping("/{userId}")
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

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }

}
