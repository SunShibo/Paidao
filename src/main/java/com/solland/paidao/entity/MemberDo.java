package com.solland.paidao.entity;
import java.io.Serializable;
import java.util.Date;

public class MemberDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**0锁定状态、1可用状态、2未激活状态*/
	public static final int ENABLEDSTATE_DISABLED = 0 ;
	public static final int ENABLEDSTATE_ABLE = 1 ;
	public static final int ENABLEDSTATE_NONACTIVATED = 2 ;

	/**性别 0女、1男*/
	public static final int GENDER_MALE = 1 ;
	public static final int GENDER_FEMALE = 0 ;

	/**1正常状态，2退会状态*/
	public static final int STATE_FORMAL = 1 ;
	public static final int STATE_QUIT = 2 ;


	private Integer id; //主键
	private String name; //会员姓名
	private String employeeId; //员工id
	private String englishName; //英文名
	private Integer gender; //性别
	private Integer educationBackground; //学历
	private Date birthday; //生日
	private String university; //毕业院校
	private String major; //专业
	private String nationality; //国籍
	private String nativePlace; //籍贯
	private String residenceCountry; //现居住国家
	private String residenceCity; //现居住城市
	private String phone; //手机
	private String qq; //扣扣
	private String email; //邮箱
	private String skype; //skype
	private String photo; //照片
	private Date joinTime; //加入时间
	private Date positiveTime; //转正时间
	private Date quitTime; //退会时间
	private Date createTime; //创建时间
	private Integer delFlag; //删除标记 0未删除 ， 1已删除
	private Integer state; //会员状态
	private String remarks;	//备注
	private String description;	//个人描述
	private String address;//地址
	private String password;//密码
	private Integer roleId;	//角色id
	private String roleName;//角色名称
	private Integer enabledState; //打开状态  0为停用状态、1启用状态 、2待激活
	private String jobGrade ;//职级
	private String title ;//职务

	private String department ;//参数 部门
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getEnabledState() {
		return enabledState;
	}
	public void setEnabledState(Integer enabledState) {
		this.enabledState = enabledState;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setGender(Integer gender){
		this.gender=gender;
	}
	public Integer getGender(){
		return gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setEducationBackground(Integer educationBackground){
		this.educationBackground=educationBackground;
	}
	public Integer getEducationBackground(){
		return educationBackground;
	}
	public void setUniversity(String university){
		this.university=university;
	}
	public String getUniversity(){
		return university;
	}
	public void setMajor(String major){
		this.major=major;
	}
	public String getMajor(){
		return major;
	}
	public void setNationality(String nationality){
		this.nationality=nationality;
	}
	public String getNationality(){
		return nationality;
	}
	public void setNativePlace(String nativePlace){
		this.nativePlace=nativePlace;
	}
	public String getNativePlace(){
		return nativePlace;
	}
	public void setResidenceCountry(String residenceCountry){
		this.residenceCountry=residenceCountry;
	}
	public String getResidenceCountry(){
		return residenceCountry;
	}
	public void setResidenceCity(String residenceCity){
		this.residenceCity=residenceCity;
	}
	public String getResidenceCity(){
		return residenceCity;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSkype(String skype){
		this.skype=skype;
	}
	public String getSkype(){
		return skype;
	}
	public void setPhoto(String photo){
		this.photo=photo;
	}
	public String getPhoto(){
		return photo;
	}
	public void setJoinTime(Date joinTime){
		this.joinTime=joinTime;
	}
	public Date getJoinTime(){
		return joinTime;
	}
	public void setPositiveTime(Date positiveTime){
		this.positiveTime=positiveTime;
	}
	public Date getPositiveTime(){
		return positiveTime;
	}
	public void setQuitTime(Date quitTime){
		this.quitTime=quitTime;
	}
	public Date getQuitTime(){
		return quitTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setDelFlag(Integer delFlag){
		this.delFlag=delFlag;
	}
	public Integer getDelFlag(){
		return delFlag;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setRemarks(String remarks){
		this.remarks=remarks;
	}
	public String getRemarks(){
		return remarks;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobGrade() {
		return jobGrade;
	}

	public void setJobGrade(String jobGrade) {
		this.jobGrade = jobGrade;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}

