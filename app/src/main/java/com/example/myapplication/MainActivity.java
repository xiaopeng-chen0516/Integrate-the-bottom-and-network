package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.fragment.MyFragment1;
import com.example.fragment.MyFragment2;
import com.example.fragment.MyFragment3;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;





public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    List<Fragment> fragments;
    BottomNavigationView bottomNavigationView;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);


        //底部菜单导航栏与viewPager相交互
        bottomNavigationView=findViewById(R.id.bottom1);




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //当点击到某子项，ViewPager就滑动到对应位置
                    case R.id.item1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item3:
                        viewPager.setCurrentItem(2);
                        break;
                    default: break;

                }
                return false;
            }
        });
        fragments=new ArrayList<>();
        fragments.add(new MyFragment1());
        fragments.add(new MyFragment2());
        fragments.add(new MyFragment3());
        Adapter adapter=new Adapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            //onPageSelected()方法只在滑动停止时调用，position滑动停止所在页面位置
            @Override
            public void onPageSelected(int position) {
                if(menuItem!=null){
                    //将菜单子项设置为未选中状态
                    menuItem.setChecked(false);
                }else{
                    //菜单项均未被选中时，获取菜单子项中的第一个，将其设置为选中状态
                    menuItem=bottomNavigationView.getMenu().getItem(0);
                    menuItem.setChecked(true);
                }
                menuItem=bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }





    private class Adapter extends FragmentStatePagerAdapter {

        List<Fragment> list1;
        public Adapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.list1=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return list1.get(position);
        }

        @Override
        public int getCount() {
            return list1.size();
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /*避免Fragment的销毁*/
            // super.destroyItem(container, position, object);

        }

    }
}
