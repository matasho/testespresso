package aero.panasonic.testespresso;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mtvHello;
    private TextView mtvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtvHello = (TextView) findViewById(R.id.hello_t);
        mtvStatus = (TextView) findViewById(R.id.status_t);

        Button btnStatus = (Button) findViewById(R.id.status_button);
        btnStatus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(getDisplayState() == 1)
                    mtvStatus.setText("getDisplayState: 1");
            }
        });


        //getDisplayState();
    }

    public int getDisplayState(){
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        Log.v(TAG, "in getdisplaystate");

        if(pm.isScreenOn())
            return 1;
        else
            return 0;
    }
}
