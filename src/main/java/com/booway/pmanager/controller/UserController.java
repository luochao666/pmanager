package com.booway.pmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booway.pmanager.entity.User;
import com.booway.pmanager.result.MsgVo;
import com.booway.pmanager.utils.RedisTemplateUtil;
import com.booway.pmanager.utils.SessionUtil;
import com.booway.umanager.service.UserService;
/**
 * 远程调用
 * @author 超帅
 *
 */
@RestController
@RequestMapping("/project")
public class UserController
{   
    @Autowired
    @Qualifier("userClient")
    private UserService userService;
    
    @Autowired
    private RedisTemplateUtil redisTemplate;
    
    /**
     * @param loginName
     * @param pwd
     * @return
     */
    @RequestMapping("/login")
    public MsgVo login(@RequestParam("loginName")String loginName,@RequestParam("pwd")String pwd)
    {
        User user =  userService.login(loginName, pwd);
        if (user == null)
        {
        	return MsgVo.getError("用户或密码错误");
        }
        SessionUtil.setAttribute(user.getLoginName());
        redisTemplate.add(user);
        if ("项目专员".equals(user.getRoleName()))
        {
        	return new MsgVo(3);
        }
        if ("项目经理".equals(user.getRoleName()))
        {
        	return new MsgVo(4);
        }
        if ("部门经理".equals(user.getRoleName()))
        {
        	return new MsgVo(5);
        }
        if ("总经理".equals(user.getRoleName()))
        {
        	return new MsgVo(6);
        }
        return MsgVo.getError("该角色没有访问本系统的权力");  	
    }
    
    @RequestMapping("/queryBydeptId")
    public List<User> queryBydeptId()
    {
        String loginName = SessionUtil.getAttrbute();
        String deptId = redisTemplate.get(loginName + "-" + "deptId");
        return userService.queryById(deptId);
    }
    
    @RequestMapping("/logout")
    public MsgVo logout()
    {
        redisTemplate.remove();
        SessionUtil.remove();
        return MsgVo.getSuccess(0, null, null);
    }
    

    

}
