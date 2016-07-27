package Allocate;

public class LinkList {
	public Node first;
	public LinkList() {
		first = new Node(0);
	}
	//previous insert
	public void preInsert(Object object) {
		Node node = new Node(object);
		node.next = first.next;
		first.next = node;
	}
	
	
	//find element
	public Node find(Object object) {
		if (first == null)
			return null;
		Node p = first;
		Node q = p;
		while (p.next != null) {
			if (p.next.object.equals(object))			//notice:equals的对象要存在才能判断是否相等
				return q;
			q = p.next;
			p.next = p.next.next;
		}
		return null;
	}
	
	//delete element
	public void delEle(Object object) {
		Node p = find(object);
		if (p == null)
			System.out.println("the link is empty or not own the element");
		else
			p.next = p.next.next;
	}
	
	//display the link
	public void display() {
		if (first == null)
			System.out.println("the link is empty");
		else {
			Node p = first.next;
			while (p != null) {
				System.out.println(p.object);
				p = p.next;
			}
		}
	}
	
	public static void main(String [] args) {
		LinkList link = new LinkList();
		link.find(2);
		link.display();
		link.preInsert(5);
		link.preInsert(4);
		link.preInsert(3);
		link.preInsert(2);
		link.preInsert(1);
		link.display();
		link.delEle(2);
		link.preInsert(2);
		link.display();
		link.delEle(6);
		System.out.println("aaaaaaaaaaaaaaaaa");
		System.out.println(args.length);
		for (int i=0; i<args.length; ++i) {
			int temp = i+1;
			System.out.println(temp + args[i]);
		}
	}
}
