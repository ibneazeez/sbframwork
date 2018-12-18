package hello.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.entities.User;
import hello.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method for getting all users
     *
     * @return List of user objects.
     */
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Method for getting e-mail by username (key)
     *
     * @param username - provided username
     * @return e-mail
     */
    public String findEmailByUsername(String username)
    {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getEmail();
        }
        return null;
    }
    
    
    /**
     * Method for getting mobile by username (key)
     *
     * @param username - provided username
     * @return mobilenumber
     */
    public String findMobileByUsername(String username)
    {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getMobile();
        }
        return null;
    }
}
