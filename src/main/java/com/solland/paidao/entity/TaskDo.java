package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class TaskDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**严重级别 高中低*/
	public static final String LEVEL_HIGH = "High" ;
	public static final String LEVEL_MIDDLE = "Middle" ;
	public static final String LEVEL_LOW = "Low" ;

	/**新任务  打开  重新打开  等待  完成  关闭*/
	public static final String STATUS_NEW = "New" ;
	public static final String STATUS_OPEN  = "Open" ;
	public static final String STATUS_REOPEN  = "Reopen" ;
	public static final String STATUS_WAIT  = "Wait" ;
	public static final String STATUS_FINISH = "Finish" ;
	public static final String STATUS_CLOSE = "Close" ;
	public static final Set<String> STATUS_VALUE = Sets
			.newHashSet("New" ,"Open","Reopen","Wait","Finish","Close") ;


	private Integer id; //主键
	private String subject ;//主题
	private Date createTime ;//创建时间
	private Date beginTime ;
	private Date endTime ;//计划结束时间
	private String content ;//内容
	private String status ;//状态
	private String level ;//任务级别
	private Date finishTime ;//最总完成时间
	private Integer createUser ;//创建人

	private String memberIds ;// 参数:参与任务者ID
	private List<TaskUserDo> taskUsers ;//关联的TaskUserDo的集合

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public List<TaskUserDo> getTaskUsers() {
		return taskUsers;
	}

	public void setTaskUsers(List<TaskUserDo> taskUsers) {
		this.taskUsers = taskUsers;
	}
}

