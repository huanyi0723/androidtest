保存key-value对信息
- SharedPreferences
	contains()
	getAll()
	getXxx()
- SharedPreferences.Editor
	clear() //清空
	putXxx() //写入
	remove() //清除
	commit() //提交
-Context 
	getSharedPreferences("follow", MODE_WORLD_READABLE);
	//Context.MODE_PRIVATE 本应用程序读写
	//Context.MODE_WORLD_READABLE 其它应用读 不可写
	//Context.MODE_WORLD_WRITEABLE 其它应用读写
