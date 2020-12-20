package vn.poly.storegreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, passs;
    Button btn_login, btn_createAccount;
    FirebaseAuth auth;
    String perfername = "my_data";
    CheckBox checkBox;
    String checkUser;
    String checkPass;
    TextView txt_ForgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        restoringPreferences();
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.med_lEmail);
        passs = findViewById(R.id.med_lPassword);
        btn_login = findViewById(R.id.btn_lLogin);
        txt_ForgotPassword = findViewById(R.id.txt_ForgotPassword);
        btn_createAccount = findViewById(R.id.btn_createAccount);


txt_ForgotPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
    }
});

        btn_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = passs.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(LoginActivity.this, "All fileds are required ", Toast.LENGTH_SHORT).show();

                } else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences(perfername, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        boolean bchk = pre.getBoolean("checked", false);
        if (bchk) {
            //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
            String user = pre.getString("user", "");
            String pwd = pre.getString("pwd", "");

            checkUser = email.getText().toString().trim();
            checkPass = passs.getText().toString().trim();

            checkUser = user;
            checkPass = pwd;

            email.setText(checkUser);
            passs.setText(checkPass);
        }
        checkBox.setChecked(bchk);
    }
}