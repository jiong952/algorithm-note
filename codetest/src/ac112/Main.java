package ac112;

import java.util.*;
public class Main{
    static Scanner sc = new Scanner(System.in);
    static int N = 1010;
    static Segment[] segs = new Segment[N];
    public static void main(String[] args){
        int n = sc.nextInt();
        int d = sc.nextInt();
        int maxy = 0;
        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            maxy = Math.max(maxy,Math.abs(y));
            // 计算塔的位置,得到线段
            double len = Math.sqrt(d*d - y*y);
            double l = x - len, r = x + len;
            segs[i] = new Segment(l, r);
        }
        if(maxy > d){
            System.out.print(-1);
            return;
        }
        Arrays.sort(segs,0,n);
        double last = segs[0].r;
        int cnt = 1;
        for (int i = 1; i < n; i++) {
			if(segs[i].l <= last) continue;
			last = segs[i].r;
			cnt++;
		}
        System.out.print(cnt);
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
		return Double.compare(r,o.r);
	}
	
}