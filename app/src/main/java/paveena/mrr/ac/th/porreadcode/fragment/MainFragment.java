package paveena.mrr.ac.th.porreadcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import paveena.mrr.ac.th.porreadcode.R;

/**
 * Created by Teacher on 20/3/2561.
 */

public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R. layout.fragment_maim, container, false);

        return view;
    }
}  // Main Class
