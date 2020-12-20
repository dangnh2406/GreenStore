package vn.poly.storegreen.frangment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;



import de.hdodenhof.circleimageview.CircleImageView;
import vn.poly.appchatonline.R;


public class HintProductFragment extends Fragment {
    private CircleImageView imageView;
    private   TextView nameProduct, priceProduct, DescribeProduct, AdddayProduct, AmountProduct;

    public HintProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hint_product, container, false);
        anhXa(view);
        return view;
    }

    private void anhXa(View view) {
        nameProduct = view.findViewById(R.id.nameProductHint);
        priceProduct = view.findViewById(R.id.priceProductHint);
        DescribeProduct = view.findViewById(R.id.describeProductHint);
        AdddayProduct = view.findViewById(R.id.addDayProductHint);
        AmountProduct = view.findViewById(R.id.amountProductHint);
        imageView = view.findViewById(R.id.product_image);

        Bundle bundle = getArguments();
        if (bundle != null) {
            nameProduct.setText("NAME PRODUCT \t " + bundle.getString("goodName"));
        } else {
            nameProduct.setText("KHONG CO GI O DAY");
        }


    }
}