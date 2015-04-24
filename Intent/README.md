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