package vn.poly.storegreen.frangment;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import java.util.List;
import java.util.Locale;

import vn.poly.appchatonline.R;
import vn.poly.storegreen.adapter.BillDetaisAdapter;
import vn.poly.storegreen.dao.BillDetaisDAO;
import vn.poly.storegreen.model.BillDetai;


public class BillDetaisFragment extends Fragment {
    private TextView textView;
    private ListView listView;
    private Fragment fragment = null;
    ImageView btn_backBillDetail;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_detais, container, false);
        anhXa(view);
        filltoList();
        formatMoney();
        btn_backBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer , new BillFragment()).addToBackStack("").commit();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.item, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void formatMoney() {
        //format tiền theo tỉ giá việt nam
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        Bundle bundle = getArguments();
        if (bundle != null) {
            double tong = (bundle.getDouble("total"));
            String giaSP = currencyVN.format(tong);
            textView.setText("Tiền Thanh Toán :\t" + giaSP);
        }
    }

    //đổ dữ liệu
    private void filltoList() {
        BillDetaisDAO billDetaisDAO = new BillDetaisDAO(getContext());
        String id = BillFragment.id();
        List<BillDetai> list = billDetaisDAO.getAlList(id);
        BillDetaisAdapter billDetaisAdapter = new BillDetaisAdapter(getContext(), list);
        listView.setAdapter(billDetaisAdapter);
    }

    private void anhXa(View view) {
        listView = view.findViewById(R.id.lv_bListBillDetais);
        textView = view.findViewById(R.id.totalMoney);
        btn_backBillDetail = view.findViewById(R.id.btn_backBillDetail);
    }
}