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
	private String[] names = new String[] { "��ͷ", "Ū��", "������", "���" };
	private String[] descs = new String[] { "�ɰ���С��", "һ���ó����ֵ�Ů��", "һ���ó�����ѧ��Ů��", "��������ʫ��" };
	private int[] imageIds = new int[] { R.drawable.tiger, R.drawable.nongyu, R.drawable.qingzhao, R.drawable.libai };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ����һ��List���ϣ�List���ϵ�Ԫ����Map
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
			// ��position�����ʱ�����÷�����
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(SimpleAdapterTest.this, names[position] + "��������", Toast.LENGTH_SHORT).show();
			}
		});
		//һ���ð��������������ƶ����㶼��������ѡ���¼�
		list.setOnItemSelectedListener(new OnItemSelectedListener() {
			// ��position�ѡ��ʱ�����÷�����
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(SimpleAdapterTest.this, names[position] + "��ѡ����", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}
}