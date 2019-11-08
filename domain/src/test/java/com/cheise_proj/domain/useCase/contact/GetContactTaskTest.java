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
 * Get Contact Task test, use case
 */
@RunWith(JUnit4.class)
public class GetContactTaskTest {
    private GetContactTask getContactTask;
    @Mock
    private ContactRepository contactRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getContactTask = new GetContactTask(contactRepository, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void Get_Single_Contact_with_params_success() {
        ContactEntity inputs = TestContactGenerator.getContact();
        Mockito.when(contactRepository.getContact(inputs.getUserId(), inputs.getId())).thenReturn(Observable.just(inputs));
        getContactTask.buildCase(getContactTask.new Params(inputs.getUserId(), inputs.getId()))
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(contactEntity -> contactEntity.getContact().equals(inputs.getContact()))
                .assertComplete();
    }

    @Test
    public void Get_Single_Contact_with_params_failed() {
        String errorMessage = "An error occurred";
        ContactEntity inputs = TestContactGenerator.getContact();
        Mockito.when(contactRepository.getContact(inputs.getUserId(), inputs.getId())).thenReturn(Observable.error(new Throwable(errorMessage)));
        getContactTask.buildCase(getContactTask.new Params(inputs.getUserId(), inputs.getId()))
                .test()
                .assertSubscribed()
                .assertError(throwable -> throwable.getLocalizedMessage().equals(errorMessage))
                .assertNotComplete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Get_Single_Contact_with_null_params_throws_exception() {
        getContactTask.buildCase(null);
    }
}