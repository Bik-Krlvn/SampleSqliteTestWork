package com.cheise_proj.samplesqlitetestwork.ui.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.presentation.viewmodel.ContactViewModel;
import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseFragment;

import java.util.Objects;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class CreateContactFragment extends BaseFragment {
    private ContactViewModel contactViewModel;
    private EditText mName;
    private EditText mEmail;
    private EditText mTelephone;


    public CreateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail = view.findViewById(R.id.et_contact_email);
        mName = view.findViewById(R.id.et_contact_name);
        mTelephone = view.findViewById(R.id.et_contact_tel);
        Button btnCreate = view.findViewById(R.id.btn_create_contact);
        btnCreate.setOnClickListener(v -> validateContactData());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configViewModel();
    }

    /**
     * Initialize view model
     */
    private void configViewModel() {
        contactViewModel = new ViewModelProvider(this, viewModelFactory).get(ContactViewModel.class);
        subScribe();
    }

    /**
     * Observe on live data
     */
    private void subScribe() {
        contactViewModel.insertedRow.observe(getViewLifecycleOwner(), integer -> {
                    if (integer > 0) {
                        Toast.makeText(getContext(), "contact created", Toast.LENGTH_SHORT).show();
                        Objects.requireNonNull(getActivity()).onBackPressed();
                    } else {
                        Toast.makeText(getContext(), "save contact failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void validateContactData() {
        String name = mName.getText().toString();
        String tel = mTelephone.getText().toString();
        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mName.setError("provide contact name");
        }
        if (TextUtils.isEmpty(tel)) {
            mTelephone.setError("provide telephone");
        } else {
            Contact contact = new Contact();
            contact.setName(name);
            contact.setEmail(email);
            contact.setContact(tel);
            contact.setUserId(preferenceUtil.getSession().getUserId());
            contactViewModel.createContact(contact);
        }
    }
}
