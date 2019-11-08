package com.cheise_proj.presentation.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cheise_proj.domain.repository.ContactRepository;
import com.cheise_proj.domain.useCase.contact.CreateContactTask;
import com.cheise_proj.domain.useCase.contact.GetAllContactTask;
import com.cheise_proj.domain.useCase.contact.GetContactTask;
import com.cheise_proj.presentation.mapper.ContactEntityMapper;
import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.presentation.utils.TestContactGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * ContactViewModel test class
 */
@RunWith(JUnit4.class)
public class ContactViewModelTest {
    private ContactViewModel contactViewModel;
    @Mock
    ContactRepository contactRepository;
    private ContactEntityMapper contactEntityMapper = new ContactEntityMapper();

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CreateContactTask createContactTask = new CreateContactTask(
                contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
        GetContactTask getContactTask = new GetContactTask(
                contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
        GetAllContactTask getAllContactTask = new GetAllContactTask(
                contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
        contactViewModel = new ContactViewModel(
                createContactTask, getContactTask, getAllContactTask, contactEntityMapper);
    }

    @Test
    public void createContact() {
        int insertedRow = 1;
        Contact newContact = TestContactGenerator.createContact();
        Mockito.when(contactRepository.createContact(
                newContact.getUserId(),
                newContact.getName(),
                newContact.getContact(),
                newContact.getEmail()
        )).thenReturn(Observable.just(insertedRow));
        contactViewModel.createContact(newContact);
        Mockito.verify(contactRepository, times(1))
                .createContact(newContact.getUserId(),
                        newContact.getName(),
                        newContact.getContact(),
                        newContact.getEmail());
    }

    @Test
    public void getContactById() {
        Contact inputs = TestContactGenerator.getContact();
        Mockito.when(contactRepository.getContact(inputs.getUserId(), inputs.getId()))
                .thenReturn(Observable.just(contactEntityMapper.from(inputs)));
        contactViewModel.getContactById(inputs.getUserId(), inputs.getId());
        Mockito.verify(contactRepository, times(1)).getContact(inputs.getUserId(), inputs.getId());
    }

    @Test
    public void getAllContacts() {
        Contact inputs = TestContactGenerator.getContact();
        List<Contact> getContacts = TestContactGenerator.getAllContacts();
        Mockito.when(contactRepository.getAllContacts(inputs.getUserId()))
                .thenReturn(Observable.just(contactEntityMapper.fromList(getContacts)));
        contactViewModel.getAllContacts(inputs.getUserId());
        Mockito.verify(contactRepository, times(1)).getAllContacts(inputs.getUserId());
    }
}