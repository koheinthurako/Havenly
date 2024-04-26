package com.Havenly.Backend.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Havenly.Backend.Entity.TestPhoto;

@Service
public interface TestPhotoService {
	
	public List<TestPhoto> savePhotos(List<TestPhoto> photos);
    public List<TestPhoto> getPhotos();

}
