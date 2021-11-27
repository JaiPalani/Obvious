package com.obvious.nasa.activity;

import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obvious.nasa.R;
import com.obvious.nasa.models.NASADataModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jai on 27/11/2021.
 * Location India
 */

public class MainActivity extends BaseActivity {
    ArrayList<NASADataModel> dataModelArrayList;
    ArrayList<String> imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top
        }

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Gallery");

        getJsonData();

        final GridView gridview = (GridView) findViewById(R.id.gv_images);
        gridview.setAdapter(new ImageAdapter(this, imageURL));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                /*Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                i.putExtra("id", position);
                startActivity(i);*/
            }
        });


    }

    private void getJsonData() {
        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open("data.json");

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);

            Type listType = new TypeToken<List<NASADataModel>>(){}.getType();
            dataModelArrayList = gson.fromJson(reader, listType);

            imageURL = new ArrayList<>();
            for (NASADataModel nasaDataModel: dataModelArrayList) {
                imageURL.add(nasaDataModel.getUrl());
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    class ImageAdapter extends BaseAdapter {
        private Context mContext;
        public ArrayList<String> imageURLsList;

        public ImageAdapter(Context c, ArrayList<String> imageURLList) {
            mContext = c;
            this.imageURLsList = imageURLList;
        }

        public int getCount() {
            return imageURLsList.size();
        }

        @Override
        public String getItem(int position) {
            return imageURLsList.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.grid_custom_view, null);
            }
            ImageView imgNasa = v.findViewById(R.id.img_nasa);

            String url = getItem(position);
            Picasso.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.progress_animation)
                    .fit()
                    .centerCrop().into(imgNasa);
            return v;
        }
    }
}