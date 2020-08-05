package com.example.demo.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperty {

	private String jwtSecret;
	private int jwtExpiration;
	private int showException;
	private int resolutionImage;
	private String seedPassword;
	private String uploadDir;
	private int imageResolution;
	private String urlSitio;
	private String fromMail;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public int getJwtExpiration() {
		return jwtExpiration;
	}

	public void setJwtExpiration(int jwtExpiration) {
		this.jwtExpiration = jwtExpiration;
	}

	public int getShowException() {
		return showException;
	}

	public void setShowException(int showException) {
		this.showException = showException;
	}

	public int getResolutionImage() {
		return resolutionImage;
	}

	public void setResolutionImage(int resolutionImage) {
		this.resolutionImage = resolutionImage;
	}

	public String getSeedPassword() {
		return seedPassword;
	}

	public void setSeedPassword(String seedPassword) {
		this.seedPassword = seedPassword;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public int getImageResolution() {
		return imageResolution;
	}

	public void setImageResolution(int imageResolution) {
		this.imageResolution = imageResolution;
	}

	public String getUrlSitio() {
		return urlSitio;
	}

	public void setUrlSitio(String urlSitio) {
		this.urlSitio = urlSitio;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

}
