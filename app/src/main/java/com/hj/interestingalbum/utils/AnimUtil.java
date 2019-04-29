package com.hj.interestingalbum.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.hj.interestingalbum.MyConstant;

public class AnimUtil {

    public static void circleRevealAnim(View animView, int animType) {
        animView.post(() -> {
            if (ViewCompat.isAttachedToWindow(animView)) {
                int centerX = 0;
                int centerY = -10;
                if (animType == MyConstant.REVEAL_ANIM_TYPE_CENTER) {
                    centerX = animView.getWidth() / 2;
                    centerY = -20;
                } else if (animType == MyConstant.REVEAL_ANIM_TYPE_RIGHT) {
                    centerX = animView.getMeasuredWidth();
                }
                final Animator animator = ViewAnimationUtils.createCircularReveal(
                        animView, centerX, 0, centerX / 4,
                        (float) Math.hypot(animView.getWidth(), animView.getHeight())
                );
                animator.setDuration(450);
                if (ViewCompat.isAttachedToWindow(animView)) {
                    animator.start();
                }
            }
        });
    }

    public static void circleRevealAnimScrren(View animView, int animType) {
        animView.post(() -> {
            if (ViewCompat.isAttachedToWindow(animView)) {
                int centerX = 0;
                if (animType == MyConstant.REVEAL_ANIM_TYPE_CENTER) {
                    centerX = animView.getWidth() / 2;
                } else if (animType == MyConstant.REVEAL_ANIM_TYPE_RIGHT) {
                    centerX = animView.getMeasuredWidth();
                }
                final Animator animator = ViewAnimationUtils.createCircularReveal(
                        animView, centerX, 0, 0,
                        (float) Math.hypot(animView.getWidth(), animView.getHeight())
                );
                animator.setDuration(350);
                if (ViewCompat.isAttachedToWindow(animView)) {
                    animator.start();
                }
            }
        });
    }

    public static void showRotation(View rotateIv, boolean isRotation) {
        ObjectAnimator animator;
        if (isRotation) {
            animator = ObjectAnimator.ofFloat(rotateIv, "rotation", 180f, 0f);
        } else {
            animator = ObjectAnimator.ofFloat(rotateIv, "rotation", 0f, 180f);
        }
        animator.setDuration(350);
        if (ViewCompat.isAttachedToWindow(rotateIv)) {
            animator.start();
        }
    }

    public static void showRotationGame(View rotateIv, boolean isRotation) {
        ObjectAnimator animator;
        if (isRotation) {
            animator = ObjectAnimator.ofFloat(rotateIv, "rotation", 0f, 180f);
        } else {
            animator = ObjectAnimator.ofFloat(rotateIv, "rotation", 180f, 0f);
        }
        animator.setDuration(350);
        animator.start();
    }

    public static void showDib(View animView) {
        animView.animate()
                .translationX(0)
                .setDuration(300);
    }

    public static void hideDibWithTime(View animView) {
        animView.animate()
                .translationX(ScreenUtils.dpToPx(50))
                .setDuration(300);

    }

    public static void circleRevealDecorationSelect(View animView) {
        animView.post(() -> {
            if (ViewCompat.isAttachedToWindow(animView)) {
                int centerX = animView.getMeasuredWidth() / 2;
                int centerY = animView.getMeasuredHeight() / 2;
                final Animator animator = ViewAnimationUtils.createCircularReveal(
                        animView, centerX, centerY, 0,
                        (float) Math.hypot(animView.getWidth(), animView.getHeight())
                );
                animator.setDuration(500);
                if (ViewCompat.isAttachedToWindow(animView)) {
                    animator.start();
                }
            }
        });
    }

    public static void showHangAnimation(LinearLayout linearTab, LinearLayout linearHang, LinearLayout linearHanging) {
//        linearHang.animate()
//                .translationY(0)
//                .setDuration(500);
//        linearTab.animate()
//                .translationY(ScreenUtils.dpToPx(60))
//                .setDuration(400);
//        linearHanging.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(400);
        linearHang.setVisibility(View.VISIBLE);
        linearTab.setVisibility(View.GONE);
        linearHanging.setVisibility(View.GONE);
    }

    public static void showTabAnimation(LinearLayout linearTab, LinearLayout linearHang, LinearLayout linearHanging) {
//        linearTab.animate()
//                .translationY(0)
//                .setDuration(500)
//                .setStartDelay(300);
//        linearHang.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(400)
//                .setStartDelay(200);
//        linearHanging.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(400)
//                .setStartDelay(200);
        linearTab.setVisibility(View.VISIBLE);
        linearHang.setVisibility(View.GONE);
        linearHanging.setVisibility(View.GONE);
    }

    public static void showHangingAnimation(LinearLayout linearTab, LinearLayout linearHang, LinearLayout linearHanging) {
//        linearHanging.animate()
//                .translationY(0)
//                .setDuration(500);
//        linearTab.animate()
//                .translationY(ScreenUtils.dpToPx(60))
//                .setDuration(400);
//        linearHang.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(400);
        linearHanging.setVisibility(View.VISIBLE);
        linearTab.setVisibility(View.GONE);
        linearHang.setVisibility(View.VISIBLE);
    }


    public static void hideHangAndHanging(LinearLayout linearHang, LinearLayout linearHanging) {
//        //linearHang 先置为不可见
//        linearHang.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(0);
//        //linearHang 先置为不可见
//        linearHanging.animate()
//                .translationY(ScreenUtils.dpToPx(46))
//                .setDuration(0);
//        linearHang.setVisibility(View.VISIBLE);
//        linearHanging.setVisibility(View.VISIBLE);
        linearHang.setVisibility(View.GONE);
        linearHanging.setVisibility(View.GONE);
    }

    public static void hideCombainSelect(View expandView, View combainView, LinearLayout linearSearch) {
        combainView.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearSearch.getWidth(), ScreenUtils.dpToPx(30));
        params.gravity = Gravity.CENTER_VERTICAL;
        expandView.setLayoutParams(params);
    }

    public static void showCombainSelect(View expandView, View combainView, LinearLayout linearSearch, int width) {
        combainView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(linearSearch.getWidth() - width - ScreenUtils.dpToPx(10), ScreenUtils.dpToPx(30));
        params.rightMargin = ScreenUtils.dpToPx(10);
        params.gravity = Gravity.CENTER_VERTICAL;
        expandView.setLayoutParams(params);

    }

    public static void showBottomAnimation(LinearLayout linearTab) {
        linearTab.animate()
                .translationY(ScreenUtils.dpToPx(46))
                .setDuration(400)
                .setStartDelay(200);
    }

    public static void hideBottomAnimation(LinearLayout linearTab) {
        linearTab.animate()
                .translationY(0)
                .setDuration(500)
                .setStartDelay(300);
    }
}
