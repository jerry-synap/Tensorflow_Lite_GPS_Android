package customcamera.diseasedetector.org.checkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class startPage extends AppCompatActivity {


    private Timer timer;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(startPage.this, Detector_location_seoul_food.class);
                startActivity(intent);





            }
        }, 1000);











    }
}
