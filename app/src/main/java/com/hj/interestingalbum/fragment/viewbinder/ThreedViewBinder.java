package com.hj.interestingalbum.fragment.viewbinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.bean.ThreedBean;
import com.hj.interestingalbum.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;

public class ThreedViewBinder extends ItemViewProvider<ThreedBean, ThreedViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_threed_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final ThreedBean threedBean) {
        Context context = holder.rootView.getContext();
        holder.threedImg.setImageResource(threedBean.getThreedImg());
        holder.threedTitle.setText(threedBean.getTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (threedBean.getId()) {
                    case 1:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                    case 2:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                    case 3:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                    case 4:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                    case 5:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                    case 6:
                        MyToast.makeText(context, threedBean.getTitle(), 0).show();
                        break;
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.threed_img)
        ImageView threedImg;
        @BindView(R.id.threed_title)
        TextView threedTitle;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
