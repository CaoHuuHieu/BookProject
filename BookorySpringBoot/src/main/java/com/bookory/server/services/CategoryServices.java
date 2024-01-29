package com.bookory.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.models.request.CategoryRequestDTO;
import com.bookory.entity.CategoryEntity;
import com.bookory.server.repositories.CategoryRepository;

@Service
public class CategoryServices {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ImageStorageService imageStorageService;
	public List<CategoryEntity> getAllCategories(){
		return categoryRepository.findByStatus(0);
	}
	
	public CategoryEntity getCategoriesByID(long id){
		return categoryRepository.findById(id).orElse(null);
	}
	
	public CategoryEntity addNewCategory(CategoryRequestDTO category, MultipartFile file){
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setName(category.getName());
		if(file != null) {
			String fileName=imageStorageService.storeFile(file);
			categoryEntity.setThumbnail(fileName);
		}
		categoryEntity.setStatus(0);
		return categoryRepository.save(categoryEntity);
	}

	public CategoryEntity updateCategory(long id, CategoryRequestDTO category, MultipartFile file){
		CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
		if(categoryEntity != null) {
			categoryEntity.setId(id);
			categoryEntity.setName(category.getName());
			if(file != null) {
				String fileName=imageStorageService.storeFile(file);
				categoryEntity.setThumbnail(fileName);
			}
			return categoryRepository.save(categoryEntity);
		}else
			return null;
	}

	public CategoryEntity deleteCategory(long id){
		CategoryEntity categoryEntity = getCategoriesByID(id);
		categoryEntity.setStatus(1);
		return categoryRepository.save(categoryEntity);
	}
}
