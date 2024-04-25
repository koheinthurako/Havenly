package com.Havenly.Backend.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Havenly.Backend.Entity.Photos;

@Service
public interface Photos_Service {
	
	public MultipartFile upload(MultipartFile file) throws IOException;
//	public byte[] getPhoto(String fileName) throws IOException;
	public List<Photos> getAllPhotos();

}
