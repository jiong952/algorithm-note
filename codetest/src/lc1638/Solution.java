package lc1638;

import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static void main(String[] args) {
		countSubstrings("aba","baba");
	}
    public static int countSubstrings(String s, String t) {
        // �Ȱ�t���Ӵ������ϣ��
        Map<String,Integer> map_t = new HashMap<>();
        int n = s.length(), m = t.length();
        int res = 0;
        // ˫ָ�뷨���Ӵ�
        for(int i = 0; i < m; i++){
            for(int j = i; j < m; j++){
                String str = t.substring(i,j+1);
                map_t.put(str,map_t.getOrDefault(str,0) + 1);
            }
        }
     // ��s��ÿһ���Ӵ� �ı�һ���ַ���Ѱ��t���Ƿ�����ͬ��
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                String str = s.substring(i,j+1);
                char[] chs = str.toCharArray();
                for(int k = 0; k < str.length(); k++){
                    char c = chs[k];
                    for(int q = 0; q < 26; q++){
                        char cc = (char)(q+'a');
                        if(c == cc) continue;
                        chs[k] = cc;
                        String cur = new String(chs);
                        res += map_t.getOrDefault(cur,0);
                        chs[k] = c;
                    }
                }
            }
        }
        return res;
    }
}
