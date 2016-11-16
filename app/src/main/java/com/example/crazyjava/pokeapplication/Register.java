package com.example.crazyjava.pokeapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class Register extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = (EditText) findViewById(R.id.editTextUser);
        final EditText pass = (EditText) findViewById(R.id.editTextPassword);
        final TextView text1 = (TextView)findViewById(R.id.textViewUser);
        final TextView text2 = (TextView)findViewById(R.id.textViewPassword);
        final TextView text3 = (TextView)findViewById(R.id.textViewResult);
        final Button button = (Button) findViewById(R.id.button);

        User users =new User(this, "User", null, 1);
        final SQLiteDatabase bbdd = users.getWritableDatabase();


        name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                text1.setText("...");
            }

            @Override
            public void afterTextChanged(Editable s)
            {

                if(name.getText().toString().equalsIgnoreCase(""))
                {
                    text1.setText("Rellene el campo");
                    button.setEnabled(false);
                }
                else
                {

                    int state = 0;
                    String campos[] = new String[]{"Name", "Pass"};
                    Cursor cursorbbdd = bbdd.query("User", campos, null, null, null, null, null);
                    if (cursorbbdd.moveToFirst())
                    {
                        do
                        {
                            String nombre = cursorbbdd.getString(0);

                            if(nombre.equalsIgnoreCase(name.getText().toString())) state = 1 ;
                        }
                        while (cursorbbdd.moveToNext());

                        if(state == 1)
                        {
                            text1.setText("Usuario no disponible");
                            button.setEnabled(false);
                        }
                        else
                        {
                            text1.setText("OK");
                            if(text2.getText().toString().equalsIgnoreCase("OK"))
                            {
                                button.setEnabled(true);
                            }
                        }

                    }
                }
            }


        });


        pass.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                text2.setText("...");
            }

            @Override
            public void afterTextChanged(Editable s)
            {

                if(pass.getText().toString().equalsIgnoreCase(""))
                {
                    text2.setText("Rellene el campo");
                    button.setEnabled(false);
                }
                else
                {
                    if(pass.getText().toString().length() < 8)
                    {
                        text2.setText("Demasiado Corta");
                    }
                    else
                    {

                        text2.setText("OK");
                        if(text1.getText().toString().equalsIgnoreCase("OK"))
                        {
                            button.setEnabled(true);
                        }
                    }
                }
            }


        });

        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                ContentValues valores = new ContentValues();
                valores.put("Name", name.getText().toString());
                valores.put("Pass", pass.getText().toString());

                bbdd.insert("User", null, valores);

                text3.setText("Usuario Registrado Satisfactoriamente");
            }
        });

        name.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
               if(name.getText().toString().equalsIgnoreCase("Usuario")) name.setText("");

            }
        });

        pass.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(pass.getText().toString().equalsIgnoreCase("Password")) pass.setText("");

            }
        });

    }
}