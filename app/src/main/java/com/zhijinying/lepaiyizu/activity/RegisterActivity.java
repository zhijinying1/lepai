package com.zhijinying.lepaiyizu.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhijinying.lepaiyizu.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button btnSubmit;
    EditText nameEditText, passwordEditText, emailEditText;

    SharedPreferences sPreferences;
    SharedPreferences.Editor editor;
    String name, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText) findViewById(R.id.userName);
        passwordEditText = (EditText) findViewById(R.id.userPwd);
        emailEditText = (EditText) findViewById(R.id.userMail);
        textView = (TextView) findViewById(R.id.tvRegister);
        btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);

        sPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        editor = sPreferences.edit();
    }

    @Override
    public void onClick(View v) {

        name = nameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        email = emailEditText.getText().toString();

        switch (v.getId()) {
            case R.id.submit:
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(email)) {

                    editor.putString("name", name);
                    editor.putString("password", password);
                    editor.putString("email", email);
                    editor.commit();
                    textView.setText("注册成功");

                } else {
                    Toast.makeText(getApplicationContext(), "用户名密码或邮箱均不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBack:
                this.finish();
                break;

            default:
                break;
        }
    }
}
