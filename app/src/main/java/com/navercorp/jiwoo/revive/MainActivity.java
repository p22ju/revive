package com.navercorp.jiwoo.revive;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.navercorp.jiwoo.revive.UI.DetailTab.DetailViewRecyclerAdapter;
import com.navercorp.jiwoo.revive.Database.UserExpense.DetailViewSingleItem;
import com.navercorp.jiwoo.revive.UI.OverviewTab.OverViewRecyclerAdapter;
import com.navercorp.jiwoo.revive.UI.OverviewTab.OverViewSingleItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private TabLayout mTabLayout;
    private MyAdapter mAdapter;

    private FloatingActionButton mFloatingActionButton;

//    public final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
////    private TextView mTestTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        //**********************************
        // FloatingActionButton 관련
        //**********************************
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "스낵바", Snackbar.LENGTH_INDEFINITE).setAction("액션", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast
                        Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                        startActivity(intent);
                    }
                }).show();
            }
        });
        //mFloatingActionButton.att


        //**********************************
        // CollapsingToolbarLayout 관련
        //**********************************
        // 라이브러리 상단 전체 툴바
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarCollapse);
        // 금액부분 타이틀 텍스트뷰
        toolbarLayout.setTitle("Revive");
        // 현재날짜 타이틀 텍스트뷰
        TextView textViewForCurrentDate = (TextView) toolbarLayout.getChildAt(1);
        textViewForCurrentDate.setText(getCurrentTimeString());

        // 금액부분에 애니메이션을 넣어줄까 하는 부분
        /*
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            }
        });
        */

        //**********************************
        // ViewPager 관련
        //**********************************
        // 탭 메뉴
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        // 뷰페이저, 뷰페이저 어댑터
        mAdapter = new MyAdapter(getSupportFragmentManager());
        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);
        // 탭 메뉴에 뷰페이저 접합
        mTabLayout.setupWithViewPager(mPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        setUpDrawerContent(navigationView);

        getSupportActionBar().setTitle("posttesttest"); //TODO 이게 왜 drawer부분에서 바뀌는지 알아내야됨. (원래 상단부분타이틀이 맞는건데)
    }


    //TODO NonNull 이 시그니처 정확히 뭔지 파악하기
    @NonNull
    private String getCurrentTimeString() {
        // 뒷부분 오늘 날짜(MM.dd) 구하기
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd");
        Date currentTime = new Date();
        String mTime = simpleDateFormat.format(currentTime);

        // 앞부분 현재 월수(MM.01) 구하기
        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.MONTH);

        // 구한 날짜들을 붙인다.
        StringBuilder sb = new StringBuilder("");
        sb.append(String.valueOf(year + 1));
        sb.append(".01~");
        sb.append(mTime);
        sb.append(" (이번 달 지출)");
        return sb.toString();
    }


    public void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    mDrawerLayout.closeDrawers();
                    menuItem.setChecked(true);
                    getSupportActionBar().setTitle(menuItem.getTitle());
                    return true;
                }
            });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    //**********************************
    // ViewPager 어댑터
    // (FragmentStatePagerAdapter 상속)
    //**********************************
    public static class MyAdapter extends FragmentStatePagerAdapter {
        private static final int NUM_ITEMS = 2;

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            //TODO 더 좋은 방법 없나
            if(position == 0) {
                return OverViewRecyclerViewFragment.newInstance(position);
            } else {
                return DetailViewRecyclerViewFragment.newInstance(position);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //TODO 더 좋은 방법 없나
            if(position == 0) {
                return "전체";
            } else {
                return "지출상세";
            }
        }
    }


    //**********************************
    // 1. OverView 프래그먼트
    //**********************************
    public static class OverViewRecyclerViewFragment extends Fragment {
        int mNum;
        RecyclerView mRecyclerView;


        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static OverViewRecyclerViewFragment newInstance(int num) {
            OverViewRecyclerViewFragment f = new OverViewRecyclerViewFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num + 1);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            layoutManager.setAutoMeasureEnabled(true);
            //layoutManager.
            mRecyclerView.setLayoutManager(layoutManager);
            OverViewRecyclerAdapter overViewRecyclerAdapter = new OverViewRecyclerAdapter(mockDataForRecyclerView());
            mRecyclerView.setAdapter(overViewRecyclerAdapter);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
          //setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
        }

//        @Override
//        public void onListItemClick(ListView l, View v, int position, long id) {
//            Log.i("FragmentList", "Item clicked: " + id);
//        }
    }


    //**********************************
    // 2. DetailView 프래그먼트
    //**********************************
    public static class DetailViewRecyclerViewFragment extends Fragment {
        int mNum;
        RecyclerView mRecyclerView;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static DetailViewRecyclerViewFragment newInstance(int num) {
            DetailViewRecyclerViewFragment f = new DetailViewRecyclerViewFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num + 1);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            layoutManager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(layoutManager);
            DetailViewRecyclerAdapter overViewRecyclerAdapter = new DetailViewRecyclerAdapter(mockDataForRecyclerViewForDetail());
            mRecyclerView.setAdapter(overViewRecyclerAdapter);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

//        @Override
//        public void onListItemClick(ListView l, View v, int position, long id) {
//            Log.i("FragmentList", "Item clicked: " + id);
//        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS: {
//
//                if( (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED) ) {
//                    // permission was granted, yay! Do the
//                    // sms-related task you need to do.
//
//                    //TODO 여기다가 sms 관련코드 넣어야 할듯
//
//                } else {
//
//                }
//                return;
//            }
//        }
//    }


     static public ArrayList<OverViewSingleItem> mockDataForRecyclerView() {
         ArrayList<OverViewSingleItem> mocks = new ArrayList<>();
         OverViewSingleItem overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("신한");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("111");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("BC");
         overViewSingleItem.setCurrentSpending("2342");
         overViewSingleItem.setTargetBudget("3333");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("국민");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("44444");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("현대");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("5555");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("현대");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("666666");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("현대");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("77777");
         mocks.add(overViewSingleItem);
         overViewSingleItem = new OverViewSingleItem();
         overViewSingleItem.setCardType("현대");
         overViewSingleItem.setCurrentSpending("222");
         overViewSingleItem.setTargetBudget("888");
         mocks.add(overViewSingleItem);
         return  mocks;
     }


    static public ArrayList<DetailViewSingleItem> mockDataForRecyclerViewForDetail() {
        ArrayList<DetailViewSingleItem> mocks = new ArrayList<>();
        DetailViewSingleItem detailViewSingleItem = new DetailViewSingleItem();
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(5500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        detailViewSingleItem.setCardType("신한");
        detailViewSingleItem.setExpenseDate("10/02");
        detailViewSingleItem.setExpenseDesc("파리크라상");
        detailViewSingleItem.setExpenditure(6500);
        mocks.add(detailViewSingleItem);
        return  mocks;
    }

}








//        //**********************************
//        // DB 관련
//        //*********************************
//        final DBManager dbManager = new DBManager(getApplicationContext(), "ExpenseList.db", null, 1);
////        mTestTextView = (TextView) findViewById(R.id.textView01);
//
//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
//        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            //사용자에게 권한이 없다면
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
//
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.RECEIVE_SMS},
//                        MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
//            }
//        } else {
//            //사용자에게 권한이 잆다면
//        }
//
//        dbManager.dropTable();
//
//        String message = "[Web발신]\n" +
//                "KB국민카드 7*5*\n" +
//                "박*배님\n" +
//                "10/08 14:09\n" +
//                "6,400원\n" +
//                "(주)파리크라상\n" +
//                "누적 3,728,374원\n";
//        String  exp = extractExpenseToString(message);
//        extractExpenseToInt(message);
//        String totalExp = extractAccumExpense(message);
//        extractExpenseDate(message);
//        dbManager.insert("INSERT INTO " + ExpenseDB.CreateDB._TABLE_NAME + " values(null, '10/8', '파리크라상', '" + exp + "', '" + totalExp + "', '남은가능금액');");
////        mTestTextView.setText(dbManager.printData());
//
//        message = "[Web발신]\n" +
//                "KB국민카드 7*5*\n" +
//                "박*배님\n" +
//                "10/08 14:09\n" +
//                "99원\n" +
//                "(주)파리크라상\n" +
//                "누적 3,728,374원\n";
//        exp = extractExpenseToString(message);
//        extractExpenseToInt(message);
//        totalExp = extractAccumExpense(message);
//        extractExpenseDate(message);
//        dbManager.insert("INSERT INTO " + ExpenseDB.CreateDB._TABLE_NAME + " values(null, '10/8', '파리크라상', '" + exp + "', '" + totalExp + "', '남은가능금액');");
////        mTestTextView.setText(dbManager.printData());
//
//
//        message = "[Web발신]\n" +
//                "롯데 박*배(3*7*) 9,000원 일시불 10/07 15:18 누적1,995,725원 주민약국\n";
//        extractExpenseDate(message);
//
//        message = "[Web발신]\n" +
//                "삼성카드승인8758황*진\n" +
//                "09/28 01:01 롯데홈쇼핑\n" +
//                "133,920원 06개월\n" +
//                "누적1,176,320원\n";
//        extractExpenseDate(message);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



//    ArrayList<OverViewItem> mockItems = new ArrayList();
//    OverViewItem overViewItem = new OverViewItem();
//overViewItem.setCardType("신한");
//        overViewItem.setTargetBudget("222");
//        overViewItem.setTotalExpense("5555");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한2");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//        overViewItem = new OverViewItem();
//        overViewItem.setCardType("신한55555555555555555555555555");
//        overViewItem.setTargetBudget("222234234");
//        overViewItem.setTotalExpense("55554443");
//        mockItems.add(overViewItem);
//
//        OverViewListAdapter overViewListAdapter = new OverViewListAdapter(getContext(), mockItems);
//        mRecyclerView.setAdapter(overViewListAdapter);




