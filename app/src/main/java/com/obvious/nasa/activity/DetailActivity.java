package com.obvious.nasa.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.obvious.nasa.R;
import com.obvious.nasa.helpers.AppConstants;
import com.obvious.nasa.models.NASADataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Jai on 27/11/2021.
 * Location India
 */

public class DetailActivity extends BaseActivity {
    private int itemPosition;
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //status bar or the time bar at the top
        }

        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail ");

        itemPosition = getIntent().getIntExtra("id", 0);

        mViewPager = (ViewPager) findViewById(R.id.vp_details);
        mViewPagerAdapter = new ViewPagerAdapter(DetailActivity.this, AppConstants.dataModelArrayList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(itemPosition);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        dismissKeyboard();
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    class ViewPagerAdapter extends PagerAdapter {
        Context context;
        LayoutInflater mLayoutInflater;
        ArrayList<NASADataModel> nasaDataModels;

        public ViewPagerAdapter(Context context, ArrayList<NASADataModel> nasaDataModels) {
            this.context = context;
            this.nasaDataModels = nasaDataModels;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // return the number of images
            return nasaDataModels.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((RelativeLayout) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            // inflating the item.xml
            View itemView = mLayoutInflater.inflate(R.layout.custom_viewpager_view, container, false);
            TextView tvTitle = itemView.findViewById(R.id.tv_title);
            TextView tvDate = itemView.findViewById(R.id.tv_date);
            TextView tvDiscription = itemView.findViewById(R.id.tv_discription);
            ImageView imageView = itemView.findViewById(R.id.img_data);
            ProgressBar pbLoading = itemView.findViewById(R.id.pg_image_load);

            tvTitle.setText(nasaDataModels.get(position).getTitle());
            tvDate.setText(nasaDataModels.get(position).getDate());
            tvDiscription.setText(nasaDataModels.get(position).getExplanation());

            String url = nasaDataModels.get(position).getHdurl();
            Picasso.with(context).load(url).fit().centerInside()
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            if (pbLoading != null) {
                                pbLoading.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });

            // Adding the View
            Objects.requireNonNull(container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((RelativeLayout) object);
        }
    }
}