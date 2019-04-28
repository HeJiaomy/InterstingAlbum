package com.hj.interstingalbum.fragment;

import com.hj.interstingalbum.R;
import com.hj.interstingalbum.base.BaseFragment;

/**
 * 所有照片
 */
public class AllPhotoFragment extends BaseFragment {


    @Override
    public void initView() {
        setHasOptionsMenu(true);
    }

    @Override
    public void initData() {

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_all_photo,menu);
////        menu.setGroupVisible(R.menu.menu_main,false);
//    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_all_photo;
    }
}
