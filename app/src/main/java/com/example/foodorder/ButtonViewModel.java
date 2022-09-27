package com.example.foodorder;

import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

public class ButtonViewModel extends ViewModel
{
    // This class allows fragments access to buttons to fragments to change functionality of buttons as it changes
    public Button but1;
    public Button but2;
    public Button but3;
    private FragmentManager fm;
    public ButtonViewModel(Button but1, Button but2, Button but3, FragmentManager fm)
    {
        this.but1 = but1;
        this.but2 = but2;
        this.but3 = but3;
        this.fm = fm;
    }
    public void ReplaceFrag(Fragment frag)
    {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainFragment,frag);
        ft.commit();
    }

}
