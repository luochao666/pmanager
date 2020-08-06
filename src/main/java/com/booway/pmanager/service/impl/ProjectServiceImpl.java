package com.booway.pmanager.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booway.pmanager.dao.ItemDao;
import com.booway.pmanager.dao.ProjectDao;
import com.booway.pmanager.entity.Item;
import com.booway.pmanager.result.MsgVo;
import com.booway.pmanager.utils.CommonUtil;
import com.booway.pmanager.utils.RedisTemplateUtil;
import com.booway.pmanager.utils.SessionUtil;
import com.booway.umanager.service.ProjectService;

/**
 * 
 * @author 超帅
 *
 */
@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ItemDao itemDao;
    
    @Autowired
    private ProjectDao projectDao;
    
    @Autowired RedisTemplateUtil redisTemplate;

    @Override
    public MsgVo addProject(String parentId, String worker, String itemName) throws Exception
    {
        String loginName = SessionUtil.getAttrbute();
        Item item = new Item();
        item.setItemId(CommonUtil.getUUID());
        item.setUserId(redisTemplate.get(loginName + "-" + "userId"));
        item.setUserName(redisTemplate.get(loginName + "-" + "userName"));
        item.setDeptId(redisTemplate.get(loginName + "-" + "deptId"));
        Date current = new Date();
        item.setTime(current);
        item.setItemName(itemName);
        item.setWorker(worker);
        item.setParentId(parentId);
        try
        {
            int i = itemDao.insertItem(item);
            if (i > 0)
            {
                return MsgVo.getSuccess(0, "工程添加成功", null);
            }
            return MsgVo.getError("工程添加失败");  
        } catch (Exception e)
        {
            throw new Exception("在执行sql语句时出错");
        }
    }

    @Override
    public MsgVo editProject(String itemId, String itemName) throws Exception
    {
        try
        {
            int i = projectDao.editProject(itemId, itemName);
            if (i > 0)
            {
                return MsgVo.getSuccess(0, "工程编辑成功", null);
            }
            return MsgVo.getError("工程编辑失败");  
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("在执行sql语句时出错");
        }
    }

    @Override
    public MsgVo deleteProject(String itemId) throws Exception
    {
        try
        {
            int i = itemDao.deleteItem(itemId);
            if (i > 0)
            {
                return MsgVo.getSuccess(0, "工程删除成功", null);
            }
            return MsgVo.getError("工程删除失败");  
        } catch (Exception e)
        {
            throw new Exception("在执行sql语句时出错");
        }
    }

}
