package kr.tangomike.sima_20170304_kik_b;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by shimaz on 2017-02-22.
 */

public class InfoActivity extends Activity {


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            android.util.Log.i("shimaz", "receoved");

            finish();
        }
    };

    private IntentFilter mFilter = new IntentFilter("shimaz.restart");

    private DataCollection dc;

    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        registerReceiver(mReceiver, mFilter);

        dc = (DataCollection)getApplicationContext();
        dc.startTick();


        btnClose = (Button)findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);

            }
        });

    }



    @Override
    public void onDestroy(){
        unregisterReceiver(mReceiver);
        super.onDestroy();

    }


}
