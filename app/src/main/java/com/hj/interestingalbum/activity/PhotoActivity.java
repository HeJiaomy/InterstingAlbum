package com.hj.interestingalbum.activity;

import android.app.Service;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.hj.interestingalbum.R;
import com.hj.interestingalbum.base.BaseActivity;
import com.hj.interestingalbum.bean.PhotoBean;
import com.hj.interestingalbum.fragment.HomeFragment;
import com.hj.interestingalbum.fragment.viewbinder.PhotoViewBinder;
import com.hj.interestingalbum.roll3d.OnRecyclerItemClickListener;
import com.hj.interestingalbum.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class PhotoActivity extends BaseActivity {

    @BindView(R.id.photo_recyclerView)
    RecyclerView mRecyclerView;

    private MultiTypeAdapter mAdapter;
    private Items items;
    private ItemTouchHelper mItemTouchHelper;
    ArrayList<PhotoBean> photoBeans;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_photo;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        items = new Items();
        mAdapter = new MultiTypeAdapter(items);
        mAdapter.register(PhotoBean.class, new PhotoViewBinder());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        photoBeans = getIntent().getParcelableArrayListExtra(HomeFragment.PHOTOBEAN_LIST);
        if (CommonUtil.unEmpty(photoBeans)) {
            items.addAll(photoBeans);
        }
        setTouch();
    }

    private void setTouch() {
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
//                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                mItemTouchHelper.startDrag(vh);

                //获取系统震动服务
                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                vib.vibrate(70);
//                }
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             *
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(photoBeans, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(photoBeans, i, i - 1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                myAdapter.notifyItemRemoved(position);
//                photoBeans.remove(position);
            }

            /**
             * 重写拖拽可用
             *
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             *
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
                mAdapter.notifyDataSetChanged();
            }
        });
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
