package vn.poly.storegreen.frangment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.adapter.ProductAdapter;
import vn.poly.storegreen.dao.ProductDAO;
import vn.poly.storegreen.model.Product;

import java.util.ArrayList;
import java.util.List;


public class InventoryFragment extends Fragment {

    ImageView btn_backInventory;
    ListView listView;
    List<Product> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        addressMapping(view);
        btn_backInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new ProfileFragment()).commit();
            }
        });
        ProductDAO productDAO = new ProductDAO(getActivity());
        list = productDAO.getAllProductExpired();
        if (list == null) {
            list = new ArrayList<>();
        } else {
            ProductAdapter productAdapter = new ProductAdapter(getContext(), list);
            listView.setAdapter(productAdapter);

        }
        return view;
    }

    private void addressMapping(View view) {
        btn_backInventory = view.findViewById(R.id.btn_backInventory);
        listView = view.findViewById(R.id.lv_pInventory);
    }
}