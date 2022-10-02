package com.example.foodorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodMenu extends Fragment {

    private  FoodStore store;
    private String RestaurantName;
    private FrameLayoutViewModel flModel;
    private String restaurantName;

    public FoodMenu(String restaurantName) {
        this.restaurantName = restaurantName;
        // Required empty public constructor
    }
    public FoodMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = new FoodStore();
        store.load(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_menu, container, false);
        RecyclerView rv = view.findViewById(R.id.container_food);
        FoodAdapter adapter = new FoodAdapter(store.getFoodList(restaurantName),flModel);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        return view;
    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder>
    {
        private List<Food> foodList;
        private  FrameLayoutViewModel flModel;
        private FoodAdapter(List<Food> foodList, FrameLayoutViewModel flModel) {
            this.foodList = foodList;
            this.flModel = flModel;
        }

        @NonNull
        @Override
        public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.food_list_layout,parent,false);
            FoodHolder foodHolder = new FoodHolder(view);
            return foodHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
            holder.bind(this);
            holder.price.setText(String.valueOf(foodList.get(position).getPrice()));
            holder.name.setText(String.valueOf(foodList.get(position).getName()));
            holder.description.setText(String.valueOf(foodList.get(position).getDescription()));
            holder.image.setImageResource(foodList.get(position).getImage());
        }

        @Override
        public int getItemCount() {
            return foodList.size();
        }
    }
    private class FoodHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView description;
        TextView price;
        ImageView image;
        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodName);
            description = itemView.findViewById(R.id.foodDescription);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.foodImage);
        }
        public void bind(FoodAdapter adapter)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition() == RecyclerView.NO_POSITION)
                    {
                        return;
                    }
                    FoodAmountDialog dialog = new FoodAmountDialog();
                    MutabaleCart cart = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MutabaleCart.class);
                    dialog.createNew(getActivity(),cart,adapter.foodList.get(getAdapterPosition()));

                }
            });
        }
    }

}