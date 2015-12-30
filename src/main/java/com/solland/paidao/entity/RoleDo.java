package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色DO
 */
public class RoleDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**主键*/
    private Integer id ;

    /**角色名*/
    private String name ;

	/**level*/
	private Integer level ;

	/**描述*/
    private String description ;

	/**创建者id*/
	private Integer creator ;

	/**创建时间*/
	private Date createTime ;

	/**权限id集合，通过","分隔*/
	private String permissionIds ;

	/**参数： 权限列表*/
	private List<PermissionDo> permissionList ;

    public RoleDo() {

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	public List<PermissionDo> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionDo> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "RoleDo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", level=" + level +
				", description='" + description + '\'' +
				", creator=" + creator +
				", createTime=" + createTime +
				", permissionIds='" + permissionIds + '\'' +
				'}';
	}
}

