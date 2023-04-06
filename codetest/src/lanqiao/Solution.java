package lanqiao;

import java.util.Scanner;

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Integer a = sc.nextInt();
        Integer b = sc.nextInt();
        System.out.println(a + b);
        System.out.print(b);
        System.out.print(false);
    }
}
class Segment implements Comparable<Segment>{
	double l,r;
	public Segment(double l, double r) {
		super();
		this.l = l;
		this.r = r;
	}
	@Override
	public int compareTo(Segment o) {
		return Double.compare(r, o.r);
	}
}
