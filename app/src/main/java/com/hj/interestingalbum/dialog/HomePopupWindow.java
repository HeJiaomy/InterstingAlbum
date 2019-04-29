package com.hj.interestingalbum.dialog;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hj.interestingalbum.MyConstant;
import com.hj.interestingalbum.R;
import com.hj.interestingalbum.utils.AnimUtil;
import com.hj.interestingalbum.utils.ScreenUtils;

import java.util.concurrent.atomic.AtomicReference;

public class HomePopupWindow implements View.OnClickListener {
    private static final AtomicReference<HomePopupWindow> instance = new AtomicReference<>();
    private PopupWindow mPopupWindow;

    public static HomePopupWindow getInstance() {
        for (; ; ) {
            HomePopupWindow window = instance.get();
            if (window != null) {
                return window;
            }
            window = new HomePopupWindow();
            if (instance.compareAndSet(null, window)) {
                return window;
            }
        }
    }

    public void init(final Activity mContext) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_layout, null);
//        mPopupWindow.setFocusable(true);

        TextView tvToolbarAdd = contentView.findViewById(R.id.tv_toolbar_add);
        TextView toolbarScan = contentView.findViewById(R.id.toolbar_scan);
        TextView toolbarPay = contentView.findViewById(R.id.toolbar_pay);
        tvToolbarAdd.setOnClickListener(this);
        toolbarScan.setOnClickListener(this);
        toolbarPay.setOnClickListener(this);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //背景变暗
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = 0.5f;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);
        mPopupWindow.update();
        //背景还原
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                time = System.currentTimeMillis();
                WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
                lp.alpha = 1.0f;
                mContext.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                mContext.getWindow().setAttributes(lp);
            }
        });
    }

    private long time;

    public void show(View view) {
        if (!isShow() && System.currentTimeMillis() - time > 450) {
            showAsDropDown(mPopupWindow, view, 0, 0);

        }
    }

    private void showAsDropDown(final PopupWindow pw, final View anchor,
                                final int xoff, final int yoff) {
        //显示在指定控件下;xoff表示x轴的偏移，正值表示向左，负值表示向右；yoff表示相对y轴的偏移，正值是向下，负值是向上；
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            height += ScreenUtils.getStatusBarHeightHasNotch();
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
        AnimUtil.circleRevealAnimScrren(pw.getContentView(), MyConstant.REVEAL_ANIM_TYPE_RIGHT);
    }

    private boolean isShow() {
        return mPopupWindow != null && mPopupWindow.isShowing();
    }

    @Override
    public void onClick(View v) {

    }
}
