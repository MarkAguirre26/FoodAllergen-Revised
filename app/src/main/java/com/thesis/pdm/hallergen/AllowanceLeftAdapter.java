package com.thesis.pdm.hallergen;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AllowanceLeftAdapter implements ListAdapter {
    ArrayList<ModelAllowanceLeft> arrayList;
    Context context;

    public AllowanceLeftAdapter(Context context, ArrayList<ModelAllowanceLeft> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelAllowanceLeft subjectData = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_family_allowance_left_preview, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView allowance_left_name = convertView.findViewById(R.id.allowance_left_name);
            TextView allowance_left_energy = convertView.findViewById(R.id.allowance_left_energy);
            TextView allowance_left_protein = convertView.findViewById(R.id.allowance_left_protein);
            TextView allowance_left_total_fat = convertView.findViewById(R.id.allowance_left_total_fat);
            TextView allowance_left_carbohydrate = convertView.findViewById(R.id.allowance_left_carbohydrate);
            TextView allowance_left_essential_fatty_acid = convertView.findViewById(R.id.allowance_left_essential_fatty_acid);
            TextView allowance_left_dietary_fiber = convertView.findViewById(R.id.allowance_left_dietary_fiber);
            TextView allowance_left_water = convertView.findViewById(R.id.allowance_left_water);

            allowance_left_name.setText(subjectData.getName());
            allowance_left_energy.setText(subjectData.getEnergy());
            allowance_left_protein.setText(subjectData.getProtein());
            allowance_left_total_fat.setText(subjectData.getTotal_fat());
            allowance_left_carbohydrate.setText(subjectData.getCarbohydtrate());
            allowance_left_essential_fatty_acid.setText(subjectData.getEssential_fatty());
            allowance_left_dietary_fiber.setText(subjectData.getDietary_fiber());
            allowance_left_water.setText(subjectData.getWater());

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
