- 参数传递
Intent intent=new Intent();
intent.putExtra("extra", "这是页面一传来的值！");
intent.setClass(Test_for_intentActivity.this, actpage2.class);
startActivity(intent);

取值：
Intent intent=getIntent();
String StringE=intent.getStringExtra("extra");
TextView text2=(TextView)findViewById(R.id.textView2);
text2.setText(StringE);

打开邮箱客户端
	Intent email = new Intent(Intent.ACTION_SEND); 
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { "chengz@staff.l99.com" }); 
        email.putExtra(Intent.EXTRA_SUBJECT, "我要投稿"); 
        email.putExtra(Intent.EXTRA_TEXT, ""); 

        email.setType("message/rfc822"); 

        startActivity(Intent.createChooser(email, "请选择一个邮件客户端:"));