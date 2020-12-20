package vn.poly.storegreen.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.frangment.AddProductFragment;
import vn.poly.storegreen.frangment.BillFragment;
import vn.poly.storegreen.frangment.ProfileFragment;
import vn.poly.storegreen.frangment.ShoppingFragment;
import vn.poly.storegreen.frangment.SupemarketFragment;
import vn.poly.storegreen.model.Product;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ChipNavigationBar chipNavigationBar;
    Fragment fragment = null;
    public List<Product> productList = new ArrayList<>();
    public  List<Product> expiredProductList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String mail = intent.getStringExtra("mail");

        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.market, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new SupemarketFragment()).commit();
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.market:
                        fragment = new SupemarketFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.shopping:
                        fragment = new ShoppingFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.setting:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.bill:
                        fragment = new BillFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.aaa:
                        fragment = new AddProductFragment();
                        loadFragment(fragment);
                        break;
                }

            }
        });

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.action_hContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}