package com.test.demo.attachment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 附件实体
 * 
 * @author Tian yunfeng
 * @date 2018年10月27日
 */
public class AttachmentVO {
	
	private Integer id;//主键

	private Integer outid;//外键

	private String sourcetype;//附件所属单据类型

	private String url;//附件路径

	private String attachmenttype;//附件类型

	private String name;//附件名称
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	@JsonIgnore
	private Date ts;//时间戳

	/*
	 * 数据是否失效标识
	 * 0：有效
	 * 1：失效
	 */
	@JsonIgnore
	private Integer dr;//数据是否失效标识

	@JsonIgnore
	private String vdef1;//自定义项1

	@JsonIgnore
	private String vdef2;//自定义项2

	@JsonIgnore
	private String vdef3;//自定义项3

	@JsonIgnore
	private String vdef4;//自定义项4

	@JsonIgnore
	private String vdef5;//自定义项5

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOutid() {
		return outid;
	}

	public void setOutid(Integer outid) {
		this.outid = outid;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAttachmenttype() {
		return attachmenttype;
	}

	public void setAttachmenttype(String attachmenttype) {
		this.attachmenttype = attachmenttype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public String getVdef1() {
		return vdef1;
	}

	public void setVdef1(String vdef1) {
		this.vdef1 = vdef1;
	}

	public String getVdef2() {
		return vdef2;
	}

	public void setVdef2(String vdef2) {
		this.vdef2 = vdef2;
	}

	public String getVdef3() {
		return vdef3;
	}

	public void setVdef3(String vdef3) {
		this.vdef3 = vdef3;
	}

	public String getVdef4() {
		return vdef4;
	}

	public void setVdef4(String vdef4) {
		this.vdef4 = vdef4;
	}

	public String getVdef5() {
		return vdef5;
	}

	public void setVdef5(String vdef5) {
		this.vdef5 = vdef5;
	}
}
