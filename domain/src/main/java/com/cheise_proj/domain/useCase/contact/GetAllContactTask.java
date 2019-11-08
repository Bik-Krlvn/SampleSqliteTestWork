package com.cheise_proj.domain.useCase.contact;

import com.cheise_proj.domain.entity.ContactEntity;
import com.cheise_proj.domain.repository.ContactRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Get All Contact Task, retrieves contact per user case
 */
public class GetAllContactTask extends ObservableUseCase<List<ContactEntity>, Integer> {
    private ContactRepository contactRepository;

    /**
     * Constructor
     * @param contactRepository provide contact repository
     * @param background provide rx java background scheduler
     * @param foreground provide rxj ava main thread scheduler
     */
    @Inject
    public GetAllContactTask(ContactRepository contactRepository, Scheduler background, Scheduler foreground) {
        super(background, foreground);
        this.contactRepository = contactRepository;
    }


    @Override
    protected Observable<List<ContactEntity>> generateObservable(@Nullable Integer integer) {
        if (integer == null) throw new IllegalArgumentException("user id required");
        return contactRepository.getAllContacts(integer);
    }
}
