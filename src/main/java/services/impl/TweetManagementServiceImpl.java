package services.impl;

import dao.Impl.TweetDAOImpl;
import dao.Impl.UserDAOImpl;
import dao.TweetDAO;

import dao.UserDAO;
import model.Tweet;
import model.User;
import services.TweetManagementService;

import java.util.HashSet;
import java.util.Set;

public class TweetManagementServiceImpl implements TweetManagementService {
    private TweetDAO tweetDAO;
    private UserDAO userDAO;

    public TweetManagementServiceImpl() {
        this.tweetDAO = new TweetDAOImpl();
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void addTweet(String userLogin, String message) {
        tweetDAO.addTweet(userDAO.getUserByLogin(userLogin), message);
    }

    @Override
    public void updateTweet(Long tweetId, String message) {
        tweetDAO.updateTweet(tweetId, message);
    }

    @Override
    public void deleteTweet(Long id) {
        tweetDAO.deleteTweet(id);
    }

    @Override
    public Set<Tweet> getFollowedTweets(String userLogin) {
        Set<Tweet> followedTweets = new HashSet<>();
        Set<User> follows = userDAO.getFollows(userLogin);
        followedTweets.addAll(tweetDAO.getUserTweets(userLogin));
        follows.stream().forEach(p -> followedTweets.addAll(tweetDAO.getUserTweets(p.getLogin())));
        return followedTweets;
    }
}
