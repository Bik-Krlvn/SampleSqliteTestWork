package com.cheise_proj.domain.useCase.contact;

import com.cheise_proj.domain.entity.ContactEntity;
import com.cheise_proj.domain.repository.ContactRepository;
import com.cheise_proj.domain.utils.TestContactGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * CreateContactTask class, unit test
 */
@RunWith(JUnit4.class)
public class CreateContactTaskTest {
    private CreateContactTask createContactTask;
    @Mock
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createContactTask = new CreateContactTask(contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void Create_new_contact_success() {
        int insertRow = 1;
        ContactEntity newContact = TestContactGenerator.createContact();
        Mockito.when(contactRepository.createContact(
                newContact.getUserId(), newContact.getName(), newContact.getContact(), newContact.getEmail()
        ))
                .thenReturn(Observable.just(insertRow));
        createContactTask.buildCase(createContactTask.new Params(
                newContact.getUserId(), newContact.getName(), newContact.getContact(), newContact.getEmail()
        ))
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(integer -> integer == 1)
                .assertComplete();
    }

    @Test
    public void Create_new_contact_failed() {
        String errorMessage = "insert action failed";
        ContactEntity newContact = TestContactGenerator.createContact();
        Mockito.when(contactRepository.createContact(
                newContact.getUserId(), newContact.getName(), newContact.getContact(), newContact.getEmail()
        ))
                .thenReturn(Observable.error(new Throwable(errorMessage)));
        createContactTask.buildCase(createContactTask.new Params(
                newContact.getUserId(), newContact.getName(), newContact.getContact(), newContact.getEmail()
        ))
                .test()
                .assertSubscribed()
                .assertError(throwable -> throwable.getLocalizedMessage().equals(errorMessage))
                .assertNotComplete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Create_new_contact_with_null_params_throws_exception() {
        createContactTask.buildCase(null);
    }
}