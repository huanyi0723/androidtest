## 点击事件的几种写法
		// 第一步 找控件
		crop_back = (TextView) findViewById(R.id.crop_back);
		crop_img = (ImageView) findViewById(R.id.crop_img);
		crop_select = (TextView) findViewById(R.id.crop_select);
		cropimage = (ImageView) findViewById(R.id.cropimage);

		// 第二步 注册监听器
		crop_back.setOnClickListener(new CropListeners());
		crop_img.setOnClickListener(new CropListeners());
		crop_select.setOnClickListener(new CropListeners());

		private class CropListeners implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 点击取消按钮 返回上一界面
			case R.id.crop_back:
				onBackPressed();
				break;
				
			// 点击放缩按钮
			case R.id.crop_img:
				// 图片全屏按钮 图片不可放缩
				show();
				break;
				
			// 选取按钮 点击后 截取图片 并跳转到编辑界面
			case R.id.crop_select:
				bar.setVisibility(View.VISIBLE);
				// 启动线程来执行任务
				new Thread() {
					public void run() {
						getViewBitmap();
						Message m = new Message();
						m.what = 0x111;
						mHandler.sendMessage(m);
					}
				}.start();
				break;
			}
		}
	}