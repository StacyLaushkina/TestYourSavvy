package com.laushkina.anastasia.uiexperiments.view.statistics;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.databinding.StatisticsContentBinding;
import com.laushkina.anastasia.uiexperiments.presenter.StatisticsPresenter;

public class StatisticsFragment extends Fragment {
    private StatisticsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        presenter = new StatisticsPresenter();

        StatisticsContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.statistics_content,
                                                                   container, false);
        binding.setStatistics(presenter.getCurrentStatistics(getActivity()));
        binding.setFragment(this);

        return binding.getRoot();
    }

    public void onClearStatisticsClick(){
        presenter.clearStatistics(getActivity());
    }
}
