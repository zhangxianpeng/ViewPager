package com.example.administrator.viewpager;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * 先将图片资源放到一个int[]中，
 * 然后在for循环里面循环创建ImageView设置图片资源，
 * 再把ImageView添加到ArrayList集合中，
 * 然后在instantiateItem()方法中初始化item,
 * 把ImageView集合中的ImageView添加到ViewGroup中，
 * 然后返回ImageView，
 * 销毁ImageView的时候不能用super.destroyItem(container, position, object);，
 * 这个会导致滑到最后一个ImageView时报错。
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        initData();
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        Intent intent = new Intent(MainActivity.this,AnotherActivity.class);
        startActivity(intent);
    }


    private void initData() {
        mImageViewList = new ArrayList<>();
                 //把所有的ImageView都添加都mImageViewList的集合中
                 for (int i=0; i<mImageIds.length; i++){
                         //新建一个ImgeView将图片资源添加到ImageView中
                        ImageView imageView = new ImageView(this);
                        imageView.setBackgroundResource(mImageIds[i]);
                        //将ImageView添加到mImageViewList的集合中
                        mImageViewList.add(imageView);
                    }
    }

    class ViewPagerAdapter extends PagerAdapter {

                 //item的个数
                 @Override
                 public int getCount() {
                        return mImageViewList.size();
                    }

                 @Override
                public boolean isViewFromObject(View view, Object object) {
                        return view==object;
                    }
                    //初始化item布局
                 @Override
                public Object instantiateItem(ViewGroup container, int position) {
                        ImageView imageView = mImageViewList.get(position);
                        container.addView(imageView);//将imageView添加进来
                         return imageView;//将imageView返回
                     }

                //销毁Item
                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                        //super.destroyItem(container, position, object);//如果用这个一出Item的话滑到最后一个会报错
                        container.removeView((View)object);
                    }
     }


}
