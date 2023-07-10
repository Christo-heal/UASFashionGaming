package com.example.uasfashiongaming;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment history.
     */
    // TODO: Rename and change types and number of parameters
    public static history newInstance(String param1, String param2) {
        history fragment = new history();
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
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<HistoryItem> historyItems = getHistoryItems(); // Anda perlu mengimplementasikan ini
        HistoryAdapter adapter = new HistoryAdapter(historyItems);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<HistoryItem> getHistoryItems() {
        List<HistoryItem> historyItems = new ArrayList<>();

        // Menambah item ke list
        HistoryItem baju1 = new HistoryItem("Kemeja", "10$");
        HistoryItem baju2 = new HistoryItem("Kaos", "5$");
        HistoryItem baju3 = new HistoryItem("Jaket", "20$");
        historyItems.add(baju1);
        historyItems.add(baju2);
        historyItems.add(baju3);

        return historyItems;
    }

    public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
        private List<HistoryItem> historyItems;
        public HistoryAdapter(List<HistoryItem> historyItems) {
            this.historyItems = historyItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            HistoryItem item = historyItems.get(position);
            // Set your views here based on the item content
            holder.tvItemName.setText(item.getName());
            holder.tvItemPrice.setText(item.getPrice());
        }

        @Override
        public int getItemCount() {
            return historyItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvItemName, tvItemPrice;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvItemName = itemView.findViewById(R.id.tv_item_name);
                tvItemPrice = itemView.findViewById(R.id.tv_item_price);
            }
        }
    }
}