package com.cheise_proj.domain.useCase.contact;

import com.cheise_proj.domain.entity.ContactEntity;
import com.cheise_proj.domain.qualifiers.Background;
import com.cheise_proj.domain.qualifiers.Foreground;
import com.cheise_proj.domain.repository.ContactRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetContactTask extends ObservableUseCase<ContactEntity, GetContactTask.Params> {
    private ContactRepository contactRepository;

    /**
     * Constructor
     * @param contactRepository provide contact repository
     * @param background provide rx java background scheduler
     * @param foreground provide rxj ava main thread scheduler
     */
    @Inject
    public GetContactTask(ContactRepository contactRepository, @Background Scheduler background, @Foreground Scheduler foreground) {
        super(background, foreground);
        this.contactRepository = contactRepository;
    }

    @Override
    protected Observable<ContactEntity> generateObservable(@Nullable Params params) {
        if (params == null) throw new IllegalArgumentException("get contact params can't be null");
        return contactRepository.getContact(params.userId, params.contactId);
    }


    /**
     * Get Contact Task Params Object
     */
    public class Params {
        private int userId;
        private int contactId;

        /**
         * Constructor
         *
         * @param userId    input user id
         * @param contactId input contact id
         */
        public Params(int userId, int contactId) {
            this.userId = userId;
            this.contactId = contactId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }
    }
}
