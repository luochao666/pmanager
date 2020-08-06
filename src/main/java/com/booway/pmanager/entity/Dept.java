package com.booway.pmanager.entity;

import java.io.Serializable;
/**
 * 
 * @author 超帅
 *
 */
public class Dept implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String deptId;
    
    private String deptName;

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

    @Override
    public String toString()
    {
        return "Dept [deptId=" + deptId + ", deptName=" + deptName + "]";
    }
    
    
    
    
}
