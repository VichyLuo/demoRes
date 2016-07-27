package DataOpr;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataInput {
	
	private BufferedReader read;
	public int [][] dataX = new int [100][100];		//storing the input of exercising data
	public int [][] dataY = new int [100][100];	   //storing the input of expecting data
	public int line;							  //the lines of inputing a data  
	
	//the function intends to read data from test file
	public void readData(int n)
	{
		int k = 0;									//record lines
		try {
			read = new BufferedReader( new FileReader("d://test.txt"));
			String temp;
			while ((temp = read.readLine()) != null) {
				int dataTemp = 0;
				int j = 0;						//record dataX array 
				int l = 0;					   //record dataY array
				for (int i=0; i<temp.length(); i++) {
					if(temp.charAt(i) >= '0' && temp.charAt(i) <= '9')
						dataTemp = dataTemp*10 + (temp.charAt(i)-'0');
					else {
						if(j < n)
					        dataX[k][j++] = dataTemp;
						else 
							dataY[k][l++] = dataTemp;
					    dataTemp = 0;
					}
				}
				++k;
			}
			this.line = k;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}//bugs:cannot read the end of data
	
	
	/*public static void main(String []args) {
		DataInput data = new DataInput();
		data.readData(3);
		for (int i=0; i<data.line; i++) {
			for (int j=0; j<3; j++)
				System.out.print(data.dataY[i][j]+ " ");
			System.out.println();
		}
		System.out.println(data.line);
	}*/
}
