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

生命周期
场景演示 : 切换到该Fragment

11-29 14:26:35.095: D/AppListFragment(7649): onAttach
11-29 14:26:35.095: D/AppListFragment(7649): onCreate
11-29 14:26:35.095: D/AppListFragment(7649): onCreateView
11-29 14:26:35.100: D/AppListFragment(7649): onActivityCreated
11-29 14:26:35.120: D/AppListFragment(7649): onStart
11-29 14:26:35.120: D/AppListFragment(7649): onResume

屏幕灭掉：

11-29 14:27:35.185: D/AppListFragment(7649): onPause
11-29 14:27:35.205: D/AppListFragment(7649): onSaveInstanceState
11-29 14:27:35.205: D/AppListFragment(7649): onStop


屏幕解锁

11-29 14:33:13.240: D/AppListFragment(7649): onStart
11-29 14:33:13.275: D/AppListFragment(7649): onResume


切换到其他Fragment:
11-29 14:33:33.655: D/AppListFragment(7649): onPause
11-29 14:33:33.655: D/AppListFragment(7649): onStop
11-29 14:33:33.660: D/AppListFragment(7649): onDestroyView


切换回本身的Fragment:

11-29 14:33:55.820: D/AppListFragment(7649): onCreateView
11-29 14:33:55.825: D/AppListFragment(7649): onActivityCreated
11-29 14:33:55.825: D/AppListFragment(7649): onStart
11-29 14:33:55.825: D/AppListFragment(7649): onResume

回到桌面

11-29 14:34:26.590: D/AppListFragment(7649): onPause
11-29 14:34:26.880: D/AppListFragment(7649): onSaveInstanceState
11-29 14:34:26.880: D/AppListFragment(7649): onStop

回到应用

11-29 14:36:51.940: D/AppListFragment(7649): onStart
11-29 14:36:51.940: D/AppListFragment(7649): onResume


退出应用

11-29 14:37:03.020: D/AppListFragment(7649): onPause
11-29 14:37:03.155: D/AppListFragment(7649): onStop
11-29 14:37:03.155: D/AppListFragment(7649): onDestroyView
11-29 14:37:03.165: D/AppListFragment(7649): onDestroy
11-29 14:37:03.165: D/AppListFragment(7649): onDetach
