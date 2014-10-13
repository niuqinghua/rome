package rome.footstone.shiro.service.impl;

import org.apache.cxf.jaxrs.client.WebClient;
import rome.footstone.shiro.User;
import rome.footstone.shiro.service.UserService;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class UserServiceCXFImpl implements UserService {

    private WebClient webClient;

    @Override
    public User getUser(String userName) {
        User user = new User();
        user.setId("1");
        user.setName(userName);
        return user;
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
