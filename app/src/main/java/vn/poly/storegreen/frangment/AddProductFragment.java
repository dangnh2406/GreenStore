package vn.poly.storegreen.frangment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.poly.appchatonline.R;
import vn.poly.storegreen.dao.ProductDAO;
import vn.poly.storegreen.field.FuntionClass;
import vn.poly.storegreen.model.Product;

public class AddProductFragment extends Fragment implements View.OnClickListener {


    private int REQUEST_CODE_FOLDER = 123;
    private ImageView getImg, setImg;
    private Button add, cancel, btn_addDate;
    private EditText productName, importPrices, prices, amount, desribe, idPoduct, tv_expriced;
    private ProductDAO productDAO;

    private TextView tv_importDate;
    private Spinner spinner;
    private String test;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        anhXa(view);
        add.setOnClickListener(this::onClick);
        btn_addDate.setOnClickListener(this::onClick);
        getImg.setOnClickListener(this::onClick);
        listSpiner(view);
        nows(view);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer , new AddProductFragment()).commit();
            }
        });
        return view;
    }

    private void nows(View view) {
        tv_importDate.setText(" Now : " + FuntionClass.getNows());
        test = tv_expriced.getText().toString();
    }

    private void listSpiner(View view) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(10);
        list.add(25);
        list.add(40);
        list.add(55);
        list.add(75);
        list.add(85);
        list.add(90);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(arrayAdapter);
    }

    private void anhXa(View view) {
        cancel = view.findViewById(R.id.btn_aCancel);
        desribe = view.findViewById(R.id.ed_aDescribe);
        productName = view.findViewById(R.id.ed_aNameGoods);
        importPrices = view.findViewById(R.id.ed_aImportPrices);
        prices = view.findViewById(R.id.ed_aPrices);
        amount = view.findViewById(R.id.ed_aAmount);
        getImg = view.findViewById(R.id.img_aGetImg);
        setImg = view.findViewById(R.id.img_aSetImg);
        add = view.findViewById(R.id.btn_aAddGoods);
        btn_addDate = view.findViewById(R.id.btn_addDate);
        idPoduct = view.findViewById(R.id.ed_aID);
        tv_importDate = view.findViewById(R.id.tv_addDateImport);
        tv_expriced = view.findViewById(R.id.tv_addExpired);
        spinner = view.findViewById(R.id.spiner);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //cho phép lấy ảnh từu thư viện ra
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                setImg.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view == add) {

            try {
                String cId = "^[0-9]{5}";
                //Create list img type byte [] ...
                BitmapDrawable bitmapDrawable = (BitmapDrawable) setImg.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] img = byteArrayOutputStream.toByteArray();

                String codeProduct = idPoduct.getText().toString().trim();
                String nameProduct = productName.getText().toString().trim();
                double importPrice = Double.parseDouble(importPrices.getText().toString().trim());
                double price = Double.parseDouble(prices.getText().toString().trim());
                double weight = Double.parseDouble(amount.getText().toString().trim());
                String describe = desribe.getText().toString().trim();
                String dateAdd = FuntionClass.getNows();
                String dateExpriced = tv_expriced.getText().toString();

                if (idPoduct.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền ma san pham ", Toast.LENGTH_SHORT).show();
                } else if (productName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền ten san pham ", Toast.LENGTH_SHORT).show();
                } else if (importPrices.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền giá nhap ", Toast.LENGTH_SHORT).show();
                } else if (prices.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền gia ban ", Toast.LENGTH_SHORT).show();
                } else if (amount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền so luong ", Toast.LENGTH_SHORT).show();
                } else if (desribe.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Vui lòng điền mo ta ", Toast.LENGTH_SHORT).show();
                } else if (tv_expriced.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Vui lòng điền ngày", Toast.LENGTH_SHORT).show();
                } else if (tv_expriced.getText().toString().compareTo(FuntionClass.getNows()) < 0) {
                    Toast.makeText(getActivity(), "Ngày hết hạn không được nhỏ hơn ngày nhập kho  ", Toast.LENGTH_SHORT).show();
                } else {
                    int choose = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));
                    productDAO = new ProductDAO(getActivity());
                    Product product = new Product();
                    product.setImg(img);
                    product.setiD(codeProduct);
                    product.setProductName(nameProduct);
                    product.setImportPrices(importPrice);
                    product.setPrices(price);
                    product.setAmount(weight);
                    product.setDescribe(describe);
                    product.setDateAdded(dateAdd);
                    product.setDateExpiration(dateExpriced);
                    product.setSale(choose);
                    productDAO.insertProduct(product);
                    Toast.makeText(getActivity(), "  Thêm Thành Công  ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), " Vui Lòng CHọn Ảnh Sản Phẩm  ", Toast.LENGTH_SHORT).show();
            }

        }


        if (view == btn_addDate) {
            //lấy ngày hệ thống
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
                    tv_expriced.setText(simpleDateFormat.format(calendar.getTime()));

                }
            }, year, month, day);

            picker.show();
        }

        if (view == getImg) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_FOLDER);
        }
    }

}