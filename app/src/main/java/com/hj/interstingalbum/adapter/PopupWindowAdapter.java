package com.hj.interstingalbum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hj.interstingalbum.R;
import com.hj.interstingalbum.bean.PopupBean;

import java.util.ArrayList;

public class PopupWindowAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<PopupBean> popList;
    private Context context;

    public PopupWindowAdapter(ArrayList<PopupBean> popList, Context context) {
        inflater = LayoutInflater.from(context);
        this.popList = popList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return popList==null?0:popList.size();
    }

    @Override
    public Object getItem(int position) {
        return popList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_add, parent, false);
        TextView tvPlus = view.findViewById(R.id.tv_toolbar_plus);
        ImageView imgPlus= view.findViewById(R.id.img_toolbar_plus);
        tvPlus.setText(popList.get(position).getTitle());
        imgPlus.setImageDrawable(popList.get(position).getImage());

        return view;
    }
}
