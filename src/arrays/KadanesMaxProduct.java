package arrays;
/**
 * Max subarray product - Kadane's algorithm.
 * 
 * <pre>
 * steps :
 * 1. initialize curMin, curMax and max with nums[0]
 * 2. for each loop use local variable to initialize
 * 		current value x = nums[i]
 * 		min = curMin * x
 * 		max = curMax * x
 * 	 <span style=
"color : red">We have to keep track curMin and curMax because at any point a -ve number
 * 	 can turn a smallest number to largest number and vice-versa.</span>
 * 3. compare current, maxx, minx to get curMax and curMin among them.
 * 4. compare maxPro with curMax and assign the max value to maxPro.
 * 
 * Example:
 * 
 * <b>
 * min = curMin*cur
 * max = curMax*cur
 * curMin = min of cur,min,max
 * curMax = max of cur, min, max
 * maxPro = max of maxPro and curMax
 *</b>
 *	{2,-5,-2,-4,3}				
 * 	cur	|min 		|max 		|curMin		|curMax		|maxPro
 * 	2	-		 -		 2		 2		 2
 * curMin = {<span style="color:red">2</span>,-5,-2,-4,3}
 * curMax = {<span style="color:red">2</span>,-5,-2,-4,3}
 * maxPro = {<span style="color:red">2</span>,-5,-2,-4,3}
 * 
 *	-5	2*-5=-10	2*-5=-10	-10		-5			2
 * curMin = {<span style="color:red">2,-5</span>,-2,-4,3}
 * curMax = {2,<span style="color:red">-5</span>,-2,-4,3}
 * maxPro = {<span style="color:red">2</span>,-5,-2,-4,3}
 * 
 *	-2	-10*-2=20	-5*-2=10	-2		20			20
 * curMin = {2,-5,<span style="color:red">-2</span>,-4,3}
 * curMax = {<span style="color:red">2,-5,-2</span>,-4,3}
 * maxPro = {<span style="color:red">2,-5,-2</span>,-4,3}
 * 
 *	-4	-2*-4=8		20*-4=-80	-80		8			20
 * curMin = {<span style="color:red">2,-5,-2,-4</span>,3}
 * curMax = {2,-5,<span style="color:red">-2,-4</span>,3}
 * maxPro = {<span style="color:red">2,-5,-2</span>,-4,3}
 * 
 *	3	-80*3=-240	8*3=24		-240		24			24
 * curMin = {<span style="color:red">2,-5,-2,-4,3</span>}
 * curMax = {2,-5,<span style="color:red">-2,-4,3</span>}
 * maxPro = {2,-5,<span style="color:red">-2,-4,3</span>}
 * </pre>
 */
public class KadanesMaxProduct {

	public static void main(String[] args) {
		int arr[] = { 2, -5, -2, -4, 3 };
		int resutl = maxProd(arr);
		System.out.println(resutl);
	}
	
	public static int maxProd(int nums[]) {
		int curMin = nums[0];
		int curMax = nums[0];
		int max = nums[0];

		for (int i = 1; i < nums.length; i++) {
			int x = nums[i];
			int minx = curMin * x;
			int maxx = curMax * x;
			curMin = Math.min(nums[i], Math.min(minx, maxx));
			curMax = Math.max(nums[i], Math.max(maxx, minx));
			max = Math.max(max, curMax);
		}
		return max;
	}
}
