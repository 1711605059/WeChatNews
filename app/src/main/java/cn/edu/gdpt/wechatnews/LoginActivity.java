package cn.edu.gdpt.wechatnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText login_username;
    private EditText login_password;
    private Button login_enter;
    private TextView login_forgetpassword;
    private TextView login_join;
    SPUtils spUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        spUtils = new SPUtils(this);
       // startActivity(new Intent(this, MainActivity.class));
    }

    private void initView() {
        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login_enter = (Button) findViewById(R.id.login_enter);
        login_forgetpassword = (TextView) findViewById(R.id.login_forgetpassword);
        login_join = (TextView) findViewById(R.id.login_join);

        login_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_enter:
                String id = login_username.getText().toString();
                String pass = login_password.getText().toString();
                spUtils.putString("id",id);
                spUtils.putString("pass",pass);
                spUtils.putBoolean("login",true);
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void submit() {
        // validate
        String username = login_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名:", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = login_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码:", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
