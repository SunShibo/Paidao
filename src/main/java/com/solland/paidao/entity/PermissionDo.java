package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
	 * 权限DO
	 */
public class PermissionDo implements Serializable {

		private static final long serialVersionUID = 1L;

		/**主键*/
		private Integer id ;

		/**权限名*/
		private String name ;

		/**路径*/
		private String url ;

		/**父类id*/
		private Integer parentId ;

		/**树形结构级别*/
		private Integer level ;

		/**启用状态*/
		private Integer enabledState ;

		/**菜单状态*/
		private Integer isMenu ;

		/**创建时间*/
		private Date createTime ;

		/**唯一标识*/
		private String uniqueFlag ;

		/**空参构造*/
		public PermissionDo() {

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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getParentId() {
			return parentId;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public Integer getEnabledState() {
			return enabledState;
		}

		public void setEnabledState(Integer enabledState) {
			this.enabledState = enabledState;
		}

		public Integer getIsMenu() {
			return isMenu;
		}

		public void setIsMenu(Integer isMenu) {
			this.isMenu = isMenu;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public void setUniqueFlag(String uniqueFlag) {
			this.uniqueFlag = uniqueFlag;
		}

	@Override
		public String toString() {
			return "PermissionDo{" +
					"id=" + id +
					", name='" + name + '\'' +
					", url='" + url + '\'' +
					", parentId=" + parentId +
					", level=" + level +
					", enabledState=" + enabledState +
					", isMenu=" + isMenu +
					'}';
		}

	}

