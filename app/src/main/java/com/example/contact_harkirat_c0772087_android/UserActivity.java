package com.example.contact_harkirat_c0772087_android;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    ListView listView;
    DataBaseHelper mDatabase;
    List<User> userList;
    SearchView searchView;
    List<User> filterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        listView = findViewById(R.id.listView);
        userList = new ArrayList<>();
        mDatabase = new DataBaseHelper(this);
        filterList = new ArrayList<>();
        searchView = findViewById(R.id.searchView);
        loadUsers();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(!newText.isEmpty()) {
                    filterList.clear();
                    for (int i = 0; i < UserActivity.this.userList.size(); i++) {
                        User getcontact = UserActivity.this.userList.get(i);
                        if (getcontact.firstname.contains(newText)) {
                            filterList.add(getcontact);
                        }
                    }
                    UserList userList = new UserList(UserActivity.this,R.layout.list_layout,filterList,mDatabase);
                    listView.setAdapter(userList);
                }
                if(newText.isEmpty()){
                    UserList userList = new UserList(UserActivity.this,R.layout.list_layout, UserActivity.this.userList,mDatabase);
                    listView.setAdapter(userList);
                }
                return false;
            }
        });
    }

    private void loadUsers() {
        Cursor cursor = mDatabase.getAllUsers();
        if (cursor.moveToFirst()) {
            System.out.println("if is Contact");
            do {
                System.out.println("do is Contact");
                this.userList.add(new User(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
                System.out.println(cursor.getInt(0));
                System.out.println(cursor.getString(1));

            } while (cursor.moveToNext());
            cursor.close();
            System.out.println("");

            System.out.println("data loaded");


            UserList userList = new UserList(this,R.layout.list_layout, this.userList,mDatabase);
            listView.setAdapter(userList);

        }
    }
}
