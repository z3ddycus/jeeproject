package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import univ.domain.User;
import univ.repository.RoleRepository;
import univ.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        if (user.getRole() == null) {
            user.setRole(roleRepository.findOneByName("USER"));
        }
        userRepository.save(user);
    }

    @Override
    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }
}