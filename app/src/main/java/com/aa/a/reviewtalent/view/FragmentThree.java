package com.aa.a.reviewtalent.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aa.a.reviewtalent.Adapter.RecyclerAdapter;
import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.model.CloneData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentThree.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentThree#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentThree extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public @BindView(R.id.root_layout)
    ConstraintLayout rootlayout;

    boolean isDark = false;

    public @BindView(R.id.fab_switcher)
    FloatingActionButton fabswitcher;

    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager manager;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

  //  private OnFragmentInteractionListener mListener;

    public FragmentThree() {
        // Required empty public constructor
    }

    public static FragmentThree newInstance() {

        Bundle args = new Bundle();

        FragmentThree fragment = new FragmentThree();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentThree.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentThree newInstance(String param1, String param2) {
        FragmentThree fragment = new FragmentThree();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this,v);
        manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        isDark = getThemeState();
        if(isDark)
        {
            rootlayout.setBackgroundColor(getResources().getColor(R.color.black));

        }
        else {

            rootlayout.setBackgroundColor(getResources().getColor(R.color.white));

        }
        recyclerAdapter = new RecyclerAdapter(getData(),getActivity(),isDark);
        recyclerView.setAdapter(recyclerAdapter);

        fabswitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDark = !isDark;
                if(isDark)
                {
                    rootlayout.setBackgroundColor(getResources().getColor(R.color.black));
                }
                else
                {
                    rootlayout.setBackgroundColor(getResources().getColor(R.color.white));
                }

                recyclerAdapter = new RecyclerAdapter(getData(),getActivity(),isDark);
                recyclerView.setAdapter(recyclerAdapter);
                saveThemeState(isDark);
            }
        });
        return v;
    }

    private void saveThemeState(boolean isDark) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDark",isDark);
        editor.commit();
    }

    private boolean getThemeState(){

        SharedPreferences pref = getActivity().getSharedPreferences("myPref",MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark",false);
        return  isDark;
    }



    public ArrayList<CloneData> getData()
    {
        ArrayList<CloneData> cloneData = new ArrayList<>();
        for(int i=0; i<20;i++)
        {
            CloneData cloneDatas = new CloneData();
            cloneDatas.setTitle("Premier League");
            cloneDatas.setDescription("With the item animation done it’s time to define the layout animation which will apply the item animation to each child in the layout. Create a new file called");
            cloneData.add(cloneDatas);

        }
        return cloneData;
    }

    // TODO: Rename method, update argument and hook method into UI event
  /*  public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

/*    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
