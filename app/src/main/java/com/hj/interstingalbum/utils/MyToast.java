package com.hj.interstingalbum.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hj.interstingalbum.R;

public class MyToast {
    private Toast toast;
    private MyToast(Context context, CharSequence text, int duration){
        View v= LayoutInflater.from(context).inflate(R.layout.toast_layout,null);
        TextView textView= v.findViewById(R.id.toast_tv);
        textView.setText(text);
        toast= new Toast(context);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER,0,100);
        toast.setView(v);
    }

    public static MyToast makeText(Context context,CharSequence text,int duration){
        return new MyToast(context,text,duration);
    }

    public void show(){
        if (toast!= null){
            toast.show();
        }
    }
}
