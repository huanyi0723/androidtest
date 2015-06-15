# ListView

# 属性
- android:choiceMode //选择模式 单选 多选 
- android:drawSelectorOnTop //选中列表项是否显示最上面
- android:fastScrollEnabled //是否可以快速滚动
- android:listSelector //被选中列表项上图
- android:scrollingCache //是否滚动时绘制缓存
- android:smoothScrollbar //分割条
- android:stackFromBottom //从底部排列列表
- android:textFilterEnabled //对列表进行过滤
- android:transcriptMode //滚动模式
- android:scrollbars="none" //隐藏滚动条 
- android:divider //分割条
- android:dividerHeight //分割线高度
- android:entries //数组资源生成列表
- android:headerDividersEnabled //headerView 之后分割条
- android:footerDividersEnabled //footerView 之前分割条


# 方法
- 常用的Adapter
ArrayAdapter
SimpleAdapter
SimpleCursorAdapter
BaseAdapter

- ArrayAdapter
		ListView list1 = (ListView) findViewById(R.id.list1);
		// 定义一个数组
		String[] arr1 = { "孙悟空", "猪八戒", "牛魔王" };
		// 将数组包装ArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.array_item, arr1);
		// 为ListView设置Adapter
		list1.setAdapter(adapter1);
		
		
		ListView list2 = (ListView) findViewById(R.id.list2);
		// 定义一个数组
		String[] arr2 = { "Java", "Hibernate", "Spring", "Android" };
		// 将数组包装ArrayAdapter
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.checked_item, arr2);
		// 为ListView设置Adapter
		list2.setAdapter(adapter2);

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!-- 设置使用红色的分隔条 -->

    <ListView
        android:id="@+id/list1"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:divider="#f00"
        android:dividerHeight="2px"
        android:headerDividersEnabled="false" />
    <!-- 设置使用绿色的分隔条 -->

    <ListView
        android:id="@+id/list2"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:divider="#0f0"
        android:dividerHeight="2px"
        android:headerDividersEnabled="false" />

</LinearLayout>

- SimpleAdapter
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

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal" >

    <ListView
        android:id="@+id/mylist"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" />

</LinearLayout>

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal" >

    <!-- 定义一个ImageView，用于作为列表项的一部分。 -->

    <ImageView
        android:id="@+id/header"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:paddingLeft="10dp" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical" >

        <!-- 定义一个TextView，用于作为列表项的一部分。 -->

        <TextView
            android:id="@+id/name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="10dp"
            android:textColor="#f0f"
            android:textSize="20dp" />
        <!-- 定义一个TextView，用于作为列表项的一部分。 -->

        <TextView
            android:id="@+id/desc"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingLeft="10dp"
            android:textSize="14dp" />
    </LinearLayout>

</LinearLayout>

- BaseAdapter
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <ListView
        android:id="@+id/myList"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>

public class BaseAdapterTest extends Activity {
	ListView myList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myList = (ListView) findViewById(R.id.myList);
		
		
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public int getCount() {
				// 指定一共包含40个选项
				return 40;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			// 重写该方法，该方法的返回值将作为列表项的ID
			@Override
			public long getItemId(int position) {
				return position;
			}

			// 重写该方法，该方法返回的View将作为列表框
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// 创建一个LinearLayout，并向其中添加2个组件
				LinearLayout line = new LinearLayout(BaseAdapterTest.this);
				line.setOrientation(0);
				ImageView image = new ImageView(BaseAdapterTest.this);
				image.setImageResource(R.drawable.ic_launcher);
				TextView text = new TextView(BaseAdapterTest.this);
				text.setText("第" + (position + 1) + "个列表项");
				text.setTextSize(20);
				text.setTextColor(Color.RED);
				line.addView(image);
				line.addView(text);
				// 返回LinearLayout实例
				return line;
			}
		};
		
		myList.setAdapter(adapter);
	}
}



- 1 给ListView添加头部
2、当listview需要添加headerview时，可以通过调用listview的addHeaderView(headView, null, false) 方法，
该方法还有一个重载方法 addHeaderView(headView);这两个方法的区别是前一个方法可以控制header是否可以被selected，
如果不想被selected则将第三个参数设置成false；
3、接着上面说的添加header，添加header时调用的addHeaderView方法必须放在listview.setadapter前面，
意思很明确就是如果想给listview添加头部则必须在给其绑定adapter前添加，否则会报错。
原因是当我们在调用setAdapter方法时会android会判断当前listview是否已经添加header，
如果已经添加则会生成一个新的tempadapter，这个新的tempadapter包含我们设置的adapter所有内容以及listview
的header和footer。所以当我们在给listview添加了header后在程序中调用listview.getadapter时返回的是tempadapter
而不是我们通过setadapter传进去的adapter。如果没有设置adapter则tempadapter与我们自己的adapter是一样的。
listview.getadapter().getcount()方法返回值会比我们预期的要大，原因是添加了header。
4、接着上面的tempadapter说，我们自定义adapter里面的getitem方法里面返回的position是不包括header的，
是我们自定义adapter中数据position编号从0开始，也就是说与我们传进去的list的位置是一样的。


代码写法
			private View mListViewHeader;// 头部View 
			mListViewHeader = createListViewHeader(mBannerList);
			mXListView.addHeaderView(mListViewHeader);
//首页栏
		private View createListViewHeader(List<NewsBanner> bannerList) {
		LayoutInflater inflater = LayoutInflater.from(this.getActivity());

		View listviewHeader = inflater.inflate(R.layout.listview_header, null);
		listviewHeader.setLayoutParams(new XListViewHeadViewLayoutParam(
				getActivity()).getListviewImagePagerLayoutParams());

		ViewGroup imageIndicator = (ViewGroup) listviewHeader
				.findViewById(R.id.image_indicator);

		bannerViewPager = (AutoScrollViewPager) listviewHeader
				.findViewById(R.id.image_pager);
		bannerViewPager.setInterval(3000l);
		mBannerAdapter = new ImagePagerAdapter(inflater, bannerList);
		bannerViewPager.setAdapter(mBannerAdapter);
		bannerViewPager.setOnPageChangeListener(new ImagePageChangeListener(
				imageIndicator, bannerList.size()));
		bannerViewPager.startAutoScroll();
		return listviewHeader;
	}

- 如何判断ListView已添加头部
目前的方法
			if (mListViewHeader == null) {
				mListViewHeader = createListViewHeader(contentDataResp);
				contentfrag_list.addHeaderView(mListViewHeader);
			}

- 2 判断滑动到顶端还是底部
	getListView().setOnScrollListener(new OnScrollListener() {  
            @Override  
            public void onScrollStateChanged(AbsListView view, int scrollState) {  
            }  
  
            @Override  
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {  
                if(firstVisibleItem==0){  
                    Log.e("log", "滑到顶部");  
                }  
                if(visibleItemCount+firstVisibleItem==totalItemCount){  
                    Log.e("log", "滑到底部");  
                }  
            }  
        });

//另外一种写法
mListView.setOnScrollListener(new OnScrollListenerImple());  

private class OnScrollListenerImple implements OnScrollListener{ 
        @Override 
        public void onScroll(AbsListView listView, int firstVisibleItem,int visibleItemCount, int totalItemCount) { 
            int lastItem = firstVisibleItem + visibleItemCount; 
            if(lastItem == totalItemCount) { 
                System.out.println("Scroll to the listview last item"); 
                View lastItemView=(View) listView.getChildAt(listView.getChildCount()-1); 
                if ((listView.getBottom())==lastItemView.getBottom()) { 
                    System.out.println("========Scroll to the listview bottom ============="); 
                    addDataForListView(); 
                    mSimpleAdapter.notifyDataSetChanged(); 
                } 
            } 
        } 
   
        @Override 
        public void onScrollStateChanged(AbsListView listview, int scrollState) { 
               
        } 
           
    }  


listview.setOnScrollListener(new OnScrollListener(){
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState){
		// 当不滚动时
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			// 判断是否滚动到底部
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				//加载更多功能的代码
			}
		}
	}
});


# android listview直接定位到某一行位置 

- listview.setSelection(int position);
- adapter.notifyDataSetInvalidated();