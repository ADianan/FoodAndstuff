package com.example.foodorder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmen_cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmen_cart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CartViewModel cartViewModel;
    public fragmen_cart(CartViewModel cartViewModel) {
        // Required empty public constructor
        this.cartViewModel = cartViewModel;
    }
    public fragmen_cart() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmen_cart.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmen_cart newInstance(String param1, String param2) {
        fragmen_cart fragment = new fragmen_cart();
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
        View view = inflater.inflate(R.layout.fragment_fragmen_cart, container, false);
        RecyclerView rv = view.findViewById(R.id.container_cart);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        CartAdapter cartAdapter = new CartAdapter(cartViewModel.cart);
        rv.setAdapter(cartAdapter);
        Button checkout = view.findViewById(R.id.checkout);
        Button history = view.findViewById(R.id.history);
        checkout.setText("Checkout");
        history.setText("History");
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
    private class CartAdapter extends RecyclerView.Adapter<CartHolder>
    {

        List<Cart> cart;
        int position1;
        public CartAdapter( List<Cart> cart) {
            this.cart  = cart;
        }

        @NonNull
        @Override
        public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.food_list_layout,parent,false);
            CartHolder homeHolder = new CartHolder(view);
            return homeHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, int position) {

//            holder.price.setText(String.valueOf(cart.get(position).getPrice()));
//            holder.name.setText(String.valueOf(cart.get(position).getName()));
//            holder.description.setText(String.valueOf(cart.get(position).getDescription()));

        }



        @Override
        public int getItemCount() {
            return cart.size();
        }
    }
    private class CartHolder  extends ViewHolder
    {
        TextView name;
        TextView description;
        TextView price;
        ImageView image;

        public CartHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.foodName);
        description = itemView.findViewById(R.id.foodDescription);
        price = itemView.findViewById(R.id.price);
        image = itemView.findViewById(R.id.foodImage);

    }
        public void bind (List<Food> list,CartAdapter cartAdapter)
        {
            //Add food to cart when item is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                        return;
                    }

                    list.add(list.get(getAdapterPosition()));
                    cartAdapter.notifyDataSetChanged();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                        return true;
                    }

                    list.remove(getAdapterPosition());
                    cartAdapter.notifyDataSetChanged();
                    return true;
                }
            });

        }
    }
}