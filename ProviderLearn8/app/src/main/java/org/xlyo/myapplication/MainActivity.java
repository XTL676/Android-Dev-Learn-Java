package org.xlyo.myapplication;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xlyo.myapplication.bean.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    private final String[] perms = new String[]{READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getPermissions();
        ((ListView) findViewById(R.id.contact_list)).setAdapter(new ContactAdapter(getContacts()));
    }

    public void getPermissions(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return;
        List<String> list = Arrays.stream(perms)
                .filter(perm -> checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            Toast.makeText(this, "所有权限已授权", Toast.LENGTH_SHORT).show();
            return;
        }

        requestPermissions(list.toArray(new String[0]), 1);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 1) return;

        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i].equals(READ_CONTACTS) &&
                    grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "用户确认授权", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "用户取消授权", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("Range")
    public List<Contact> getContacts() {
        List<Contact> result = new ArrayList<>();

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
             String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
             String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
             int isHas = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
             if (isHas <= 0) return result;

            Cursor c = getContentResolver().query(
                     ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                     null,
                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                     null,
                     null);
             while (c.moveToNext()) {
                 String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).trim();
                 number = number.replace(" ", "")
                         .replace("-", "")
                         .replace("(","")
                         .replace(")","");
                 result.add(new Contact(name, number));
             }

             c.close();
        }

        cursor.close();
        return result;
    }
}