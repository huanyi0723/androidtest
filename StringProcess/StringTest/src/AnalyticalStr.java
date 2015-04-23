import java.util.StringTokenizer;

public class AnalyticalStr {

	/**
	 * 使用StringTokenizer类将字符串按分隔符转换成字符数组
	 * 
	 * @param string
	 *            字符串
	 * @param divisionChar
	 *            分隔符
	 * @return 字符串数组
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] stringAnalytical(String string, String divisionChar) {
		int i = 0;
		StringTokenizer tokenizer = new StringTokenizer(string, divisionChar);

		String[] str = new String[tokenizer.countTokens()];

		while (tokenizer.hasMoreTokens()) {
			str[i] = new String();
			str[i] = tokenizer.nextToken();
			i++;
		}

		return str;
	}

//	/**
//	 * 字符串解析，不使用StringTokenizer类和java.lang.String的split()方法 将字符串根据分割符转换成字符串数组
//	 * 
//	 * @param string
//	 *            字符串
//	 * @param c
//	 *            分隔符
//	 * @return 解析后的字符串数组
//	 */
//	public static String[] stringAnalytical(String string, char c) {
//		// 字符串中分隔符的个数
//		int count = 0;
//
//		// 如果不含分割符则返回字符本身
//		if (string.indexOf(c) == -1) {
//			return new String[] { string };
//		}
//
//		char[] cs = string.toCharArray();
//
//		// 过滤掉第一个和最后一个是分隔符的情况
//		for (int i = 1; i < cs.length - 1; i++) {
//			if (cs[i] == c) {
//				count++; // 得到分隔符的个数
//			}
//		}
//
//		String[] strArray = new String[count + 1];
//		int k = 0, j = 0;
//		String str = string;
//
//		// 去掉第一个字符是分隔符的情况
//		if ((k = str.indexOf(c)) == 0) {
//			str = string.substring(k + 1);
//		}
//
//		// 检测是否包含分割符，如果不含则返回字符串
//		if (str.indexOf(c) == -1) {
//			return new String[] { str };
//		}
//
//		while ((k = str.indexOf(c)) != -1) {
//			strArray[j++] = str.substring(0, k);
//			str = str.substring(k + 1);
//			if ((k = str.indexOf(c)) == -1 && str.length() > 0) {
//				strArray[j++] = str.substring(0);
//			}
//		}
//
//		return strArray;
//	}

	public static void main(String[] args) {
		
	    String s = "{\"imgs\":[\"http://i2.sinaimg.cn/ty/additional/2014-05-04/U8451P6T12D7148361F44DT20140504095111.jpg\"],\"text\":\"第3届希腊极限马拉松开跑，全程255KM陈盆滨参赛。 　　第三届希腊德尔斐－奥林匹亚极限马拉松2日在德尔斐开跑。陈盆滨作为第一次参加此项赛事的中国人，和各国长跑好手一起奔向255公里外的终点。 　　德\"}";  
	    //String s = "{\"imgs\":[\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144215.jpg\",\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144233.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144458.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144519.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144539.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144555.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144647.jpg\",\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144714.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144731.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144748.jpg\",\"http://i2.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144820.jpg\"],\"text\":\"　　无论你是刚刚踏上\\u201c跑步不归路\\u201d的初级跑者，还是早已上了\\u201c跑步贼船\\u201d的跑步发烧友，在这里都可以根据各自的需要找到一双适合自己的跑鞋\\u2014\\u2014随着双渡、烈骏和云马三款专业跑步鞋的问世，李宁品牌完成了专业跑鞋\"}";
	    String[] ss = s.split("\"");  
	    System.out.println(ss.length);
	    for (int i = 0; i < ss.length; i++) {
	    	 System.out.println(ss[i]);
		}
		
//		String str = "{\"imgs\":[\"http://i0.sinaimg.cn/ty/additional/2014-05-09/U8451P6T12D7156372F44DT20140509101204.jpg\",\"http://i1.sinaimg.cn/ty/additional/2014-05-09/U8451P6T12D7156372F45DT20140509101204.jpg\",\"http://i2.sinaimg.cn/ty/2014/0509/U8451P6DT20140509101146.jpg\"],\"text\":\"英男子遵亡妻遗嘱跑步减肥 一年减去120斤。 英男子遵亡妻遗嘱跑步减肥 一年减去120斤。 　　为了妻子临终前的一个愿望，58岁的英国人戴夫·比弗斯决定开始减肥，在短短的一年里，依靠长跑，他整整减掉了\"}";
//		String divisionChar = "“";
//		
//		for (String tmp : AnalyticalStr.stringAnalytical(str, divisionChar)) {
//			System.out.println();
//			System.out.println(tmp);
//		}
		
		
	}

}
