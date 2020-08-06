package com.booway.pmanager.entity;

import java.io.Serializable;
/**
 * 
 * @author 超帅
 *
 */
public class User implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userId;
    
    private String loginName;
    
    private String userName;
    
    private String deptId;
    
    private String deptName;
    
    private String roleId;
    
    private String roleName;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
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

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @Override
    public String toString()
    {
        return "User [userId=" + userId + ", loginName=" + loginName + ", userName=" + userName + ", deptId=" + deptId
                + ", deptName=" + deptName + ", roleId=" + roleId + ", roleName=" + roleName + "]";
    }
    
    
    
}
