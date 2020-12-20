package vn.poly.storegreen.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.dao.AccountDAO;
import vn.poly.storegreen.model.User;


public class CreateAccountActivity extends AppCompatActivity {
    EditText edt_Username, edt_Password, edt_checkPass, edt_Email;
    Button btn_signUp;
    String username, password, checkpass, email;
    AccountDAO accountDAO;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        anhXa();
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAcount();
            }
        });


    }

    //phần add tài khoản và validate dữ liệu
    private void addAcount() {
        username = edt_Username.getText().toString().trim();
        password = edt_Password.getText().toString().trim();
        email = edt_Email.getText().toString().trim();
        checkpass = edt_checkPass.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui  lòng điền đầy đủ thông tin tài khoản",
                    Toast.LENGTH_SHORT).show();
            return;
        } else if (password.isEmpty() || password.length() < 5) {
            Toast.makeText(getApplicationContext(), "mật khẩu quá yếu không đủ kí tự", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkpass.equals(password)) {
            Toast.makeText(getApplicationContext(), "mật khẩu không đúng vui  lòng xem lại", Toast.LENGTH_SHORT).show();
            return;

        } else if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui  lòng điền đầy đủ địa chỉ emal", Toast.LENGTH_SHORT).show();
            return;
        } else {
            user = new User();
            user.setAccount(username);
            user.setPassWord(password);
            user.setEmail(email);
            accountDAO = new AccountDAO(getApplication());
            accountDAO.insertAccount(user);
            Toast.makeText(getApplicationContext(), "Thêm tài khoản thành công ", Toast.LENGTH_SHORT).show();

        }
    }


    //phần ánh xạ
    private void anhXa() {
        edt_checkPass = findViewById(R.id.edt_cNhapLaiPass);
        edt_Email = findViewById(R.id.edt_cMail);
        edt_Username = findViewById(R.id.edt_cUsername);
        edt_Password = findViewById(R.id.edt_cPassword);
        btn_signUp = findViewById(R.id.btn_signUp);
    }
}