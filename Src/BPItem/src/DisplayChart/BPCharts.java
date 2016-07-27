package DisplayChart;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import BPAlogrithm.BPNetual;

public class BPCharts extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Panel northPanel;
	private Panel centerPanel;
	private Panel southPanel;
	private JButton preButton;
	private JButton nextButton;
	private JButton errorButton;
	private CardLayout card ;
	
	public BPCharts() {
		//����ʼ��
		super("BPSystem");
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		//BP����
		BPNetual bp = new BPNetual();
		bp.getResult();
		
		//��centerPanel������
		card = new CardLayout();
		centerPanel = new Panel();
		centerPanel.setLayout(card);
		TrainErrorChart train = new TrainErrorChart(bp);
		centerPanel.add(train.getPanel());
		TrainResultChart result = new TrainResultChart(bp);
		centerPanel.add(result.getPanel());
		GenResultChart gen = new GenResultChart(bp);
		centerPanel.add(gen.getPanel());
		RedPacket red = new RedPacket();
		centerPanel.add(red.getPanel());
		
		//��southPanel����
		southPanel = new Panel();
        final JTextArea textArea = new JTextArea(5,60);
		JScrollPane scrollPane = new JScrollPane(textArea);
		southPanel.add(scrollPane);
		
		//��northPanel������
		northPanel = new Panel();
		preButton = new JButton("��һ����ͼ��");
		nextButton = new JButton("��һ��ͼ��");
		errorButton = new JButton("������");
		//Ϊÿ����ťע�������
		preButton.addActionListener(this);
		nextButton.addActionListener(this);
		errorButton.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent event) {
				textArea.append("ѵ������������Ϊ:" + bp.trainRel + "\n");
				textArea.append("��������������Ϊ:" + bp.genRel + "\n");
			}
		});
		northPanel.add(preButton);
		northPanel.add(nextButton);
		northPanel.add(errorButton);
		
		//����һ������,�����������
		Container container = getContentPane();
		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		container.add(southPanel, BorderLayout.SOUTH);
		
	}
	//���¼�����
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == preButton)
			card.previous(centerPanel);
		if (e.getSource() == nextButton)
			card.next(centerPanel);
	}
	
	public void print() {
		System.out.println("the run is successful");
	}
	
	public static void main(String[] args) {
		BPCharts bpChart = new BPCharts();
		bpChart.print();
	}
}
