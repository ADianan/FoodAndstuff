package com.example.foodorder;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.UUID;

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
    private  MutabaleCart mutabaleCart;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        View view = inflater.inflate(R.layout.fragment_fragmen_cart, container, false);
        RecyclerView rv = view.findViewById(R.id.container_cart);
        mutabaleCart =  new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MutabaleCart.class);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        CartAdapter cartAdapter = new CartAdapter();
        rv.setAdapter(cartAdapter);

        LinearLayout ll = new LinearLayout(container.getContext());
        ll.setOrientation(LinearLayout.HORIZONTAL);

        Button checkout = view.findViewById(R.id.checkout);
        checkout.setText("Checkout");
        checkout.setLayoutParams(layoutParams);

        Button history = view.findViewById(R.id.history);
        history.setText("History");
        history.setLayoutParams(layoutParams);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog log = new loginDialog();
                log.customer(getActivity());
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartViewModel cartViewModel = mutabaleCart.getMutableLiveData();
                if(cartViewModel.customer !=null)
                {
                    ButtonViewModel model = new ButtonViewModel(getActivity().getSupportFragmentManager());
                    model.ReplaceFrag(new fragment_history(UUID.randomUUID().toString()));
                }
            }
        });

        return view;
    }
    public class CartAdapter extends RecyclerView.Adapter<CartHolder>
    {
        MutabaleCart mutabaleCart;
        CartViewModel cart;
        public CartAdapter() {
            mutabaleCart = new ViewModelProvider(getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(MutabaleCart.class);
            cart = mutabaleCart.getMutableLiveData();
            mutabaleCart.mutableLiveData.observe(getActivity(), new Observer<CartViewModel>() {
                @Override
                public void onChanged(CartViewModel cartViewModel) {
                    notifyChange();
                }
            });
        }
        private void notifyChange()
        {
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.cart_layout,parent,false);
            CartHolder homeHolder = new CartHolder(view);
            return homeHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, int position) {

            double price = cart.cart.get(position).getFood().getPrice();
            int amount= cart.cart.get(position).getAmount();
            price = price * amount;
            holder.price.setText(String.valueOf(price));
            holder.name.setText(String.valueOf(cart.cart.get(position).getFood().getName()));
            holder.image.setImageResource(cart.cart.get(position).getFood().getImage());
            holder.amount.setText(String.valueOf(amount));
            holder.bind(this);

        }



        @Override
        public int getItemCount() {
            Log.d("getItemCount", "getItemCount: "+ cart.cart.size());
            return cart.cart.size();
        }
    }
    private class CartHolder  extends ViewHolder
    {
        TextView name;
        TextView amount;
        TextView price;
        ImageView image;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.foodCartName);
            amount = itemView.findViewById(R.id.foodcartAmount);
            price = itemView.findViewById(R.id.foodcartPrice);
            image = itemView.findViewById(R.id.foodCartImage);

        }
        public void bind (CartAdapter cartAdapter)
        {
            //Add food to cart when item is clicked
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                        return;
                    }

                    FoodAmountDialog dialog = new FoodAmountDialog();
                    int og =cartAdapter.cart.cart.get(getAdapterPosition()).getAmount();
                    dialog.EditCart(getActivity(),getAdapterPosition());
                }
            });
        }
    }
}