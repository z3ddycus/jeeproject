package univ.service;

import univ.domain.User;

public interface UserService {
    void save(User user);
    User findByMail(String mail);
}
