package com.example.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileServices {

	ResponseEntity<?> uploadBuyPrice(MultipartFile file);

}
