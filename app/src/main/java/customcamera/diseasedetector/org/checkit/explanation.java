package customcamera.diseasedetector.org.checkit;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.codemybrainsout.ratingdialog.RatingDialog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class explanation extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    private void openCameraApp() {
        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (picIntent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(picIntent, REQUEST_TAKE_PHOTO1);
        }
    }

    static final int REQUEST_TAKE_PHOTO = 99;
    static final int REQUEST_TAKE_PHOTO1 = 91;


    String mCurrentPhotoPath;

    public void dispatchTakePictureIntent() {
        //Toast.makeText(explanation.this,"해당 부위 사진을 찍어주세요.", Toast.LENGTH_LONG).show();

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCurrentPhotoPath = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(this,
                        "customcamera.diseasedetector.org.skintechbeta.provider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera");
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);



    }



    private ProgressBar progress;

    private static final String TAG = explanation.class.getSimpleName();
    private RelativeLayout rlRate;

    BillingProcessor bp;
    public TextView textView;

    private Button mNextBtn, mBackBtn, nextBtn1, PrevBtn1;

    private int mCurrentPage;


    private TextView[] mDots;


    private RelativeLayout relativeLayout;

    private ViewPager slideViewPager;


    private SliderAdapterexplanation sliderAdapter;


    private Timer timer;


    private ImageView imageView1;

    private TextView textView1, textView2;

    private EditText editText1, editText2;


    private ImageView imgView_logo;
    long animationDuration = 1000;


    private Button skip;

    private RelativeLayout relativeLayout1, rellay1, rellay2;

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };


    /**
     * permissions request code
     */
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 12;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                break;
        }
    }


    private Button nextBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        checkPermissions();


        progress = findViewById(R.id.progress);




        //   button.setVisibility(View.INVISIBLE);
        nextBtn1 = findViewById(R.id.nextBtn1);
        mNextBtn = findViewById(R.id.nextBtn);
        mBackBtn = findViewById(R.id.PrevBtn);
        PrevBtn1 = findViewById(R.id.PrevBtn1);

        nextBtn2 = findViewById(R.id.nextBtn2);

        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //payment
                /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O || Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
                    progress.setVisibility(View.VISIBLE);
                    progress.bringToFront();
                    dispatchTakePictureIntent();
                    Toast.makeText(explanation.this,R.string.Toast1, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(explanation.this,R.string.Toast2, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(explanation.this, Detector_first.class);
                    startActivity(intent);*/

                Intent intent = new Intent(explanation.this, MainActivity.class);
                startActivity(intent);




            }
        });




        PrevBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapterexplanation(this);


        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);


        timer = new Timer();


        imageView1 = findViewById(R.id.imageView1);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        imgView_logo = findViewById(R.id.imgView_logo);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);

        imgView_logo.startAnimation(myanim);
        textView1.startAnimation(myanim);
        textView2.startAnimation(myanim);
        editText1.startAnimation(myanim);
        editText2.startAnimation(myanim);


        skip = findViewById(R.id.skip);


        relativeLayout1 = findViewById(R.id.relativeLayout1);


        relativeLayout1.bringToFront();


        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000);


    }


    public void handleAnimation(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imgView_logo,
                "x", 420f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imgView_logo,
                "y", 200f);
        animatorY.setDuration(animationDuration);

        animatorX.setDuration(animationDuration);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(imgView_logo, view.ALPHA, 1.0f, 0.0f);

        alphaAnimation.setDuration(animationDuration);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imgView_logo, "rotation", 0f, 360f);
        rotateAnimation.setDuration(animationDuration);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY, alphaAnimation, rotateAnimation);
        animatorSet.start();


    }


    private void addDotsIndicator(int position) {
        mDots = new TextView[3];

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));


        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.VISIBLE);
                nextBtn1.setVisibility(View.INVISIBLE);
                PrevBtn1.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");


            } else if (i == 2) {
                mNextBtn.setEnabled(false);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.INVISIBLE);
                mBackBtn.setText("Back");
                nextBtn1.setVisibility(View.VISIBLE);
                nextBtn1.bringToFront();


            } else if (i == 1) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.VISIBLE);
                nextBtn1.setVisibility(View.INVISIBLE);
                PrevBtn1.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mNextBtn.bringToFront();
                mBackBtn.setText("Back");


            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {


        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }


    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }



    private void showDialog() {

        final RatingDialog ratingDialog = new RatingDialog.Builder(explanation.this)
                .session(3)
                .threshold(3)
                .title("리뷰를 해주시면 좋은 정보를 제공하는데 큰 도움이 됩니다")
                .titleTextColor(R.color.black)
                .ratingBarColor(R.color.orange)
                .negativeButtonText("")
                .playstoreUrl("https://play.google.com/store/apps/details?id=customcamera.diseasedetector.org.skintechlite")
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        Toast.makeText(explanation.this, "아래 중앙에 있는 Analysis 버튼을 눌러주세요", Toast.LENGTH_LONG).show();

                    }
                })
                .build();


        ratingDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if (!bp.handleActivityResult(requestCode, resultCode, data)) {
        //    super.onActivityResult(requestCode, resultCode, data);
        //}

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            galleryAddPic();
            Intent intent = new Intent(explanation.this, MainActivity.class);
            startActivity(intent);

        }


    }



}

