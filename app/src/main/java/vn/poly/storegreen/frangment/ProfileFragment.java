package vn.poly.storegreen.frangment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.poly.appchatonline.R;


public class ProfileFragment extends Fragment {

    CardView statistical ,inventory,dienThoai,dangXuat,info;
    TextView tv_username , tv_tv_mail;
    String username,mail;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    String userId = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addressMapping(view);
        statistical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer,new StatisticalFragment()).commit();

            }
        });


        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer,new InventoryFragment()).commit();
            }
        });
        dienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = "0839621234";
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(getActivity(), "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer,new InfoFragment()).commit();
            }
        });
        return view;
    }

    private void addressMapping(View view){
        statistical = view.findViewById(R.id.card_pStatistical);
        tv_tv_mail = view.findViewById(R.id.tv_pEmail);
        tv_username = view.findViewById(R.id.tv_pUsername);
        inventory = view.findViewById(R.id.card_pInventory);
        dienThoai = view.findViewById(R.id.dienThoai);
        dangXuat = view.findViewById(R.id.dangXuat);
        info = view.findViewById(R.id.info);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        firebaseUser = auth.getCurrentUser();
        userId = firebaseUser.getUid();

        reference.child(userId).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                tv_username.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child(userId).child("mail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mail = dataSnapshot.getValue(String.class);
                tv_tv_mail.setText(mail+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}