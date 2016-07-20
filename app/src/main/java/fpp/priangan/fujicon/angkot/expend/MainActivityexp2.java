package fpp.priangan.fujicon.angkot.expend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import fpp.priangan.fujicon.angkot.R;

public class MainActivityexp2 extends ActionBarActivity implements ExpandableListView.OnChildClickListener {

	ExpandableListView expandableListView;
	MyExpandableListAdapter myExpandableListAdapter;
	List<String> groupList;
	HashMap<String, List<String>> childMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainexp);
		
		init();
		expandableListView = (ExpandableListView) findViewById(R.id.mylist);
		myExpandableListAdapter = new MyExpandableListAdapter(this, groupList, childMap);
		expandableListView.setAdapter(myExpandableListAdapter);
	}

	private void init() {
		groupList = new ArrayList<String>();
		childMap = new HashMap<String, List<String>>();

		List<String> groupList0 = new ArrayList<String>();
		groupList0.add("groupList0 - 1");
		groupList0.add("groupList0 - 2");
		groupList0.add("groupList0 - 3");
		
		List<String> groupList1 = new ArrayList<String>();
		groupList1.add("groupList1 - 1");
		groupList1.add("groupList1 - 2");
		groupList1.add("groupList1 - 3");

		List<String> groupList2 = new ArrayList<String>();
		groupList2.add("groupList2 - 1");
		groupList2.add("groupList2 - 2");
		groupList2.add("groupList2 - 3");

		List<String> groupList3 = new ArrayList<String>();
		groupList3.add("groupList3 - 1");
		groupList3.add("groupList3 - 2");
		groupList3.add("groupList3 - 3");
		
		

		groupList.add("Group List 0");
		groupList.add("Group List 1");
		groupList.add("Group List 2");
		groupList.add("Group List 3");

		childMap.put(groupList.get(0), groupList0);
		childMap.put(groupList.get(1), groupList1);
		childMap.put(groupList.get(2), groupList2);
		childMap.put(groupList.get(3), groupList3);
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
								int groupPosition, int childPosition, long id) {
		Toast.makeText(MainActivityexp2.this, "Clicked On Child",
				Toast.LENGTH_SHORT).show();
		return true;
	}
}
