package com.dsjsys.pojo;

public class Message {
    @Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", stuffId="
				+ stuffId + "]";
	}

	private Long id;

    private String content;

    private Long stuffId;

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

    public Long getStuffId() {
        return stuffId;
    }

    public void setStuffId(Long stuffId) {
        this.stuffId = stuffId;
    }
}