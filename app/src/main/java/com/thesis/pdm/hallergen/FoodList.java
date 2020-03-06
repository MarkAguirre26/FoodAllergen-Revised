package com.thesis.pdm.hallergen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FoodList extends AppCompatActivity {

    ListView list_foodlist;
    String[] foodlist = {"Eggs","Fish","Milk","Peanut","Shellfish","Treenuts","Wheat"};
    TextView textfoodlist;

    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        list_foodlist = findViewById(R.id.list_view_foodlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,foodlist);
        list_foodlist.setAdapter(adapter);

        list_foodlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                AlertDialog.Builder builder = new AlertDialog.Builder(FoodList.this);
                View myView = getLayoutInflater().inflate(R.layout.layout_dialog_foodlist,null);
                textfoodlist = myView.findViewById(R.id.text_foodlist);
                if(i==0)
                    textfoodlist.setText(getString(R.string.egginfo));
                else if(i==1)
                    textfoodlist.setText(getString(R.string.fishinfo));
                else if(i==2)
                    textfoodlist.setText(getString(R.string.cowsmilkinfo));
                else if(i==3)
                    textfoodlist.setText(getString(R.string.peanutinfo));
                else if(i==4)
                    textfoodlist.setText(getString(R.string.shellfishinfo));
                else if(i==5)
                    textfoodlist.setText(getString(R.string.treenutinfo));
                else if(i==6)
                    textfoodlist.setText(getString(R.string.wheatinfo));

                builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(myView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
