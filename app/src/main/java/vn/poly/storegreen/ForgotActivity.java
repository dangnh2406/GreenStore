package vn.poly.storegreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import vn.poly.appchatonline.R;

public class ForgotActivity extends AppCompatActivity {

    Button btn_fAccept;
    EditText med_fEmail;
    String email;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        addressMapping();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Forgot");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_fAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(med_fEmail.getText().toString().isEmpty()){
                    Toast.makeText(ForgotActivity.this,"Vui lòng điền thông tin ! ." ,Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    auth.sendPasswordResetEmail(med_fEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ForgotActivity.this,"Reset Link Sent To Your Email ." ,Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ForgotActivity.this,"Error ! Reset Link is Not Sent ."+e ,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
    private void addressMapping(){
        btn_fAccept = findViewById(R.id.btn_fAccept);
        med_fEmail = findViewById(R.id.med_fEmail);
        auth = FirebaseAuth.getInstance();
    }
}