package com.booway.pmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booway.pmanager.entity.Dept;
import com.booway.umanager.service.DeptService;

/**
 * 部门的远程调用
 * @author 超帅
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptController
{
    
    @Autowired
    @Qualifier("dpetClient")
    private DeptService deptService;
    
    
    @RequestMapping("/getList")
    @ResponseBody
    public List<Dept> getList()
    {
        return deptService.getList();
    }

}
