package com.cheise_proj.local_sqlite.mapper.user;

import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.local_sqlite.mapper.base.Mapper;
import com.cheise_proj.local_sqlite.model.ContactLocal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * ContactDataLocalMapper, provide map model from local to data layer model
 * class implements mapper {@see package com.cheise_proj.local_sqlite.mapper.base.Mapper}
 */
public class ContactDataLocalMapper implements Mapper<ContactData, ContactLocal> {
    @Inject
    public ContactDataLocalMapper() {
    }

    @Override
    public ContactData from(ContactLocal contactLocal) {
        return new ContactData(
                contactLocal.getId(),
                contactLocal.getUserId(),
                contactLocal.getName(),
                contactLocal.getContact(),
                contactLocal.getEmail()
        );
    }

    @Override
    public ContactLocal to(ContactData contactData) {
        return new ContactLocal(
                contactData.getId(),
                contactData.getUserId(),
                contactData.getName(),
                contactData.getContact(),
                contactData.getEmail()
        );
    }

    public List<ContactData> fromList(List<ContactLocal> contactLocalList) {
        List<ContactData> contactDataList = new ArrayList<>();
        for (ContactLocal contactLocal : contactLocalList) {
            contactDataList.add(from(contactLocal));
        }
        return contactDataList;
    }

    public List<ContactLocal> toList(List<ContactData> contactDataList) {
        List<ContactLocal> contactLocalList = new ArrayList<>();
        for (ContactData contactData : contactDataList) {
            contactLocalList.add(to(contactData));
        }
        return contactLocalList;
    }
}
