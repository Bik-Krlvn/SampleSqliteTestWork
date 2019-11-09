package com.cheise_proj.samplesqlitetestwork.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.presentation.viewmodel.ContactViewModel;
import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.adapter.ContactAdapter;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseFragment;

import java.util.Objects;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ContactListFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ContactViewModel contactViewModel;
    private ContactAdapter adapter;
    private int userId;

    public ContactListFragment() {
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
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);

        adapter = new ContactAdapter(new DiffUtil.ItemCallback<Contact>() {
            @Override
            public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
                return oldItem.getId() == newItem.getId()
                        && oldItem.getContact().equals(newItem.getContact());
            }
        });
        adapter.setContactItemClickListener(data -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+data));
            Objects.requireNonNull(getContext()).startActivity(callIntent);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userId = preferenceUtil.getSession().getUserId();
        initRecyclerView();
        configViewModel();
    }

    private void initRecyclerView() {
        recyclerView.hasFixedSize();
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    private void configViewModel() {
        contactViewModel = new ViewModelProvider(this, viewModelFactory).get(ContactViewModel.class);
        contactViewModel.getAllContacts(userId);
        subscribers();

    }

    private void subscribers() {
        contactViewModel.contactListLive.observe(getViewLifecycleOwner(), contacts -> {
            adapter.submitList(contacts);
            recyclerView.setAdapter(adapter);
        });
    }


}

