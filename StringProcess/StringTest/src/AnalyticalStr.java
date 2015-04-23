import java.util.StringTokenizer;

public class AnalyticalStr {

	/**
	 * ʹ��StringTokenizer�ཫ�ַ������ָ���ת�����ַ�����
	 * 
	 * @param string
	 *            �ַ���
	 * @param divisionChar
	 *            �ָ���
	 * @return �ַ�������
	 * @see [�ࡢ��#��������#��Ա]
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
//	 * �ַ�����������ʹ��StringTokenizer���java.lang.String��split()���� ���ַ������ݷָ��ת�����ַ�������
//	 * 
//	 * @param string
//	 *            �ַ���
//	 * @param c
//	 *            �ָ���
//	 * @return ��������ַ�������
//	 */
//	public static String[] stringAnalytical(String string, char c) {
//		// �ַ����зָ����ĸ���
//		int count = 0;
//
//		// ��������ָ���򷵻��ַ�����
//		if (string.indexOf(c) == -1) {
//			return new String[] { string };
//		}
//
//		char[] cs = string.toCharArray();
//
//		// ���˵���һ�������һ���Ƿָ��������
//		for (int i = 1; i < cs.length - 1; i++) {
//			if (cs[i] == c) {
//				count++; // �õ��ָ����ĸ���
//			}
//		}
//
//		String[] strArray = new String[count + 1];
//		int k = 0, j = 0;
//		String str = string;
//
//		// ȥ����һ���ַ��Ƿָ��������
//		if ((k = str.indexOf(c)) == 0) {
//			str = string.substring(k + 1);
//		}
//
//		// ����Ƿ�����ָ������������򷵻��ַ���
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
		
	    String s = "{\"imgs\":[\"http://i2.sinaimg.cn/ty/additional/2014-05-04/U8451P6T12D7148361F44DT20140504095111.jpg\"],\"text\":\"��3��ϣ�����������ɿ��ܣ�ȫ��255KM����������� ����������ϣ���¶�쳣�����ƥ�Ǽ���������2���ڵ¶�쳿��ܡ��������Ϊ��һ�βμӴ������µ��й��ˣ��͸������ܺ���һ����255��������յ㡣 ������\"}";  
	    //String s = "{\"imgs\":[\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144215.jpg\",\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144233.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144458.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144519.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144539.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144555.jpg\",\"http://i1.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144647.jpg\",\"http://i0.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144714.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144731.jpg\",\"http://i3.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144748.jpg\",\"http://i2.sinaimg.cn/ty/2014/0506/U8451P6DT20140506144820.jpg\"],\"text\":\"�����������Ǹո�̤��\\u201c�ܲ�����·\\u201d�ĳ������ߣ�������������\\u201c�ܲ�����\\u201d���ܲ������ѣ������ﶼ���Ը��ݸ��Ե���Ҫ�ҵ�һ˫�ʺ��Լ�����Ь\\u2014\\u2014����˫�ɡ��ҿ�����������רҵ�ܲ�Ь������������Ʒ�������רҵ��Ь\"}";
	    String[] ss = s.split("\"");  
	    System.out.println(ss.length);
	    for (int i = 0; i < ss.length; i++) {
	    	 System.out.println(ss[i]);
		}
		
//		String str = "{\"imgs\":[\"http://i0.sinaimg.cn/ty/additional/2014-05-09/U8451P6T12D7156372F44DT20140509101204.jpg\",\"http://i1.sinaimg.cn/ty/additional/2014-05-09/U8451P6T12D7156372F45DT20140509101204.jpg\",\"http://i2.sinaimg.cn/ty/2014/0509/U8451P6DT20140509101146.jpg\"],\"text\":\"Ӣ���������������ܲ����� һ���ȥ120� Ӣ���������������ܲ����� һ���ȥ120� ����Ϊ����������ǰ��һ��Ը����58���Ӣ���˴��򡤱ȸ�˹������ʼ���ʣ��ڶ̶̵�һ����������ܣ�������������\"}";
//		String divisionChar = "��";
//		
//		for (String tmp : AnalyticalStr.stringAnalytical(str, divisionChar)) {
//			System.out.println();
//			System.out.println(tmp);
//		}
		
		
	}

}
