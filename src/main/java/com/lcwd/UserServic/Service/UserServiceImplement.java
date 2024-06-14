package com.lcwd.UserServic.Service;

import com.lcwd.UserServic.Entities.Hotel;
import com.lcwd.UserServic.Entities.Rating;
import com.lcwd.UserServic.Entities.User;
import com.lcwd.UserServic.Exception.ResourceNotFound;
import com.lcwd.UserServic.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService{
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger= LoggerFactory.getLogger(UserServiceImplement.class);

    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        String userramdom = UUID.randomUUID().toString();

        user.setId(userramdom);

        User save = userRepository.save(user);
        return save;
    }

    @Override
    public List<User> getAllUser() {

        return   userRepository.findAll();

    }

    @Override
    public User getUser(String userId) {
        User userInvalid = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("Invalid username and password"));
//http://localhost:8081/user/47db419d-29e3-411d-afd6-de844fb9bff5

        // Fetch ratings of the user from the Rating service
        ArrayList<Rating> uservalue = restTemplate.getForObject("http://RatingService/review/reviewuser/" + userId, ArrayList.class);

        userInvalid.setRating(uservalue);

        return userInvalid;
    }
}
