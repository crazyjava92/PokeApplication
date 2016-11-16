package com.example.crazyjava.pokeapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User users =new User(this, "User", null, 1);
        final SQLiteDatabase bbdd = users.getWritableDatabase();

        if(bbdd != null)
        {
            bbdd.execSQL("INSERT INTO User VALUES ('Admin' , 'Admin')");
        }


        final EditText name = (EditText) findViewById(R.id.editTextLUser);
        final EditText pass = (EditText) findViewById(R.id.editTextLPass);
        final TextView text1 = (TextView)findViewById(R.id.textViewLUser);
        final TextView text2 = (TextView)findViewById(R.id.textViewLPass);
        final TextView text3 = (TextView)findViewById(R.id.textViewStado);
        final Button login = (Button) findViewById(R.id.buttonLogin);
        final Button registrar = (Button) findViewById(R.id.buttonRegister);

        registrar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intencion2= new Intent (v.getContext(), Register.class);
                startActivity(intencion2);

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
                    login.setEnabled(false);
                }
                else
                {
                    text1.setText("OK");
                    if(text2.getText().toString().equalsIgnoreCase("OK"))
                    {
                        login.setEnabled(true);
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
                    login.setEnabled(false);
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
                            login.setEnabled(true);
                        }
                    }
                }
            }


        });


        login.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                int state = 0;
                String campos[] = new String[]{"Name", "Pass"};
                Cursor cursorbbdd = bbdd.query("User", campos, null, null, null, null, null);
                if (cursorbbdd.moveToFirst())
                {
                    do
                    {
                        String nombre = cursorbbdd.getString(0);
                        String password = cursorbbdd.getString(1);

                        if(nombre.equalsIgnoreCase(name.getText().toString()) &&
                                password.equalsIgnoreCase(pass.getText().toString())) state = 1 ;
                    }
                    while (cursorbbdd.moveToNext());


                    if(state == 0)
                    {
                        text3.setText("Datos de sesion Incorrectos");
                    }
                    else
                    {

                        Intent intencion = new Intent (v.getContext(), Main.class);
                        intencion.putExtra("user", name.getText().toString());
                        startActivity(intencion);

                    }

                }


            }
        });

    }
}
