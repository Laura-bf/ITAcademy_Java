package com.video.model.domain;

import java.util.HashSet;
import java.util.Set;

public class Video {
	private static int counterId = 0;
	private int videoId;
	private String url;
	private String title;
	private Set<Tag> videoTagSet;

	public Video(String url, String title) {
		counterId++;
		this.videoId = counterId;
		this.url = url;
		this.title = title;
		videoTagSet = new HashSet<Tag>();
	}

	// GETTER Y SETTERS (sólo los que se vayan a usar)
	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public int getVideoId() {
		return videoId;
	}

	public void updateCounterId() {
		counterId--;
	}

	// añadir tags
	public void addTag(Tag videoTag) {
		videoTagSet.add(videoTag);
	}

	public Set<Tag> getVideoTagSet() {
		return videoTagSet;
	}

	// PRUEBAS: para crear videos al inicio del repository
	public static Video getTestVideo(String url, String title) {
		Video testVideo = new Video(url, title);
		return testVideo;
	}

	@Override
	public String toString() {
		return "videoId=" + videoId + "\turl=" + url + "\ttitle=" + title + "\ttags=" + videoTagSet;
	}
}
