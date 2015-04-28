package org.crazyit.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SimpleAdapterTest extends Activity {
	private String[] names = new String[] { "虎头", "弄玉", "李清照", "李白" };
	private String[] descs = new String[] { "可爱的小孩", "一个擅长音乐的女孩", "一个擅长的文学的女性", "浪漫主义诗人" };
	private int[] imageIds = new int[] { R.drawable.tiger, R.drawable.nongyu, R.drawable.qingzhao, R.drawable.libai };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.simple_item, new String[] {
				"personName", "header", "desc" }, new int[] { R.id.name, R.id.header, R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			// 第position项被单击时激发该方法。
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(SimpleAdapterTest.this, names[position] + "被单击了", Toast.LENGTH_SHORT).show();
			}
		});
		//一般用按键或者鼠标滚轮移动焦点都会产生这个选中事件
		list.setOnItemSelectedListener(new OnItemSelectedListener() {
			// 第position项被选中时激发该方法。
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(SimpleAdapterTest.this, names[position] + "被选中了", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}
}