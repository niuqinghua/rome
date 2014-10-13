package rome.footstone.shiro.service.impl;

import rome.footstone.shiro.User;
import rome.footstone.shiro.service.UserService;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class UserServiceDubboImpl implements UserService {

    @Override
    public User getUser(String userName) {
        User user = new User();
        user.setId("1");
        user.setName(userName);
        return user;
    }
}
