package com.solland.paidao.entity.dto.param;

import com.solland.paidao.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态圈入参
 * @author zhaojiafu
 *
 * 2016年1月8日 下午12:46:54
 */
@SuppressWarnings("serial")
public class AddActivityParam extends BaseModel implements Serializable {

	private int userId;	// 发布人ID
	private String tags;	// 标签集
	private String position;	// 位置
	private String mediaType;		// 媒体类型（image-图片；video-视频）
	private String mediaPaths;		// 媒体路径集
	private String description;		// 文本描述
	private Double longitude;	// 经度
	private Double latitude;	// 纬度
	private String geohash;		// Geohash值
	private String status;	// 状态：illegal-违规、normal-正常
	private Date createTime;		// 创建时间
	private Date modifyTime;		// 修改时间
	
	public AddActivityParam() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
