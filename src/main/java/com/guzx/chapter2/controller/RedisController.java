package com.guzx.chapter2.controller;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @ResponseBody
    @RequestMapping("/stringAndHash")
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("university", "changchun university");
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        // 使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", "古之兄");
        hashMap.put("age", "23");
        stringRedisTemplate.opsForHash().putAll("hash", hashMap);
        stringRedisTemplate.opsForHash().put("hash", "newKey", "newValue");
        // 绑定散列操作的key
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        hashOperations.delete("name", "age");
        hashOperations.put("name", "lianghong");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> testList() {
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");
        Object result1 = listOperations.rightPop();
        // redis中下表从0开始
        Object result2 = listOperations.index(1);
        listOperations.leftPush("v0");
        Long size = listOperations.size();
        // 求链表下标区间成员
        List element = listOperations.range(0, size - 2);
        System.out.println(element.toArray());
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;

    }

    @ResponseBody
    @RequestMapping("/set")
    public Map<String, Object> testSet() {
        stringRedisTemplate.opsForSet().add("set1", "v1", "v2", "v3", "v4", "v5", "v5");
        stringRedisTemplate.opsForSet().add("set2", "v2", "v4", "v6", "v8");
        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("set1");
        setOperations.add("v6", "v7");
        setOperations.remove("v1", "v2");
        Set set1 = setOperations.members();
        Long size = setOperations.size();
        // 交集
        Set inter = setOperations.intersect("set2");
        setOperations.intersectAndStore("set2", "set3");
        // 差集
        Set diff = setOperations.diff("set2");
        setOperations.diffAndStore("set2", "set4");
        // 并集
        Set union = setOperations.union("set2");
        setOperations.unionAndStore("set2", "set5");

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;

    }

    @ResponseBody
    @RequestMapping("/zset")
    public Map<String, Object> testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        BoundZSetOperations zSetOperations = stringRedisTemplate.boundZSetOps("zset1");
        zSetOperations.add("value10", 20.6);
        Set<List> setRange = zSetOperations.range(1, 6);
        // 按分数排序
        Set<String> setScore = zSetOperations.rangeByScore(0.2, 0.6);
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");// 大于
//        range.gte("value3");// 大于等于
//        range.lt("value3");// 小于
        range.lte("value8");// 小于等于
        // 按值排序
        Set<String> setLex = zSetOperations.rangeByLex(range);
        zSetOperations.remove("value9", "value2");
        Double score = zSetOperations.score("value8");
        // 在下标区间，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zSetOperations.rangeWithScores(1, 6);
        // 在分数区间，按分数排序，同时返回value和score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zSetOperations.rangeByScoreWithScores(1, 6);
        // 从大到小排序
        Set<String> reverseSet = zSetOperations.reverseRange(2, 8);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @ResponseBody
    @RequestMapping("/multi")
    public Map<String, Object> testMulti() {
        redisTemplate.opsForValue().set("city", "shenzhen");
        List list = (List) redisTemplate.execute(new (RedisOperations redisOperations) -> {
            // 设置要监控的key
            redisOperations.watch("city");
            // 开启事务，在exec命令执行前，全部都只是进入队列
            redisOperations.multi();
            redisOperations.opsForValue().set("key2", "value2");
//            redisOperations.opsForValue().increment("city",1);
            Object value2 = redisOperations.opsForValue().get("key2");
            System.out.println("命令在队列中，所以value2值为null" + value2);
            redisOperations.opsForValue().set("key3", "value3");
            Object value3 = redisOperations.opsForValue().get("key3");
            System.out.println("命令在队列中，所以value3值为null" + value3);
            // 执行exec，其将先判断key1在被监控后是否改变过，如果是则不执行事务，否则执行事务
            return redisOperations.exec();
        });
        System.out.println(list);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
}
