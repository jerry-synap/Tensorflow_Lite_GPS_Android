package customcamera.diseasedetector.org.checkit;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Detector_location_seoul_vegetable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    static final int REQUEST_TAKE_PHOTO = 99;


    String mCurrentPhotoPath;

    public void dispatchTakePictureIntent() {
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





/*private void galleryAddPic() {

        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ mCurrentPhotoPath)));

        //MediaScannerConnection.scanFile(Detector_location_seoul_food.this, new String[] { Environment.getExternalStorageDirectory().getPath() }, new String[] { "image/jpeg" }, null);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);



            }
        else
        {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))));

        }




    }


    String pathToFile;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createImageFile();

            if(photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(this,"customcamera.diseasedetector.org.skintechlite.provider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(timeStamp,".jpg",storageDir);
        } catch (IOException e){
            Log.d("mylog", "Excep : " + e.toString());

        }

        return image;
    }*/







    private ProgressBar progress;

    private TextView textviewsystom,textView3 ;

    private RelativeLayout r0,textViewResult1;


    private Button skip;

    private RelativeLayout relativeLayout1, rellay1, rellay2;


    private ImageView upload2;

    private ImageView imageViewResult2;

    private RelativeLayout imageViewResult_rel2;

    static final int REQUEST_IMAGE_CAPTURE = 100;






    private ImageView info;

    private ImageView btnCamera2;
    private ImageView image_r2, image_r3,image_r4,image_r5,image_r6,image_r7,image_r8,image_r9,image_r10,image_r11,image_r12,image_r13,image_r14,image_r15,image_r16,image_r17,image_r18,image_r19,image_r20,image_r21,image_r22;

    private ImageView image_r23,image_r24,image_r25,image_r26,image_r27,image_r28,image_r29,image_r30,image_r31,image_r32,image_r33,image_r34;


    private Button imageView2_12_button, imageView2_13_button,imageView2_14_button,imageView2_15_button;

    private RelativeLayout rel12, rel13, rel14, rel15, rel16,rel17,rel18,rel19,rel20,rel21,rel100,rel101;

    private RelativeLayout rel22 ,rel23, rel24, rel25, rel26, rel27, rel28, rel29, rel30, rel31, rel32, rel33;

    private TextView text0,text1, text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,text20,text21;

    private TextView text22, text23, text24, text25, text26, text27, text28, text29, text30, text31, text32, text33;

    private LinearLayout colorfultable0, colorfultable1, colorfultable2, colorfultable3, colorfultable4, colorfultable5, colorfultable6, colorfultable7, colorfultable8, colorfultable9, colorfultable10, colorfultable11, colorfultable12,colorfultable13,colorfultable14,colorfultable15,colorfultable16,colorfultable17,colorfultable18,colorfultable19,colorfultable20,colorfultable21;

    private LinearLayout colorfultable22, colorfultable23, colorfultable24, colorfultable25,colorfultable26,colorfultable27, colorfultable28, colorfultable29, colorfultable30, colorfultable31, colorfultable32, colorfultable33;

    private TextView r2_text, r3_text, r4_text, r5_text, r6_text, r7_text, r8_text, r9_text, r10_text, r11_text, r12_text;



    private Button imageView2_1_button, imageView2_2_button, imageView2_3_button, imageView2_4_button, imageView2_5_button, imageView2_6_button, imageView2_7_button, imageView2_8_button, imageView2_9_button, imageView2_10_button, imageView2_11_button,imageView2_16_button,imageView2_17_button,imageView2_18_button,imageView2_19_button,imageView2_20_button,imageView2_21_button;

    private Button imageView2_100_button, imageView2_101_button, imageView2_22_button, imageView2_23_button, imageView2_24_button,imageView2_25_button,imageView2_26_button,imageView2_27_button,imageView2_28_button,imageView2_29_button,imageView2_30_button,imageView2_31_button,imageView2_32_button,imageView2_33_button;

    private Button imageView2_1_1_button, imageView2_2_1_button, imageView2_3_1_button, imageView2_4_1_button, imageView2_5_1_button, imageView2_6_1_button, imageView2_7_1_button, imageView2_8_1_button, imageView2_9_1_button, imageView2_10_1_button, imageView2_11_1_button,imageView2_16_1_button,imageView2_17_1_button,imageView2_18_1_button,imageView2_19_1_button,imageView2_20_1_button,imageView2_21_1_button;

    private Button imageView2_22_1_button, imageView2_23_1_button, imageView2_24_1_button,imageView2_25_1_button,imageView2_26_1_button,imageView2_27_1_button,imageView2_28_1_button,imageView2_29_1_button,imageView2_30_1_button,imageView2_31_1_button,imageView2_32_1_button,imageView2_33_1_button;

    private Button imageView2_12_1_button, imageView2_13_1_button,imageView2_14_1_button,imageView2_15_1_button;


    private RelativeLayout rel1, rel2, rel3, rel4, rel5, rel6, rel7, rel8, rel9, rel10, rel11;

    private RelativeLayout rel1_1, rel2_1, rel3_1, rel4_1, rel5_1, rel6_1, rel7_1, rel8_1, rel9_1, rel10_1, rel11_1;


    private RelativeLayout rel12_1, rel13_1, rel14_1, rel15_1, rel16_1,rel17_1,rel18_1,rel19_1,rel20_1,rel21_1;

    private RelativeLayout rel22_1 ,rel23_1, rel24_1, rel25_1, rel26_1, rel27_1, rel28_1, rel29_1, rel30_1, rel31_1, rel32_1, rel33_1;

    private RelativeLayout r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22;

    private RelativeLayout r23, r24, r25, r26,r27,r28,r29,r30,r31,r32,r33,r34;

    private ImageView imageView2_1,imageView2_2,imageView2_3,imageView2_4, imageView2_5, imageView2_6, imageView2_7, imageView2_8, imageView2_9, imageView2_10, imageView2_11;

    private ImageView imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12, imageView13, imageView14, imageView15, imageView16, imageView17,imageView18,imageView19,imageView20,imageView21,imageView22,imageView23,imageView24,imageView25,imageView26;

    private ImageView imageview27, imageview28,imageview29,imageview30, imageview31,imageview32,imageview33,imageview34,imageview35,imageview36,imageview37,imageview38;

    private RelativeLayout imageViewResult_rel;


    private RelativeLayout relativeLayout2;

    private LinearLayout LinearLayout1;

    private TextView mEmail;

    private ProgressBar progressBar1;


    private LinearLayout mLinear1;

    private Button mButtonQuestion1, question1;
    private Button mButtonQuestion2;
    private FrameLayout frameLayout;

    private static final int PICK_IMAGE_REQUEST = 44;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mEditTextFileName;
    private ProgressBar mProgressBar;

    private Uri mImageUri;




    private DrawerLayout drawer;





    private static final String MODEL_PATH = "lite.tflite";
    private static final String LABEL_PATH = "lite.txt";
    private static final int INPUT_SIZE = 224;

    private Classifier classifier;

    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private Button btnDetectObject, btnToggleCamera;
    private ImageView imageViewResult;
    private CameraView cameraView;




    private FrameLayout frameLayout1;

    private Button btnCamera;

    private ImageView img2, img3, img4;

    private RelativeLayout relative1,relative2,relative4,relative5,relative6;

    private TextView txt7;

    private ImageView img1,img5, img6,img7,img8,img9,img10,img11;






    private ImageView face;

    private ImageView upload3;

    private TextView txt100;

    private ImageView premium;

    private ImageView transparentpic;

    private RelativeLayout relative100;


    private RelativeLayout r1000, r1, rrr1, rrr2, rrr3, rrr4, rrr5, rrr6;

    private Button btnCameraTotal,btnCameraTotal2, btnCameraeyes,btnCameraeyes2, btnCameratongue,btnCameratongue2;


    private TextView ttt1,ttt2,ttt3,ttt4,ttt5,ttt6;

    private HorizontalScrollView hscroll1;


    private ProgressBar progressbar;

    private RelativeLayout v1,v2,v3,v4,v5,v6;






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


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detector_location_seoul_food);

        question1 = findViewById(R.id.question1);



        textViewResult1 = findViewById(R.id.textViewResult1);
        mEditTextFileName = findViewById(R.id.edit_text_file_name);

        mEditTextFileName.addTextChangedListener(loginTextWatcher);

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(Detector_location_seoul_vegetable.this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            //openFileChooser();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", true);
            editor.commit();
        }


        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        upload2=findViewById(R.id.upload2);

        relative1 = findViewById(R.id.relative1);



        /*upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                AlertDialog.Builder builder = new AlertDialog.Builder(Detector_location_seoul_food.this);

                builder.setTitle(R.string.Toast3);
                builder.setMessage(R.string.Toast4);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (mUploadTask != null && mUploadTask.isInProgress()){
                            Toast.makeText(Detector_location_seoul_food.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                        }else {
                            uploadFile();
                            upload2.setVisibility(View.VISIBLE);
                            relative1.bringToFront();

                        }

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        upload2.setVisibility(View.VISIBLE);
                        relative1.bringToFront();
                    }
                });

                builder.create().show();

            }
        });
*/

        checkPermissions();

        progress = findViewById(R.id.progress);


        textView3 = findViewById(R.id.textView3);

        r0 = findViewById(R.id.r0);

        textviewsystom= findViewById(R.id.textviewsystom);

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);



        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        hscroll1 = findViewById(R.id.hscroll1);

        r0 = findViewById(R.id.r0);


        r1= findViewById(R.id.r1);



        rrr1=findViewById(R.id.rrr1);
        rrr2=findViewById(R.id.rrr2);
        rrr3=findViewById(R.id.rrr3);
        rrr4=findViewById(R.id.rrr4);
        rrr5=findViewById(R.id.rrr5);
        rrr6=findViewById(R.id.rrr6);

        ttt1=findViewById(R.id.ttt1);
        ttt2=findViewById(R.id.ttt2);
        ttt3=findViewById(R.id.ttt3);
        ttt4=findViewById(R.id.ttt4);
        ttt5=findViewById(R.id.ttt5);
        ttt6=findViewById(R.id.ttt6);


        ttt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                v6.setVisibility(View.INVISIBLE);
                relative2.setVisibility(View.VISIBLE);
                relative2.bringToFront();

            }
        });

        ttt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                v6.setVisibility(View.INVISIBLE);
                relative2.setVisibility(View.VISIBLE);
                relative2.bringToFront();

            }
        });

        ttt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.VISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                v6.setVisibility(View.INVISIBLE);
                relative2.setVisibility(View.VISIBLE);
                relative2.bringToFront();
            }
        });

        ttt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.VISIBLE);
                v5.setVisibility(View.INVISIBLE);
                v6.setVisibility(View.INVISIBLE);

            }
        });

        ttt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.VISIBLE);
                v6.setVisibility(View.INVISIBLE);



            }
        });

        ttt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                v5.setVisibility(View.INVISIBLE);
                v6.setVisibility(View.VISIBLE);

            }
        });







        relative100 = findViewById(R.id.relative100);


        transparentpic = findViewById(R.id.transparentpic);









        relativeLayout1 = findViewById(R.id.relativeLayout1);




        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);







        premium = findViewById(R.id.premium);

        txt100 = findViewById(R.id.txt100);



        imageViewResult_rel2 = findViewById(R.id.imageViewResult_rel2);
        imageViewResult2 = findViewById(R.id.imageViewResult2);




        rel100 = findViewById(R.id.rel100);
        rel101 = findViewById(R.id.rel101);




        info = findViewById(R.id.info);

        btnCamera2= findViewById(R.id.btnCamera2);




        img2 = findViewById(R.id.img2);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);

        relative2 = findViewById(R.id.relative2);
        relative2.bringToFront();





        face = findViewById(R.id.face2);

        face.setVisibility(View.VISIBLE);

        txt7 = findViewById(R.id.txt7);


        relative4 = findViewById(R.id.relative4);
        relative5 = findViewById(R.id.relative5);
        relative6 = findViewById(R.id.relative6);






        relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relative2.setVisibility(View.INVISIBLE);
                relative100.bringToFront();
            }
        });

        relative4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relative4.setVisibility(View.INVISIBLE);
                relative5.setVisibility(View.VISIBLE);
                relative5.bringToFront();
            }
        });


        relative5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relative5.setVisibility(View.INVISIBLE);
                relative6.setVisibility(View.VISIBLE);
                relative6.bringToFront();

            }
        });
        relative6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relative6.setVisibility(View.INVISIBLE);
                relative2.setVisibility(View.INVISIBLE);
                relative4.setVisibility(View.INVISIBLE);
                relative5.setVisibility(View.INVISIBLE);
                relative1.setVisibility(View.INVISIBLE);
            }
        });


        image_r2 = findViewById(R.id.image_r2);
        image_r3 = findViewById(R.id.image_r3);
        image_r4 = findViewById(R.id.image_r4);
        image_r5 = findViewById(R.id.image_r5);
        image_r6 = findViewById(R.id.image_r6);
        image_r7 = findViewById(R.id.image_r7);
        image_r8 = findViewById(R.id.image_r8);
        image_r9 = findViewById(R.id.image_r9);
        image_r10 = findViewById(R.id.image_r10);
        image_r11 = findViewById(R.id.image_r11);
        image_r12 = findViewById(R.id.image_r12);
        image_r13 = findViewById(R.id.image_r13);
        image_r14 = findViewById(R.id.image_r14);
        image_r15 = findViewById(R.id.image_r15);
        image_r16 = findViewById(R.id.image_r16);
        image_r17 = findViewById(R.id.image_r17);
        image_r18 = findViewById(R.id.image_r18);
        image_r19 = findViewById(R.id.image_r19);
        image_r20 = findViewById(R.id.image_r20);
        image_r21 = findViewById(R.id.image_r21);
        image_r22 = findViewById(R.id.image_r22);
        image_r23 = findViewById(R.id.image_r23);
        image_r24 = findViewById(R.id.image_r24);
        image_r25 = findViewById(R.id.image_r25);
        image_r26 = findViewById(R.id.image_r26);
        image_r27 = findViewById(R.id.image_r27);
        image_r28 = findViewById(R.id.image_r28);
        image_r29 = findViewById(R.id.image_r29);
        image_r30 = findViewById(R.id.image_r30);
        image_r31 = findViewById(R.id.image_r31);
        image_r32 = findViewById(R.id.image_r32);
        image_r33 = findViewById(R.id.image_r33);
        image_r34 = findViewById(R.id.image_r34);





        rel1 = findViewById(R.id.rel1);
        rel2 = findViewById(R.id.rel2);
        rel3 = findViewById(R.id.rel3);
        rel4 = findViewById(R.id.rel4);
        rel5 = findViewById(R.id.rel5);
        rel6 = findViewById(R.id.rel6);
        rel7 = findViewById(R.id.rel7);
        rel8 = findViewById(R.id.rel8);
        rel9 = findViewById(R.id.rel9);
        rel10 = findViewById(R.id.rel10);
        rel11 = findViewById(R.id.rel11);



        rel12 = findViewById(R.id.rel12);
        rel13 = findViewById(R.id.rel13);
        rel14 = findViewById(R.id.rel14);
        rel15 = findViewById(R.id.rel15);
        rel16 = findViewById(R.id.rel16);
        rel17 = findViewById(R.id.rel17);
        rel18 = findViewById(R.id.rel18);
        rel19 = findViewById(R.id.rel19);
        rel20 = findViewById(R.id.rel20);
        rel21 = findViewById(R.id.rel21);
        rel22 = findViewById(R.id.rel22);
        rel23 = findViewById(R.id.rel23);
        rel24 = findViewById(R.id.rel24);
        rel25 = findViewById(R.id.rel25);
        rel26 = findViewById(R.id.rel26);
        rel27 = findViewById(R.id.rel27);
        rel28 = findViewById(R.id.rel28);
        rel29 = findViewById(R.id.rel29);
        rel30 = findViewById(R.id.rel30);
        rel31 = findViewById(R.id.rel31);
        rel32 = findViewById(R.id.rel32);
        rel33 = findViewById(R.id.rel33);
        rel100 = findViewById(R.id.rel100);


        text0 = findViewById(R.id.text0);
        text1 = findViewById(R.id.nose_text);
        text2 = findViewById(R.id.nose2_text);
        text3 = findViewById(R.id.cheek_text);
        text4 = findViewById(R.id.cheek2_text);
        text5 = findViewById(R.id.forehead_text);
        text6 = findViewById(R.id.forehead2_text);
        text7 = findViewById(R.id.jaw_text);
        text8 = findViewById(R.id.temple_text);
        text9 = findViewById(R.id.eyes_text);
        text10 = findViewById(R.id.themiddleoftheforehead_text);

        text11= findViewById(R.id.mouse_text);
        text12= findViewById(R.id.mouse_text12);
        text13= findViewById(R.id.mouse_text13);
        text14= findViewById(R.id.mouse_text14);
        text15= findViewById(R.id.mouse_text15);
        text16= findViewById(R.id.mouse_text16);
        text17= findViewById(R.id.mouse_text17);
        text18= findViewById(R.id.mouse_text18);
        text19= findViewById(R.id.mouse_text19);
        text20= findViewById(R.id.mouse_text20);
        text21= findViewById(R.id.mouse_text21);
        text22= findViewById(R.id.mouse_text22);
        text23= findViewById(R.id.mouse_text23);
        text24= findViewById(R.id.mouse_text24);
        text25= findViewById(R.id.mouse_text25);
        text26= findViewById(R.id.mouse_text26);
        text27= findViewById(R.id.mouse_text27);
        text28= findViewById(R.id.mouse_text28);
        text29= findViewById(R.id.mouse_text29);
        text30= findViewById(R.id.mouse_text30);
        text31= findViewById(R.id.mouse_text31);
        text32= findViewById(R.id.mouse_text32);
        text33= findViewById(R.id.mouse_text33);

        colorfultable0 = findViewById(R.id.colorfultable0);
        colorfultable1 = findViewById(R.id.colorfultable1);
        colorfultable2 = findViewById(R.id.colorfultable2);
        colorfultable3 = findViewById(R.id.colorfultable3);
        colorfultable4 = findViewById(R.id.colorfultable4);
        colorfultable5 = findViewById(R.id.colorfultable5);
        colorfultable6 = findViewById(R.id.colorfultable6);
        colorfultable7 = findViewById(R.id.colorfultable7);

        colorfultable8 = findViewById(R.id.colorfultable8);

        colorfultable9 = findViewById(R.id.colorfultable9);
        colorfultable10 = findViewById(R.id.colorfultable10);


        colorfultable11 = findViewById(R.id.colorfultable11);
        colorfultable12 = findViewById(R.id.colorfultable12);
        colorfultable13 = findViewById(R.id.colorfultable13);
        colorfultable14 = findViewById(R.id.colorfultable14);
        colorfultable15 = findViewById(R.id.colorfultable15);
        colorfultable16 = findViewById(R.id.colorfultable16);
        colorfultable17 = findViewById(R.id.colorfultable17);
        colorfultable18 = findViewById(R.id.colorfultable18);
        colorfultable19 = findViewById(R.id.colorfultable19);
        colorfultable20 = findViewById(R.id.colorfultable20);
        colorfultable21 = findViewById(R.id.colorfultable21);
        colorfultable22 = findViewById(R.id.colorfultable22);
        colorfultable23 = findViewById(R.id.colorfultable23);
        colorfultable24 = findViewById(R.id.colorfultable24);
        colorfultable25 = findViewById(R.id.colorfultable25);
        colorfultable26 = findViewById(R.id.colorfultable26);
        colorfultable27 = findViewById(R.id.colorfultable27);
        colorfultable28 = findViewById(R.id.colorfultable28);
        colorfultable29 = findViewById(R.id.colorfultable29);
        colorfultable30 = findViewById(R.id.colorfultable30);
        colorfultable31 = findViewById(R.id.colorfultable31);
        colorfultable32 = findViewById(R.id.colorfultable32);
        colorfultable33 = findViewById(R.id.colorfultable33);



        r2_text = findViewById(R.id.r2_text);
        r3_text = findViewById(R.id.r3_text);
        r4_text = findViewById(R.id.r4_text);
        r5_text = findViewById(R.id.r5_text);
        r6_text = findViewById(R.id.r6_text);
        r7_text = findViewById(R.id.r7_text);
        r8_text = findViewById(R.id.r8_text);
        r9_text = findViewById(R.id.r9_text);
        r10_text = findViewById(R.id.r10_text);
        r11_text = findViewById(R.id.r11_text);
        r12_text = findViewById(R.id.r12_text);


        imageView2_1_button = findViewById(R.id.imageView2_1_button);
        imageView2_2_button = findViewById(R.id.imageView2_2_button);
        imageView2_3_button = findViewById(R.id.imageView2_3_button);
        imageView2_4_button = findViewById(R.id.imageView2_4_button);
        imageView2_5_button = findViewById(R.id.imageView2_5_button);
        imageView2_6_button = findViewById(R.id.imageView2_6_button);
        imageView2_7_button = findViewById(R.id.imageView2_7_button);
        imageView2_8_button = findViewById(R.id.imageView2_8_button);
        imageView2_9_button = findViewById(R.id.imageView2_9_button);
        imageView2_10_button = findViewById(R.id.imageView2_10_button);
        imageView2_11_button = findViewById(R.id.imageView2_11_button);


        imageView2_12_button= findViewById(R.id.imageView2_12_button);
        imageView2_13_button= findViewById(R.id.imageView2_13_button);
        imageView2_14_button= findViewById(R.id.imageView2_14_button);
        imageView2_15_button= findViewById(R.id.imageView2_15_button);
        imageView2_16_button= findViewById(R.id.imageView2_16_button);
        imageView2_17_button= findViewById(R.id.imageView2_17_button);
        imageView2_18_button= findViewById(R.id.imageView2_18_button);
        imageView2_19_button= findViewById(R.id.imageView2_19_button);
        imageView2_20_button= findViewById(R.id.imageView2_20_button);
        imageView2_21_button= findViewById(R.id.imageView2_21_button);
        imageView2_22_button= findViewById(R.id.imageView2_22_button);
        imageView2_23_button= findViewById(R.id.imageView2_23_button);
        imageView2_24_button= findViewById(R.id.imageView2_24_button);
        imageView2_25_button= findViewById(R.id.imageView2_25_button);
        imageView2_26_button= findViewById(R.id.imageView2_26_button);
        imageView2_27_button= findViewById(R.id.imageView2_27_button);
        imageView2_28_button= findViewById(R.id.imageView2_28_button);
        imageView2_29_button= findViewById(R.id.imageView2_29_button);
        imageView2_30_button= findViewById(R.id.imageView2_30_button);
        imageView2_31_button= findViewById(R.id.imageView2_31_button);
        imageView2_32_button= findViewById(R.id.imageView2_32_button);
        imageView2_33_button= findViewById(R.id.imageView2_33_button);
        imageView2_100_button= findViewById(R.id.imageView2_100_button);
        imageView2_101_button= findViewById(R.id.imageView2_101_button);



        imageView2_1_1_button = findViewById(R.id.imageView2_1_1_button);
        imageView2_2_1_button = findViewById(R.id.imageView2_2_1_button);
        imageView2_3_1_button = findViewById(R.id.imageView2_3_1_button);
        imageView2_4_1_button = findViewById(R.id.imageView2_4_1_button);
        imageView2_5_1_button = findViewById(R.id.imageView2_5_1_button);
        imageView2_6_1_button = findViewById(R.id.imageView2_6_1_button);
        imageView2_7_1_button = findViewById(R.id.imageView2_7_1_button);
        imageView2_8_1_button = findViewById(R.id.imageView2_8_1_button);
        imageView2_9_1_button = findViewById(R.id.imageView2_9_1_button);
        imageView2_10_1_button = findViewById(R.id.imageView2_10_1_button);
        imageView2_11_1_button = findViewById(R.id.imageView2_11_1_button);

        imageView2_12_1_button= findViewById(R.id.imageView2_12_1_button);
        imageView2_13_1_button= findViewById(R.id.imageView2_13_1_button);
        imageView2_14_1_button= findViewById(R.id.imageView2_14_1_button);
        imageView2_15_1_button= findViewById(R.id.imageView2_15_1_button);
        imageView2_16_1_button= findViewById(R.id.imageView2_16_1_button);
        imageView2_17_1_button= findViewById(R.id.imageView2_17_1_button);
        imageView2_18_1_button= findViewById(R.id.imageView2_18_1_button);
        imageView2_19_1_button= findViewById(R.id.imageView2_19_1_button);
        imageView2_20_1_button= findViewById(R.id.imageView2_20_1_button);
        imageView2_21_1_button= findViewById(R.id.imageView2_21_1_button);
        imageView2_22_1_button= findViewById(R.id.imageView2_22_1_button);
        imageView2_23_1_button= findViewById(R.id.imageView2_23_1_button);
        imageView2_24_1_button= findViewById(R.id.imageView2_24_1_button);
        imageView2_25_1_button= findViewById(R.id.imageView2_25_1_button);
        imageView2_26_1_button= findViewById(R.id.imageView2_26_1_button);
        imageView2_27_1_button= findViewById(R.id.imageView2_27_1_button);
        imageView2_28_1_button= findViewById(R.id.imageView2_28_1_button);
        imageView2_29_1_button= findViewById(R.id.imageView2_29_1_button);
        imageView2_30_1_button= findViewById(R.id.imageView2_30_1_button);
        imageView2_31_1_button= findViewById(R.id.imageView2_31_1_button);
        imageView2_32_1_button= findViewById(R.id.imageView2_32_1_button);
        imageView2_33_1_button= findViewById(R.id.imageView2_33_1_button);










        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );





        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);
        r6 = findViewById(R.id.r6);
        r7 = findViewById(R.id.r7);
        r8 = findViewById(R.id.r8);
        r9 = findViewById(R.id.r9);
        r10 = findViewById(R.id.r10);
        r11 = findViewById(R.id.r11);
        r12 = findViewById(R.id.r12);
        r13 = findViewById(R.id.r13);
        r14 = findViewById(R.id.r14);
        r15 = findViewById(R.id.r15);
        r16 = findViewById(R.id.r16);
        r17 = findViewById(R.id.r17);
        r18 = findViewById(R.id.r18);
        r19 = findViewById(R.id.r19);
        r20 = findViewById(R.id.r20);
        r21 = findViewById(R.id.r21);
        r22 = findViewById(R.id.r22);
        r23 = findViewById(R.id.r23);
        r24 = findViewById(R.id.r24);
        r25 = findViewById(R.id.r25);
        r26 = findViewById(R.id.r26);
        r27 = findViewById(R.id.r27);
        r28 = findViewById(R.id.r28);
        r29 = findViewById(R.id.r29);
        r30 = findViewById(R.id.r30);
        r31 = findViewById(R.id.r31);
        r32 = findViewById(R.id.r32);
        r33 = findViewById(R.id.r33);
        r34 = findViewById(R.id.r34);



        rel1_1 = findViewById(R.id.rel1_1);
        rel2_1 = findViewById(R.id.rel2_1);
        rel3_1 = findViewById(R.id.rel3_1);
        rel4_1 = findViewById(R.id.rel4_1);
        rel5_1 = findViewById(R.id.rel5_1);
        rel6_1 = findViewById(R.id.rel6_1);
        rel7_1 = findViewById(R.id.rel7_1);
        rel8_1 = findViewById(R.id.rel8_1);
        rel9_1 = findViewById(R.id.rel9_1);
        rel10_1 = findViewById(R.id.rel10_1);
        rel11_1 = findViewById(R.id.rel11_1);
        rel12_1 = findViewById(R.id.rel12_1);
        rel13_1 = findViewById(R.id.rel13_1);
        rel14_1 = findViewById(R.id.rel14_1);
        rel15_1 = findViewById(R.id.rel15_1);
        rel16_1 = findViewById(R.id.rel16_1);
        rel17_1 = findViewById(R.id.rel17_1);
        rel18_1 = findViewById(R.id.rel18_1);
        rel19_1 = findViewById(R.id.rel19_1);
        rel20_1 = findViewById(R.id.rel20_1);
        rel21_1 = findViewById(R.id.rel21_1);
        rel22_1 = findViewById(R.id.rel22_1);
        rel23_1 = findViewById(R.id.rel23_1);
        rel24_1 = findViewById(R.id.rel24_1);
        rel25_1 = findViewById(R.id.rel25_1);
        rel26_1 = findViewById(R.id.rel26_1);
        rel27_1 = findViewById(R.id.rel27_1);
        rel28_1 = findViewById(R.id.rel28_1);
        rel29_1 = findViewById(R.id.rel29_1);
        rel30_1 = findViewById(R.id.rel30_1);
        rel31_1 = findViewById(R.id.rel31_1);
        rel32_1 = findViewById(R.id.rel32_1);
        rel33_1 = findViewById(R.id.rel33_1);


        imageView2_1= findViewById(R.id.imageView2_1);

        imageView2_2= findViewById(R.id.imageView2_2);

        imageView2_3= findViewById(R.id.imageView2_3);

        imageView2_4= findViewById(R.id.imageView2_4);

        imageView2_5= findViewById(R.id.imageView2_5);
        imageView2_6= findViewById(R.id.imageView2_6);
        imageView2_7= findViewById(R.id.imageView2_7);
        imageView2_8= findViewById(R.id.imageView2_8);
        imageView2_9= findViewById(R.id.imageView2_9);
        imageView2_10= findViewById(R.id.imageView2_10);
        imageView2_11= findViewById(R.id.imageView2_11);

        ///살색과 빨간색 검은색이 아닐경우, 사진 에러.

        ///만약 처음 결과에서 아토피80, 염증20이 나왔을때 유저가 아토피에 관련된 증상을 고르면 아토피 결과 출력(100%로 판단)

        ///그러나 염증을 고른다면 다시한번 사진 올리도록 하고 다시한번 아토피 80 염증 20이 나온다면 아토피로 가고(유저가 잘 모르는 것으로 판단)

        ///염증 80 아토피 20이 나오거나 평균이 60%이상이 안나올때 애매하므로 결과 2개를 같이 출력 -> 그리고 설문지로 병명 판단.

        ///정말 애매 할 경우 의사 진단 후 평가 요망





        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);

        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);
        imageView25 = findViewById(R.id.imageView25);
        imageView26 = findViewById(R.id.imageView26);


        imageview27 = findViewById(R.id.imageView27);
        imageview28 = findViewById(R.id.imageView28);
        imageview29 = findViewById(R.id.imageView29);
        imageview30 = findViewById(R.id.imageView30);
        imageview31 = findViewById(R.id.imageView31);
        imageview32 = findViewById(R.id.imageView32);
        imageview33 = findViewById(R.id.imageView33);
        imageview34 = findViewById(R.id.imageView34);
        imageview35 = findViewById(R.id.imageView35);
        imageview36 = findViewById(R.id.imageView36);
        imageview37 = findViewById(R.id.imageView37);
        imageview38 = findViewById(R.id.imageView38);





        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable1.bringToFront();
                image_r2.setVisibility(View.VISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);


            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable2.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.VISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text3.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable3.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.VISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text4.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable4.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.VISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text5.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable5.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.VISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text6.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable6.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.VISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text7.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable7.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.VISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text8.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable8.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.VISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text9.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable9.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.VISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text10.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable10.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.VISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text11.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable11.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.VISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text12.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable12.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.VISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text13.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable13.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.VISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text14.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable14.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.VISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text15.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable15.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.VISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text16.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable16.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.VISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text17.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable17.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.VISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text18.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable18.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.VISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text19.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable19.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.VISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text20.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable20.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.VISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text21.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable21.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.VISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });
        imageview27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text22.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable22.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.VISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text23.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable23.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.VISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);            }
        });

        imageview29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text24.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable24.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.VISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);            }
        });

        imageview30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text25.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable25.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.VISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text26.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable26.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.VISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);            }
        });

        imageview32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text27.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable27.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.VISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);            }
        });

        imageview33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text28.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable28.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.VISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);            }
        });

        imageview34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text29.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable29.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.VISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text30.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable30.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.VISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text31.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable31.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.VISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text32.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable32.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.VISIBLE);
                image_r34.setVisibility(View.INVISIBLE);
            }
        });

        imageview38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text33.bringToFront();
                r0.setVisibility(View.INVISIBLE);
                colorfultable33.bringToFront();
                image_r2.setVisibility(View.INVISIBLE);
                image_r3.setVisibility(View.INVISIBLE);
                image_r4.setVisibility(View.INVISIBLE);
                image_r5.setVisibility(View.INVISIBLE);
                image_r6.setVisibility(View.INVISIBLE);
                image_r7.setVisibility(View.INVISIBLE);
                image_r8.setVisibility(View.INVISIBLE);
                image_r9.setVisibility(View.INVISIBLE);
                image_r10.setVisibility(View.INVISIBLE);
                image_r11.setVisibility(View.INVISIBLE);
                image_r12.setVisibility(View.INVISIBLE);
                image_r13.setVisibility(View.INVISIBLE);
                image_r14.setVisibility(View.INVISIBLE);
                image_r15.setVisibility(View.INVISIBLE);
                image_r16.setVisibility(View.INVISIBLE);
                image_r17.setVisibility(View.INVISIBLE);
                image_r18.setVisibility(View.INVISIBLE);
                image_r19.setVisibility(View.INVISIBLE);
                image_r20.setVisibility(View.INVISIBLE);
                image_r21.setVisibility(View.INVISIBLE);
                image_r22.setVisibility(View.INVISIBLE);
                image_r23.setVisibility(View.INVISIBLE);
                image_r24.setVisibility(View.INVISIBLE);
                image_r25.setVisibility(View.INVISIBLE);
                image_r26.setVisibility(View.INVISIBLE);
                image_r27.setVisibility(View.INVISIBLE);
                image_r28.setVisibility(View.INVISIBLE);
                image_r29.setVisibility(View.INVISIBLE);
                image_r30.setVisibility(View.INVISIBLE);
                image_r31.setVisibility(View.INVISIBLE);
                image_r32.setVisibility(View.INVISIBLE);
                image_r33.setVisibility(View.INVISIBLE);
                image_r34.setVisibility(View.VISIBLE);
            }
        });


/*

        imageView2_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_5_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_6_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });


        imageView2_7_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_8_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_9_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_10_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_11_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_12_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_13_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_14_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_15_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_16_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_17_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_18_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_19_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_20_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_21_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_22_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_23_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_24_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_25_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_26_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_27_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_28_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_29_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_30_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_31_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_32_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_33_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_100_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Login.class);
                startActivity(intent);
            }
        });
        imageView2_101_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Login.class);
                startActivity(intent);
            }
        });



        imageView2_1_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //payment
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_2_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_3_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_4_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_5_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_6_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });


        imageView2_7_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_8_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_9_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_10_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_11_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });

        imageView2_12_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_13_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_14_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_15_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);

            }
        });
        imageView2_16_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_17_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_18_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_19_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_20_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_21_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_22_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_23_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_24_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_25_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_26_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_27_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_28_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_29_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_30_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_31_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_32_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, Payment.class);
                startActivity(intent);
            }
        });
        imageView2_33_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detector_location_seoul_food.this, measles.class);
                startActivity(intent);
            }
        });


*/






        mButtonUpload = findViewById(R.id.button_upload);



        imageViewResult_rel = findViewById(R.id.imageViewResult_rel);


        relativeLayout2 = findViewById(R.id.relativeLayout2);

        LinearLayout1 = findViewById(R.id.LinearLayout1);




        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_open, R.string.navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();












        mButtonChooseImage = findViewById(R.id.button_choose_image);



        mProgressBar =findViewById(R.id.progress_bar);




        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });







        cameraView = findViewById(R.id.cameraView);
        imageViewResult = findViewById(R.id.imageViewResult);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        btnToggleCamera = findViewById(R.id.btnToggleCamera);
        btnDetectObject = findViewById(R.id.btnDetectObject);







        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {


            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {



                Bitmap bitmap = cameraKitImage.getBitmap();


                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);






                imageViewResult.setImageBitmap(bitmap);


                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);


                //flower 92%

                String s = results.toString().replaceAll("\\[","")
                        .replaceAll("\\]","")
                        .replaceAll("\\(","")
                        .replaceAll("\\)","")
                        .replaceAll("[A-Za-z]","")
                        .replaceAll("%","")
                        .replaceAll(",","\n")
                        .replaceAll("^[:digit:]", "");

                String a = results.toString().replaceAll("","");

                textViewResult.setText(results.toString());
                mProgressBar.setVisibility(View.INVISIBLE);








                if(results.toString().contains("strawberry")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r2.setVisibility(View.VISIBLE);
                    rel1.setVisibility(View.VISIBLE);
                    rel1_1.setVisibility(View.VISIBLE);

                    colorfultable1.setVisibility(View.VISIBLE);
                    text1.setVisibility(View.VISIBLE);




                } else {}
                if(results.toString().contains("apple")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    rel2.setVisibility(View.VISIBLE);
                    rel2_1.setVisibility(View.VISIBLE);

                    colorfultable2.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.VISIBLE);


                } else {}

                if(results.toString().contains("grape")) {
                    rel3.setVisibility(View.VISIBLE);
                    rel3_1.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable3.setVisibility(View.VISIBLE);
                    text3.setVisibility(View.VISIBLE);


                } else {}

                if(results.toString().contains("pineapple")) {


                    r5.setVisibility(View.VISIBLE);

                    colorfultable4.setVisibility(View.VISIBLE);
                    text4.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                    rel4.setVisibility(View.VISIBLE);
                    rel4_1.setVisibility(View.VISIBLE);

                } else {}

                if(results.toString().contains("banana")) {
                    rel5.setVisibility(View.VISIBLE);
                    rel5_1.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);

                    colorfultable5.setVisibility(View.VISIBLE);
                    text5.setVisibility(View.VISIBLE);

                } else {}

                if(results.toString().contains("kiwi")) {


                    rel5.setVisibility(View.VISIBLE);
                    rel5_1.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable5.setVisibility(View.VISIBLE);
                    text5.setVisibility(View.VISIBLE);

                } else {}

                if(results.toString().contains("melon1")) {

                    r7.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable6.setVisibility(View.VISIBLE);
                    text6.setVisibility(View.VISIBLE);

                    rel6.setVisibility(View.VISIBLE);
                    rel6_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("melon2")) {

                    r8.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable7.setVisibility(View.VISIBLE);
                    text7.setVisibility(View.VISIBLE);

                    rel7.setVisibility(View.VISIBLE);
                    rel7_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("pear")) {

                    r9.setVisibility(View.VISIBLE);

                    colorfultable8.setVisibility(View.VISIBLE);
                    text8.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                    rel8.setVisibility(View.VISIBLE);
                    rel8_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("tomato")) {
                    r10.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);

                    colorfultable9.setVisibility(View.VISIBLE);
                    text9.setVisibility(View.VISIBLE);

                    rel9.setVisibility(View.VISIBLE);
                    rel9_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("lemon")) {
                    textviewsystom.setVisibility(View.VISIBLE);
                    r11.setVisibility(View.VISIBLE);


                    colorfultable10.setVisibility(View.VISIBLE);
                    text10.setVisibility(View.VISIBLE);

                    rel10.setVisibility(View.VISIBLE);
                    rel10_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("pasta")) {
                    textviewsystom.setVisibility(View.VISIBLE);
                    r12.setVisibility(View.VISIBLE);

                    colorfultable11.setVisibility(View.VISIBLE);
                    text11.setVisibility(View.VISIBLE);

                    rel11.setVisibility(View.VISIBLE);
                    rel11_1.setVisibility(View.VISIBLE);

                } else {}

                if(results.toString().contains("orange")) {
                    r13.setVisibility(View.VISIBLE);
                    text12.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable12.setVisibility(View.VISIBLE);

                    rel12.setVisibility(View.VISIBLE);
                    rel12_1.setVisibility(View.VISIBLE);



                } else {}

                if(results.toString().contains("mandarinorange")) {
                    r14.setVisibility(View.VISIBLE);
                    rel13.setVisibility(View.VISIBLE);
                    rel13_1.setVisibility(View.VISIBLE);
                    colorfultable13.setVisibility(View.VISIBLE);

                    text13.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("citron")) {
                    textviewsystom.setVisibility(View.VISIBLE);
                    r15.setVisibility(View.VISIBLE);

                    colorfultable14.setVisibility(View.VISIBLE);

                    text14.setVisibility(View.VISIBLE);
                    rel14.setVisibility(View.VISIBLE);
                    rel14_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("persimmon1")) {
                    r16.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable15.setVisibility(View.VISIBLE);

                    text15.setVisibility(View.VISIBLE);

                    rel15.setVisibility(View.VISIBLE);
                    rel15_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("watermelon")) {
                    r17.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);

                    colorfultable16.setVisibility(View.VISIBLE);

                    text16.setVisibility(View.VISIBLE);
                    rel16.setVisibility(View.VISIBLE);
                    rel16_1.setVisibility(View.VISIBLE);
                } else {}




                if(results.toString().contains("peach")) {
                    r18.setVisibility(View.VISIBLE);

                    textviewsystom.setVisibility(View.VISIBLE);
                    colorfultable17.setVisibility(View.VISIBLE);

                    text17.setVisibility(View.VISIBLE);
                    rel17.setVisibility(View.VISIBLE);
                    rel17_1.setVisibility(View.VISIBLE);
                } else {}


                if(results.toString().contains("persimmon2")) {
                    textviewsystom.setVisibility(View.VISIBLE);
                    r19.setVisibility(View.VISIBLE);

                    colorfultable18.setVisibility(View.VISIBLE);

                    text18.setVisibility(View.VISIBLE);

                    rel18.setVisibility(View.VISIBLE);
                    rel18_1.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("persimmon3")) {
                    r20.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);

                    colorfultable19.setVisibility(View.VISIBLE);

                    text19.setVisibility(View.VISIBLE);
                    rel19.setVisibility(View.VISIBLE);
                    rel19_1.setVisibility(View.VISIBLE);
                } else {}



                if(results.toString().contains("notrelative1")) {
                    Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast7, Toast.LENGTH_LONG).show();

                } else {}

                if(results.toString().contains("notrelative2")) {
                    Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast7, Toast.LENGTH_LONG).show();

                } else {}

                if(results.toString().contains("notrelative3")) {
                    Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast7, Toast.LENGTH_LONG).show();

                } else {}


                if(results.toString().contains("Jujube")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r22.setVisibility(View.VISIBLE);

                    rel21.setVisibility(View.VISIBLE);
                    rel21_1.setVisibility(View.VISIBLE);

                    colorfultable21.setVisibility(View.VISIBLE);

                    text21.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("plum")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r21.setVisibility(View.VISIBLE);

                    rel20.setVisibility(View.VISIBLE);
                    rel20_1.setVisibility(View.VISIBLE);

                    colorfultable20.setVisibility(View.VISIBLE);

                    text20.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("onion1")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r23.setVisibility(View.VISIBLE);
                    rel22.setVisibility(View.VISIBLE);
                    rel22_1.setVisibility(View.VISIBLE);

                    colorfultable22.setVisibility(View.VISIBLE);

                    text22.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("cucumber")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r24.setVisibility(View.VISIBLE);
                    rel23.setVisibility(View.VISIBLE);
                    rel23_1.setVisibility(View.VISIBLE);

                    colorfultable23.setVisibility(View.VISIBLE);

                    text23.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("carrot")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r25.setVisibility(View.VISIBLE);
                    rel24.setVisibility(View.VISIBLE);
                    rel24_1.setVisibility(View.VISIBLE);

                    colorfultable24.setVisibility(View.VISIBLE);

                    text24.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("pumpkin")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r26.setVisibility(View.VISIBLE);
                    rel25.setVisibility(View.VISIBLE);
                    rel25_1.setVisibility(View.VISIBLE);

                    colorfultable25.setVisibility(View.VISIBLE);

                    text25.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("spinach")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r27.setVisibility(View.VISIBLE);
                    rel26.setVisibility(View.VISIBLE);
                    rel26_1.setVisibility(View.VISIBLE);

                    colorfultable26.setVisibility(View.VISIBLE);

                    text26.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("onion2")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r28.setVisibility(View.VISIBLE);
                    rel27.setVisibility(View.VISIBLE);
                    rel27_1.setVisibility(View.VISIBLE);

                    colorfultable27.setVisibility(View.VISIBLE);

                    text27.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("mushroom")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r29.setVisibility(View.VISIBLE);
                    rel28.setVisibility(View.VISIBLE);
                    rel28_1.setVisibility(View.VISIBLE);

                    colorfultable28.setVisibility(View.VISIBLE);

                    text28.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("chilipepper")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r30.setVisibility(View.VISIBLE);
                    rel29.setVisibility(View.VISIBLE);
                    rel29_1.setVisibility(View.VISIBLE);

                    colorfultable29.setVisibility(View.VISIBLE);

                    text29.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("tofu")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r31.setVisibility(View.VISIBLE);
                    rel30.setVisibility(View.VISIBLE);
                    rel30_1.setVisibility(View.VISIBLE);

                    colorfultable30.setVisibility(View.VISIBLE);

                    text30.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("chives")) {
                    textviewsystom.setVisibility(View.VISIBLE);

                    r32.setVisibility(View.VISIBLE);
                    rel31.setVisibility(View.VISIBLE);
                    rel31_1.setVisibility(View.VISIBLE);

                    colorfultable31.setVisibility(View.VISIBLE);

                    text31.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("potato")) {

                    textviewsystom.setVisibility(View.VISIBLE);
                    r33.setVisibility(View.VISIBLE);
                    rel32.setVisibility(View.VISIBLE);
                    rel32_1.setVisibility(View.VISIBLE);

                    colorfultable32.setVisibility(View.VISIBLE);

                    text32.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("leek")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("radish")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}


                if(results.toString().contains("yam")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("cabbage")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("greenpepper")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("beansprouts")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("bracken")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("broccoli")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("eggplant")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("redbean")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("walnut")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("garlic1")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("ginger")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("salt")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("asparagus")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("fish8")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("soup")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("jjige")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("pancake4")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}

                if(results.toString().contains("Mungge")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("Hesam")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("soup")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}
                if(results.toString().contains("soup")) {


                    r34.setVisibility(View.VISIBLE);
                    rel33.setVisibility(View.VISIBLE);
                    rel33_1.setVisibility(View.VISIBLE);

                    colorfultable33.setVisibility(View.VISIBLE);

                    text33.setVisibility(View.VISIBLE);
                    textviewsystom.setVisibility(View.VISIBLE);
                } else {}















                progress.setVisibility(View.INVISIBLE);




            }
            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnToggleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.toggleFacing();
            }
        });

        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast8, Toast.LENGTH_SHORT).show();

                rel33_1.setVisibility(View.VISIBLE);
                colorfultable0.setVisibility(View.VISIBLE);
                text0.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                textViewResult1.setVisibility(View.VISIBLE);
                textViewResult.setVisibility(View.VISIBLE);
                progress.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                r0.setVisibility(View.VISIBLE);
                cameraView.captureImage();
                relative100.setVisibility(View.INVISIBLE);
                relative4.setVisibility(View.VISIBLE);
                relative4.bringToFront();
                btnCamera2.setVisibility(View.INVISIBLE);
                info.setVisibility(View.VISIBLE);
                txt100.setVisibility(View.INVISIBLE);
                premium.setVisibility(View.VISIBLE);
                rel100.setVisibility(View.VISIBLE);
                rel101.setVisibility(View.VISIBLE);
                imageViewResult.setVisibility(View.VISIBLE);
                transparentpic.setVisibility(View.VISIBLE);




                relativeLayout2.setVisibility(View.VISIBLE);
            }
        });

        initTensorFlowAndLoadModel();
    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        });
    }

    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_PATH,
                            LABEL_PATH,
                            INPUT_SIZE);
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_detect:

                break;
            case R.id.nav_manage:

                break;


            case R.id.nav_precise:
                break;

            case R.id.nav_signin:

                break;

            case R.id.nav_login:

                break;



        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {




        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            imageViewResult.setImageBitmap(bitmap);
            galleryAddPic();

        }




        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();


            Picasso.with(this).load(mImageUri).into(imageViewResult2);



        }






    }

    private String getFileExtenstion(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }



    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast9, Toast.LENGTH_LONG).show();


    }



    private void CloseKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);

        }
    }





    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = mEditTextFileName.getText().toString().trim();

            mButtonUpload.setEnabled(!usernameInput.isEmpty());
            mButtonChooseImage.setEnabled(!usernameInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };






    private void uploadFile() {
        if (mImageUri != null) {

            StorageReference fileReferendce = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtenstion(mImageUri));

            mUploadTask = fileReferendce.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(Detector_location_seoul_vegetable.this, "Upload successful", Toast.LENGTH_LONG).show();
                            CloseKeyboard();
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                            mButtonUpload.setVisibility(View.INVISIBLE);
                            btnCamera2.setVisibility(View.INVISIBLE);
                            relative1.setVisibility(View.INVISIBLE);
                            relative2.setVisibility(View.VISIBLE);
                            relative2.bringToFront();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Detector_location_seoul_vegetable.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            mButtonUpload.setVisibility(View.VISIBLE);
                            btnCamera2.setVisibility(View.INVISIBLE);
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                               @Override
                                               public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                   Toast.makeText(Detector_location_seoul_vegetable.this, R.string.Toast8, Toast.LENGTH_SHORT).show();

                                                   double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                                   mProgressBar.setProgress((int) progress);
                                                   mButtonUpload.setVisibility(View.VISIBLE);
                                               }
                                           }


                    );
        } else {
            Toast.makeText(this,"No file selected", Toast.LENGTH_SHORT).show();
            openFileChooser();
        }
    }







}

