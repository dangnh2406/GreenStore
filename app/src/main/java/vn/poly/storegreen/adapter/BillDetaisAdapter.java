package vn.poly.storegreen.adapter;

import android.content.Context;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.model.BillDetai;

import java.util.List;
import java.util.Locale;


public class BillDetaisAdapter extends BaseAdapter {
    private   Context context;
    private List<BillDetai> list;

    public BillDetaisAdapter(Context context, List<BillDetai> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder viewHoder = null;
        final BillDetai billDetai = list.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_bill_detais, parent, false);
            viewHoder = new ViewHoder();
            viewHoder.tenSanPham = view.findViewById(R.id.idTenSanPham);
            viewHoder.tongTien = view.findViewById(R.id.idTongTien);
            viewHoder.gia = view.findViewById(R.id.idGiaSanPham);
            viewHoder.soLuong = view.findViewById(R.id.idSoLuong);
            view.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) view.getTag();
        }

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        double total = list.get(position).quantityPurchased * list.get(position).importPrices;
        String money = currencyVN.format(total);
        String giaSP = currencyVN.format(list.get(position).importPrices);

        viewHoder.tenSanPham.setText(list.get(position).IdProduct);
        viewHoder.tenSanPham.setText(String.valueOf(list.get(position).IdProduct));
        viewHoder.soLuong.setText((list.get(position).quantityPurchased) + "KG");
        viewHoder.gia.setText(giaSP);
        viewHoder.tongTien.setText(money + "");
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(context, R.anim.left);
        view.startAnimation(animation);

        return view;
    }

    private class ViewHoder {
        private TextView tenSanPham, soLuong, gia, tongTien;
    }
}
