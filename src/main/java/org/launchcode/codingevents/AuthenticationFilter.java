package org.launchcode.codingevents;

import org.launchcode.codingevents.controllers.AuthenticationController;
import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whiteList = Arrays.asList("/login", "/register", "/logout", "/css");

    private static boolean isWhiteListed(String path){
        for(String pathRoot : whiteList){
            if(path.startsWith(pathRoot)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        //Don't require sign in for whitelisted pages
        if(isWhiteListed(request.getRequestURI())){
            return true;
        }
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        //user logged in
        if(user!=null){
            return true;
        }
        //user not logged in
        response.sendRedirect("/login");
        return false;

    }

}
