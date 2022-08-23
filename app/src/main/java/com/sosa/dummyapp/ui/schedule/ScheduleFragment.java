package com.sosa.dummyapp.ui.schedule;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.sosa.dummyapp.R;
import com.sosa.dummyapp.databinding.FragmentScheduleBinding;
import java.util.Locale;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;
    /**
     * Link to TimePicker tutorial : https://www.youtube.com/watch?v=c6c1giRekB4
     */
    Button morningTimePickerButton;
    int hourMorning, minuteMorning;
    Button nightTimePickerButton;
    int hourNight, minuteNight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        morningTimePickerButton = root.findViewById(R.id.morningTimePickerButton);
        morningTimePickerButton.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
                hourMorning = selectedHour;
                minuteMorning = selectedMinute;
                morningTimePickerButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hourMorning, minuteMorning));
            };
            TimePickerDialog dialog = new TimePickerDialog(getContext(), onTimeSetListener, hourMorning, minuteMorning, false);
            dialog.setTitle("Select Time");
            dialog.show();
        });
        nightTimePickerButton = root.findViewById(R.id.nightTimePickerButton);
        nightTimePickerButton.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
                hourNight = selectedHour;
                minuteNight = selectedMinute;
                nightTimePickerButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hourNight, minuteNight));
            };
            TimePickerDialog dialog = new TimePickerDialog(getContext(), onTimeSetListener, hourNight, minuteNight, false);
            dialog.setTitle("Select Time");
            dialog.show();
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}