package com.cheise_proj.presentation.mapper;

import com.cheise_proj.domain.entity.ContactEntity;
import com.cheise_proj.presentation.mapper.base.Mapper;
import com.cheise_proj.presentation.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactEntityMapper, provides maps from contact model to domain layer
 */
public class ContactEntityMapper implements Mapper<ContactEntity, Contact> {
    @Override
    public ContactEntity from(Contact contact) {
        return new ContactEntity(
                contact.getId(),
                contact.getUserId(),
                contact.getName(),
                contact.getContact(),
                contact.getEmail()
        );
    }

    @Override
    public Contact to(ContactEntity contactEntity) {
        return new Contact(
                contactEntity.getId(),
                contactEntity.getUserId(),
                contactEntity.getName(),
                contactEntity.getContact(),
                contactEntity.getEmail()
        );
    }

    public List<ContactEntity> fromList(List<Contact> contactDataList) {
        List<ContactEntity> contactEntityList = new ArrayList<>();
        for (Contact contact : contactDataList) {
            contactEntityList.add(from(contact));
        }
        return contactEntityList;
    }

    public List<Contact> toList(List<ContactEntity> contactEntityList) {
        List<Contact> contactList = new ArrayList<>();
        for (ContactEntity contactEntity : contactEntityList) {
            contactList.add(to(contactEntity));
        }
        return contactList;
    }
}
