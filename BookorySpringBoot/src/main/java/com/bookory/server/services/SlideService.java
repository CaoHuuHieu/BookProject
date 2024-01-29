package com.bookory.server.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.entity.SlideEntity;
import com.bookory.server.repositories.SlideRepository;

@Service
public class SlideService {
	@Autowired
	SlideRepository slideRepository;
	@Autowired
	ImageStorageService imageStorageService;
	public SlideEntity addNewSlide( MultipartFile file) {
		SlideEntity slide = new SlideEntity();
		slide.setFileName(imageStorageService.storeFile(file));
		slide.setStatus(1);
		return slideRepository.save(slide);
	}
	
	public void deleteSlide(long id) {
		slideRepository.deleteById(id);
	}
	
	public void updateStatus(long id) {
		SlideEntity slide = slideRepository.findById(id).orElse(null);
		if(slide != null)
			if(slide.getStatus() == 0)
				slide.setStatus(1);
			else
				slide.setStatus(0);
		slideRepository.save(slide);
	}

	public List<SlideEntity> getAllSlide() {
		return slideRepository.findAll();
		
	}

	public List<SlideEntity>  getSlideVisible() {
		return slideRepository.findByStatus(1);
	}
}
