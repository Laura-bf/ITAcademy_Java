package com.video.controller;

import java.util.List;

import com.video.model.domain.Tag;
import com.video.model.domain.Video;

public class VideoController {

	public static String showPostedVideos(int userId) {
		List<Video> postedVideos = UserController.searchUserById(userId).getPostedVideos();
		String allVideos = "Tus vídeos publicados:";
		for (Video v : postedVideos) {
			allVideos += v.toString() + "\n";
		}
		allVideos += "TOTAL VIDEOS = " + postedVideos.size();
		return allVideos;
	}

	public static int createNewVideo(String url, String title, int userId) {
		Video newVideo = new Video(url, title);
		int videoId = 0;
		if (!searchVideo(url, userId)) {
			UserController.searchUserById(userId).addUserVideo(newVideo);
			videoId = newVideo.getVideoId();
		} else {
			newVideo.updateCounterId();
		}
		return videoId;
	}

	public static boolean searchVideo(String url, int userId) {
		List<Video> postedVideos = UserController.searchUserById(userId).getPostedVideos();
		boolean videoExists = false;
		for (Video v : postedVideos) {
			if (v.getUrl().equals(url)) {
				videoExists = true;
			}
		}
		return videoExists;
	}

	public static Video searchVideoById(int videoId, int userId) {
		List<Video> postedVideos = UserController.searchUserById(userId).getPostedVideos();
		Video video = null;
		for (Video v : postedVideos) {
			if (v.getVideoId() == videoId) {
				video = v;
			}
		}
		return video;
	}

	public static void addVideoTag(String videoTag, int videoId, int userId) {
		Tag newVideoTag = new Tag(videoTag);
		VideoController.searchVideoById(videoId, userId).addTag(newVideoTag);
	}
}
