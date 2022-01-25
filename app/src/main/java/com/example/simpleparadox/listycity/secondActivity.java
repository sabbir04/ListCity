package com.example.simpleparadox.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        TextView showCityName;
        Button back_btn;
        showCityName = findViewById(R.id.showText);
        back_btn =  findViewById(R.id.backBtn);

        Bundle data = getIntent().getExtras();
        if(data.getString("clickedItem") != null) {
            showCityName.setText(data.getString("clickedItem"));
        }
        else {
            showCityName.setText("No City!!");
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondActivity.this.finish();
            }
        });
    }
}