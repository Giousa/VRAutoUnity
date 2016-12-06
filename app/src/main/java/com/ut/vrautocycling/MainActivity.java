package com.ut.vrautocycling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ut.vrautocycling.activity.YoutuUnityPlayerActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_unity)
    Button mBtnUnity;
    @InjectView(R.id.btn_count1)
    Button mBtnCount1;
    @InjectView(R.id.btn_count2)
    Button mBtnCount2;
    @InjectView(R.id.btn_count3)
    Button mBtnCount3;
    @InjectView(R.id.btn_count4)
    Button mBtnCount4;
    @InjectView(R.id.btn_count5)
    Button mBtnCount5;
    @InjectView(R.id.btn_speed1)
    Button mBtnSpeed1;
    @InjectView(R.id.btn_speed2)
    Button mBtnSpeed2;
    @InjectView(R.id.btn_speed3)
    Button mBtnSpeed3;
    @InjectView(R.id.tv_count)
    TextView mTvCount;
    @InjectView(R.id.tv_speed)
    TextView mTvSpeed;

    private String mSpeed= "20";
    private int mPlayerCount= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }


    @OnClick({R.id.btn_unity, R.id.btn_count1, R.id.btn_count2, R.id.btn_count3, R.id.btn_count4, R.id.btn_count5, R.id.btn_speed1, R.id.btn_speed2, R.id.btn_speed3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_count1:
                mPlayerCount = 5;
                break;
            case R.id.btn_count2:
                mPlayerCount = 10;
                break;
            case R.id.btn_count3:
                mPlayerCount = 20;
                break;
            case R.id.btn_count4:
                mPlayerCount = 30;
                break;
            case R.id.btn_count5:
                mPlayerCount = 40;
                break;
            case R.id.btn_speed1:
                mSpeed = "20";
                break;
            case R.id.btn_speed2:
                mSpeed = "30";
                break;
            case R.id.btn_speed3:
                mSpeed = "40";
                break;
            case R.id.btn_unity:
                enter3DModel();
                break;
        }

        mTvCount.setText("人数："+mPlayerCount);
        mTvSpeed.setText("速度："+mSpeed);
    }


    private void enter3DModel() {
        Intent intent = new Intent(MainActivity.this, YoutuUnityPlayerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("speed",mSpeed);
        bundle.putInt("count",mPlayerCount);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
