package com.example.section_2.j6_8googlevisionapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CAMERA = 100;
    TextView txtCode;
    Button btnTara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComp();
        checkPerm();
    }

    private void checkPerm(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, RC_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RC_CAMERA){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Kamera izni vermelisiniz", Toast.LENGTH_LONG).show();
        }
    }

    private void initComp(){
        txtCode = (TextView) findViewById(R.id.txtBarcode);
        btnTara  = (Button) findViewById(R.id.btnTara);
        btnTara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Kamera iznin olmadan buradan geçemezsin", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(MainActivity.this, ScannerActivity.class));
            }
        });
    }
}
