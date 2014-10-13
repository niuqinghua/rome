package rome.footstone.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import rome.footstone.shiro.User;
import rome.footstone.shiro.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class UserFilter extends AccessControlFilter {

    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            return true;
        } else {
            AuthenticationToken token = createToken(request, response);
            subject.login(token);
            return true;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        redirectToLogin(request, response);
        return false;
    }

    private AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userName = httpServletRequest.getParameter("username");
        User user = userService.getUser(userName);
        return new UserAuthToken(user);
    }
}
