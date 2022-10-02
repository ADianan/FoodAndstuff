package com.example.foodorder;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

public class FrameLayoutViewModel extends ViewModel {
    private FrameLayout fl1;
    private FrameLayout fl2;
    private FrameLayout fl3;
    private FragmentManager fm;

    public FrameLayoutViewModel(FrameLayout fl1, FrameLayout fl2, FrameLayout fl3, FragmentManager fm)
    {
        this.fl1 = fl1;
        this.fl2 = fl2;
        this.fl3 = fl3;
        this.fm = fm;
    }

    public void ReplaceRestaurantFrag(Fragment frag)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_restaurant2,frag);
        ft.commit();
    }
    public void ReplaceFoodFrag(Fragment frag)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_cart2,frag);
        ft.commit();
    }
    public void ReplaceCartFrag(Fragment frag)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_cart2,frag);
        ft.commit();
    }
}
