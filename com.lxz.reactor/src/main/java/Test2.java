
public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] id = {2,2,2,1,3,3,3,2,2,3,2};
		System.out.println(find(id,id.length));
	}

	public static int find(int[] ID, int n)
	{
	    int nTimes = 0;
	    int candidate = 0;
	    
	    for(int i = 0; i < n;i++)
	    {
	          if(nTimes == 0)
	          {
	              candidate = ID[i];
	          }
	          else
	          {
	              if(candidate == ID[i])
	              {
	                  nTimes++;
	              }
	              else
	              {
	                  nTimes--;
	              }
	          }
	    }
	    
	    return candidate;
	}
}
