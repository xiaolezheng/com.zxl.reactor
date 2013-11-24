import java.util.BitSet;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//BitSet bitset = new BitSet(500000000);
		/*for(int i=0;i<bitset.length();i++){
			bitset.set(i);
		}*/
		System.out.println("time: "+(System.currentTimeMillis()-start));
		System.out.println(1000>>6);
		
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
