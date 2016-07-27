package Saturday;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQue {
	public PriorityQueue<Data> priority;
	
	public PriorityQue(Compare com) {
		priority = new PriorityQueue<Data>(10,com);
	}
	
	public void scan() {
		Data d1 = new Data("Luo",100);
		Data d2 = new Data("Wen",200);
		Data d3 = new Data("Qi",150);
		priority.add(d1);
		priority.add(d2);
		priority.add(d3);
		Iterator<Data> it = priority.iterator();
		while (it.hasNext()) {
			Data temp = it.next();
			System.out.println(temp.id + "------>" + temp.name);
		}
	}
	
	public static void main(String args[]) {
		Compare com = new Compare();
		PriorityQue pr = new PriorityQue(com);
		pr.scan();
	}
}
