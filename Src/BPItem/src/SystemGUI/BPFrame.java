package SystemGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import BPAlogrithm.BPNetual;
import DisplayChart.GenResultChart;
import DisplayChart.TrainErrorChart;
import DisplayChart.TrainResultChart;

@SuppressWarnings("serial")
/**
 * the class is the whole layout which is the GUI of BP system  
 */
public class BPFrame extends JFrame{
	private JPanel eastPanel ;
	private JPanel northPanel ;
	private JPanel westPanel ;
	private JPanel centerPanel ;
	private JPanel southPanel;
	public BPFrame() {
		BPNetual bp = new BPNetual();
		bp.getResult();
		setTitle("BPsystem");
		setSize(1000,600);
		/**
		 * operate to south panel
		 */
		southPanel = new JPanel();
		final JTextArea textArea = new JTextArea(5,60);
		JScrollPane scrollPane = new JScrollPane(textArea);
		southPanel.add(scrollPane);
		southPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		add(southPanel,BorderLayout.SOUTH);
		
		
		/**
		 * operate to north panel
		 */
		northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,4,180,0));
		JButton startButton = new JButton("相对误差值");
		northPanel.add(startButton);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textArea.append("训练数据相对误差为:" + bp.trainRel + "\n");
				textArea.append("泛化数据相对误差为:" + bp.genRel + "\n");
			}
		});
		
		JButton genButton = new JButton("泛化");
		northPanel.add(genButton);
		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GenResultChart gen = new GenResultChart(bp);
				westPanel = gen.getPanel();
				westPanel.setBorder(BorderFactory.createRaisedBevelBorder());
				add(westPanel,BorderLayout.WEST);
			}
		});
		
		JButton trainButton = new JButton("训练");
		northPanel.add(trainButton);
		trainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TrainResultChart result = new TrainResultChart(bp);
				centerPanel = result.getPanel();
				centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
				add(centerPanel,BorderLayout.CENTER);
			}
		});
		
		JButton stopButton = new JButton("训练误差");
		northPanel.add(stopButton);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				TrainErrorChart train = new TrainErrorChart(bp);
				eastPanel = train.getPanel();
				eastPanel.setBorder(BorderFactory.createRaisedBevelBorder());
				add(eastPanel,BorderLayout.EAST);
			}
		});
		
		northPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		add(northPanel,BorderLayout.NORTH);
	}
	
	
	public static void main(String []args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BPFrame bp = new BPFrame();
				bp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				bp.setVisible(true);
				}
		}
		);
	}
	
}
