package vn.poly.storegreen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;


import vn.poly.appchatonline.R;
import vn.poly.storegreen.model.User;

import java.util.List;


public class AccountAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;

    public AccountAdapter(Context context, List<User> list) {
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

    private class ViewHoder {
        private TextView user, pass, emal;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        final User user = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.account_adapter, viewGroup, false);
            viewHoder = new ViewHoder();

            viewHoder.user = view.findViewById(R.id.txt_Userhint);
            viewHoder.emal = view.findViewById(R.id.txt_Emailhint);
            viewHoder.pass = view.findViewById(R.id.txt_Passhint);
            view.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) view.getTag();
        }
        viewHoder.user.setText("USER NAME:" + (list.get(i).getAccount()));
        viewHoder.pass.setText("PASSWORD : " + (list.get(i).getPassWord()));
        viewHoder.emal.setText("EMAIL  : " + (list.get(i).getEmail()));
        Animation animation = null;
        animation = AnimationUtils.loadAnimation(context, R.anim.left);
        view.startAnimation(animation);
        return view;
    }
}
