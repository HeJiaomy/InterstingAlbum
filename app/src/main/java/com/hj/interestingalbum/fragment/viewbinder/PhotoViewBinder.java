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
import com.hj.interestingalbum.bean.PhotoBean;
import com.hj.interestingalbum.utils.MyToast;
import com.scwang.smartrefresh.header.material.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewProvider;

public class PhotoViewBinder extends ItemViewProvider<PhotoBean, PhotoViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_photo_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final PhotoBean photoBean) {
        Context context = holder.rootView.getContext();
        holder.photoImg.setImageResource(photoBean.getThreedImg());
        holder.photoTitle.setText(photoBean.getTitle());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (photoBean.getId()) {
                    case 1:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                    case 2:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                    case 3:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                    case 4:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                    case 5:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                    case 6:
                        MyToast.makeText(context, photoBean.getTitle(), 0).show();
                        break;
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo_img)
        ImageView photoImg;
        @BindView(R.id.photo_title)
        TextView photoTitle;
        View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
