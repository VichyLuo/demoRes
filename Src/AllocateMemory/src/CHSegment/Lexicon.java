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
		dic1.add("��");
		dic1.add("��");
		dic1.add("ѧ");
		dic1.add("��");
		dic2.add("�о�");
		dic2.add("����");
		dic2.add("�ﻯ");
		dic2.add("��ѧ");
		dic3.add("�о���");
	}
	
}
