package com.cheise_proj.data.repository.contact;

import com.cheise_proj.data.mapper.ContactEntityDataMapper;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.domain.entity.ContactEntity;
import com.cheise_proj.domain.repository.ContactRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Contact Repository impl class
 * class implement contact repository
 * {@see package com.cheise_proj.domain.repository.ContactRepository}
 */
public class ContactRepositoryImpl implements ContactRepository {
    private LocalDataSource localDataSource;
    private ContactEntityDataMapper contactEntityDataMapper;

    /**
     * Constructor
     *
     * @param localDataSource         provide local data source
     * @param contactEntityDataMapper provide contact entity mapper
     */
    @Inject
    public ContactRepositoryImpl(LocalDataSource localDataSource, ContactEntityDataMapper contactEntityDataMapper) {
        this.localDataSource = localDataSource;
        this.contactEntityDataMapper = contactEntityDataMapper;
    }

    @Override
    public Observable<Integer> createContact(int userId, String name, String contact, String email) {
        return localDataSource.createContact(userId, name, contact, email).toObservable();
    }

    @Override
    public Observable<List<ContactEntity>> getAllContacts(int userId) {
        return localDataSource.getAllContacts(userId)
                .map(contactDataList -> contactEntityDataMapper.fromList(contactDataList))
                .toObservable();
    }

    @Override
    public Observable<ContactEntity> getContact(int userId, int contactId) {
        return localDataSource.getContact(userId, contactId)
                .map(contactData -> contactEntityDataMapper.from(contactData))
                .toObservable();
    }
}
