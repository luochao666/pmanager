package com.booway.umanager.service;

import java.util.List;





import javax.jws.WebService;

import com.booway.pmanager.entity.User;
/**
 * 
 * @author 超帅
 *
 */
@WebService(targetNamespace = "http://cxf_test")
public interface UserService
{
    User login(String loginName,String pwd);
    
    
    List<User> queryById(String deptId);
}
