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
//	private enum VideoStatus {PLAYING, PAUSED, STOPPED};
	private int videoLength; //duración del video en milisegundos
//	private long playingTime = 0; //tiempo transcurrido de reproducción en un momento dado

	public Video(String url, String title) {
		counterId++;
		this.videoId = counterId;
		this.url = url;
		this.title = title;
		this.regDate = new Date();
		videoTagSet = new HashSet<Tag>();
		videoLength = 10; //por defecto y para facilitar la comprobación de prueba los vídeos que se creen durarán 10 segundos
	}
	
//	public VideoStatus getVideoStatus() {
//		VideoStatus videoStatus;
//		if(videoLength-playingTime <= 0) {
//			videoStatus = VideoStatus.STOPPED;
//		}
//		return videoStatus;
//	}

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
	
	public int getVideoLength() {
		return videoLength;
	}
	
//	public long getPlayingTime() {
//		return playingTime;
//	}
//	
//	public void setPlayingTime (long playingTime) {
//		this.playingTime = playingTime;
//	}
	
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
		return "videoId=" + videoId + "\turl=" + url + "\ttitle=" + title +"\t regDate=" + regDate + "\ttags=" + videoTagSet +"\t"+ getUploadStatus() + "\tLength="+ videoLength +"\n";
	}
}
