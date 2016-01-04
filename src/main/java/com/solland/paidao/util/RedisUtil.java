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
        String result = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
        RedisConnectFactory.returnResource(jedis);
        return result;
    }

    /**get Object*/
    public static Object get(String key) {
        Jedis jedis = RedisConnectFactory.getJedis() ;
        byte[] value = jedis.get(key.getBytes());
        Object object = SerializeUtil. unserialize(value);
        RedisConnectFactory.returnResource(jedis);
        return object;
    }

    /**delete a key**/
    public static boolean del(String key) {
        Jedis jedis = RedisConnectFactory.getJedis() ;
        boolean result =  jedis.del(key.getBytes())>0;
        RedisConnectFactory.returnResource(jedis);
        return result;
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
