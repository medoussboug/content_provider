package com.example.crud1_content_provider.ui;

import static com.example.crud1_content_provider.data.PersonContract.PersonContractEntry.COLUMN_NAME_MAJOR;
import static com.example.crud1_content_provider.data.PersonContract.PersonContractEntry.COLUMN_NAME_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud1_content_provider.R;
import com.example.crud1_content_provider.data.MyContentProvider;
import com.example.crud1_content_provider.data.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText etName = findViewById(R.id.etName);
        EditText etMajor = findViewById(R.id.etMajor);
        Button btAdd = findViewById(R.id.btAdd);
        Button btLoad = findViewById(R.id.btLoad);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_NAME_NAME, etName.getText().toString());
                contentValues.put(COLUMN_NAME_MAJOR, etMajor.getText().toString());
                getContentResolver().insert(MyContentProvider.CONTENT_URI, contentValues);
            }
        });

        btLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Person> personList = new ArrayList<>();
                Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null,null, null, COLUMN_NAME_NAME);
                while (cursor.moveToNext()) {
                    personList.add(new Person(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
                }
                cursor.close();
                Toast.makeText(getApplicationContext(), personList.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}