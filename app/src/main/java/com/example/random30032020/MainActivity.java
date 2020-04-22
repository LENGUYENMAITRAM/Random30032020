package com.example.random30032020;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdt_SoBatDau, mEdt_SoKetThuc;
    Button mBt_Add, mBt_Reset, mBt_Random;
    TextView mT_ShowKQ;
    int msoBatDau, msoKetThuc;
    Random mRandom=new Random();
    ArrayList<Integer> mListNumber=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       mEdt_SoBatDau = findViewById(R.id.nhapsobatdau);
       mEdt_SoKetThuc = findViewById(R.id.nhapsoketthuc);
       mBt_Add = findViewById(R.id.buttonadd);
       mBt_Reset = findViewById(R.id.buttonrreset);
       mBt_Random = findViewById(R.id.buttonrandom);
       mT_ShowKQ = findViewById(R.id.showketqua);

        //button add
        mBt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_Sobatdau = mEdt_SoBatDau.getText().toString();
                String s_Soketthuc = mEdt_SoKetThuc.getText().toString();

                //kiểm tra điều kiện để có thể random

                if (s_Sobatdau.isEmpty() || s_Soketthuc.isEmpty()){
                    Toast.makeText(MainActivity.this, "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    mBt_Random.setEnabled(false);
                    mBt_Reset.setEnabled(false);
                    return;
                }else {
                    msoBatDau=Integer.parseInt(s_Sobatdau);
                    msoKetThuc=Integer.parseInt(s_Soketthuc);
                    if (msoBatDau >= msoKetThuc){
                        Toast.makeText(MainActivity.this, "Số kết thúc bắt buộc phải lớn hơn số bắt đầu", Toast.LENGTH_SHORT).show();
                        mBt_Add.setEnabled(false);
                        mBt_Random.setEnabled(false);
                        mBt_Reset.setEnabled(true);
                    }else {
                        for (int i=msoBatDau;i<=msoKetThuc;i++){
                            mListNumber.add(i);
                        }
                        mBt_Add.setEnabled(false);
                        mBt_Random.setEnabled(true);
                    }
                }
            }
        });

        //button random: random các số không trùng nhau
        mBt_Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBt_Add.setEnabled(false);
                if (mListNumber.size()>0){
                    int indexNumberRandom=mRandom.nextInt(mListNumber.size());
                    int numberRandom=mListNumber.get(indexNumberRandom);
                    if (mListNumber.size()==1){
                        mT_ShowKQ.append(String.valueOf(numberRandom));
                    }else {
                        mT_ShowKQ.append(numberRandom+" - ");
                    }
                    mListNumber.remove(indexNumberRandom);
                }
                else {
                    Toast.makeText(MainActivity.this, "Kết thúc", Toast.LENGTH_SHORT).show();
                    //bắt đầu lượt chơi tiếp theo với số bắt đầu và kết thúc tương tự
                    mBt_Add.setEnabled(true);
                    mT_ShowKQ.append("\n");
                }
            }
        });

        //button reset
        mBt_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBt_Add.setEnabled(true);
                mEdt_SoBatDau.setText("");
                mEdt_SoKetThuc.setText("");
                mT_ShowKQ.setText("");
            }
        });

    }
}
