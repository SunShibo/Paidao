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
	private int id;		// ID
	private int issuerId;	// 发布人ID
	private String tags;	// 标签集
	private String position;	// 位置
	private String shortMessage;	// 短消息
	private String imagePaths;		// 图片路径集
	private String videoPaths;		// 视频路径集
	private String voicePath;		// 音频路径
	private String createTime;		// 创建时间
	private String modifyTime;		// 修改时间
	
	public ActivityDO() {
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

	public String getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}

	public String getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String imagePaths) {
		this.imagePaths = imagePaths;
	}

	public String getVideoPaths() {
		return videoPaths;
	}

	public void setVideoPaths(String videoPaths) {
		this.videoPaths = videoPaths;
	}

	public String getVoicePath() {
		return voicePath;
	}

	public void setVoicePath(String voicePath) {
		this.voicePath = voicePath;
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
