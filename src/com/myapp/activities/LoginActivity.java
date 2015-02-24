package com.myapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

/**
 * Created by megatron on 15/2/20.
 */
public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActivityCollector.activityList.add(this);

        final EditText account = (EditText) findViewById(R.id.editText_account);
        final EditText pwd = (EditText) findViewById(R.id.editText_pwd);
        final CheckBox remember = (CheckBox) findViewById(R.id.remember);
        Button bt_login = (Button) findViewById(R.id.login);

        SharedPreferences sharedPreferences = getSharedPreferences("userProfile",MODE_PRIVATE);
        if(sharedPreferences!=null){
            String rAccount = sharedPreferences.getString("account",null);
            String rPwd = sharedPreferences.getString("password",null);
            if(rAccount!=null && rPwd!=null){
                Log.d(this.getClass().getSimpleName(),"Remembered user profile.");
                Intent intent = new Intent(LoginActivity.this,MyActivity.class);
                startActivity(intent);
                finish();
            }
        }
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strAccount = account.getText().toString();
                String strPassword = pwd.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences("userProfile",MODE_PRIVATE).edit();
                if(remember.isChecked()){
                    editor.putString("account",strAccount);
                    editor.putString("password", strPassword);
                }
                else
                    editor.clear();
                editor.commit();
                Intent intent = new Intent(LoginActivity.this,MyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}