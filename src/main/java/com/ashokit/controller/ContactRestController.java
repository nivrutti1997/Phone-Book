package com.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.constants.AppConstants;
import com.ashokit.entities.Contact;
import com.ashokit.props.AppProperties;
import com.ashokit.services.ContactServiceImpl;

@RestController
@CrossOrigin
public class ContactRestController {
	
	@Autowired
	ContactServiceImpl contactServiceImpl;
	
	@Autowired
	AppProperties appProps;
	
	@PostMapping("/contact")
	public String saveContact(@RequestBody Contact contact) {
		Map<String, String> messages = appProps.getMessages();
		if (contactServiceImpl.saveContact(contact)) {
			return messages.get(AppConstants.CONTACT_SAVE_SUCC);
		}
		return messages.get(AppConstants.CONTACT_SAVE_FAIL);
	}
	
	@GetMapping("/contact/{id}")
	public Contact updateContact(@PathVariable(name = "id") Integer contId) {
		return contactServiceImpl.getContactById(contId);
	}
	
	@GetMapping("/contact")
	public List<Contact> getAllContacts(){
		return  contactServiceImpl.getAllContacts();
	}
	
	@DeleteMapping("contact/{id}")
	public String deleteContact(@PathVariable("id") Integer contId) {
		boolean delContact = contactServiceImpl.deleteContactById(contId);
		Map<String, String> messages = appProps.getMessages();
		if (delContact) {
			return messages.get(AppConstants.CONTACT_DEL_SUCC);
		}
		else {
			return messages.get(AppConstants.CONTACT_DEL_FAIL);
		}
	}
}
