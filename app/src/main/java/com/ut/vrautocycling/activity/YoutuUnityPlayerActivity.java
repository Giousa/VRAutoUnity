package com.ut.vrautocycling.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import com.ut.vrautocycling.AndroidToUnityModel;
import com.ut.vrautocycling.ClientInfo;
import com.ut.vrautocycling.MainActivity;
import com.ut.vrautocycling.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:Giousa
 * Date:2016/12/5
 * Email:65489469@qq.com
 */
public class YoutuUnityPlayerActivity extends UnityPlayerActivity {

    private static final String TAG = YoutuUnityPlayerActivity.class.getSimpleName();
    public static YoutuUnityPlayerActivity Instance = null;
    private final String LTN_CURRENT_MODE = "CURRENT_MODE";
    private static final String INIT_PLAYER_COMMAND_ID = "initPlayer";
    private static final String SELECTED_SCENE_PARAM = "selectedScene";
    private static final String PLAYER_LIST_PARAM = "playerList";
    private static final String LTN_PARAM_SPEED = "PID_SPEED";
    private static final String LTN_COMMAND_DEVICE_ID = "DEVICE_ID";
    private static final String LTN_COMMAND_SPEED = "CID_SPEED";

    private Gson mGson;
    private List<ClientInfo> mClientInfoList = new ArrayList<>();
    private String mSpeed;
    private int mCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Instance == null){
            Instance = this;
        }
        mGson = new Gson();
        Bundle bundle = this.getIntent().getExtras();
        mSpeed = bundle.getString("speed");
        mCount = bundle.getInt("count");
        Log.d(TAG,"speed="+mSpeed+"  count="+mCount);
        //添加3D玩家
        initPlayer();
    }

    private void initPlayer() {

        Map<String, String> commandParams = new HashMap<>();

        commandParams.put(LTN_CURRENT_MODE, "0");
        commandParams.put(SELECTED_SCENE_PARAM, "1");

        for (int i = 0; i < mCount; i++) {
            ClientInfo client = new ClientInfo();
            client.setClientId("Km1930_"+i);
            client.setDeviceId("UT0"+i);
            client.setTeamId(-1);
            mClientInfoList.add(client);
        }

        commandParams.put(PLAYER_LIST_PARAM, new Gson().toJson(mClientInfoList));

        AndroidToUnityModel unityModel = new AndroidToUnityModel();
        unityModel.setCommandId(INIT_PLAYER_COMMAND_ID);
        unityModel.setCommandParams(commandParams);

        String messageToUnity = mGson.toJson(unityModel);
        UnityPlayer.UnitySendMessage("MessageCenter", "postMessageFromAndroid", messageToUnity);
        Log.d(TAG, "messageToUnity:" + messageToUnity);
        for (int i = 0; i < mClientInfoList.size(); i++) {
            Log.d(TAG, "clientInfoList:" + mClientInfoList.get(i).toString());
        }
    }

    public void loadUnityScene() {

    }

    public void loadProgressBarStart(){

    }

    public void unityGameStart(){
        Log.d(TAG,"unity unityGameStart");
        sendSpeedToUnity();
    }

    private void sendSpeedToUnity() {

        for (ClientInfo clientInfo:mClientInfoList) {
            Map<String, String> commandParams = new HashMap<>();
            commandParams.put(LTN_PARAM_SPEED, mSpeed);
            commandParams.put(LTN_COMMAND_DEVICE_ID, clientInfo.getDeviceId());

            AndroidToUnityModel unityModel = new AndroidToUnityModel();
            unityModel.setCommandId(LTN_COMMAND_SPEED);
            unityModel.setCommandParams(commandParams);

            String clientSpeed = mGson.toJson(unityModel);
            postMessageToUnity(clientSpeed);
            Log.d(TAG,"参加游戏的成员："+clientInfo.getClientId());
        }

    }

    private void unityResistance(String res){

    }

    public void loadBackActivity() {
        Log.d(TAG,"unity loadBackActivity");
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(YoutuUnityPlayerActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                if(mUnityPlayer != null){
                    mUnityPlayer.quit();
                    YoutuUnityPlayerActivity.this.finish();
                }
            }
        });
    }

    public void postMessageToUnity(String paramMessage) {
        UnityPlayer.UnitySendMessage("MessageCenter", "postMessageFromAndroid", paramMessage);
    }

}
