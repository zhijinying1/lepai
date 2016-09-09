package com.zhijinying.lepaiyizu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhijinying.lepaiyizu.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister, btnLogin;
    String name, password;
    EditText nameEditText, passwordEditText;

    SharedPreferences sPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();

        nameEditText = (EditText) findViewById(R.id.userName);
        passwordEditText = (EditText) findViewById(R.id.userPwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        sPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {

        name = nameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        switch (v.getId()) {
            case R.id.btn_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_login:
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    if (name.equals(sPreferences.getString("name", name)) &&
                            password.equals(sPreferences.getString("password", password))) {
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "用户名密码错误", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }
}
