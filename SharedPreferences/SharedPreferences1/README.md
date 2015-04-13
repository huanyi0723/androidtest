	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取只能被本应用程序读、写的SharedPreferences对象
		preferences = getSharedPreferences("crazyit", MODE_WORLD_READABLE);
		editor = preferences.edit();
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		//读
		read.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// 读取字符串数据
				String time = preferences.getString("time", null);
				// 读取int类型的数据
				int randNum = preferences.getInt("random", 0);
				String result = time == null ? "您暂时还未写入数据" : "写入时间为：" + time
						+ "\n上次生成的随机数为：" + randNum;
				// 使用Toast提示信息
				Toast.makeText(SharedPreferencesTest.this, result, 5000).show();
			}
		});
		
		//写
		write.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 "
						+ "hh:mm:ss");
				// 存入当前时间
				editor.putString("time", sdf.format(new Date()));
				// 存入一个随机数
				editor.putInt("random", (int) (Math.random() * 100));
				// 提交所有存入的数据
				editor.commit();
			}
		});
	}