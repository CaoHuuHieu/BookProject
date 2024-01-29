package com.bookory.server.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.entity.ContactEntity;
import com.bookory.server.repositories.ContactRepository;

@Service
public class ContactServices {
	@Autowired
	ContactRepository contactRepository;

	public List<ContactEntity> getAllContact(){
		return contactRepository.findAll();
	}

	public ContactEntity getContactByID(long id){
		ContactEntity contactEntity = contactRepository.findById(id).orElse(null);
		contactEntity.setStatus(1);
		return contactRepository.save(contactEntity);
	}

	public ContactEntity addNewContact(ContactEntity contact){
		contact.setCreateDate(Date.valueOf(LocalDate.now()));
		contact.setStatus(0);
		return contactRepository.save(contact);
	}
	public void deleteContact(long id){
		 contactRepository.deleteById(id);
	}
}
