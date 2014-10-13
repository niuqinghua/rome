package rome.footstone.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import rome.footstone.shiro.User;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class UserAuthToken implements AuthenticationToken {

    private User user;

    public UserAuthToken() {

    }

    public UserAuthToken(User user) {
        this.user = user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
