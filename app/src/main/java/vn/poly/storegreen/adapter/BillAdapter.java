package vn.poly.storegreen.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.frangment.BillFragment;
import vn.poly.storegreen.interclick.OnItemClickBill;

import vn.poly.storegreen.dao.BillDAO;
import vn.poly.storegreen.model.Bill;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class BillAdapter extends BaseAdapter {
    private Context context;
    private List<Bill> list;
    private OnItemClickBill onItemClickBill;
    private BillDAO billDAO;
    private Bill bill;

    public BillAdapter(Context context, List<Bill> list, OnItemClickBill onItemClickBill) {
        this.context = context;
        this.list = list;
        this.onItemClickBill = onItemClickBill;
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
        bill = list.get(position);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_bill, parent, false);
            viewHoder = new ViewHoder();
            viewHoder.maHoaDon = view.findViewById(R.id.tv_aMaHoaDon);
            viewHoder.ngayMua = view.findViewById(R.id.tv_aNgayMua);
            viewHoder.tenKhachHang = view.findViewById(R.id.tv_aTenKhachHang);
            viewHoder.tongTien = view.findViewById(R.id.tv_aTongTien);
//            viewHoder.delete = view.findViewById(R.id.deleteBill);
//            viewHoder.edit = view.findViewById(R.id.updateBill);
            view.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) view.getTag();
        }


        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        String giaSP = currencyVN.format(list.get(position).getTotal());
        viewHoder.maHoaDon.setText("Mã Hóa Đơn : " + list.get(position).getIdBill());
        viewHoder.ngayMua.setText("Ngày Mua : " + list.get(position).getDateAddBill());
        viewHoder.tenKhachHang.setText("Tên Khách Hàng : " + list.get(position).getClientName());
        viewHoder.tongTien.setText("Thanh Toán :" + giaSP);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickBill.onItemClickBill(position);
            }
        });



        return view;
    }

    private class ViewHoder {
        private TextView maHoaDon, ngayMua, tenKhachHang, tongTien;
        private ImageView edit, delete;
    }

    private void loadToList() {
        billDAO = new BillDAO(context);
        list.clear();
        list = billDAO.getAllBill();
        notifyDataSetChanged();
    }

    private void delete(View view, int positions) {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
        alertbox.setMessage("Do you Delete ?");
        alertbox.setTitle("Warning");
        alertbox.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        billDAO = new BillDAO(context);
                        billDAO.deleteBill(list.get(positions).getIdBill());
                        Toast.makeText(context, "bạn đã xóa ", Toast.LENGTH_SHORT).show();
                        loadToList();
                    }
                });
        alertbox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertbox.show();
    }

    private void update(View view, Bill bill) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setMessage("Do You Want ?");
        b.setTitle("Funtion");
        //button sửa sản phẩm
        b.setPositiveButton("Update Product", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //khởi tạo aler để sửa xóa
                AlertDialog.Builder alerBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                LayoutInflater layout = LayoutInflater.from(context);
                alerBuilder.setTitle(" UPDATE ");
                final View view1 = layout.inflate(R.layout.alert_update_bill, null);

                //ánh xạ
                EditText edt_updateName = view1.findViewById(R.id.update_nameBill);
                EditText edt_updateTotal = view1.findViewById(R.id.update_totalBill);
                TextView edt_updateday = view1.findViewById(R.id.update_day);
                Button button = view1.findViewById(R.id.btn_updateday);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);//lấy ngày jhieenj tại
                        int month = calendar.get(Calendar.MONTH);//lấy tháng hienj tại
                        int year = calendar.get(Calendar.YEAR);  //lấy năm hiên jtaij
                        // date picker dialog
                        DatePickerDialog picker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year, month, dayOfMonth);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                edt_updateday.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        }, year, month, day);

                        picker.show();
                    }
                });

                //set view

                alerBuilder.setView(view1);
                alerBuilder.setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        billDAO = new BillDAO(context);
                        bill.setClientName(edt_updateName.getText().toString());
                        bill.setTotal(Double.parseDouble(edt_updateTotal.getText().toString()));
                        bill.setDateAddBill(edt_updateday.getText().toString().trim());
                        Toast.makeText(context, "SucessFully", Toast.LENGTH_SHORT).show();
                        billDAO.updateBill(bill);
                        start();

                    }
                });
                alerBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                //vẫn là khởi tạo dialog
                AlertDialog alertDialogUpdate = alerBuilder.create();
                //vẫn là cho hiện lên
                alertDialogUpdate.show();

            }
        });

        //button xóa sản phẩm


        // buuton hủy
        b.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        //tạo dialog
        AlertDialog alertDialog = b.create();
        //hiện thị dialog lên
        alertDialog.show();

    }

    private void start() {
        BillFragment fragment = new BillFragment();
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.action_hContainer, fragment).commit();

    }
}


