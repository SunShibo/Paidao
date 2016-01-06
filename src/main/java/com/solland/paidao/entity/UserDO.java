package com.solland.paidao.entity;

import java.io.Serializable;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月5日 下午5:47:13
 */
@SuppressWarnings("serial")
public class UserDO implements Serializable {
	private int id;		// ID
	private String username;	// 用户名
	private String password;	// 密码
	private String name;		// 姓名
	private String nickname;	// 昵称
	private short gender;		// 性别：1-男；2-女
	private String birthday;	// 生日
	private String mobileCode;	// 手机号
	private String telephone;	// 固话号码
	private String email;		// 电子邮箱
	private String qqCode;		// QQ号
	private String wechat;		// 微信号
	private String alipay;		// 支付宝号
	private String sinaWeibo;		// 新浪微博号
	private String twiter;		// twiter号
	private String facebook;		// facbook 号
	private String linkin;		// linkin 号
	private String postcode;	// 邮政编码
	private String organization;	// 组织（工作单位）
	private int provinceId;		// 省份ID		
	private int cityId;			// 地市ID
	private int districtId;			// 区县ID
	private String detailAddress;	// 详细地址
	private short isFreeze;		// 是否冻结：1-是；2-否；
	private short isOpen;		// 是否公开：1-是；2-否；
	private short isValid;		// 是否启用：1-是；2-否；
	private int version;		// 版本
	private String comment;		// 备注
	private int creatorId;		// 创建人ID
	private int modifierId;		// 修改人ID
	private String createTime;		// 创建时间
	private String modifyTime;		// 修改时间
	
	private String imei;		// 移动设备国际识别码
	private String deviceType;		// 设备类型：browser-电脑；android-安卓；iOS-苹果
	private String loginId;			// 唯一标识
	private String token;			// 唯一标识
	private String captchaCode;		// 验证码
	private String smsCaptcha;		// 短信验证码
	private int headPortraitId;		// 头像ID
	private String headPortraitPath;		// 头像路径
	
	public UserDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public short getGender() {
		return gender;
	}

	public void setGender(short gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQqCode() {
		return qqCode;
	}

	public void setQqCode(String qqCode) {
		this.qqCode = qqCode;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getSinaWeibo() {
		return sinaWeibo;
	}

	public void setSinaWeibo(String sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
	}

	public String getTwiter() {
		return twiter;
	}

	public void setTwiter(String twiter) {
		this.twiter = twiter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkin() {
		return linkin;
	}

	public void setLinkin(String linkin) {
		this.linkin = linkin;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public short getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(short isFreeze) {
		this.isFreeze = isFreeze;
	}

	public short getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(short isOpen) {
		this.isOpen = isOpen;
	}

	public short getIsValid() {
		return isValid;
	}

	public void setIsValid(short isValid) {
		this.isValid = isValid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getModifierId() {
		return modifierId;
	}

	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getSmsCaptcha() {
		return smsCaptcha;
	}

	public void setSmsCaptcha(String smsCaptcha) {
		this.smsCaptcha = smsCaptcha;
	}

	public int getHeadPortraitId() {
		return headPortraitId;
	}

	public void setHeadPortraitId(int headPortraitId) {
		this.headPortraitId = headPortraitId;
	}

	public String getHeadPortraitPath() {
		return headPortraitPath;
	}

	public void setHeadPortraitPath(String headPortraitPath) {
		this.headPortraitPath = headPortraitPath;
	}
}
