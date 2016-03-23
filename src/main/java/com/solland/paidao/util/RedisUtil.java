package com.solland.paidao.util;


import com.solland.paidao.entity.dto.param.LoginParam;
import redis.clients.jedis.Jedis;

/**
 * redis工具类
 * Created by sunshibo on 2016/1/4.
 */
public class RedisUtil {

    /**set Object*/
    public static String set(Object object,String key) {
        Jedis jedis = RedisConnectFactory.getJedis() ;
        String result = null;
        try {
            result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                RedisConnectFactory.returnResource(jedis);
        }
        return result;
    }

    /**get Object*/
    public static Object get(String key) {
        Jedis jedis = RedisConnectFactory.getJedis() ;
        Object object = null;
        try {
            byte[] value = jedis.get(key.getBytes());
            object = SerializeUtil. unserialize(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
                RedisConnectFactory.returnResource(jedis);
        }
        return object;
    }

    /**delete a key**/
    public static boolean del(String key) {
        Jedis jedis = RedisConnectFactory.getJedis() ;
        try {
            boolean result =  jedis.del(key.getBytes())>0;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null)
             RedisConnectFactory.returnResource(jedis);
        }
        return false ;
    }

    public static void main(String[] args) {
        LoginParam loginParam = new LoginParam() ;
        loginParam.setAccount("sunshibo");
        loginParam.setPassword("123455");
        loginParam.setUniqueCode("xxxxxxxxxxx");
        RedisUtil.set(loginParam , "sunObj") ;

        LoginParam l2 = (LoginParam)RedisUtil.get("sunObj") ;
        System.out.println(l2.toString());
    }
}
