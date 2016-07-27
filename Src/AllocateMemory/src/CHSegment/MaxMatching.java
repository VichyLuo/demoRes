package CHSegment;

import java.util.Iterator;

public class MaxMatching {
	protected int max;
	protected Lexicon lex;
	protected String str;
	protected int len;
	protected int[] pos;

	public MaxMatching(String str) {
		lex = new Lexicon();
		max = lex.length;
		this.str = str;
		len = str.length();
		pos = new int[len];
		lex.build();
	}
	
	public int isExist(String ss) {
		int flag = 0;
		int num = ss.length();
		Iterator<String> it;
		if (num == 1) {
			it = lex.dic1.iterator();
			while (it.hasNext()) {
				if (ss.equals(it.next())) {
					flag = 1;
					break;
				}
			}
		} else if (num == 2) {
			it = lex.dic2.iterator();
			while (it.hasNext()) {
				if (ss.equals(it.next())) {
					flag = 1;
					break;
				}
			}
		} else {
			it = lex.dic3.iterator();
			while (it.hasNext()) {
				if (ss.equals(it.next())) {
					flag = 1;
					break;
				}
			}
		}
		return flag;
	}
	
	public String merge(int start,int end,String str) {
		String temp = "";
		for (int i=start; i<end; ++i)
			temp += str.charAt(i);
		return temp;
	}
	public void FMM() {
		int t=0;
		int start = 0;
		int end = len - 1;
		int m = max;
		int q = max; 
		for (int i = start;i<len;) {
			int n = end - i + 1;
			if (n == 1) {
				pos[t++] = end;
				return;
			} else if (n < m) {
				m = n;
			}
			String temp = "";
			q = i + m;
			for (int k=i;;) {
				temp = merge(k,q,str);
				int flag = isExist(temp);
				if (flag == 1) {
					pos[t++] = q - 1;
					i = q;
					if (i == end) {
						pos[t++] = end;
						return;
					}
					break;
				} else if (flag == 0 && temp.length() > 1) {
					q = q-1;
				} else if (flag == 0 && temp.length() == 1) {
					pos[t++] = q-1;
					i++;
					if (i == end) {
						pos[t++] = end;
						return;
					}
					break;
				}
			}
		}
	}
	
	public void print() {
		int j = 0;
		for (int i=0; i<len; ++i) {
			System.out.print(str.charAt(i));
			if (i == pos[j]) {
				System.out.print("||");
				++j;
			}
		}
	}
	
	
	public static void main(String args[]) {
		MaxMatching max = new MaxMatching("他是研究生物化学的");
		max.FMM();
		max.print();
	}
}
