package BPAlogrithm;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import DataOpr.ReadData;

public class BPNetual {
	public ReadData read;
	private double [][] input = new double [500][500];				//storing original value of training set 
	public double [][] output = new double [500][500];				//storing expected value of training set
	private double [][] genIn = new double [500][500];				//storing original value of generalizing set
	public double [][] genOut = new double [500][500];				//storing expected value of generalizing set
	private double [][] hid_in = new double[500][500];
	private double [][] hid_out = new double [500][500];
	private double [][] out_in = new double [500][500];
	public double [][] out_out = new double [500][500];
	private double [][] Win_hid = new double [500][500];
	private double [][] Whid_out = new double [500][500];
	private double [][] modify1 = new double [500][500];		 //modifying the weight from hidden layer to output layer 
	private double [][] modify2 = new double [500][500];
	private double [][] dik1 = new double[500][500]; 		    //storing dik
	private double [][] dik2 = new double [500][500];
	public double [][] genResult = new double [500][500];		//存放泛化数据网络输出
	public double trainRel;										//存放训练数据的相对误差
	public double genRel;										//存放泛化数据的相对误差
	
	public List<Double> list = new ArrayList<Double>();				//storing each exercising error 
	public List<Double> listTra = new ArrayList<Double>();			//storing computing result of training set
	public List<Double> listGen = new ArrayList<Double>();			//storing computing result of generalizing set
		
	private int inNum;
	private int hidNum;
	public int outNum;
	public int totalNum;													//the total number 	
	public int caseNum;													//the training number
	public int caseNum2;													//the generalizing number
	private final double revise = 0.7;		//the range is from 0.7 to 0.9
	private final double speed = 0.5;	   
	private final int max = 4000;
	private final double threshold = 0.0;
	
	/**
	 * initialize BP network
	 */
	public void initial() {
		this.inNum = 13;	
		this.hidNum = 30;
		this.outNum = 1;
		//this.layer = 2; //can not contain inputting layer
		read = new ReadData(this.inNum,this.outNum);
		read.readData();
		read.normalInSet();
		read.normalOutSet();
		//this.totalNum = read.line;
		//this.caseNum = (int) (this.totalNum * 0.5);
		//this.caseNum2 = this.totalNum - this.caseNum;
		this.caseNum = 168;
		this.caseNum2 = 338;
		// initialize input and output training date
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.inNum; j++)
				this.input[i][j] = read.dataX[i][j];
		}
		for (int i=0; i<this.caseNum; i++)
			this.input[i][this.inNum] = -1;			//to compare threshold
		
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.outNum; j++)
				this.output[i][j] = read.dataY[i][j];
		}
		
		// initialize input and output generalizing layer
		for (int i=0; i<this.caseNum2; i++) {
			for (int j=0; j<this.inNum; j++)
				this.genIn[i][j] = read.dataX[i+this.caseNum][j];
		}
		for (int i=0; i<this.caseNum2; i++)
			this.genIn[i][this.inNum] = -1;
		
		for (int i=0; i<this.caseNum2; i++) {
			for (int j=0; j<this.outNum; j++) 
				this.genOut[i][j] = read.dataY[this.caseNum + i][j];
		}
		
		// initialize weight 
		Random rand = new Random();
		for (int i=0; i<this.inNum+1; i++) {          // the input layer has more one node,but it dose not reach hidden layer "node"
			for (int j=0; j<this.hidNum; j++) {			
				this.Win_hid[i][j] = rand.nextDouble();
			}
		}
		
		for (int i=0; i<this.hidNum+1; i++) {	    //the hidden layer has more one node to reach output layer
			for (int j=0; j<this.outNum; j++) {		
				this.Whid_out[i][j] = rand.nextDouble();
			}
		}
	}
	
	/**
	 * the function intends to make use of non-Symmetry sigmoid function 
	 * @param x
	 * @return
	 */
	public double nonSymSig(double x) {
		double result = 0.0;
		result = 1.0/(1 + Math.pow(Math.E, 1.0-x/1));
		return result;
	}
	
	/**
	 * get required value of each layer
	 */
	public void getValue(int num) {
		
		//get inputting value of the hidden
		    for (int i=0; i<this.hidNum; i++) {
			    double temp = 0.0;
			    for (int j=0; j<this.inNum + 1; j++)
			    	temp += this.input[num][j] * this.Win_hid[j][i];
			    this.hid_in[num][i] = temp;
		    }
		
		//get outputting value of the hidden
			for (int j=0; j<this.hidNum; j++)
				this.hid_out[num][j] = nonSymSig(this.hid_in[num][j]);
			
			this.hid_out[num][this.hidNum] = -1;	   			 //notice
			
		
		//get inputting value of the outputting layer
			for (int i=0; i<this.outNum; i++) {
				double temp = 0.0;
				for (int j=0; j<this.hidNum + 1; j++)
					temp += this.hid_out[num][j] * this.Whid_out[j][i];
				this.out_in[num][i] = temp;
			}
		
		//get outputting value of the outputting layer
			for (int i=0; i<this.outNum; i++)
				this.out_out[num][i] = nonSymSig(this.out_in[num][i]);
	}
		
	

	
	
	/**
	 * modifying the threshold
	 */
	 public void updateThre(int num) {
		 //get the d from hidden layer to output layer
		 for (int i=0; i<this.hidNum + 1; i++) {
			 for (int j=0; j<this.outNum; j++) {
				 this.dik1[i][j] = this.out_out[num][j] * (1 - this.out_out[num][j]) * (this.output[num][j] - this.out_out[num][j]);
				 this.modify1[i][j] = this.dik1[i][j];
			 }
		 }
		 
		 //update the weight from the hidden layer to output layer 
		 for(int i=0; i<this.hidNum + 1; i++) {
			 double balance = 0.0;
			 for (int j=0; j<this.outNum; j++) {
				 balance = this.speed * this.dik1[i][j] * this.hid_out[num][i];
				 this.Whid_out[i][j] += (balance + this.revise * balance); 
			 }
		 }
		 
		 //get the d from input layer to hidden layer
		 for (int i=0; i<this.inNum + 1; i++) {
			 for (int j=0; j<this.hidNum; j++) {
				 double temp = 0.0;
				 for (int k=0; k<this.outNum; k++) {
					 temp += this.Whid_out[j][k] * this.modify1[j][k];
				 }
				 this.dik2[i][j] = this.hid_out[num][j] * (1 - this.hid_out[num][j]) * temp;
				 this.modify2[i][j] = this.dik2[i][j];
			 }
		 }
		 
		 //update the weight from input layer to hidden layer
		 for (int i=0; i<this.inNum + 1; i++) {
			 double balance2;
			 for (int j=0; j<this.hidNum; j++) {
				 balance2 = this.speed * this.dik2[i][j] * this.input[num][i]; 
			     this.Win_hid[i][j] += (balance2 + this.revise * balance2);
			 }
		 }
		 
	 }
	
	
	/**
	 * get the error
	 */
	public double getError(int num) {
		double error = 0.0;
		for (int i=0; i<this.outNum; i++)
			error += (this.output[num][i] - this.out_out[num][i]) * (this.output[num][i] - this.out_out[num][i]);
		error *= 0.5;
		return error;
	}
	
	

	
	/**
	 * the whole traverse
	 */
	/*public void traverse() {
		this.initial();
		int flag = 1;
		for (int i=0; i<this.max; i++) {
			for (int j=0; i<this.caseNum; i++) {
				this.getValue(j);
				double error = getError(j);
				if (error <= this.threshold)
					return;
				this.updateThre(j);
				System.out.println(flag + ":" + error);
				++flag;
				list.add(error);
			}
		}
	}*/
	
	public void traverse() {
		this.initial();
		int flag = 1;
		//int flag2;
		int i;
		for (i=0; i<this.max; ) {
			//flag2 = 0;
			for (int j=0; j<this.caseNum; j++) {
				this.getValue(j);
				double error = this.getError(j);
				if (error < this.threshold)
					return;
				//if (flag2 == 0) {
					list.add(error);
				//}
				this.updateThre(j);
				System.out.println(flag + ":" + error);
				++flag;
				//++flag2;
			}
			i += this.caseNum;
		}
	}
	
	
	
	/**
	 * get the result of generalizing data 
	 */
	public void getResult(){
		traverse();
		for (int num=0; num<this.caseNum2; ++num) {
			
		    for (int i=0; i<this.hidNum; i++) {
			    double temp = 0.0;
			    for (int j=0; j<this.inNum + 1; j++)
			    	temp += this.genIn[num][j] * this.Win_hid[j][i];
			    this.hid_in[num][i] = temp;
		    }
		    
			for (int j=0; j<this.hidNum; j++)
				this.hid_out[num][j] = nonSymSig(this.hid_in[num][j]);
			this.hid_out[num][this.hidNum] = -1;	   			 //notice
			
			for (int i=0; i<this.outNum; i++) {
				double temp = 0.0;
				for (int j=0; j<this.hidNum + 1; j++)
					temp += this.hid_out[num][j] * this.Whid_out[j][i];
				this.out_in[num][i] = temp;
			}
		    
			for (int i=0; i<this.outNum; i++)
				this.genResult[num][i] = nonSymSig(this.out_in[num][i]);
		}
		
		relError();
		genRelError();
		
	}
	
	
	
	//训练数据输出平均相对误差
	
	public void relError() {
		double ave = 0.0;
		double result = 0.0;
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.outNum; j++)
				ave += this.out_out[i][j];
		}
		ave /= (this.caseNum * this.outNum);
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.outNum; j++)
				result += Math.abs(ave - this.output[i][j]);
		}
		result /= (this.caseNum * this.outNum);
		result /= ave;
		this.trainRel = result;
	}
	
	
	//泛化数据输出平均相对误差
	public void genRelError() {
		double ave = 0.0;
		double result = 0.0;
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.outNum; j++)
				ave += this.genResult[i][j];
		}
		ave /= (this.caseNum * this.outNum);
		for (int i=0; i<this.caseNum; i++) {
			for (int j=0; j<this.outNum; j++)
				result += Math.abs(ave - this.genOut[i][j]);
		}
		result /= (this.caseNum * this.outNum);
		result /= ave;
		this.genRel = result;
	}
	
	
	/*public static void main(String []args) {
		BPNetual bp = new BPNetual();
		bp.traverse();
	}*/
}
