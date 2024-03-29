package com.example.connectdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button clear, save, get;

    public static final String myPref="myPref";
    public static final String namekey="namekey";
    public static final String phonekey="phoneKey";
    public static final String emailkey="emailKey";

    SharedPreferences sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1=findViewById(R.id.editText1);
        ed2=findViewById(R.id.editText2);
        ed3=findViewById(R.id.editText3);

        clear=findViewById(R.id.button3);
        save=findViewById(R.id.button1);
        get=findViewById(R.id.button2);

        sp=getSharedPreferences(myPref, Context.MODE_PRIVATE);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sp.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(MainActivity.this,"Data is Deleted", Toast.LENGTH_LONG).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=ed1.getText().toString();
                String phone=ed2.getText().toString();
                String email=ed3.getText().toString();

                SharedPreferences.Editor editor = sp.edit();

                editor.putString(namekey, name);
                editor.putString(phonekey, phone);
                editor.putString(emailkey, email);
                editor.commit();
                Toast.makeText(MainActivity.this,"Data Saved Into Shared Preference", Toast.LENGTH_LONG).show();
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sp.getAll().isEmpty()){
                    Toast.makeText(MainActivity.this,"Shared Preference is Empty", Toast.LENGTH_LONG).show();
                }else {
                    String name=sp.getString(namekey,"");
                    String phone=sp.getString(phonekey,"");
                    String email=sp.getString(emailkey,"");

                    ed1.setText(name.toString());
                    ed2.setText(phone.toString());
                    ed3.setText(email.toString());
                }

            }
        });

    }
}