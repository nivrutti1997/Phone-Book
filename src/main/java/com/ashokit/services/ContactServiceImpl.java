package com.ashokit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.entities.Contact;
import com.ashokit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepo;

	@Override
	public boolean saveContact(Contact contact) {
		contact.setActiveSw("Y");
		Contact cont = contactRepo.save(contact);
		if (cont.getContactId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Contact> getAllContacts() {
		Contact contact = new Contact();
		contact.setActiveSw("Y");
		Example<Contact> example = Example.of(contact);
		return contactRepo.findAll(example);
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> byId = contactRepo.findById(contactId);
		if (byId.isPresent()) {
			Contact contact = byId.get();
			return contact;
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		Optional<Contact> contact = contactRepo.findById(contactId);
		if (contact.isPresent()) {
			Contact contact1 = contact.get();
			contact1.setActiveSw("N");
			contactRepo.save(contact1);
			return true;
		}
		return false;
	}

}
