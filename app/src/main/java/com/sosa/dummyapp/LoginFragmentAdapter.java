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

    /**
     * Determines which fragment to provide. Currently only has login and register but may add more in future
     * @param position Position of viewpager
     * @return Corresponding fragment
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return registerFrag;
        }
        return loginFrag;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
