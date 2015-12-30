package com.solland.paidao.entity;
import java.io.Serializable;
import java.util.Date;

public class TaskUserDo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id ;
	private Integer taskId ;
	private Integer memberId ;
	private String memberName ;
	private Date createTime ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

