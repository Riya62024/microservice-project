package com.lcwd.UserServic.Controller;

import com.lcwd.UserServic.Entities.User;
import com.lcwd.UserServic.Service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //Create
   // http://localhost:8081/user
    @PostMapping()
    public ResponseEntity<User> CreateUser(@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    //singleuser get

    //http://localhost:8081/user/?userId=1
    @GetMapping("/{userId}")
  //  @CircuitBreaker(name= "comment Breaker" , fallbackMethod= "commentFallback")

    @CircuitBreaker(name= "commentBreaker" , fallbackMethod= "commentFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> commentFallback(String userId, Exception ex)
    {
        System.out.println("fallback is executed becouse service is down :" +ex.getMessage());
        ex.printStackTrace();
        User user= new User();
        user.setId("123");
        user.setName("Service down");
        user.setEmail("Service down");
        user.setAbout("Service down");
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    //all user get
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
