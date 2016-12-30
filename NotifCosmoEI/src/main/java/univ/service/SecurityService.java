package univ.service;


public interface SecurityService {
    String findLoggedInMail();
    void autologin(String username, String password);
}