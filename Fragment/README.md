## 之间参数传递
在Activity上fragment.setArguments(bundle);通过Bundle传递数据，
在Fragment里面Bundle bundle = getArguments();获取数据 	
	
	static PagerFragment newInstance(int position) {
		PagerFragment fragment = new PagerFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		fragment.setArguments(args);
		return fragment;
	}
