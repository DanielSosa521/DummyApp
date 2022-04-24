package com.sosa.dummyapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginFragmentAdapter extends FragmentStateAdapter {

    public LoginFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    Fragment loginFrag = new LoginFragment();
    Fragment registerFrag = new RegisterFragment();
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            default : return loginFrag;
            case 1 : return registerFrag;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
