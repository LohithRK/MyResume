package com.example.myresume;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myresume.adapter.DrawerListAdapter;
import com.example.myresume.model.TabElement;

public class MainActivity extends Activity {

	DrawerLayout drawer_layout;
	ListView lvSlideMenu;

	String[] tabNames;

	ArrayList<TabElement> tabList;

	CharSequence appTitle;
	CharSequence drawerTitle;

	ActionBarDrawerToggle drawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lvSlideMenu = (ListView) findViewById(R.id.lvSlideMenu);
		
		tabNames = getResources().getStringArray(R.array.drawer_tab_names);

		tabList = new ArrayList<TabElement>();
		tabList.add(new TabElement(tabNames[0]));
		tabList.add(new TabElement(tabNames[1]));
		tabList.add(new TabElement(tabNames[2]));
		tabList.add(new TabElement(tabNames[3]));
		tabList.add(new TabElement(tabNames[4]));
		tabList.add(new TabElement(tabNames[5]));
		tabList.add(new TabElement(tabNames[6]));
		tabList.add(new TabElement(tabNames[7]));
		tabList.add(new TabElement(tabNames[8]));

		DrawerListAdapter tabAdapter = new DrawerListAdapter(
				getApplicationContext(), tabList);

		lvSlideMenu.setAdapter(tabAdapter);

		lvSlideMenu.setOnItemClickListener(new SlideMenuItemClickListener());

		appTitle = drawerTitle = getTitle();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawer_layout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(drawerTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(appTitle);
				invalidateOptionsMenu();
			}
		};

		drawer_layout.setDrawerListener(drawerToggle);

		if (savedInstanceState == null) {
			setFragmentToContentView(0);
		}

	}

	private class SlideMenuItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			setFragmentToContentView(position);
		}

	}

	public void setFragmentToContentView(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new ObjectiveFragment();
			break;
		case 2:
			fragment = new AcademicsFragment();
			break;
		case 3:
			fragment = new SkillsFragment();
			break;
		case 4:
			fragment = new ProjectsFragment();
			break;
		case 5:
			fragment = new InterestsFragment();
			break;
		case 6:
			fragment = new HobbiesFragment();
			break;
		case 7:
			fragment = new ContactFragment();
			break;
		case 8:
			fragment = new ThankYouFragment();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager manager = getFragmentManager();
			manager.beginTransaction()
					.replace(R.id.fragment_container, fragment).commit();
			lvSlideMenu.setItemChecked(position, true);
			lvSlideMenu.setSelection(position);
			setTitle(tabNames[position]);
			drawer_layout.closeDrawer(lvSlideMenu);
		} else {
			Log.e("MainActivity", "Error in Creating Fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		drawerTitle = title;
		getActionBar().setTitle(drawerTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawer_layout.isDrawerOpen(lvSlideMenu);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		drawerToggle.onConfigurationChanged(newConfig);
		super.onConfigurationChanged(newConfig);
	}	

}
