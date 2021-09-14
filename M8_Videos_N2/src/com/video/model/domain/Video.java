package com.video.model.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Video {
	private static int counterId = 0;
	private int videoId;
	private String url;
	private String title;
	private Set<Tag> videoTagSet;
	private Date regDate;
	private enum UploadStatus {UPLOADING, VERIFYING, PUBLIC};

	public Video(String url, String title) {
		counterId++;
		this.videoId = counterId;
		this.url = url;
		this.title = title;
		this.regDate = new Date();
		videoTagSet = new HashSet<Tag>();
	}

	public UploadStatus getUploadStatus() {
		long secRegDate = regDate.getTime();
		Date currentDate = new Date();
		long secCurrentDate = currentDate.getTime();
		long lastedSeconds = secCurrentDate - secRegDate;
		long lastedMinutes = TimeUnit.MILLISECONDS.toMinutes(lastedSeconds);
		UploadStatus status;
		if(lastedMinutes<1) {
			status = UploadStatus.UPLOADING;
		}else if(lastedMinutes<2) {
			status = UploadStatus.VERIFYING;
		}else {
			status = UploadStatus.PUBLIC;
		}
		return status;
	}
	
	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public int getVideoId() {
		return videoId;
	}
	
	//para que no cuente nuevo videoId si al final no se confirma la creación del vídeo
	public void updateCounterId() {
		counterId--;
	}

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
		return "videoId=" + videoId + "\turl=" + url + "\ttitle=" + title +"\t regDate=" + regDate + "\ttags=" + videoTagSet +"\t"+ getUploadStatus() +"\n";
	}
}
