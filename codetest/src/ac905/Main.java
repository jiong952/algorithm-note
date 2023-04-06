package ac905;
import java.util.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	static int N = 100010;
	static Pair[] pairs = new Pair[N];
	public static void main(String[] args) {
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			pairs[i] = new Pair(l,r);
		}
		// ÅÅÐò
        Arrays.sort(pairs,0,n);
		int cnt = 1;
		int last = pairs[0].r;
		for(int i = 1; i < n; i++) {
			if(pairs[i].l <= last) continue;
			last = pairs[i].r;
			cnt++;
		}
		System.out.println(cnt);
	}
}
class Pair implements Comparable<Pair>{
	int l,r;

	public Pair(int l, int r) {
		super();
		this.l = l;
		this.r = r;
	}

	@Override
	public int compareTo(Pair o) {
		return r - o.r;
	}
	
}
