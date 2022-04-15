package com.sosa.dummyapp.ui.schedule;

import androidx.lifecycle.ViewModelProvider;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sosa.dummyapp.R;
import com.sosa.dummyapp.databinding.FragmentHomeBinding;
import com.sosa.dummyapp.databinding.FragmentScheduleBinding;
import com.sosa.dummyapp.ui.home.HomeViewModel;

import java.util.Locale;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;
    /**
     * Link to TimePicker tutorial : https://www.youtube.com/watch?v=c6c1giRekB4
     */
    Button morningTimePickerButton;
    int hour, minute;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        morningTimePickerButton = root.findViewById(R.id.morningTimePickerButton);
        morningTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        morningTimePickerButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    }
                };
                TimePickerDialog dialog = new TimePickerDialog(getContext(), onTimeSetListener, hour, minute, false);
                dialog.setTitle("Select Time");
                dialog.show();
            }
        });


//        final TextView textView = binding.textSchedule;
//        scheduleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void popTimePicker(View view){
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}