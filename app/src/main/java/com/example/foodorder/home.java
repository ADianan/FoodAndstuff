package com.example.foodorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List <Food> data;
    public home (List<Food> foodList)
    {
        data = foodList;
    }
    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rv = view.findViewById(R.id.container_home);
        HomeAdapter adapter = new HomeAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        return view;
    }
    private class HomeAdapter extends RecyclerView.Adapter<HomeHolder>
    {

        List<Food> data;

        public HomeAdapter(List<Food> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.food_list_layout,parent,false);
            HomeHolder homeHolder = new HomeHolder(view);

            return homeHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
            holder.price.setText(String.valueOf(data.get(position).getPrice()));
            holder.name.setText(String.valueOf(data.get(position).getName()));
            holder.description.setText(String.valueOf(data.get(position).getDescription()));

        }



        @Override
        public int getItemCount() {
            return data.size();
        }
    }
    private class HomeHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView description;
        TextView price;
        ImageView image;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            description = itemView.findViewById(R.id.foodDescription);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.foodImage);


        }

    }
}