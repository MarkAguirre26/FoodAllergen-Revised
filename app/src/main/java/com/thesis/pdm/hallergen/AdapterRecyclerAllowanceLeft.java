package com.thesis.pdm.hallergen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerAllowanceLeft extends RecyclerView.Adapter<AdapterRecyclerAllowanceLeft.ViewHolder> {

    //Declairation
    private Context context;
    private static ArrayList<ModelAllowanceLeft> allowanceLeft_list = new ArrayList<>();
    private OnClickAllowanceLeftListener onClickAllowanceLeftListener;
    private OnLongClickAllowanceLeftListener onLongClickAllowanceLeftListener;

    //Constructor
    public AdapterRecyclerAllowanceLeft(Context context, ArrayList<ModelAllowanceLeft> list,
                                        OnClickAllowanceLeftListener clicklistener,
                                        OnLongClickAllowanceLeftListener longClickListener) {
        this.context = context;
        this.allowanceLeft_list = list;
        this.onClickAllowanceLeftListener = clicklistener;
        this.onLongClickAllowanceLeftListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_family_allowance_left_preview, parent, false),
                onClickAllowanceLeftListener, onLongClickAllowanceLeftListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.allowance_left_name.setText(allowanceLeft_list.get(position).getName());
        holder.allowance_left_energy.setText(allowanceLeft_list.get(position).getEnergy());
        holder.allowance_left_protein.setText(allowanceLeft_list.get(position).getProtein());
        holder.allowance_left_total_fat.setText(allowanceLeft_list.get(position).getTotal_fat());
        holder.allowance_left_carbohydrate.setText(allowanceLeft_list.get(position).getCarbohydtrate());
        holder.allowance_left_essential_fatty_acid.setText(allowanceLeft_list.get(position).getEssential_fatty());
        holder.allowance_left_dietary_fiber.setText(allowanceLeft_list.get(position).getDietary_fiber());
        holder.allowance_left_water.setText(allowanceLeft_list.get(position).getWater());
    }

    @Override
    public int getItemCount() {
        return allowanceLeft_list == null ? 0 : allowanceLeft_list.size();
    }

    //Class of the Item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        TextView allowance_left_name, allowance_left_energy, allowance_left_protein, allowance_left_total_fat, allowance_left_carbohydrate, allowance_left_essential_fatty_acid, allowance_left_dietary_fiber, allowance_left_water;

        OnClickAllowanceLeftListener clickAllowanceLeftListener;
        OnLongClickAllowanceLeftListener longClickAllowanceLeftListener;

        public ViewHolder(@NonNull View itemView,
                          OnClickAllowanceLeftListener onClickAllowanceLeftListener,
                          OnLongClickAllowanceLeftListener onLongClickAllowanceLeftListener) {
            super(itemView);

            allowance_left_name = itemView.findViewById(R.id.allowance_left_name);
            allowance_left_energy = itemView.findViewById(R.id.allowance_left_energy);
            allowance_left_protein = itemView.findViewById(R.id.allowance_left_protein);
            allowance_left_total_fat = itemView.findViewById(R.id.allowance_left_total_fat);
            allowance_left_carbohydrate = itemView.findViewById(R.id.allowance_left_carbohydrate);
            allowance_left_essential_fatty_acid = itemView.findViewById(R.id.allowance_left_essential_fatty_acid);
            allowance_left_dietary_fiber = itemView.findViewById(R.id.allowance_left_dietary_fiber);
            allowance_left_water = itemView.findViewById(R.id.allowance_left_water);

            this.clickAllowanceLeftListener = onClickAllowanceLeftListener;
            this.longClickAllowanceLeftListener = onLongClickAllowanceLeftListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickAllowanceLeftListener.OnClickAllowanceLeftListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longClickAllowanceLeftListener.OnLongClickAllowanceLeftListener(getAdapterPosition());
            return true;
        }
    }

    //Use to tap function
    public interface OnClickAllowanceLeftListener {
        void OnClickAllowanceLeftListener(int position);
    }

    //Use to long tap function
    public interface OnLongClickAllowanceLeftListener {
        void OnLongClickAllowanceLeftListener(int position);
    }
}
