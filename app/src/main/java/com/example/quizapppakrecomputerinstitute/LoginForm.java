package com.example.quizapppakrecomputerinstitute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {
   private EditText e1;
   private EditText e2;
   private Button login;
   private TextView Info;
   private int counter = 5;
   private Button about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        e1 = (EditText ) findViewById(R.id.username);
        e2 = (EditText ) findViewById(R.id.password);
        Info = findViewById(R.id.tvinfo);
        login = (Button ) findViewById(R.id.login);
        Info.setText("No of attempts remaining: 5");

        about =(Button)findViewById(R.id.ab);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myInt=new Intent(LoginForm.this, com.example.quizapppakrecomputerinstitute.about.class);
                startActivity(myInt);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(e1.getText().toString(), e2.getText().toString());


            }
        });





    }

    private void validate( String userName, String userPassword){

        if((userName.equals("arazoo")) && (userPassword.equals("1234"))){
            Toast.makeText(this,"Success Login",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginForm.this,StartingScreenActivity.class);

            startActivity(intent);




        }else{
            Toast.makeText(this,"Wrong username or password",Toast.LENGTH_LONG).show();
            counter--;
            Info.setText("No of attempts remaining: " + String.valueOf(counter) );
            if(counter == 0){


                login.setEnabled(false);
            }


        }


    }




    }
