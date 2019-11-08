package com.cheise_proj.data.mapper;

import com.cheise_proj.data.mapper.base.Mapper;
import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.domain.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * ContactEntityDataMapper, maps contact data to contact entity in domain layer
 */
public class ContactEntityDataMapper implements Mapper<ContactEntity, ContactData> {
    @Inject
    public ContactEntityDataMapper() {
    }

    @Override
    public ContactEntity from(ContactData contactData) {
        return new ContactEntity(
                contactData.getId(),
                contactData.getUserId(),
                contactData.getName(),
                contactData.getContact(),
                contactData.getEmail()
        );
    }

    @Override
    public ContactData to(ContactEntity contactEntity) {
        return new ContactData(
                contactEntity.getId(),
                contactEntity.getUserId(),
                contactEntity.getName(),
                contactEntity.getContact(),
                contactEntity.getEmail()
        );
    }

    public List<ContactEntity> fromList(List<ContactData> contactDataList) {
        List<ContactEntity> contactEntityList = new ArrayList<>();
        for (ContactData contactData1 : contactDataList) {
            contactEntityList.add(from(contactData1));
        }
        return contactEntityList;
    }

    public List<ContactData> toList(List<ContactEntity> contactEntityList) {
        List<ContactData> contactDataList = new ArrayList<>();
        for (ContactEntity contactEntity : contactEntityList) {
            contactDataList.add(to(contactEntity));
        }
        return contactDataList;
    }
}
