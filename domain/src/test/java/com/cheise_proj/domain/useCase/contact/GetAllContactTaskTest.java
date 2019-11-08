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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * GetAllContactTask class test
 */
@RunWith(JUnit4.class)
public class GetAllContactTaskTest {
    private GetAllContactTask getAllContactTask;
    @Mock
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAllContactTask = new GetAllContactTask(contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void GetAllContact_from_Current_User() {
        ContactEntity inputs = TestContactGenerator.getContact();
        List<ContactEntity> getContacts = TestContactGenerator.getAllContacts();
        Mockito.when(contactRepository.getAllContacts(inputs.getUserId()))
                .thenReturn(Observable.just(getContacts));
        getAllContactTask.buildCase(inputs.getUserId())
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(contactEntities -> contactEntities.size() == getContacts.size())
                .assertComplete();
    }

    @Test
    public void Get_All_user_contact_failed() {
        String errorMessage = "An error occurred";
        ContactEntity inputs = TestContactGenerator.getContact();
        Mockito.when(contactRepository.getAllContacts(inputs.getUserId()))
                .thenReturn(Observable.error(new Throwable(errorMessage)));
        getAllContactTask.buildCase(inputs.getUserId())
                .test()
                .assertSubscribed()
                .assertError(throwable -> throwable.getLocalizedMessage().equals(errorMessage))
                .assertNotComplete();

    }

    @Test(expected = IllegalArgumentException.class)
    public void Get_All_user_contact_with_null_params() {
        getAllContactTask.buildCase(null);
    }
}