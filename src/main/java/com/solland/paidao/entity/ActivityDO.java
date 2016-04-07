package com.solland.paidao.entity;

import java.io.Serializable;

/**
 * 动态圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午12:46:54
 */
@SuppressWarnings("serial")
public class ActivityDO implements Serializable {
	private Integer id;		// ID
	private Integer userId;	// 发布人ID
	private String tags;	// 标签集
	private String position;	// 位置
	private String mediaType;		// 媒体类型（image-图片；video-视频）
	private String mediaPaths;		// 媒体路径集
	private String descriptionVoicePath;		// 音频路径
	private String description;		// 文本描述
	private Integer likeNum;		// 被赞的数量
	private Integer heatValue;		// 热度值
	private Integer commentNum;	// 评论数
	private Integer enshrineNum;	// 收藏数
	private Double longitude;	// 经度
	private Double latitude;	// 纬度
	private String geohash;		// Geohash值
	private String status;	// 状态：illegal-违规、normal-正常
	private String createTime;		// 创建时间
	private String modifyTime;		// 修改时间
	
	public ActivityDO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getHeatValue() {
		return heatValue;
	}

	public void setHeatValue(Integer heatValue) {
		this.heatValue = heatValue;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getEnshrineNum() {
		return enshrineNum;
	}

	public void setEnshrineNum(Integer enshrineNum) {
		this.enshrineNum = enshrineNum;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
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
}
