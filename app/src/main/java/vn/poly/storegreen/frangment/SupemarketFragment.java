package vn.poly.storegreen.frangment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.adapter.ProductAdapter;
import vn.poly.storegreen.dao.ProductDAO;
import vn.poly.storegreen.model.Product;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class SupemarketFragment extends Fragment {

    private ListView listView;
    private List<Product> list;
    private ProductAdapter productAdapter;
    private ProductDAO productDAO;
    private TextInputLayout searchProduct;
    private String search;
    private Toolbar toolbar;
    private ImageView btn_GioHang;

    public SupemarketFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supemarket, container, false);
        listView = view.findViewById(R.id.lv_sList);
        searchProduct = view.findViewById(R.id.search_product);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        btn_GioHang = view.findViewById(R.id.btn_GioHang);
        btn_GioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer , new ShoppingFragment()).commit();
            }
        });


        productDAO = new ProductDAO(getActivity());

        //tìm kiếm sản phẩm
        searchProduct.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search = searchProduct.getEditText().getText().toString().trim();
                    searchProductions();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        filtoList();
        updateData(list);
        return view;
    }

    //tìm kiếm
    private void searchProductions() {
        list = productDAO.searchProduct(search);
        productAdapter = new ProductAdapter(getContext(), list);
        listView.setAdapter(productAdapter);
    }

    // đổ dữ liệu vào list
    private void filtoList() {
        productDAO = new ProductDAO(getActivity());
        list = productDAO.getAllProduct();
        productAdapter = new ProductAdapter(getContext(), list);
        listView.setAdapter(productAdapter);
    }

    //load lại list viêw
    private void updateData(List<Product> list1) {
        list.clear();
        ProductDAO goodsDAO = new ProductDAO(getContext());
        list1 = goodsDAO.getAllProduct();
        list.addAll(list1);
        productAdapter.notifyDataSetChanged();

    }


}