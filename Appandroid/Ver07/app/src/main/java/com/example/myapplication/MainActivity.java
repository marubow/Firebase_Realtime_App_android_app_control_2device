package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8;
    boolean test2,test1, test3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1 = false;
        test2 = false;
        test3 = false;
        anhxa();
        clicklight();
        clickfan();
        clickall();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("messenger");
        DatabaseReference fan = database.getReference("HOME/fan/value");
        DatabaseReference light =database.getReference("HOME/light/value");
        myRef.setValue("Hello World");
        myRef.setValue("Nguyen Hong Cuong");

    }
    private  void   clicklight() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference light = database.getReference("HOME/light/value");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!test1) {
                    light.setValue("off");
                    Toast.makeText(MainActivity.this, "Tắt đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_off);
                    btn1.setText("Light Off");
                    test1 = true;
                } else {
                    light.setValue("on");
                    Toast.makeText(MainActivity.this, "Bật đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_on);
                    btn1.setText("Light on");
                    test1 = false;
                }
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!test1) {
                    light.setValue("off");
                    Toast.makeText(MainActivity.this, "Tắt đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_off);
                    btn1.setText("Light Off");
                    test1 = true;
                } else {
                    light.setValue("on");
                    Toast.makeText(MainActivity.this, "Bật đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_on);
                    btn1.setText("Light on");
                    test1 = false;
                }
            }
        });
    }
    private void    clickfan() {
        // button2 and img2
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference fan = database.getReference("HOME/fan/value");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!test2) {
                    fan.setValue("off");
                    Toast.makeText(MainActivity.this, "Tắt quạt", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundResource(R.drawable.fan_off);
                    btn2.setText("Fan OFF");
                    test2 = true;
                } else {
                    Toast.makeText(MainActivity.this, "Bật quạt", Toast.LENGTH_SHORT).show();
                    fan.setValue("on");
                    img2.setBackgroundResource(R.drawable.fan_on);
                    btn2.setText("Fan ON");
                    test2 = false;
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!test2) {
                    fan.setValue("off");
                    Toast.makeText(MainActivity.this, "Tắt quạt", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundResource(R.drawable.fan_off);
                    btn2.setText("Fan OFF");
                    test2 = true;
                } else {
                    fan.setValue("on");
                    Toast.makeText(MainActivity.this, "Bật quạt", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundResource(R.drawable.fan_on);
                    btn2.setText("Fan ON");
                    test2 = false;
                }
            }
        });
    }
    private void    clickall()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference alldevice = database.getReference("HOME/all devices/value");        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!test3)
                {

                    Toast.makeText(MainActivity.this, "Tắt tất cả", Toast.LENGTH_SHORT).show();
                    img3.setBackgroundResource(R.drawable.all_off);
                    btn3.setText("Checkall off");
                    test3 = true;

                    Toast.makeText(MainActivity.this, "Tắt đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_off);
                    btn1.setText("Light Off");
                    test1 = true;

                    Toast.makeText(MainActivity.this, "Tắt quạt", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundResource(R.drawable.fan_off);
                    btn2.setText("Fan OFF");
                    test2 = true;

                    for(int i = 0; i < 15000; i++)
                    {
                        alldevice.setValue("off");
                    }
                    alldevice.setValue("khongtrangthai");
                }
                else {
                    Toast.makeText(MainActivity.this, "Bật tất cả", Toast.LENGTH_SHORT).show();
                    img3.setBackgroundResource(R.drawable.all_on);
                    btn3.setText("Checkall on");
                    test3 = false;

                    Toast.makeText(MainActivity.this, "Bật đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_on);
                    btn1.setText("Light on");
                    test1 = false;

                    img2.setBackgroundResource(R.drawable.fan_on);
                    btn2.setText("Fan ON");
                    test2 = false;
                    for(int i = 0; i < 15000; i++)
                    {
                        alldevice.setValue("on");
                    }
                    alldevice.setValue("khongtrangthai");
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!test3)
                {

                    Toast.makeText(MainActivity.this, "Tắt tất cả", Toast.LENGTH_SHORT).show();

                    img3.setBackgroundResource(R.drawable.all_off);
                    btn3.setText("Checkall off");
                    test3 = true;

                    Toast.makeText(MainActivity.this, "Tắt đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_off);
                    btn1.setText("Light Off");
                    test1 = true;

                    Toast.makeText(MainActivity.this, "Tắt quạt", Toast.LENGTH_SHORT).show();
                    img2.setBackgroundResource(R.drawable.fan_off);
                    btn2.setText("Fan OFF");
                    test2 = true;

                    for(int i = 0; i < 15000; i++)
                    {
                        alldevice.setValue("off");
                    }
                    alldevice.setValue("khongtrangthai");
                }
                else {

                    Toast.makeText(MainActivity.this, "Bật tất cả", Toast.LENGTH_SHORT).show();

                    img3.setBackgroundResource(R.drawable.all_on);
                    btn3.setText("Checkall on");
                    test3 = false;

                    Toast.makeText(MainActivity.this, "Bật đèn", Toast.LENGTH_SHORT).show();
                    img1.setBackgroundResource(R.drawable.light_on);
                    btn1.setText("Light on");
                    test1 = false;

                    img2.setBackgroundResource(R.drawable.fan_on);
                    btn2.setText("Fan ON");
                    test2 = false;
                    for(int i = 0; i < 15000; i++)
                    {
                        alldevice.setValue("on");
                    }
                    alldevice.setValue("khongtrangthai");
                }
            }
        });

    }
    private  void   anhxa()
    {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
    }
}
