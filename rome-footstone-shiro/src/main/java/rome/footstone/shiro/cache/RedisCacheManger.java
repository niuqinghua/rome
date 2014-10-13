package rome.footstone.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import redis.clients.jedis.Jedis;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class RedisCacheManger implements CacheManager {

    private int timeout;

    private Jedis jedis;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<K, V>(name, timeout, jedis);
    }

}
