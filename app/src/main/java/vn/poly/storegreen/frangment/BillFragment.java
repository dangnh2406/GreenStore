package vn.poly.storegreen.frangment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import vn.poly.appchatonline.R;
import vn.poly.storegreen.adapter.BillAdapter;
import vn.poly.storegreen.dao.BillDAO;
import vn.poly.storegreen.interclick.OnItemClickBill;
import vn.poly.storegreen.model.Bill;


public class BillFragment extends Fragment implements OnItemClickBill {
    private static String idBill;
    private List<Bill> bills;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment lv_bListBill
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        //tắt action bar


        ListView listView = view.findViewById(R.id.lv_bListBill);
        filtoList(listView);
        return view;
    }

    private void filtoList(ListView listView) {
        BillDAO billDAO = new BillDAO(getContext());
        bills = billDAO.getAllBill();
        BillAdapter billAdapter = new BillAdapter(getContext(), bills, this::onItemClickBill);
        listView.setAdapter(billAdapter);
    }


    public static String id() {
        return idBill;
    }


    @Override
    public void onItemClickBill(int positons) {
        //xem hóa đơn chi tiết
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        Bill bill = bills.get(positons);
        idBill = bill.getIdBill();
        double totalMoney = (bill.getTotal());
        //gửi dữ  liệu của tổng tiền phải tra sang fragment   hóa dơn chi tiết
        Bundle bundle = new Bundle();
        bundle.putDouble("total", totalMoney);
        BillDetaisFragment fragment = new BillDetaisFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.action_hContainer, fragment).commit();
        Toast.makeText(getContext(), "Xem Chi Tiết ", Toast.LENGTH_SHORT).show();
    }
}