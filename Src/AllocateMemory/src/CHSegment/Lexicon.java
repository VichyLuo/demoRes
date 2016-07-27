package CHSegment;

import java.util.ArrayList;
import java.util.List;

public class Lexicon {
	protected List<String> dic1;
	protected List<String> dic2;
	protected List<String> dic3;
	protected int length;
	public Lexicon() {
		dic1 = new ArrayList<String>();
		dic2 = new ArrayList<String>();
		dic3 = new ArrayList<String>();
		length = 3;
	}
	
	public void build() {
		dic1.add("他");
		dic1.add("是");
		dic1.add("学");
		dic1.add("的");
		dic2.add("研究");
		dic2.add("生物");
		dic2.add("物化");
		dic2.add("化学");
		dic3.add("研究生");
	}
	
}
