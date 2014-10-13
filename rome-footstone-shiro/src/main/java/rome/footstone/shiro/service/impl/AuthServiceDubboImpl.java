package rome.footstone.shiro.service.impl;

import rome.footstone.shiro.service.AuthService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class AuthServiceDubboImpl implements AuthService {

    @Override
    public List<String> getPermissionCodes(String userName) {
        List<String> codes = new ArrayList<String>();
        codes.add("perm1");
        codes.add("perm2");
        codes.add("perm3");
        codes.add("perm4");
        codes.add("perm5");
        return codes;
    }
}
