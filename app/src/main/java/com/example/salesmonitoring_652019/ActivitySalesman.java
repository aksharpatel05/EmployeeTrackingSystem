package com.example.salesmonitoring_652019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitySalesman extends AppCompatActivity {
    Button audio;
    Button location;
    Button picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman);
        location = (Button) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFindLocation(v);
            }
        });
        picture = (Button) findViewById(R.id.image);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUploadImage(v);
            }
        });
        audio = (Button) findViewById(R.id.audio);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordAudio(v);
            }
        });
    }
    public void openFindLocation(View v){
        Intent intent = new Intent(this,FindLocation.class);
        startActivity(intent);
    }
    public void openUploadImage(View v){
        Intent intent = new Intent(this,ImageUpload.class);
        startActivity(intent);
    }
    public void openRecordAudio(View v){
        Intent intent = new Intent(this,RecordAudio.class);
        startActivity(intent);
    }

}
