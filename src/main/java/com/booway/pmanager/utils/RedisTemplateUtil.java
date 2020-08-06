package com.booway.pmanager.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.booway.pmanager.entity.User;

/**
 * 
 * @author 超帅
 *
 */
@Component
public class RedisTemplateUtil
{
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    public void add(User user)
    {
        redisTemplate.opsForValue().set(user.getLoginName() + "-" + "userName", user.getUserName());
        redisTemplate.opsForValue().set(user.getLoginName() + "-" + "userId", user.getUserId());
        redisTemplate.opsForValue().set(user.getLoginName() + "-" + "deptId", user.getDeptId());
        redisTemplate.opsForValue().set(user.getLoginName() + "-" + "roleName", user.getRoleName());
    }
    
    public String get(String str)
    {
        return redisTemplate.opsForValue().get(str);
    }
    
    public void remove()
    {
        String loginName = SessionUtil.getAttrbute();
        List<String> list = new ArrayList<>();
        list.add(loginName + "-" + "userName");
        list.add(loginName + "-" + "userId");
        list.add(loginName + "-" + "deptId");
        list.add(loginName + "-" + "roleName");
        redisTemplate.delete(list);
        list = null;
    }
}
