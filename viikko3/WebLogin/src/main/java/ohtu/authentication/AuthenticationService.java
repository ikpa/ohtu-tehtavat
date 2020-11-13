package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()< 3 ) {
            status.addError("username should have at least 3 characters");
        }
        
        if (password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }
        
        char[] userchars = username.toCharArray();
        char[] passchars = password.toCharArray();
        
        
        for (Character c : userchars) {
            if (!Character.isLetter(c)) {
                status.addError("username should only contain letters");
            }
        }
        
        int numofalpha = 0;
        for (Character c : passchars) {
            if (Character.isLetter(c)) {
                numofalpha++;
            }
        }
        
        if (numofalpha == passchars.length) {
            status.addError("password should not only contain letters");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
