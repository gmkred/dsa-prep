package arrays.binarysearch;

/**
 * <pre>
 * 
 * books[] = {12,34,67,90} - pages
 * n=4
 * students =2
 * conditions :
 * 1. A book will be allocated to one student
 * 2. Each students must get a minimum of 1 book
 * 3. Allotment should be in contiguous order
 * 
 * Find the maximum number of pages allocated is minimum.
 * 
 * student 1                     student 2
 *   12                            34,67,90 Max = 191
 *   12,34                          67,90   Max = 157
 *   12,34,67                        90     Max = 113 is the minimum of the maximums.
 * 
 * Min (191,157,113) = 113;
 * 
 * Binary search :
 * 
 * arr[] = {12,12,12,12} s =4
 * min of max = 12
 * 
 * arr[] = {10,20,30} s =1;
 * min of max = 10+20+30 = 60
 * 
 * books[] = {12,34,67,90} - pages
 * n=4
 * s=2
 * total pages = 12+34+67+90 = 203
 * [12...............203]
 * low = 12
 * high =203
 * mid = (12+203)/2 = 215/2 = 107
 * 
 * We know the min of max's lie in 12 to 203
 * if we put barrier as 107 we end up allocating books to 3 student but given s=2
 * s1 = 12,34 = 46
 * s2 = 67
 * s3 = 90
 * 
 * so increase the barrier to allocate to only 2 students
 * 
 * 
 * [108 ..........203]
 * low=108
 * high = 203
 * mid = (108+203)/2 = 311/2 = 155
 * s1 = 12, 34, 67 = 113
 * s2 = 90 = 90
 * 
 * 155 is one of the possible ans but find the min of max
 * 
 * [108 ........ 154]
 * low=108
 * high=154
 * mid = (108+154)/2 = 262/2 = 131
 * 
 * s1 = 12,34,67
 * s2=90
 * 
 * 131 could be an ans but find min of max
 * 
 * [108 ..... 131]
 * low = 108
 * high = 131
 * mid = (108+131)/2 = 239/2 = 119
 * s1 = 12,34,67 =113
 * s2 = 90
 * 
 * 119 can be an ans, but find min of max.
 * 
 * [108 .......... 119]
 * low = 108
 * high = 119
 * mid = (108+119)/2 = 226/2 = 113
 * 
 * s1 = 12,34,67 = 113 >= 113
 * s2 = 90
 * 
 * so 113 is the min of max, if we shrink again.
 * lets so how it goes
 * 
 * [108 ...... 112]
 * low = 108
 * high = 112
 * mid = (108+112)/2 = 220/2 = 110
 * s1 = 12,34 = 46
 * s2 = 67
 * s3 =90
 * again students count increased to 3, it should be only 2
 * 
 * [111.....112]
 * low =111
 * high=112
 * mid = 111+112 = 223/2 = 111
 * 
 * s1 = 12,23 = 46
 * s2 = 67
 * s3 = 90
 * again 3 student
 * 
 * [112 ...112] 
 * low =112
 * high=112
 * mid = 112+112 = 224/2 = 112
 * s1 = 12,23 = 46
 * s2 = 67
 * s3 = 90
 * 
 * 
 * low =113
 * high =112
 * low > high loop ends
 * 
 * still 3 students
 * the last max value when we have 2 students is 113
 *  or the low value can alos be min of max.
 * 
 * now s1 has min of max pages allocated.
 * 
 * 
 * if able to allocate
 * high = mid-1;
 * 
 * if not able to allocate low = mid+1
 * </pre>
 * 
 */
public class AllocateBooksOrBookAllocationVeryImportant {
	public static void main(String[] args) {
		int books[] = { 12, 34, 67, 90 };
		int students = 2;
		System.out.println(minOfMaxpages(books, 2));
	}

	private static int minOfMaxpages(int arr[], int students) {
		int low = arr[0];
		int high = 0;
		for (int i = 0; i < arr.length; i++) {
			high += arr[i];
		}

		while (low <= high) {
			int barrier = (low + high) / 2;
			if (isAllocatable(arr, barrier, students)) {
				high = barrier - 1;
			} else {
				low = barrier + 1;
			}
		}
		return low;
	}

	private static boolean isAllocatable(int books[], int barrier, int students) {
		int pages = 0;
		int allocatedStdents = 1;// first student

		for (int i = 0; i < books.length; i++) {
			if (books[i] > barrier) {
				return false;
			}

			if (pages + books[i] > barrier) {
				pages = books[i];
				allocatedStdents++;
			} else {
				pages += books[i];
			}
		}
		return allocatedStdents == students;

	}
}
