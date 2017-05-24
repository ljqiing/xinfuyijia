package xfyj.com.cn.xinfuyija;


import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locMan = (LocationManager) this.getSystemService(MainActivity.LOCATION_SERVICE);
        long networkTs = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getTime();
    }

    public void getTime (){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String rt = sdf.format(calendar.getTime());


    }

}
