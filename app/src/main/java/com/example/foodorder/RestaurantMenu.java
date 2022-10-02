package com.example.foodorder;

import android.content.res.Configuration;
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
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class RestaurantMenu extends Fragment {

    private String mParam1;
    private String mParam2;
    private RestaurantStore store;
    private ButtonViewModel model;
    private FrameLayoutViewModel flModel;
    int width;
    public RestaurantMenu(ButtonViewModel model, int widthDp) {
        // Required empty public constructor
        this.model = model;
        width = widthDp;
    }
    public RestaurantMenu() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = new RestaurantStore();
        store.load(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_menu, container, false);
        RecyclerView rv = view.findViewById(R.id.container_restaurant);
        RestaurantAdapter adapter = new RestaurantAdapter(store.getRestaurantList(), model);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);

        return view;
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder>
    {
        private  List<Restaurant> restaurantList;
        private RestaurantAdapter(List<Restaurant> restaurantList, ButtonViewModel model) {
            this.restaurantList = restaurantList;
        }

        @NonNull
        @Override
        public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.restaurant_layout,parent,false);
            RestaurantHolder restaurantHolder = new RestaurantHolder(view);
            return restaurantHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
            holder.bind(this);
            holder.name.setText(restaurantList.get(position).getName());
            holder.image.setImageResource(restaurantList.get(position).getImageid());
        }

        @Override
        public int getItemCount() {
            return restaurantList.size();
        }
    }
    private class RestaurantHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView image;
        public RestaurantHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.restaurantName);
            image = itemView.findViewById(R.id.restautantImage);
        }
        public void bind(RestaurantAdapter adapter)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition() == RecyclerView.NO_POSITION)
                    {
                        return;
                    }
                    if(width<600)
                    {

                        model.ReplaceFrag(new FoodMenu((String) name.getText()));
                    }
                    else{
                        model.ReplaceCenterFrag(new FoodMenu((String) name.getText()));
                    }
                }
            });
        }
    }
}