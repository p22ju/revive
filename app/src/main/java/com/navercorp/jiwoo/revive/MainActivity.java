package com.navercorp.jiwoo.revive;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.navercorp.jiwoo.revive.UI.OverviewTab.RAdapter;
import com.navercorp.jiwoo.revive.UI.OverviewTab.SingleCardViewItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle toggle;
    private TabLayout tabLayout;
    private MyAdapter mAdapter;

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


        // 움직이는 라이브러리 툴바
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarCollapse);
        toolbarLayout.setTitle(getString(R.string.collapsingToolBar_Title_Text));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        setUpDrawerContent(navigationView);

        getSupportActionBar().setTitle("posttesttest"); //TODO 이게 왜 drawer부분에서 바뀌는지 알아내야됨. (원래 상단부분타이틀이 맞는건데)

        //**********************************
        // ViewPager관련
        //*********************************
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mAdapter = new MyAdapter(getSupportFragmentManager());
        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(mPager);
        //TODO 위에 모습이랑 어떻게 다른지 제대로 보기
        //ViewPagerAdapter mAdapter = new ViewPagerAdapter(getLayoutInflater(), this);  //TODO 파라미터 Fragment로 넣었고, 이전에는 Inflater 넣음. 차이확실히 찾기
        //mPager.setAdapter(mAdapter);

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
            if(position == 0) {
                return OverViewRecyclerViewFragment.newInstance(position);
            } else {
                return OverViewRecyclerViewFragment.newInstance(position);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " +( position + 1);
        }
    }


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
            //mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setAutoMeasureEnabled(true);
            mRecyclerView.setLayoutManager(layoutManager);
            RAdapter rAdapter = new RAdapter(mockDataForRecyclerView());
            mRecyclerView.setAdapter(rAdapter);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

//            setListAdapter(new ArrayAdapter<String>(getActivity(),
//                    android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
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


     static public ArrayList<SingleCardViewItem> mockDataForRecyclerView() {
         ArrayList<SingleCardViewItem> mocks = new ArrayList<>();
         SingleCardViewItem singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("신한");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("111");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("BC");
         singleCardViewItem.setExpense("2342");
         singleCardViewItem.setTargetBudgetCut("3333");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("국민");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("44444");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("현대");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("5555");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("현대");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("666666");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("현대");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("77777");
         mocks.add(singleCardViewItem);
         singleCardViewItem = new SingleCardViewItem();
         singleCardViewItem.setCardName("현대");
         singleCardViewItem.setExpense("222");
         singleCardViewItem.setTargetBudgetCut("888");
         mocks.add(singleCardViewItem);
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




