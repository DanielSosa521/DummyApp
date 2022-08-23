package com.sosa.dummyapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.sosa.dummyapp.tasks.GetHomeContentTask;
import com.sosa.dummyapp.contentresources.HomeResource;
import com.sosa.dummyapp.R;
import com.sosa.dummyapp.databinding.FragmentHomeBinding;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private static final String webhost = "https://smartplugapi-dummy.herokuapp.com/";

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //emulator host loopback url     //"http://127.0.0.1:5000";
//        String localhost = getResources().getString(R.string.emulator_local_host);
        Log.i(TAG, "Creating GetUrlContentTask");
        GetHomeContentTask task = new GetHomeContentTask(this);
        task.execute(webhost + "/home");

        return root;
    }

    public void updateHomeViews(HomeResource res){
        View root = binding.getRoot();
        TextView monthTextView = root.findViewById(R.id.monthTextView);
        TextView summaryTextView = root.findViewById(R.id.summaryDescTextView);     //Get relevant text views
        TextView savingsTextView = root.findViewById(R.id.savingsDescTextView);
        TextView deltaTextView = root.findViewById(R.id.deltaDescTextView);
        TextView plugs = root.findViewById(R.id.plugDescTextView);

        monthTextView.setText(res.getMonth());
        summaryTextView.setText(res.getSummary());
        savingsTextView.setText(res.getSavings());                 //Update text views with data from API call
        deltaTextView.setText(String.format("%s%%", res.getDelta().toString()));
        plugs.setText(Arrays.toString(res.getPlugs()));
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}