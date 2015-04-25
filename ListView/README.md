- 隐藏隐藏滚动条 android:scrollbars="none"
- android listview addHeaderView和addFooterView的注意事项
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


判断滑动到顶端还是底部
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

- 如何判断ListView已添加头部
目前的方法
			if (mListViewHeader == null) {
				mListViewHeader = createListViewHeader(contentDataResp);
				contentfrag_list.addHeaderView(mListViewHeader);
			}