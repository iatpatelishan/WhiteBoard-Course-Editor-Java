package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import webdev.exception.RestConflictException;
import webdev.exception.RestNotFoundException;
import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping()
public class UserService {

    private UserRepository userRepository;
    private JavaMailSender emailSender;

    @Autowired
    UserService(UserRepository userRepository, JavaMailSender emailSender) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @Async
    @GetMapping("/api/forgotpassword/{username}")
    public String forgotPassword(@PathVariable("username") String username, HttpServletRequest request){
        User user = userRepository.findUserByUsername(username).orElse(null);
        if(user!=null && user.getEmail()!=null) {
            String appUrl = request.getScheme() + "://" + request.getServerName();

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            try {
                helper.setTo(user.getEmail());
                helper.setText("To reset account password, please click the link below:\n"
                        + appUrl + "/jquery/components/login/login.template.client.html?reset="+user.getId()+" \n\n\n");
                helper.setSubject("Password Reset - CS5610 - IshanPatel");
                helper.setFrom("cs5610@musixplayer.com");
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("Error Sending Email");
            }
            emailSender.send(message);
        }
        return "{}";
    }

    @GetMapping("/api/findby/username/{username}")
    public Optional<User> findUserByUsername(@PathVariable("username") String username){
        return userRepository.findUserByUsername(username);
    }

    @PostMapping("/api/register")
    @ResponseBody
    public User register(@RequestBody User user, HttpServletResponse response) {
        if(findUserByUsername(user.getUsername()).isPresent()){
            throw new RestConflictException("User Already Exists");
        } else {
            return userRepository.save(user);
        }
    }

    @PostMapping("/api/login")
    public int login(@RequestBody User user, HttpSession session) {
        if(!findUserByUsername(user.getUsername()).isPresent()){
            throw new RestNotFoundException("Username doesnt exist!");
        }
        if(!userRepository.findUserByCredentials(user.getUsername(),user.getPassword()).isPresent()){
            throw new RestNotFoundException("Invalid Password!");
        }
        return 1;
    }


    @GetMapping("/api/user")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new RestNotFoundException("Invalid User");
        }
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
            return findUserById(user.getId());
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int id) {
        userRepository.deleteById(id);
    }

}
