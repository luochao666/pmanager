package com.booway.pmanager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.booway.pmanager.dao.ItemDao;
import com.booway.pmanager.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booway.pmanager.result.MsgVo;
import com.booway.pmanager.utils.CommonUtil;
import com.booway.pmanager.utils.RedisTemplateUtil;
import com.booway.pmanager.utils.SessionUtil;
import com.booway.umanager.service.ItemService;

/**
 * 
 * @author 超帅
 *
 */
@Service
public class ItemServiceImpl implements ItemService
{
    @Autowired
    private ItemDao itemDao;
    
    @Autowired
    private RedisTemplateUtil redisTemplate;
    
    
	@Override
	public MsgVo createItem(String itemName, String worker) throws Exception
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
		item.setParentId("0");
		
		try
        {
		    int i = itemDao.insertItem(item);
	        if (i > 0)
	        {
	            return MsgVo.getSuccess(0, "添加成功", null);
	        }
	        return MsgVo.getError("添加失败");  
        } catch (Exception e)
        {
            throw new Exception("在执行sql语句时出错");
        }
		
	}


    @Override
    public MsgVo deleteItem(String itemId) throws Exception
    {
        try
        {
            int i = itemDao.deleteItem(itemId);
            if (i > 0)
            {
                return MsgVo.getSuccess(0, "删除成功", null);
            }
            return MsgVo.getError("删除失败"); 
        } catch (Exception e)
        {
            throw new Exception("删除时，sql执行出错");
        }
        
    }


    @Override
    public MsgVo editItem(String itemId, String itemName, String worker) throws Exception
    {
        try
        {
            int i = itemDao.editItem(itemId,itemName,worker);
            if (i > 0)
            {
                return MsgVo.getSuccess(0, "编辑成功", null);
            }
            return MsgVo.getError("编辑失败"); 
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("编辑时，sql执行出错");
        }
    }

    /**
     * 所有角色查询的专用接口
     */
    @Override
    public MsgVo queryItemByName(String name,String deptId) throws Exception
    {
        List<Item> items = null;
        String userName = null;
        try
        {
            String loginName = SessionUtil.getAttrbute();
            String roleName = redisTemplate.get(loginName + "-" + "roleName");
            //自己创建的项目、工程
            if ("项目专员".equals(roleName))
            {
                userName = redisTemplate.get(loginName + "-" + "userName");
                //获得符合模糊查询到项目、工程
                items = itemDao.getItems(userName,null,null,name);
            }
            //自己负责的项目、工程
            if ("项目经理".equals(roleName))
            {
                userName = redisTemplate.get(loginName + "-" + "userName");
                items = itemDao.getItems(null,userName,null,name);
            }
            //自己部门内的项目、工程
            if ("部门经理".equals(roleName))
            {
                String departmentId = redisTemplate.get(loginName + "-" + "deptId");
                items = itemDao.getItems(null,null,departmentId,name);
            }
            //总经理---查询条件可以有两个：项目名、部门id
            if ("总经理".equals(roleName))
            {
                items = itemDao.getItems(null,null,deptId,name);
            }        
            //没有使用条件查询，就直接将查询出来的结果返回
            
            if (items.size() > 0)
            {
                updateItem(items);
            }
        if (CommonUtil.isEmpty(name))
        {
            return MsgVo.getSuccess(items.size(), "查询成功", items);
        }
        else
        {
            check(items);
        }
            
            return MsgVo.getSuccess(items.size(), "查询成功", items);
        } catch (Exception e) 
        {
            e.printStackTrace();
            throw new Exception("查询出错");
        }  
    }
    
    
    
    
    
    
    private void updateItem(List<Item> items)
    {
        for (Item item : items)
        {
            if (!"0".equals(item.getParentId()))
            {
                item.setUserName(null);
                item.setWorker(null);
            }
        }
    }


    /**
     * 检查工程的父项目是否存在现有的列表中
     * @param items
     */ 
    private void check(List<Item> items)
    {
        List<String> projectList = new ArrayList<>();
        // 得到的工程的父项目可能不在items中
        List<String> itemIds = new ArrayList<String>();
        for (Item item : items)
        {
            itemIds.add(item.getItemId());
            //如果parentId为0，则为工程
            if (!"0".equals(item.getParentId()))
            {
                
                projectList.add(item.getParentId());
            }
        }
        
        for (String str : projectList)
        {
            //如果工程的父项目id不在已有的中，就重新去查
            if (!itemIds.contains(str))
            {
                Item item = itemDao.getItemById(str);
                itemIds.add(item.getItemId());
                items.add(item);
            }
        }
    }


    @Override
    public int checkName(String itemName) throws Exception
    {
        try
        {
            return itemDao.checkName(itemName);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("项目名查重时出现异常");
        }
        
    }
}
