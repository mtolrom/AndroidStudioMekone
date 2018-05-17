package com.mekonetolrom.homework_week1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;




public class MatchesFragment extends Fragment {

    // TODO: Customize parameter argument names
    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "data-set";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private List<MatchesItem> mDataSet;
    private OnListFragmentInteractionListener mListener;

    private FirebaseMatchesViewModel viewModel;
    private FrameLayout frameLayout;
    private EditText newMatchesItemText;

    private static String name, imageUri;
    private static boolean liked;
    private FragmentManager manager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MatchesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MatchesFragment newInstance(int columnCount) {
        MatchesFragment fragment = new MatchesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }

        viewModel.getMatchesItems(
                (ArrayList<MatchesItem> matchesItems) -> {
                    FragmentManager manager = getFragmentManager();
                    MatchesFragment fragment = (MatchesFragment) manager.findFragmentByTag("matchesItemFragment");

                    /*
                    if (fragment != null) {
                        // Remove fragment to re-add it
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.remove(fragment);
                        transaction.commit();
                    }
                    */

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(ARG_DATA_SET, matchesItems);

                    MatchesFragment matchesItemFragment = new MatchesFragment();
                    matchesItemFragment.setArguments(bundle);

                    //FragmentTransaction transaction = manager.beginTransaction();
                    //transaction.add(R.id.todoItemListFragmentContainer, todoItemFragment, "todoItemFragment");
                    //transaction.commit();
                }
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMatchesItemRecyclerViewAdapter(mDataSet, mListener));
        }
        return view;
    }

    public void addMatchesItem(View view) {
        //String name = newMatchesItemText.getText().toString();
        //String imageUri = newMatchesItemText.getText().toString();
        MatchesItem item = new MatchesItem(name, imageUri, false);
        viewModel.addMatchesItem(item);
    }

    //@Override
    public void onListFragmentInteraction(MatchesItem item) {
        item.liked = true;
        viewModel.updateMatchesItem(item);
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(MatchesItem item);
    }
}
