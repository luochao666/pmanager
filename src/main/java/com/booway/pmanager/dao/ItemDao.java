package com.booway.pmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.booway.pmanager.entity.Item;

/**
 * 
 * @author 超帅
 *
 */
@Repository
public interface ItemDao
{
    /**
     * 新建项目
     * @param item
     * @return
     */
    Integer insertItem(Item item);
    
    /**
     * 删除项目
     * @param itemId
     * @return
     */
    Integer deleteItem(String itemId);
    
    /**
     * 编辑项目
     * @param itemId
     * @param itemName
     * @param worker
     * @return
     */
    Integer editItem(@Param("itemId")String itemId, @Param("itemName")String itemName, @Param("worker")String worker);
    

    
    /**
     * 通过项目id查询项目
     * @param parentId
     * @return
     */
    Item getItemById(String itemId);
    
    /**
     * 根据不同的角色，展现不同的项目
     * @param createName
     * @param deptId
     * @param worker
     * @return
     */
    List<Item> getItems(@Param("createName") String createName, @Param("worker")String worker, @Param("deptId")String deptId, @Param("itemName") String itemName);
    
    /**
     * 检查新建项目或者工程是否同名
     * @param itemName
     * @return
     */
    int checkName(@Param("itemName")String itemName);
}
