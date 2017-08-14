package com.kpf.sujeet.android_app;


import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Show_List extends Fragment {

    ListView list;
    public Show_List() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show__list, container, false);
//Creating a listView to show the image list
        list = (ListView)view.findViewById(R.id.clicked_image);
        ArrayList<String> array = new ArrayList<String>();

        //getting the path of internal storage where images are stored

        File file = new File(Environment.getExternalStorageDirectory(),File.separator+"Pictures/MyCameraApp");

        //checking for null value if no images are saved in internal storage
        if(file.exists()) {
            //creating an array of files
            File file_A[] = file.listFiles();
//converting each file into string format and extracting the image name only and storing it into an arraylist
            for (int i = 0; i < file_A.length; i++) {
                String str = file_A[i].toString();
                int a = str.lastIndexOf("/") + 1;
                int b = str.length();
                str = str.substring(a, b);
                array.add(str);
            }
        }
//if there are no items in list then passing string message
            if (array.isEmpty()) {
                array.add("File Not Found");
            }
        //now with the help of ArrayAdapter setting the ArrayList to ListView
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array);
            list.setAdapter(adapter);



        return view;
    }

}
