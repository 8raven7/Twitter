package controllers.servlets;

import controllers.utils.ServletsUtils;
import services.UserManagementService;
import services.impl.UserManagementServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UnFollowServlet", value = "/unfollow")
public class UnFollowServlet extends HttpServlet {

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
        String currentUserLogin = ServletsUtils.getUserLoginFromSession(req);
        String userLoginToUnfollow = req.getParameter(ServletsUtils.USER_LOGIN_TO_STOP_FOLLOW);
        service.stopFollowing(currentUserLogin, userLoginToUnfollow);
        req.getRequestDispatcher("users").forward(req, resp);
    }

}
