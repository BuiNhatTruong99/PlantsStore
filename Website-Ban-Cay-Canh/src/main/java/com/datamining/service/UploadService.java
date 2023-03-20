package com.datamining.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadService {
	File uploadFile(MultipartFile file, String folder);
}
