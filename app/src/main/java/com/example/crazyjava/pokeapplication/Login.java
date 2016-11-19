package com.example.crazyjava.pokeapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button botonEntrar;
    EditText txtusuario, txtpassword;
    TextView lblLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusuario = (EditText)findViewById(R.id.txtUsuario);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        lblLogin = (TextView)findViewById(R.id.lblErrorLogin);
        botonEntrar = (Button)findViewById(R.id.btnEntrar);

        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtusuario.getText().toString();
                String pass = txtpassword.getText().toString();

                if(user.equals("a") && pass.equals("aa")){

                    lblLogin.setText("Usuario OK");

                }
                else {
                    lblLogin.setText("Usuario o contraseña incorrecto");
                }

            }
        });



    }
}
