package rome.footstone.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import redis.clients.jedis.Jedis;
import rome.footstone.shiro.util.SerializeUtil;

import java.util.Collection;
import java.util.Set;

/**
 * Created by niuqinghua on 2014/9/17.
 */
public class RedisCache<K,V> implements Cache<K,V> {

    private String prefix;

    private int timeout;

    private Jedis jedis;

    public RedisCache(String prefix, int timeout, Jedis jedis) {
        this.prefix = prefix;
        this.timeout = timeout;
        this.jedis = jedis;
    }

    @Override
    public V get(K key) throws CacheException {
        try {
            byte[] bytes = jedis.get(SerializeUtil.serialize(getKey(key)));
            if(bytes == null) {
                return null;
            }
            return (V) SerializeUtil.deSeialize(bytes);
        } catch (Exception e) {
            throw new CacheException("获取数据异常", e);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        try{
            jedis.setex(SerializeUtil.serialize(key), timeout, SerializeUtil.serialize(value));
            return value;
        } catch (Exception e) {
            throw new CacheException("更新数据异常", e);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        try{
            V value = get(key);
            jedis.expire(getKey(key), 0);
            return value;
        } catch (Exception e) {
            throw new CacheException("删除数据异常", e);
        }
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private String getKey(K key) {
        return prefix + key;
    }
}
