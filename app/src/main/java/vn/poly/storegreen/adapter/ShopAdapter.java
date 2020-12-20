package vn.poly.storegreen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import vn.poly.appchatonline.R;
import vn.poly.storegreen.model.Product;


import java.util.List;
import java.util.Locale;

import vn.poly.storegreen.interclick.OnItemClicks;

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<Product> list;
    private OnItemClicks onItemClicks;

    public ShopAdapter(Context context, List<Product> list, OnItemClicks onItemClicks) {
        this.context = context;
        this.list = list;
        this.onItemClicks = onItemClicks;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;

        final Product product = list.get(i);

        byte[] hinh = product.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.shop_row, viewGroup, false);
            viewHoder = new ViewHoder();
            viewHoder.imageView = view.findViewById(R.id.img_gGoods1);
            viewHoder.adday = view.findViewById(R.id.tv_gAdday1);
            viewHoder.nameGood = view.findViewById(R.id.tv_gNameGoods1);
            viewHoder.importPrices = view.findViewById(R.id.tv_gImportPrices1);
            viewHoder.prices = view.findViewById(R.id.tv_gPrices1);
            viewHoder.amount = view.findViewById(R.id.tv_gAmount1);
            viewHoder.sale = view.findViewById(R.id.tv_gSale1);
            viewHoder.img = view.findViewById(R.id.imgsale1);
            viewHoder.delete = view.findViewById(R.id.delete_production);
            view.setTag(viewHoder);

        } else {
            viewHoder = (ViewHoder) view.getTag();
        }
//fomat tỉ giá theo việt nam
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String giaSP = currencyVN.format(list.get(i).prices);

        viewHoder.nameGood.setText("TÊN SẢN PHẨM :" + (list.get(i).getProductName().toUpperCase()));
        viewHoder.amount.setText("SỐ LƯỢNG :" + (list.get(i).getAmount()) + "\tKG");
        viewHoder.prices.setText("GIÁ BÁN :" + (giaSP + "  \tVND"));
        viewHoder.importPrices.setText("MÔ TẢ : " + (list.get(i).getDescribe()));
        viewHoder.adday.setText("NgÀY NHẬP KHO : " + (list.get(i).getDateAdded()));
        viewHoder.img.setText("K");
        viewHoder.sale.setText(list.get(i).getSale() + "%");
        viewHoder.imageView.setImageBitmap(bitmap);


        Animation animation = null;
        animation = AnimationUtils.loadAnimation(context, R.anim.left);
        view.startAnimation(animation);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicks.onItemClickListShoping(i);
            }
        });

        viewHoder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }


    private class ViewHoder {
        private ImageView imageView, delete;
        private TextView nameGood, importPrices, prices, adday;
        private TextView amount, sale, img;
    }
}
