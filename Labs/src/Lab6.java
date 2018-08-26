import java.util.*;

public class Lab6 {
	
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
		double max = data[0];
		for(int i = 0; i < data.length; i++){
			if(data[i] >= max)
				max = data[i];
		}
		return max;
	}
	public static double min(double[] data){
		double min = data[0];
		for(int i = 0; i < data.length; i++){
			if(data[i] <= min)
				min = data[i];
		}
		return min;
	}
	public static double sum(double[] data){
		double sum = 0;
		for(int i = 0; i < data.length; i++)
			sum += data[i];
		return sum;
	}
	public static double ave(double[] data){
		double ave = sum(data);
		ave = ave / data.length;
		return ave;
	}

}
