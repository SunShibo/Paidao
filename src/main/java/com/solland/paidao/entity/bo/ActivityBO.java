package com.solland.paidao.entity.bo;

import java.io.Serializable;

/**
 * 活动
 * @author zhaojiafu
 *
 * 2016年1月11日 下午7:12:43
 */
@SuppressWarnings("serial")
public class ActivityBO implements Serializable {
	private int id;		// ID
	private int issuerId;	// 发布人ID
	private String tags;	// 标签集
	private String position;	// 位置
	private String mediaType;		// 媒体类型（image-图片；video-视频）
	private String mediaPaths;		// 媒体路径集
	private String descriptionVoicePath;		// 音频路径
	private String descriptionType;		// 描述类型：voice-语音、text-文本
	private String descriptionText;		// 文本描述
	private int likeNum;		// 被赞的数量
	private int heatValue;		// 热度值
	private int commentNum;	// 评论数
	private int enshrineNum;	// 收藏数
	private String longitude;	// 经度
	private String latitude;	// 纬度
	private String geohash;		// Geohash值
	private String status;	// 状态：illegal-违规、normal-正常
	private String createTime;		// 创建时间
	private String modifyTime;		// 修改时间
	
	private short isHeat;	// 是否已加热
	private short isEnshrine;	// 是否已收藏
	private int loginedUserId;	// 当前登录用户ID
	
	public ActivityBO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(int issuerId) {
		this.issuerId = issuerId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaPaths() {
		return mediaPaths;
	}

	public void setMediaPaths(String mediaPaths) {
		this.mediaPaths = mediaPaths;
	}

	public String getDescriptionVoicePath() {
		return descriptionVoicePath;
	}

	public void setDescriptionVoicePath(String descriptionVoicePath) {
		this.descriptionVoicePath = descriptionVoicePath;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public int getHeatValue() {
		return heatValue;
	}

	public void setHeatValue(int heatValue) {
		this.heatValue = heatValue;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getEnshrineNum() {
		return enshrineNum;
	}

	public void setEnshrineNum(int enshrineNum) {
		this.enshrineNum = enshrineNum;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public short getIsHeat() {
		return isHeat;
	}

	public void setIsHeat(short isHeat) {
		this.isHeat = isHeat;
	}

	public short getIsEnshrine() {
		return isEnshrine;
	}

	public void setIsEnshrine(short isEnshrine) {
		this.isEnshrine = isEnshrine;
	}

	public int getLoginedUserId() {
		return loginedUserId;
	}

	public void setLoginedUserId(int loginedUserId) {
		this.loginedUserId = loginedUserId;
	}
}
