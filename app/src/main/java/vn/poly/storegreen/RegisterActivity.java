package vn.poly.storegreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import vn.poly.appchatonline.R;

public class RegisterActivity extends AppCompatActivity {
    EditText ed_mail,ed_password,ed_username;
    Button btn_register;
    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ed_mail = findViewById(R.id.med_rEmail);
        ed_password = findViewById(R.id.med_rPassword);
        ed_username = findViewById(R.id.med_rUsername);
        btn_register = findViewById(R.id.btn_rRegister);
        auth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = ed_mail.getText().toString();
                String txt_password = ed_password.getText().toString();
                String txt_username = ed_username.getText().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_email)){
                    Toast.makeText(RegisterActivity.this,"All fileds are required",Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password must be at 6 character",Toast.LENGTH_SHORT).show();
                }else{
                    register(txt_email,txt_password,txt_username);
                    Log.e("tag",reference+"");
                }
            }
        });

    }

    private void register(String email , String pass, String username){
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String userid =  firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String ,String> hashMap = new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("username",username);
                    hashMap.put("mail",email);
                    hashMap.put("imgURL","default");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);

                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"You can't register woth this email or password",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}