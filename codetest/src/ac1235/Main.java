package ac1235;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N = 100010;
	
	public static void main(String[] args) {
		List<Integer> list = getFactor(8);
		for(int i : list) {
			System.out.println(i);
		}
	}
	public static List<Integer> getFactor(int n){
        List<Integer> list = new ArrayList<>();
        for(int i = 2; i * i <= n; i++){
            while(n % i == 0){
                list.add(i);
                n/=i;
            }
        }
        if(n > 1) list.add(n);
        return list;
    }
}
