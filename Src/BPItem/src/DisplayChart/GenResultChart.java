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
		JFreeChart jfreechart = ChartFactory.createXYLineChart("��������������������Ƚ�ͼ", "��������", "���ֵ", xydataset,PlotOrientation.VERTICAL, true,true,true);
		frame1=new ChartPanel(jfreechart,true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		NumberAxis xx = (NumberAxis)xyplot.getDomainAxis();		//get x axis
		xx.setLabelFont(new Font("����",Font.BOLD,15));
		ValueAxis yy = xyplot.getRangeAxis();					//get y axis
		yy.setLabelFont(new Font("����",Font.BOLD,15));
		jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������	
	}
	
	public XYDataset createDataSet(BPNetual bp) {
		//ʹ�ýӿ�XYDatasetʵ�ֵ���������
		XYSeries xyseries = new XYSeries("��������������������ֵ");
		int count = 1;
		for (int i=0; i<bp.caseNum; i++) {
			for (int j=0; j<bp.outNum; j++) {
				xyseries.add(count, bp.genResult[i][j]);
				++count;
			}
		}
		XYSeries xyseries1 = new XYSeries("���������������ֵ");
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
