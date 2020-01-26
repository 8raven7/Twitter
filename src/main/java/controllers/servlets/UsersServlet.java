package controllers.servlets;

import controllers.utils.ServletsUtils;
import model.User;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static controllers.utils.ServletsUtils.FOLLOWED_USERS;
import static controllers.utils.ServletsUtils.NOT_FOLLOWED_USERS;

@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    private UserManagementService service;

    @Override
    public void init() throws ServletException {
        service = new UserManagementServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = ServletsUtils.getUserLoginFromSession(req);
        Set<User> followedUsers = service.getFollowedUsers(login);
        Set<User> notFollowedUsers = service.getNotFollowedUsers(login);
        req.setAttribute(FOLLOWED_USERS, followedUsers);
        req.setAttribute(NOT_FOLLOWED_USERS, notFollowedUsers);
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
