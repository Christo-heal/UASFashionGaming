package com.example.uasfashiongaming;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link itempage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itempage extends Fragment {
    private Button btnHistory;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public itempage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment item.
     */
    // TODO: Rename and change types and number of parameters
    public static itempage newInstance(String param1, String param2) {
        itempage fragment = new itempage();
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

    String[] daftar;
    ListView listView;
    Menu menu;
    Cursor cursor;
    Database database;
    EditText itemName, itemPrice;
    Button btnAdd;
    public static itempage ip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ip = this;
        database = new Database(getActivity());

        itemName = view.findViewById(R.id.addName);
        itemPrice = view.findViewById(R.id.addPrice);
        btnAdd = view.findViewById(R.id.addBtn);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into product(itemName, itemPrice) values('" +
                        itemName.getText().toString() + "','"+
                        itemPrice.getText().toString() + "')");
                Toast.makeText(getActivity(), "Item Ditambahkan", Toast.LENGTH_SHORT).show();
                ip.RefreshList(view);
            }
        });
        RefreshList(view);
        return view;
    }



    private void RefreshList(View view) {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM product", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Update Item", "Hapus Item"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent in = new Intent(getActivity(), updateitem.class);
                                in.putExtra("itemName", selection);
                                startActivity(in);
                                break;
                            case 1:
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.execSQL("delete from product where itemName ='"+ selection + "'");
                                RefreshList(view);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}