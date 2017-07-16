package com.example.colin.gwent_android_v2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

/**
 * Created by Colin on 7/15/2017.
 */

public class DashboardFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {
    RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dashboard_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // data to populate the RecyclerView with
        String[] data = {"Create a Deck", "View Decks", "View Cards", "Help"};

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.getInstance(), numberOfColumns));
        adapter = new RecyclerViewAdapter(MainActivity.getInstance(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        if (adapter.getItem(position) == "View Cards") {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            CardViewerFragment fragment = new CardViewerFragment();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}
