package kr.tangomike.sima_20170304_kik_b;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * Created by shimaz on 2017-02-22.
 */


public class NoteActivity extends Activity {

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    private IntentFilter mFilter = new IntentFilter("shimaz.restart");


    private DataCollection dc;
    private Button btnClose;

    private Button btnNote1;
    private Button btnNote2;

    private int noteNum;

    private ImageView ivContent;
    private ScrollView scrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        registerReceiver(mReceiver, mFilter);

        dc = (DataCollection)getApplicationContext();
        dc.startTick();

        noteNum = 1;

        btnClose = (Button)findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short);

            }
        });

        ivContent = (ImageView)findViewById(R.id.iv_scrl_content);
        scrl = (ScrollView)findViewById(R.id.scrl_note);

        scrl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dc.resetTimer();
                return false;
            }
        });

        btnNote1 = (Button)findViewById(R.id.btn_note_1);
        btnNote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteNum != 1){
                    noteNum = 1;
                    btnNote1.setBackgroundResource(R.drawable.note_btn_1_push);
                    btnNote2.setBackgroundResource(R.drawable.note_btn_2);
                    ivContent.setBackgroundResource(R.drawable.note_img_text_1);
                    scrl.setScrollY(0);


                }

            }
        });


        btnNote2 = (Button)findViewById(R.id.btn_note_2);
        btnNote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteNum != 2){
                    noteNum = 2;
                    btnNote1.setBackgroundResource(R.drawable.note_btn_1);
                    btnNote2.setBackgroundResource(R.drawable.note_btn_2_push);
                    ivContent.setBackgroundResource(R.drawable.note_img_text_2);
                    scrl.setScrollY(0);

                }

            }
        });


    }

    @Override
    public void onDestroy(){
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

}
