package DisplayChart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Font;
import java.util.Iterator;

import BPAlogrithm.BPNetual;

public class TrainErrorChart {
	public ChartPanel frame1;
	public TrainErrorChart(BPNetual bp) {
		XYDataset xydataset = createDataSet(bp);
		JFreeChart jfreechart = ChartFactory.createXYLineChart("误差曲线", "训练次数", "误差值", xydataset,PlotOrientation.VERTICAL, true,true,true);
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
		Iterator<Double> it = bp.list.iterator();
		//使用接口XYDataset实现的类来返回
		XYSeries xyseries = new XYSeries("误差图");
		int i = 1;
		while (it.hasNext()) {
			xyseries.add(i, it.next());
			++i;
		}
		XYSeriesCollection xycoll = new XYSeriesCollection();
		xycoll.addSeries(xyseries);
		return xycoll;
	}
	
	public ChartPanel getPanel() {
		return frame1;
	}
	
}
