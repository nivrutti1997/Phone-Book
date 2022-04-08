package com.ashokit.services;

import java.util.List;

import com.ashokit.entities.Contact;

public interface ContactService {
	
	public boolean saveContact (Contact contact);

	public List<Contact> getAllContacts();

	public Contact getContactById (Integer contactId);

	public boolean deleteContactById (Integer contactId);
	
}
