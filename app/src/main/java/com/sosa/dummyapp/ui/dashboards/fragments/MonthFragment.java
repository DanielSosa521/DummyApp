package com.sosa.dummyapp.ui.dashboards.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.sosa.dummyapp.contentresources.DashboardResource;
import com.sosa.dummyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthFragment extends Fragment {

    private static final String TAG = "MonthFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthFragment newInstance(String param1, String param2) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    GraphView graphView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        graphView = getView().findViewById(R.id.graphMonth);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 500),
                new DataPoint(2, 450),
                new DataPoint(3, 525),
                new DataPoint(4, 485)
        });
        graphView.addSeries(series);
        // set manual X bounds
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1);
        graphView.getViewport().setMaxX(30);

        // set manual Y bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(1000);

    }

    /**
     * Draw data from DashboardResource on the graph
     * Clears all previously drawn series
     * @param res Resource with DataPoint data
     */
    public void updateGraphWithResource(DashboardResource res){
        clearGraph();
        Integer[] p = res.getPoints();
        if (p.length % 2 != 0){
            Log.i(TAG, "ERROR : DashboardResource invalid length: Odd number of data");
            return;
        }
        int cnt = p.length;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        for (int i = 0; i < cnt; i+=2) {
            DataPoint point = new DataPoint(p[i], p[i+1]);
            Log.i(TAG, point.toString());
            series.appendData(point, false, 30);
        }
        graphView.addSeries(series);
    }

    /**
     * Wipe the graph so its empty
     */
    public void clearGraph(){
        graphView.removeAllSeries();
        Log.i(TAG, "Month graph cleared");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false);
    }
}