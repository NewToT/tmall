package com.wanczy.tmall.pojo;

import java.util.Date;

public class Review {
	private Integer id;
	
	private String content;
	
	private Integer pid;
	
	private Integer uid;
	
	private Date createDate;
	
	/*非数据库字段
*/
	private User user;//在展示评价时不仅仅要显示评价的内容，还要显示评价的用户是谁，但是如果没有User在评价里的话，无法通过一个uid来获取

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
