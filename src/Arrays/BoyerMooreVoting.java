package Arrays;

public class BoyerMooreVoting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {3,2,3};//candidate is 3
		BoyerMooreVoting bmv = new BoyerMooreVoting();
		int majority = bmv.majorityElement(arr);
		System.out.println("Majority element: " + majority);
		//what-if there is no majority element? The problem states that there will always be a majority element, so we don't need to handle that case here.
		//If we wanted to handle that case, we would need to count the occurrences of the candidate and check if it appears more than n/2 times.
		
	}
	//Find the majority element (appears more than n/2 times) using Boyer-Moore voting.
	public int majorityElement(int[] nums) {
	    int count = 0;
	    Integer candidate = null;

	    for (int num : nums) {
	        if (count == 0) {
	            candidate = num;
	        }
	        count += (num == candidate) ? 1 : -1;
	    }

	    return candidate;
	}

}
