package com.example.salesmonitoring_652019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class RecordAudio extends AppCompatActivity {
    private Button mStart;
    private Button mStop;
    private TextView mRecordLabel;
    private MediaRecorder recorder;
    private String fileName = null;
    private static final String LOG_TAG = "Record_Log";
    private StorageReference mStorage;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

        mRecordLabel = (TextView) findViewById(R.id.recordLabel);
        mStart = (Button) findViewById(R.id.recordStart);
        mStop = (Button) findViewById(R.id.recordStop);


        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
                mRecordLabel.setText("Recording Started");
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
                mRecordLabel.setText("Recording Stopped");
            }
        });


//        mrecordBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent Motionevent) {
//
//                if (Motionevent.getAction() == MotionEvent.ACTION_DOWN) {
//                    startRecording();
//                    mRecordLabel.setText("Recording Started");
//                } else if (Motionevent.getAction() == MotionEvent.ACTION_UP) {
//                    stopRecording();
//                    mRecordLabel.setText("Recording Stopped");
//                }
//
//                return false;
//            }
//        });


    }

    private void startRecording() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/recorded_audio"+timeStamp+".mp3";
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);


        try {
            recorder.prepare();
        } catch (Exception e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;

        uploadAudio();
    }

    private void uploadAudio() {
        mProgress.setMessage("Uploading Audio");
        mProgress.show();

        Uri uri = Uri.fromFile(new File(fileName));
        StorageReference filepath = mStorage.child("AUDIO").child(uri.getLastPathSegment());
        String uniqueID = UUID.randomUUID().toString();




        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){


                mProgress.dismiss();



                Toast.makeText(RecordAudio.this,"Uploading Finished....", Toast.LENGTH_LONG).show();
            }
        });

    }
}

