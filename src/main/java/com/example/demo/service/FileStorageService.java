package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.utils.AppProperty;
import com.example.demo.utils.ObjFile;

@Service
public class FileStorageService {
	
	private final Path fileStorageLocation;
	private String rootPath;
	private String relativePath;

	@Autowired
	public FileStorageService(AppProperty properties) throws IOException {
		rootPath = properties.getUploadDir();
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy/MM/dd");
		relativePath = simpleDateformat.format(now);
		rootPath = rootPath + relativePath;
		this.fileStorageLocation = Paths.get(rootPath).toAbsolutePath().normalize();
		Files.createDirectories(this.fileStorageLocation);
	}

	public ObjFile storeFile(MultipartFile file) throws IOException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		filename = String.valueOf(Math.random());
		filename = filename.replace(".", "");
		if (!extension.isEmpty()) {
			filename += "." + extension;
		}
		Path targetLocation = this.fileStorageLocation.resolve(filename);
		File f = targetLocation.toFile();
		while(f.exists()) {
			filename = String.valueOf(Math.random());
			filename = filename.replace(".", "");
			if (!extension.isEmpty()) {
				filename += "." + extension;
			}
			targetLocation = this.fileStorageLocation.resolve(filename);
			f = targetLocation.toFile();
		}
		Files.copy(file.getInputStream(), targetLocation);
		ObjFile archivo = new ObjFile();
		archivo.setFilename(filename);
		archivo.setExtension(file.getContentType());
		archivo.setSize(file.getSize());
		archivo.setPathFile(relativePath + "/"+ filename);
		return archivo;
	}

	public Resource loadFileAsResource(String filename) throws MalformedURLException {
		Path filePath = this.fileStorageLocation.resolve(filename).normalize();
		Resource resource = new UrlResource(filePath.toUri());
		return resource;
	}

	public String getRootPath() {
		return rootPath;
	}

}
