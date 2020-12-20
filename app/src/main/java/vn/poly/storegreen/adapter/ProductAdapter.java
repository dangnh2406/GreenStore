package vn.poly.storegreen.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.activity.HomeActivity;
import vn.poly.storegreen.dao.BillDetaisDAO;
import vn.poly.storegreen.dao.ProductDAO;
import vn.poly.storegreen.field.FuntionClass;
import vn.poly.storegreen.model.Product;

import java.util.List;


public class ProductAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Product> list;
    private BillDetaisDAO Dao;
    private ProductDAO productDAO;
    private String item;
    public String test;


    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int positions, View view, ViewGroup parent) {

        ViewHoder viewHoder = null;
        productDAO = new ProductDAO(context);
        Dao = new BillDetaisDAO(context);
        final Product product = list.get(positions);
        byte[] hinh = product.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.goods_row, parent, false);
            viewHoder = new ViewHoder();
            viewHoder.imageView = view.findViewById(R.id.img_gGoods);
            viewHoder.adday = view.findViewById(R.id.tv_gAdday);
            viewHoder.nameGood = view.findViewById(R.id.tv_gNameGoods);
            viewHoder.desCribe = view.findViewById(R.id.tv_gImportPrices);
            viewHoder.prices = view.findViewById(R.id.tv_gPrices);
            viewHoder.amount = view.findViewById(R.id.tv_gAmount);
            viewHoder.sale = view.findViewById(R.id.tv_gSale);
            viewHoder.dateExpiration = view.findViewById(R.id.tv_gDateExpiration);
            viewHoder.imgdelete = view.findViewById(R.id.delete_product);
            viewHoder.imgupdate = view.findViewById(R.id.edit_product);
            view.setTag(viewHoder);
        } else {

            viewHoder = (ViewHoder) view.getTag();
        }
        //tính sale
        double moneySale = list.get(positions).getPrices();

        viewHoder.imageView.setImageBitmap(bitmap);
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(context, R.anim.left);
        view.startAnimation(animation);



        //nếu ngày hiên tại  mà lớn hơn hạn sử dụng thì
        if (product.getDateExpiration().compareTo(FuntionClass.getNows()) < 0) {
            viewHoder.nameGood.setText("TÊN SẢN PHẨM :" + (list.get(positions).getProductName().toUpperCase()));
            viewHoder.amount.setText("SỐ LƯỢNG   : HẾT HÀNG ");
            viewHoder.prices.setText("GIÁ BÁN :  " + moneySale + "  \tVND");
            viewHoder.desCribe.setText(" MÔ TẢ : " + (list.get(positions).getDescribe()));
            viewHoder.sale.setText("Sale \t" + list.get(positions).getSale() + "%");
            product.setSale(0);
            productDAO.updateProduct(product);
            //kiem tra ngày hạn bằng ngày sử dụng
        } else if (product.getDateExpiration().compareTo(FuntionClass.getNows()) == 0) {
            viewHoder.nameGood.setText("TÊN SẢN PHẨM :" + (list.get(positions).getProductName().toUpperCase()));
            viewHoder.amount.setText("SỐ LƯỢNG  : HẾT HÀNG ");
            viewHoder.prices.setText("GIÁ BÁN :  " + moneySale + "  \tVND");
            viewHoder.desCribe.setText(" MÔ TẢ : " + (list.get(positions).getDescribe()));
            viewHoder.sale.setText("Sale \t" + list.get(positions).getSale() + "%");
            product.setSale(0);
            productDAO.updateProduct(product);

        }

        //kiểm tra còn 1 ngày hạn

        //so sanh ngay han su dung -1  ngày mà bằng ngày hiện tại thì sale 55%
        else if (FuntionClass.checkHSD().compareTo(product.getDateExpiration()) == 0) {

            viewHoder.nameGood.setText("TÊN SẢN PHẨM :" + (list.get(positions).getProductName().toUpperCase()));
            viewHoder.amount.setText("Số lượng :" + list.get(positions).getAmount());
            viewHoder.prices.setText("GIÁ BÁN :  " + moneySale + "  \tVND");
            viewHoder.adday.setText("NgÀY NHẬP KHO : " + (list.get(positions).getDateAdded()));
            viewHoder.dateExpiration.setText("Ngày hết hạn : " + (list.get(positions).getDateExpiration()));
            viewHoder.desCribe.setText("Sale 55 %  Còn 1 Ngày");
            viewHoder.sale.setText("Sale \t" + list.get(positions).getSale() + "%");
            product.setSale(55);
            productDAO.updateProduct(product);


        }
        //nếu ngày hiện tại mà nhỏ hơn hạn sử dụng thì
        else if (FuntionClass.getNows().compareTo(product.getDateExpiration()) < 0) {
            viewHoder.nameGood.setText("TÊN SẢN PHẨM :" + (list.get(positions).getProductName().toUpperCase()));
            viewHoder.prices.setText("GIÁ BÁN :  " + moneySale + "  \tVND");
            viewHoder.amount.setText("SỐ LƯỢNG  HIỆN CÓ :" + (list.get(positions).getAmount() + "\tKG"));
            viewHoder.adday.setText("NgÀY NHẬP KHO : " + (list.get(positions).getDateAdded()));
            viewHoder.dateExpiration.setText("Ngày hết hạn : " + (list.get(positions).getDateExpiration()));
            viewHoder.desCribe.setText(" MÔ TẢ : " + (list.get(positions).getDescribe()));
            viewHoder.sale.setText("Sale \t" + list.get(positions).getSale() + "%");

        }

        //mua hàng  hóa
        viewHoder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = list.get(positions);
                List<Product> getList = ((HomeActivity) context).productList;

                if (product.getDateExpiration().compareTo(FuntionClass.getNows()) < 0) {
                    Toast.makeText(context, "Hàng đã hết", Toast.LENGTH_SHORT).show();

                    //kiem tra ngày hạn bằng ngày sử dụng
                } else if (product.getDateExpiration().compareTo(FuntionClass.getNows()) == 0) {
                    Toast.makeText(context, "Hàng đã hết", Toast.LENGTH_SHORT).show();
                }

                //kiểm tra còn 1 ngày hạn


                //so sanh ngay han su dung -1  ngày mà bằng ngày hiện tại thì sale 55%
                else if (FuntionClass.checkHSD().compareTo(product.getDateExpiration()) == 0) {
                    product.setAmount(1);
                    getList.add(product);
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
                //nếu ngày hiện tại mà nhỏ hơn hạn sử dụng thì
                else if (FuntionClass.getNows().compareTo(product.getDateExpiration()) < 0) {
                    product.setAmount(1);
                    getList.add(product);
                    Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //delete product

        viewHoder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
                alertbox.setMessage("Do you Delete ?");
                alertbox.setTitle("Warning");
                alertbox.setNeutralButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                productDAO = new ProductDAO(context);
                                productDAO.deleteProduct(list.get(positions).getiD());
                                Toast.makeText(context, "bạn đã xóa ", Toast.LENGTH_SHORT).show();
                                loadToList(view);
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
        });
        viewHoder.imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
                        final View view1 = layout.inflate(R.layout.alert_update_product, null);

                        //ánh xạ
                        EditText edt_updateNameProduct = view1.findViewById(R.id.update_nameProduct);
                        EditText edt_updatepriceProduct = view1.findViewById(R.id.update_priceProduct);
                        EditText edt_updateimportPriceProduct = view1.findViewById(R.id.update_importPriceProduct);
                        EditText edt_updatedescribeProduct = view1.findViewById(R.id.update_describeProduct);
                        EditText edt_updateamoutProduct = view1.findViewById(R.id.update_amountProduct);
                        AutoCompleteTextView autoCompleteTextView = view1.findViewById(R.id.update_sale);

                        edt_updateNameProduct.setText(product.getProductName());
                        edt_updatedescribeProduct.setText(product.getDescribe());
                        edt_updateamoutProduct.setText(String.valueOf(product.getAmount()));
                        edt_updateimportPriceProduct.setText(String.valueOf(product.getImportPrices()));
                        edt_updatepriceProduct.setText(String.valueOf(product.getPrices()));



                        //set view
                        Integer[] list = {0, 10, 45, 55, 75, 85, 90};
                        autoCompleteTextView.setAdapter(new ArrayAdapter<Integer>(context, android.R.layout.simple_dropdown_item_1line, list));
                        autoCompleteTextView.setThreshold(1);
                        alerBuilder.setView(view1);
                        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                               
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        alerBuilder.setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                test = autoCompleteTextView.getText().toString();

                                productDAO = new ProductDAO(context);
                                product.setProductName(edt_updateNameProduct.getText().toString());
                                product.setDescribe(edt_updatedescribeProduct.getText().toString());
                                product.setAmount(Double.parseDouble(edt_updateamoutProduct.getText().toString()));
                                product.setImportPrices(Double.parseDouble(edt_updateimportPriceProduct.getText().toString()));
                                product.setPrices(Double.parseDouble(edt_updatepriceProduct.getText().toString().trim()));
                                product.setSale(Integer.parseInt(test));
                                productDAO.updateProduct(product);
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
        });


        return view;
    }

    @Override
    public void onClick(View view) {


    }
    //update product


    private class ViewHoder {
        private ImageView imageView, imgdelete, imgupdate;
        private TextView nameGood, desCribe, prices, amount, adday, sale, dateExpiration;
    }

    private void loadToList(View view) {
        ProductDAO productDAO = new ProductDAO(context);
        list.clear();
        list = productDAO.getAllProduct();
        notifyDataSetChanged();
    }


}
