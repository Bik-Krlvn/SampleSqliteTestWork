package com.cheise_proj.data.repository.contact;

import com.cheise_proj.data.mapper.ContactEntityDataMapper;
import com.cheise_proj.data.model.ContactData;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.data.utils.TestContactGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;

/**
 * ContactRepository impl test
 */
public class ContactRepositoryImplTest {
    private ContactRepositoryImpl contactRepository;
    @Mock
    private LocalDataSource localDataSource;
    private ContactEntityDataMapper contactEntityDataMapper = new ContactEntityDataMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        contactRepository = new ContactRepositoryImpl(localDataSource, contactEntityDataMapper);
    }

    @Test
    public void createContact_success() {
        int insertedRow = 1;
        ContactData newContact = TestContactGenerator.createContact();
        Mockito.when(localDataSource.createContact(
                newContact.getUserId(),
                newContact.getName(),
                newContact.getContact(),
                newContact.getEmail()
        )).thenReturn(Single.just(insertedRow));
        contactRepository.createContact(newContact.getUserId(),
                newContact.getName(),
                newContact.getContact(),
                newContact.getEmail())
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(integer -> integer == insertedRow)
                .assertComplete();
    }

    @Test
    public void Get_All_Contact_success() {
        ContactData inputs = TestContactGenerator.createContact();
        List<ContactData> getContacts = TestContactGenerator.getAllContacts();
        Mockito.when(localDataSource.getAllContacts(
                inputs.getUserId()
        )).thenReturn(Single.just(getContacts));
        contactRepository.getAllContacts(inputs.getUserId())
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(contactEntityList -> contactEntityList.size() == getContacts.size())
                .assertComplete();
    }

    @Test
    public void Get_Single_Contact_success() {
        ContactData inputs = TestContactGenerator.createContact();
        Mockito.when(localDataSource.getContact(
                inputs.getUserId(), inputs.getId()
        )).thenReturn(Single.just(inputs));
        contactRepository.getContact(inputs.getUserId(), inputs.getId())
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(contactEntity -> contactEntity.getEmail().equals(inputs.getEmail()))
                .assertComplete();

    }
}