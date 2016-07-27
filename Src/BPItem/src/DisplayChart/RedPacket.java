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

public class RedPacket {
	public ChartPanel frame1;
	public RedPacket() {
		XYDataset xydataset = createDataSet();
		JFreeChart jfreechart = ChartFactory.createXYLineChart("红包曲线", "领取次序", "金额", xydataset,PlotOrientation.VERTICAL, true,true,true);
		frame1=new ChartPanel(jfreechart,true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		NumberAxis xx = (NumberAxis)xyplot.getDomainAxis();		//get x axis
		xx.setLabelFont(new Font("黑体",Font.BOLD,15));
		ValueAxis yy = xyplot.getRangeAxis();					//get y axis
		yy.setLabelFont(new Font("黑体",Font.BOLD,15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
		
	}
	
	public XYDataset createDataSet() {
		//使用接口XYDataset实现的类来返回
		XYSeries xyseries = new XYSeries("红包曲线");
		xyseries.add(1, 9.66);
		xyseries.add(2, 3.35);
		xyseries.add(3, 0.2);
		xyseries.add(4, 8);
		xyseries.add(5, 0.71);
		xyseries.add(6, 2.67);
		xyseries.add(7, 4.17);
		xyseries.add(8, 9.78);
		xyseries.add(9, 0.31);
		xyseries.add(10, 3.10);
		xyseries.add(11, 9.67);
		xyseries.add(12, 7.83);
		xyseries.add(13, 9.62);
		xyseries.add(14, 4.00);
		xyseries.add(15, 5.4);
		xyseries.add(16, 2.08);
		xyseries.add(17, 2);
		xyseries.add(18, 2.02);
		xyseries.add(19, 0.68);
		xyseries.add(20, 6.33);
		xyseries.add(21, 4.16);
		xyseries.add(22, 10.89);
		xyseries.add(23, 6.74);
		xyseries.add(24, 7.49);
		xyseries.add(25, 8.67);
		xyseries.add(26, 8.74);
		xyseries.add(27, 7.35);
		xyseries.add(28, 6.28);
		xyseries.add(29, 3.92);
		xyseries.add(30, 6.78);
		xyseries.add(31, 1.03);
		xyseries.add(32, 1.49);
		xyseries.add(33, 5.83);
		xyseries.add(34, 5.59);
		xyseries.add(35, 5.80);
		XYSeriesCollection xycoll = new XYSeriesCollection();
		xycoll.addSeries(xyseries);
		return xycoll;
	}
	
	public ChartPanel getPanel() {
		return frame1;
	}
	
}
