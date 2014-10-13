package rome.footstone.shiro.service;

import java.util.List;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public interface AuthService {

    List<String> getPermissionCodes(String userName);
}
