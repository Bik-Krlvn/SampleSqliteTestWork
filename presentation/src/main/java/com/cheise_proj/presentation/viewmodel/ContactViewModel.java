package com.cheise_proj.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cheise_proj.domain.useCase.contact.CreateContactTask;
import com.cheise_proj.domain.useCase.contact.GetAllContactTask;
import com.cheise_proj.domain.useCase.contact.GetContactTask;
import com.cheise_proj.presentation.mapper.ContactEntityMapper;
import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.presentation.viewmodel.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * ContactViewModel, provides ui layer with specified data
 */
public class ContactViewModel extends BaseViewModel {
    private CreateContactTask createContactTask;
    private GetContactTask getContactTask;
    private GetAllContactTask getAllContactTask;
    private ContactEntityMapper contactEntityMapper;
    private MutableLiveData<Integer> _insertRow = new MutableLiveData<>();
    private MutableLiveData<Contact> _contact = new MutableLiveData<>();
    private MutableLiveData<List<Contact>> _contactList = new MutableLiveData<>();
    public LiveData<Integer> insertedRow = _insertRow;
    public LiveData<Contact> contactLive = _contact;
    public LiveData<List<Contact>> contactListLive = _contactList;


    /**
     * Constructor
     *
     * @param createContactTask   provide instance of create contact task
     * @param getContactTask      provide instance of get contact task
     * @param getAllContactTask   provide instance of get all contact task
     * @param contactEntityMapper provide contact entity mapper
     */
    @Inject
    public ContactViewModel(CreateContactTask createContactTask, GetContactTask getContactTask, GetAllContactTask getAllContactTask, ContactEntityMapper contactEntityMapper) {
        this.createContactTask = createContactTask;
        this.getContactTask = getContactTask;
        this.getAllContactTask = getAllContactTask;
        this.contactEntityMapper = contactEntityMapper;
    }

    public void createContact(Contact contact) {
        disposable.add(createContactTask.buildCase(createContactTask.new Params(
                contact.getUserId(),
                contact.getName(),
                contact.getContact(),
                contact.getEmail()
        ))
                .subscribe(integer -> _insertRow.setValue(integer)));
    }

    public void getContactById(int userId, int contactId) {
        disposable.add(getContactTask.buildCase(
                getContactTask.new Params(userId, contactId))
                .map(contactEntity -> contactEntityMapper.to(contactEntity))
                .subscribe(contact -> _contact.setValue(contact))
        );
    }

    public void getAllContacts(int userId) {
        disposable.add(getAllContactTask.buildCase(userId)
                .map(contactEntityList -> contactEntityMapper.toList(contactEntityList))
                .subscribe(contacts -> _contactList.setValue(contacts))
        );
    }
}
