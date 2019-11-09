package com.cheise_proj.samplesqlitetestwork.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.samplesqlitetestwork.R;

/**
 * Custom Adapter for contact list
 */
public class ContactAdapter extends ListAdapter<Contact, ContactAdapter.ContactViewHolder> {
    private ContactItemClickListener<String> contactItemClickListener;


    public ContactAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(getItem(position), contactItemClickListener, position);

    }

    public void setContactItemClickListener(ContactItemClickListener<String> contactItemClickListener) {
        this.contactItemClickListener = contactItemClickListener;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView mRowNo;
        private TextView mName;
        private TextView mTelephone;
        private TextView mEmail;


        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mRowNo = itemView.findViewById(R.id.tv_num);
            mName = itemView.findViewById(R.id.tv_name);
            mTelephone = itemView.findViewById(R.id.tv_contact);
            mEmail = itemView.findViewById(R.id.tv_email);
        }

        void bind(Contact item, ContactItemClickListener<String> contactItemClickListener, int position) {
            int pos =  1+position;
            mRowNo.setText( ""+pos);
            mName.setText(item.getName());
            mTelephone.setText(item.getContact());
            mEmail.setText(item.getEmail());
            itemView.setOnClickListener(v -> contactItemClickListener.onClick(item.getContact()));
        }
    }
}
