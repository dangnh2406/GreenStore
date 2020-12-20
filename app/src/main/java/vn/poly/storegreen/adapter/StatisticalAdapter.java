package vn.poly.storegreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.poly.appchatonline.R;
import vn.poly.storegreen.model.Bill;
import vn.poly.storegreen.model.BillDetai;
import vn.poly.storegreen.model.Product;
import vn.poly.storegreen.model.Statistical;

public class StatisticalAdapter extends BaseAdapter {
   List<Statistical> bills = new ArrayList<>();
   Context context;

    public StatisticalAdapter(List<Statistical> bills, Context context) {
        this.bills = bills;
        this.context = context;
    }



    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.thongke_row,parent,false);
        TextView id,soLuongMua,giaBan,tongTien;
        id = view.findViewById(R.id.tv_sIdSanPham);
        soLuongMua = view.findViewById(R.id.tv_saSoLuongMua);
        giaBan = view.findViewById(R.id.tv_sGiaSanPham);
        tongTien = view.findViewById(R.id.tv_sTongTien);

        id.setText("ID sản phẩm : "+bills.get(position).id);
        soLuongMua.setText("Số lượng mua : "+String.valueOf(bills.get(position).soLuongMua));
        giaBan.setText(String.valueOf("Giá Bán : "+bills.get(position).giaBan));
        tongTien.setText(String.valueOf("Tổng tiền : "+bills.get(position).tongTien));

        return view;
    }
}
