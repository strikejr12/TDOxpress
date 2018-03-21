package com.beyondthecode.tdoscharff.tdoxpress;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.beyondthecode.tdoscharff.tdoxpress.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.id_tablayout);
        mViewPager = findViewById(R.id.id_viewpager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Agregar Fragment acá

        mAdapter.agregarFragment(new ContactFragment(),"");
        mAdapter.agregarFragment(new FavoritoFragment(),"");


        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_group_black_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite_black_24dp);


        //remover la sombra del action bar

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {




        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.search_bar);

        SearchView searchView = (SearchView) searchItem.getActionView();

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        searchView.setQueryHint("Buscar contactos...");

                /*searchItem.getActionView();*/


        return true;
    }
}
