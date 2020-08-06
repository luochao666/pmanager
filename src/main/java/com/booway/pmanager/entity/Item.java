package com.booway.pmanager.entity;

import java.util.Date;

/**
 * 项目实体
 * @author 超帅
 *
 */
public class Item
{
    private String itemId;
    
    private String itemName;
    
    private String parentId;
    
    private String userId;
    
    private String userName;
    
    private String deptId;
    
    private String worker;
    
    private Date time;

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }



    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getDeptId()
    {
        return deptId;
    }

    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }

    public String getWorker()
    {
        return worker;
    }

    public void setWorker(String worker)
    {
        this.worker = worker;
    }


    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", parentId=" + parentId + ", userId=" + userId
                + ", userName=" + userName + ", deptId=" + deptId + ", worker=" + worker + ", time=" + time + "]";
    }


    
    

}
