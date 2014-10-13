package rome.footstone.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import redis.clients.jedis.Jedis;
import rome.footstone.shiro.util.SerializeUtil;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class RedisSessionDao extends AbstractSessionDAO {

    private String prefix;

    private int timeout;

    private Jedis jedis;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String key = getKey(sessionId);
        jedis.setex(SerializeUtil.serialize(key), timeout, SerializeUtil.serialize(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String key = getKey(sessionId);
        Object object = jedis.get(key);
        if(object == null) {
            return null;
        }
        return (Session) object;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String key = getKey(session.getId());
        jedis.setex(SerializeUtil.serialize(key), timeout, SerializeUtil.serialize(session));
    }

    @Override
    public void delete(Session session) {
        String key = getKey(session.getId());
        jedis.expire(key, 0);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

    private String getKey(Serializable sessionId) {
        return prefix + sessionId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
