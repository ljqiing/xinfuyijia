
package mstar.tvsetting.factory.ui.designmenu;

import mstar.factorymenu.ui.R;
import mstar.tvsetting.factory.ui.factorymenu.FactoryMenuActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UrsaTestActivity extends Activity {
    private TextView result;

    private Button runAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ursa_test);
        findViews();
        registerListener();
    }

    private void registerListener() {
        // TODO Auto-generated method stub
        runAuto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                result.setText("OK");
            }
        });

    }

    private void findViews() {
        result = (TextView) findViewById(R.id.ursa_test_result);
        runAuto = (Button) findViewById(R.id.ursa_test_run_button);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
