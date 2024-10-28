package org.xlyo.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xlyo.myapplication.bean.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contact_item, null);
        ((TextView) view.findViewById(R.id.t_name)).setText(((Contact) getItem(i)).getName());
        ((TextView) view.findViewById(R.id.t_phone)).setText(((Contact) getItem(i)).getPhone());
        return view;
    }
}
