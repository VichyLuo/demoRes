package Saturday;

import java.util.Comparator;

public class Compare implements Comparator<Data>{
/*���������Ĵ������ǰ��*/
	/*public int compare(Data d1, Data d2) {
		if (d1.id < d2.id)
			return -1;
		else if (d1.id > d2.id)
			return 1;
		else
			return 0;
	}*/
	public int compare(Data d1, Data d2) {
		return d1.name.compareTo(d2.name);
		//d2.name.compareTo(d1.name)	//����
	}

}
