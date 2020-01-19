package dao;

import model.User;

import java.util.Set;

public interface UserDAO {

    void saveUser(User user);

     void deleteUser(Long userId);

     User getUserByLogin(String login);

     User getUserByEmail(String email);

     Set<User> getFollows(String login); // kto śledzi User'a

     Set<User> getFollowers(String login); // kogo User śledzi

     Set<User> getNotFollowedUsers(String login);


}
