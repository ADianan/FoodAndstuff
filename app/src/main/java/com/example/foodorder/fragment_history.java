package com.example.foodorder;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private HistoryStore store;
    private String userId;
    public fragment_history(String userId) {
        // Required empty public constructor
       this.userId = userId;
    }
    public fragment_history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_history.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_history newInstance(String param1, String param2) {
        fragment_history fragment = new fragment_history();
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
        store = new HistoryStore();
        store.load(getActivity());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);
        RecyclerView rv = view.findViewById(R.id.containerhistory);
        AdapterHistory adapterHistory = new AdapterHistory(store.getCustomerHistory(userId));
        rv.setAdapter(adapterHistory);
        rv.setLayoutManager( new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));//set fragment layout
        Button back = view.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    private class AdapterHistory extends RecyclerView.Adapter<HistoryHolder>
    {
        List<History> histories;
        @RequiresApi(api = Build.VERSION_CODES.O)
        public AdapterHistory(List<History> historyList)
        {
            int id =getResources().getIdentifier("angrybirds","drawable", getActivity().getPackageName());
            String yo = "whatlahk";
            History history = new History(RandomIDGen.orderID(),new Cart(new Food(yo,yo,yo,2,id),2),RandomIDGen.getTime());
            this.histories = new ArrayList<>();
            histories.add(history);
        }
        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View view = li.inflate(R.layout.history_layout,parent,false);
            HistoryHolder historyHolder = new HistoryHolder(view);
            return historyHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
            Food food =histories.get(position).purchasedFood.getFood();
            int amount =histories.get(position).purchasedFood.getAmount();
            double price = food.getPrice();

            holder.orderId.setText(histories.get(position).orderId);
            holder.historyfoodname.setText(food.getName());
            holder.date.setText(histories.get(position).dateOfPurchase.toString());
            holder.amountofFood.setText(String.valueOf(amount));
            holder.price.setText(String.valueOf(price));
            holder.historyfoodimage.setImageResource(food.getImage());
        }

        @Override
        public int getItemCount() {
            return histories.size();
        }
    }
    private class HistoryHolder extends RecyclerView.ViewHolder
    {
        TextView orderId;
        TextView historyfoodname;
        TextView date;
        TextView amountofFood;
        TextView price;
        ImageView historyfoodimage;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            historyfoodname = itemView.findViewById(R.id.historyFoodName);
            date = itemView.findViewById(R.id.date);
            amountofFood = itemView.findViewById(R.id.amountofFood);
            price = itemView.findViewById(R.id.price);
            historyfoodimage = itemView.findViewById(R.id.historyfoodimageam);
        }

    }
}