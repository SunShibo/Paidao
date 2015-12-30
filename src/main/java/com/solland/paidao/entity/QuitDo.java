package com.solland.paidao.entity;
import java.io.Serializable;
import java.util.Date;

public class QuitDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**状态的枚举值  new新流程 processing处理中 pass通过 reject驳回 */
	public static final String STATUS_NEW = "new";
	public static final String STATUS_PROCESSING = "processing";
	public static final String STATUS_PASS = "pass";
	public static final String STATUS_REJECT = "reject";


	/**主键*/
	private Integer id;
	/**退会会员id*/
	private Integer memberId ;
	/**会员名字*/
	private String memberName ;
	/**英文名*/
	private String englishName ;
	/**部门*/
	private String department ;
	/**职级*/
	private String jobGrade ;
	/**创建记录时间*/
	private Date createTime ;
	/**最后工作日期*/
	private Date lastDate ;
	/**加入时间*/
	private Date joinDate ;
	/**离职原因描述*/
	private String description ;
	/**制表人*/
	private Integer listerId ;
	/**制表人名称*/
	private String listerName ;
	/**状态*/
	private String status ;
	/**序列号*/
	private String serialNumber ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getListerId() {
		return listerId;
	}

	public void setListerId(Integer listerId) {
		this.listerId = listerId;
	}

	public String getListerName() {
		return listerName;
	}

	public void setListerName(String listerName) {
		this.listerName = listerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}

