package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NotAllowedIngredientsForFamily extends AppCompatActivity {

    TextView txtPersonName, txtFoodAllergen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_allowed_engridients_for_family);
        initComponents();
        initData();
    }

    private void initData() {
        txtPersonName.setText(Variable.personName);
        txtFoodAllergen.setText(Variable.foodAllergen);
    }

    private void initComponents() {
        txtPersonName = findViewById(R.id.txtPersonName);
        txtFoodAllergen = findViewById(R.id.txtFoodAllergen);
    }

    public void backClicked(View view) {
        back();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        startActivity(new Intent(getApplicationContext(), CaptureActivity.class));
        overridePendingTransition(0, 0);
        finish();

    }
}
