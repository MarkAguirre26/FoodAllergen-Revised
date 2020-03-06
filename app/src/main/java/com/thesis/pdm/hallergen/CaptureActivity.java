package com.thesis.pdm.hallergen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;
import static com.thesis.pdm.hallergen.Variable.outputText;

public class CaptureActivity extends AppCompatActivity {

    //Declairation
    private String fileName;
    private Uri uriImage;
    private ModelsFamily loadFamilies = new ModelsFamily();
    private ModelsUser loadUser = new ModelsUser();
    String checkedAllergy = "";
    private DatabaseAdapter da;
    private Button btnScanFood;
    private ArrayList<String> foodIngredients = new ArrayList<String>();
    private ArrayList<ModelsAllergy> selectedAllergies = new ArrayList<ModelsAllergy>();
    private ImageView imageTakePreview;
    private TextView tvTitle, tvOutputText, tvImageTextTitle, tvResultTitle, tvResult, tvPersonName, tvFoodAllergen;
    ArrayList<String> resultList = new ArrayList<String>();
    ArrayList<String> allergyList = new ArrayList<String>();
    String checkResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        da = new DatabaseAdapter(this);
        tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(R.string.str_scan_foodproducts);

        btnScanFood = findViewById(R.id.btnScanFoood);
        imageTakePreview = findViewById(R.id.imageTakePreview);
        tvOutputText = findViewById(R.id.tvOutput);
        tvImageTextTitle = findViewById(R.id.tvImageTextTitle);
        tvResultTitle = findViewById(R.id.tvResultTitle);
        tvResult = findViewById(R.id.tvResult);
        tvPersonName = findViewById(R.id.txtPersonName);
        tvFoodAllergen = findViewById(R.id.txtFoodAllergen);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 0: // Capture image
                if (fileName == null) {
                    return;
                }
                imageTakePreview.setImageURI(uriImage);
                imageTakePreview.setTag("takePhoto");
                break;
            case 1: // Pick from gallary
                if (data == null) {
                    return;
                }
                uriImage = data.getData();
                imageTakePreview.setImageURI(uriImage);
                imageTakePreview.setTag("pickPhoto");
                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE: // Crop Image API
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                imageTakePreview.setImageURI(resultUri);
                break;
        }
        imageTakePreview.setBackgroundColor(getResources().getColor(R.color.colorBlack));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ToolbarBack();
    }

    //Function for back buttion
    private void ToolbarBack() {
        if (btnScanFood.getTag().toString().equals("CHECK")) {
            HidePreview();
        } else {
            finish();
        }
    }

    // Go to Scanned Image
    private void ShowPreview(String textpreview) {

        tvTitle.setText(textpreview.equals("Nutrition") ? R.string.scan_nutriontions : R.string.scan_ingredients);
        findViewById(R.id.con_imagetotext_preview).setVisibility(View.VISIBLE);
        findViewById(R.id.con_imagetotext_preview).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_bot_norm));
        btnScanFood.setText(R.string.check_for_family);
        btnScanFood.setTag("CHECK");
    }

    //Back to Image
    private void HidePreview() {
        tvTitle.setText(R.string.str_scan_foodproducts);
        findViewById(R.id.con_imagetotext_preview).setVisibility(View.GONE);
        findViewById(R.id.con_imagetotext_preview).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_norm_bot));
        btnScanFood.setText(R.string.scan_image);
        btnScanFood.setTag("SCAN");
    }

    // OCR or Google Vision API
    private String GetTextFromImage(ImageView InputImage) {
        if (InputImage.getDrawable() == null) {
            return "No Image";
        }
        BitmapDrawable drawable = (BitmapDrawable) InputImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            // Device is not compatible for API
            return "Detector dependencies are not yet available";
        } else {
            // Set the bitmap taken to the frame to perform OCR Operations.
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                TextBlock item = (TextBlock) items.valueAt(i);
                strBuilder.append(item.getValue());
                strBuilder.append("\n");
                foodIngredients.add(item.getValue());
            }
            return strBuilder.toString();
        }
    }

    private void CheckHarfulIngredients(View view) {

        if (!tvImageTextTitle.getText().toString().equals("Ingredients") && !tvImageTextTitle.getText().toString().equals("Nutrition")) {
            Toast.makeText(getApplicationContext(), "Not Ingredients or Nutrition", Toast.LENGTH_SHORT).show();
            return;
        }

        Variable.isNew = "New";

        if (outputText.contains("INGREDIENTS") || outputText.contains("Ingredients") || outputText.contains("ingredients")) {

            HidePreview();
            String personName = "", foodAllergen = "";

            for(int i=0;i< da.countFamilies(); i++) {
                loadFamilies = logUser.getFamily().get(i);
                selectedAllergies = loadFamilies.getAllergylist();
                for (ModelsAllergy allergy : selectedAllergies) {
                    for(String alergy : foodIngredients) {
                        if(alergy.contains(allergy.getAllergyName())){
//                            resultList.add(loadFamilies.getName() + "\n");
//                            allergyList.add(allergy.getAllergyName() + "\n");
//                            checkResult += loadFamilies.getName() + " is allergy in " + allergy.getAllergyName() + "\n";
                            personName += loadFamilies.getName() + "\n";
                            foodAllergen += allergy.getAllergyName() + "\n";
                        }
                    }
                }
            }

            showResult(personName,foodAllergen);
        }
        // check if contain nutrition
        else if (outputText.contains("NUTRITION") || outputText.contains("Nutrition") || outputText.contains("nutrition")) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
            startActivity(new Intent(this, CheckNutrientIntakeActivity.class));
        }



    }

    public void OnClick_ToolbarBack(View view) {
        ToolbarBack();
    }

    public void OnClick_TakePhoto(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        switch (view.getId()) {
            case R.id.btnCapture:

                // Set Path to Internal Storage
                File filepath = new File(Environment.getExternalStorageDirectory().getPath() + "/Hallergen", "CaptureImage");
                // Add folder if not exist
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                //get URI from path
                fileName = filepath + "/capture.jpg";
                uriImage = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileprovider", new File(fileName));

                // capture
                Intent takePhoto = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                // set image location
                takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                // apply permission
                takePhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(takePhoto, 0);
                break;
            case R.id.btnGallery:
                // pick
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
                break;
        }
    }

    // CROP API
    public void OnCLick_Crop(View view) {
        if (imageTakePreview.getTag().toString().equals("NoImage")) {
            return;
        }
        CropImage.activity(uriImage).start(this);
    }

    // All in one button
    public void OnClick_ScanFood(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        btnScanFood = (Button) view;
        if (btnScanFood.getTag().toString().equals("SCAN")) {
            // validate if have image
            if (imageTakePreview.getTag().toString().equals("NoImage")) {
                return;
            }
            // get text from image
            String outputText = GetTextFromImage(imageTakePreview);
            //pass the output to static variable
            Variable.outputText = outputText;
            // display text
            tvOutputText.setText(outputText);
            Log.d("tvOutputText", outputText);
            // check if contain ingredients
            if (outputText.contains("INGREDIENTS") || outputText.contains("Ingredients") || outputText.contains("ingredients")) {
                tvImageTextTitle.setText("Ingredients");
            }
            // check if contain nutrition
            else if (outputText.contains("NUTRITION") || outputText.contains("Nutrition") || outputText.contains("nutrition")) {
                tvImageTextTitle.setText("Nutrition");
            }
            // Take other image if both is not visible
            else {
                Toast.makeText(getApplicationContext(), "Take or crop other image", Toast.LENGTH_SHORT).show();
                return;
            }
            // display page of text
            ShowPreview(tvImageTextTitle.getText().toString());
        } else {
            CheckHarfulIngredients(view);
        }
    }

    private void showResult(String personName, String foodAllergen) {
        tvTitle.setText(R.string.check_result);
//        tvResultTitle.setText("Check Result");
//        tvResult.setText(result);
        tvPersonName.setText(personName);
        tvFoodAllergen.setText(foodAllergen);
        findViewById(R.id.con_result_preview).setVisibility(View.VISIBLE);
        findViewById(R.id.con_result_preview).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_bot_norm));

    }
}
