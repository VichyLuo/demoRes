package DataOpr;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 本程序中要求训练集文件中训练数据和教师信号必须同属一行，并且它们仅仅占用一行
 *
 */
public class ReadData {
	
	private BufferedReader read;
	public double [][] dataX = new double [1000][1000];		//storing the input of exercising data
	public double [][] dataY = new double [1000][1000];	   //storing the input of expecting data
	public int line;							  //the lines of inputing a data  
	public int line2;
	private int n;								  //the numbers of inputting layer 
	private int m;								 //the numbers of outputting layer
	public ReadData(int n,int m) {
		this.n = n;
		this.m = m;
	}
	
	
	//the function intends to read data from test file
	public void readData()
	{
		int k = 0;									//record lines
		try {
			read = new BufferedReader( new FileReader("d://Test2.txt"));
			String temp;
			while ((temp = read.readLine()) != null) {
				int j = 0;						//record dataX array 
				int l = 0;					   //record dataY array
				Pattern p = Pattern.compile("[\\-]?[\\d]+[\\.]?[\\d]+");
				Matcher m = p.matcher(temp);
				int count = 0;
				while (m.find()) {
					if(count < n) {
						dataX[k][j++] = Double.valueOf(m.group());
						++count;
					}
					else
						dataY[k][l++] = Double.valueOf(m.group());
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
	}
	
		
	
	//归一化输入和输出数据
	public void normalInSet() {
		double max [] = new double[1000];
		double min [] = new double[1000];
		for (int i=0; i<n; i++) {
			max[i] = dataX[0][i];
			min[i] = dataX[0][i];
			for (int j=1; j<line; j++) {
				if (max[i] < dataX[j][i])
					max[i] = dataX[j][i];
				else if (min[i] > dataX[j][i])
					min[i] = dataX[j][i];
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<line; j++)
				dataX[j][i] = (dataX[j][i] - min[i])/(max[i] - min[i]);
		}
	}
	
	public void normalOutSet() {
		double max [] = new double[1000];
		double min [] = new double[1000];
		for (int i=0; i<m; i++) {
			max[i] = dataY[0][i];
			min[i] = dataY[0][i];
			for (int j=1; j<line; j++) {
				if (max[i] < dataY[j][i])
					max[i] = dataY[j][i];
				else if (min[i] > dataY[j][i])
					min[i] = dataY[j][i];
			}
		}
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<line; j++)
				dataY[j][i] = (dataY[j][i] - min[i])/(max[i] - min[i]);
		}
	}
	
	
	
}