package com.video.model.domain;

public class Tag {
	private String videoTag;

	public Tag(String videoTag) {
		this.videoTag = videoTag;
	}

	@Override
	public String toString() {
		return videoTag;
	}

}
