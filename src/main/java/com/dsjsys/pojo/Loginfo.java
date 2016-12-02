package com.dsjsys.pojo;

import java.util.Date;

public class Loginfo {
    @Override
	public String toString() {
		return "Loginfo [id=" + id + ", content=" + content + ", username="
				+ username + ", date=" + date + "]";
	}

	private Long id;

    private String content;

    private String username;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}