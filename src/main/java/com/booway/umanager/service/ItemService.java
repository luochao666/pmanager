package com.booway.umanager.service;

import com.booway.pmanager.result.MsgVo;

/**
 * 操作项目的service接口
 * @author JSB-LUOchao
 *
 */
public interface ItemService
{
    int checkName(String itemName) throws Exception;
    /**
     * 创建项目，前端只需要提供两个参数，其它参数都是在service层获取到的
     * @param itemName
     * @param worker
     * @return
     * @throws Exception 
     */
	MsgVo createItem(String itemName, String worker) throws Exception;
	
	/**
	 * 删除项目
	 * @param itemId
	 * @return
	 * @throws Exception 
	 */
	MsgVo deleteItem(String itemId) throws Exception;
	
	/**
	 * 编辑项目，itemId定位
	 * @param itemId
	 * @param itemName
	 * @param worker
	 * @return
	 */
	MsgVo editItem(String itemId, String itemName, String worker) throws Exception;
	
	/**
	 * 根据项目名或者工程名进行模糊查询
	 * @return
	 */
	MsgVo queryItemByName(String name, String deptId) throws Exception;
	
	
}
