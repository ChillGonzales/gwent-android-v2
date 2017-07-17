package com.example.colin.gwent_android_v2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Colin on 7/16/2017.
 */

public class CardViewerFragment extends Fragment implements RecyclerViewImageAdapter.ItemClickListener {
    RecyclerViewImageAdapter recyclerViewImageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.card_viewer_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //TODO: Get images for cards here from CardManager
        Bitmap[] imgs = CardManager.getInstance().getCardsArtwork();
        String[] names = CardManager.getInstance().getCardNames();

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cardRecyclerView);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.getInstance(), numberOfColumns));
        RecyclerViewImageAdapter adapter = new RecyclerViewImageAdapter(MainActivity.getInstance(), imgs, names);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast tst = Toast.makeText(MainActivity.getInstance(), "You clicked on " + CardManager.getInstance().getCardNames()[position] + "!", Toast.LENGTH_SHORT);
        tst.show();
        //FragmentManager fragmentManager = getFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    }
}
