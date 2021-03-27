package sw.iot.droid.coordinatorlayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.frame_in, FragmentIn.newInstance())
                    .add(R.id.frame_out, FragmentOut.newInstance())
                    .commit();
        }
    }
}
