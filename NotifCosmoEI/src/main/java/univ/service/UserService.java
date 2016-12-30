package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import univ.domain.User;
import univ.repository.RoleRepository;
import univ.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        if (user.getRole() == null) {
            user.setRole(roleRepository.findOneByName("USER"));
        }
        userRepository.save(user);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return userRepository.findByMail(((UserDetails)principal).getUsername());
        } else {
            return userRepository.findByMail(principal.toString());
        }

    }

    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }
}