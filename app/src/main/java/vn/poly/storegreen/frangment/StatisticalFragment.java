package vn.poly.storegreen.frangment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.adapter.ProductAdapter;
import vn.poly.storegreen.adapter.StatisticalAdapter;
import vn.poly.storegreen.dao.BillDAO;
import vn.poly.storegreen.dao.BillDetaisDAO;
import vn.poly.storegreen.dao.ProductDAO;
import vn.poly.storegreen.model.Bill;
import vn.poly.storegreen.model.BillDetai;
import vn.poly.storegreen.model.Product;
import vn.poly.storegreen.model.Statistical;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticalFragment extends Fragment {

    ImageView btn_backStatistical;
    TextView tv_ngayBatDau, tv_ngayKetThuc, tv_soLuongSanPham, tv_tongThu, tv_tienLai;
    Button btn_ngayBatDau, btn_ngayKetThuc, btn_timKiem;
    ListView listView;


    List<Statistical> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        addressMapping(view);
        BillDAO billDAO = new BillDAO(getContext());

        btn_backStatistical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new ProfileFragment()).commit();
            }
        });
        btn_ngayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);//lấy ngày jhieenj tại
                int month = calendar.get(Calendar.MONTH);//lấy tháng hienj tại
                int year = calendar.get(Calendar.YEAR);  //lấy năm hiên jtaij
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        tv_ngayBatDau.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                }, year, month, day);

                picker.show();
            }
        });
        btn_ngayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);//lấy ngày jhieenj tại
                int month = calendar.get(Calendar.MONTH);//lấy tháng hienj tại
                int year = calendar.get(Calendar.YEAR);  //lấy năm hiên jtaij
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        tv_ngayKetThuc.setText(simpleDateFormat.format(calendar.getTime()));

                    }
                }, year, month, day);

                picker.show();
            }
        });

        btn_timKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list == null) {
                    list = new ArrayList<>();
                } else if (tv_ngayBatDau.getText().toString().equals("Ngày bắt đầu") || tv_ngayKetThuc.getText().toString().equals("Ngày kế tiếp")) {
                    Toast.makeText(getActivity(), "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                } else {
                    list = billDAO.getAllBillID(tv_ngayBatDau.getText().toString(), tv_ngayKetThuc.getText().toString());
                    StatisticalAdapter productAdapter = new StatisticalAdapter(list, getActivity());
                    listView.setAdapter(productAdapter);
                    double giaSP = 0, tongThu = 0,tienLai=0;

                    for (Statistical bill : list) {
                        tongThu += bill.tongTien;
                        giaSP += bill.giaNhap;
                        tienLai = tongThu - giaSP;
                        tv_tongThu.setText("Tổng thu : " + tongThu);
                        tv_tienLai.setText("Tài lãi : " +tienLai);
                        tv_soLuongSanPham.setText("Số lượng sản phẩm :" + list.size());

                    }


                }
            }
        });
        return view;
    }

    private void addressMapping(View view) {
        btn_backStatistical = view.findViewById(R.id.btn_backStatistical);
        tv_ngayBatDau = view.findViewById(R.id.tv_ngayBatDau);
        tv_ngayKetThuc = view.findViewById(R.id.tv_ngayKetThuc);
        btn_ngayBatDau = view.findViewById(R.id.btn_ngayBatDau);
        btn_ngayKetThuc = view.findViewById(R.id.btn_ngayKetThuc);
        listView = view.findViewById(R.id.lv_statisticalList);
        btn_timKiem = view.findViewById(R.id.btn_timKiem);
        tv_soLuongSanPham = view.findViewById(R.id.tv_soLuongSanPham);
        tv_tongThu = view.findViewById(R.id.tv_sstongThu);
        tv_tienLai = view.findViewById(R.id.tv_tienLai);

    }
}