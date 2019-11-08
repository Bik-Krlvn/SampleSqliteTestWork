package com.cheise_proj.domain.useCase.contact;

import com.cheise_proj.domain.repository.ContactRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Create Contact Task, create user contact use case
 */
public class CreateContactTask extends ObservableUseCase<Integer, CreateContactTask.Params> {
    private ContactRepository contactRepository;

    /**
     * Constructor
     * @param contactRepository provide contact repository
     * @param background provide rx java background scheduler
     * @param foreground provide rxj ava main thread scheduler
     */
    @Inject
    public CreateContactTask(ContactRepository contactRepository, Scheduler background, Scheduler foreground) {
        super(background, foreground);
        this.contactRepository = contactRepository;
    }


    @Override
    protected Observable<Integer> generateObservable(@Nullable Params params) {
        if (params == null) throw new IllegalArgumentException("create contact can't be null");
        return contactRepository.createContact(params.userId, params.name, params.contact, params.email);
    }

    /**
     * Create Contact Params Object
     */
    public class Params {
        private int userId;
        private String name;
        private String contact;
        private String email;

        /**
         * Constructor
         *
         * @param userId  input userId
         * @param name    input contact name
         * @param contact input contact number
         * @param email   input contact email
         */
        public Params(int userId, String name, String contact, String email) {
            this.userId = userId;
            this.name = name;
            this.contact = contact;
            this.email = email;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
