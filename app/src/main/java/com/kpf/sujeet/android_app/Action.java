package com.kpf.sujeet.android_app;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Action extends Fragment {

    Button btn_click;
    private Camera mCamera;
    private CameraSurfaceView mSurfaceView;
    public static final int MEDIA_TYPE_IMAGE = 1;

    public static Boolean b=true;

    View view;
    String name,age,address,gender;


    public Action() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_action, container, false);



        btn_click = (Button) view.findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b) {
                    FrameLayout preview = (FrameLayout) view.findViewById(R.id.camera_preview);
                    mCamera = checkDeviceCamera();
                    mSurfaceView = new CameraSurfaceView(getContext(), mCamera);
                    preview.addView(mSurfaceView);
                    b=false;
                }else {
                    mCamera.takePicture(null, null, mPicture);
                    fill_info();
                    b=true;
                }
            }
        });



        return view;
    }

    public void fill_info()
    {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("User Details");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.user_details, null);
        final EditText edt_name = (EditText)dialogView.findViewById(R.id.edt_name);
        final EditText edt_age = (EditText)dialogView.findViewById(R.id.edt_age);
        final EditText edt_add = (EditText)dialogView.findViewById(R.id.edt_address);
        final EditText edt_gend = (EditText)dialogView.findViewById(R.id.edt_gender);
        Button btn_submit = (Button)dialogView.findViewById(R.id.btn_submit);
        alertDialog.setView(dialogView);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt_name.getText().toString().trim();
                age = edt_age.getText().toString().trim();
                address = edt_add.getText().toString().trim();
                gender = edt_gend.getText().toString().trim();

                try {
                    FileOutputStream fileout=getActivity().openFileOutput("abc.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                    outputWriter.write(name);
                    outputWriter.write(age);
                    outputWriter.write(address);
                    outputWriter.write(gender);
                    outputWriter.close();

                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });

        alertDialog.create();
        alertDialog.show();

    }

    private Camera checkDeviceCamera() {
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
//                Log.d("MediaFileError", "Error creating media file, check storage permissions: "+ e.getMessage());
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("File", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("AcceseError", "Error accessing file: " + e.getMessage());
            }
        }
    };

    private Bitmap scaleDownBitmapImage(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
        return resizedBitmap;
    }

    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        }else {
            return null;
        }

        return mediaFile;
    }


}
