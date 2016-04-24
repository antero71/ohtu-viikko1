package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationService {

    private UserDao userDao;

    @Autowired
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

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password


        if(password.length()<8)
            return true;
        
        
        //Pattern p = Pattern.compile("[\\x20-\\x7E]{8,}");
        Pattern p = Pattern.compile("[a-zA-Z]{8,}");
        Matcher m = p.matcher(password);
        boolean b = m.matches();
        
        System.out.println(password+" matches "+b);
        
     
        
        Pattern p2 = Pattern.compile("[a-z]{3,}");
        Matcher m2 = p2.matcher(username);
        boolean b2 = m2.matches();
        
        return b || !b2;

    }
}
