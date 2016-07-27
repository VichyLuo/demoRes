package DisplayChart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import BPAlogrithm.BPNetual;

public class GenResultChart {
	public ChartPanel frame1;
	public GenResultChart(BPNetual bp) {
		XYDataset xydataset = createDataSet(bp);
		JFreeChart jfreechart = ChartFactory.createXYLineChart("泛化数据与网络计算结果比较图", "样本次数", "输出值", xydataset,PlotOrientation.VERTICAL, true,true,true);
		frame1=new ChartPanel(jfreechart,true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		NumberAxis xx = (NumberAxis)xyplot.getDomainAxis();		//get x axis
		xx.setLabelFont(new Font("黑体",Font.BOLD,15));
		ValueAxis yy = xyplot.getRangeAxis();					//get y axis
		yy.setLabelFont(new Font("黑体",Font.BOLD,15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体	
	}
	
	public XYDataset createDataSet(BPNetual bp) {
		//使用接口XYDataset实现的类来返回
		XYSeries xyseries = new XYSeries("泛化练数据网络计算输出值");
		int count = 1;
		for (int i=0; i<bp.caseNum; i++) {
			for (int j=0; j<bp.outNum; j++) {
				xyseries.add(count, bp.genResult[i][j]);
				++count;
			}
		}
		XYSeries xyseries1 = new XYSeries("泛化数据期望输出值");
		count = 1;
		for (int i=0; i<bp.caseNum; i++) {
			for (int j=0; j<bp.outNum; j++) {
				xyseries1.add(count, bp.genOut[i][j]);
				++count;
			}
		}
		
		XYSeriesCollection xycoll = new XYSeriesCollection();
		xycoll.addSeries(xyseries);
		xycoll.addSeries(xyseries1);
		return xycoll;
	}
	
	public ChartPanel getPanel() {
		return frame1;
	}
}
