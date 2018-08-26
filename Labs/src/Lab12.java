import java.util.*;
public class Lab12 {
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the size");
		double[] nums = new double[input.nextInt()];
		for(int i = 0; i < nums.length; i++){
			System.out.println("Enter a value:");
			nums[i] = input.nextDouble();
		}
		System.out.println("MAX:" + max(nums));
		System.out.println("MIN:" + min(nums));
		System.out.println("SUM:" + sum(nums));
		System.out.println("AVE:" + ave(nums));
	}
	public static double max(double[] data){
		return max_rec(data, 0, data[0]);
	}
	public static double max_rec(double[] data, int loc, double max){
		if(loc == data.length)
			return max;
		else{
			if(data[loc] >= max)
				max = data[loc];
			return max_rec(data, loc + 1, max);
		}
	}
	public static double min(double[] data){
		return min_rec(data, 0, data[0]);
	}
	public static double min_rec(double[] data, int loc, double min){
		if(loc == data.length)
			return min;
		else{
			if(data[loc] <= min)
				min = data[loc];
			return min_rec(data, loc + 1, min);
		}
	}
	public static double sum(double[] data){
		return sum_rec(data, 0, 0);
	}
	public static double sum_rec(double[] data, int loc, double sum){
		if(loc == data.length)
			return sum;
		else{
			sum += data[loc];
			return sum_rec(data, loc + 1, sum);
		}
	}
	public static double ave(double[] data){
		return ave_rec(data, 0, 0);
	}
	public static double ave_rec(double[] data, int loc, double ave){
		ave = sum_rec(data, loc, 0);
		return ave / data.length;
	}
}