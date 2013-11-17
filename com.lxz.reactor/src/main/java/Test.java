
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s0 = "你test-baidu-0.baidu.com";
		String s1 = "你test-baidu-1.baidu.com";
		String s2 = "你test-baidu-12.baidu.com";
		System.out.println((int)'-');
		System.out.println((int)'a');
		System.out.println((int)'b');
		int num0 = 0;
		int num1 = 0;
		int num2 = 0;
		for(int i=0;i<s0.length();i++){
			num0 += (int)s0.charAt(i);
		}

		for(int i=0;i<s1.length();i++){
			num1 += (int)s1.charAt(i);
		}
		
		for(int i=0;i<s2.length();i++){
			num2 += (int)s2.charAt(i);
		}
		System.out.println(compare(s1,s2));
	}

	/**
	 * 字符串比较函数，返回值大于0则a大于b，等于0则a等于b，小于0则a小于b
	 * 实现原理比较两个字符串ASCII大小
	 * @param a  字符串参数
	 * @param b  字符串参数
	 * @return
	 */
	public static int compare(String a,String b){
		if(a == null && b == null)
			return 0;
		if(b == null)
			return 1;
		if(a == null)
			return -1;
		
		int suma = 0;
		int sumb = 0;
		
		for(int i=0;i<a.length();i++){
			suma += (int)a.charAt(i);
		}
		
		for(int i=0;i<b.length();i++){
			sumb += (int)b.charAt(i);
		}
		
		return suma - sumb;
	}
}
