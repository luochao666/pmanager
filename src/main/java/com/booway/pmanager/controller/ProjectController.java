package com.booway.pmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booway.pmanager.result.MsgVo;
import com.booway.umanager.service.ItemService;
import com.booway.umanager.service.ProjectService;

/**
 * 工程接口
 * @author 超帅
 *
 */
@RestController
@RequestMapping("/project")
public class ProjectController
{

    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private ItemService itemService;
    /**
     * 添加工程
     * @param parentId
     * @param worker
     * @param itemName
     * @return
     */
    @RequestMapping("/add")
    public MsgVo addProject(String parentId, String worker, String itemName)
    {
        try
        {
            if (itemService.checkName(itemName) > 0)
            {
                return new MsgVo(2,0,"该项目/工程名已存在，请重写填写！",null);
            }
            return projectService.addProject(parentId, worker, itemName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 编辑工程
     * @param itemId
     * @param itemName
     * @return
     */
    
    @RequestMapping("/edit")
    public MsgVo editProject(String itemId,String itemName)
    {
        try
        {
            return projectService.editProject(itemId, itemName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 删除工程
     * @param itemId
     * @return
     */
    @RequestMapping("delete")
    public MsgVo deleteProject(String itemId)
    {
        try
        {
            return projectService.deleteProject(itemId);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
