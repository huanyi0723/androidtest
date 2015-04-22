public class LaunchActivity extends BaseFragmentActivity {

	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_fragment);
        
        Log.setLevel(Log.LEVEL_TEST);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LaunchFragment())
                    .commit();
        }
    }

    public static class LaunchFragment extends Fragment {
    	private final String PAGE_NAME = this.getClass().getSimpleName();

    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.launch, container, false);
            //信鸽推送时先登录
            rootView.postDelayed(new Runnable() {

				@Override
				public void run() {
					if (getActivity() != null) { 
						if(UserState.getInstance().isLoggedOn()){
							String temp = UserState.getInstance().getUser().account_id + "";
							if(temp.length()<=1){
								temp = "0"+temp;
							}
							XGUtils.getInstances(getActivity()).registerXG(temp);
						}else{
							XGUtils.getInstances(getActivity()).registerXG();
						}
						getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
						getActivity().finish();
					}
				}

            }, 2000);
            return rootView;
        }

    }
}


