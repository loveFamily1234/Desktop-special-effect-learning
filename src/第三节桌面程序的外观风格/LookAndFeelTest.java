package ����������������۷��;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.plaf.metal.*;

public class LookAndFeelTest extends JFrame implements ActionListener{
	//���δ�����ť���ı��򡢵�ѡ�򡢸�ѡ�򡢲˵����˵���˵�������ť����
	JButton button=new JButton("��ť");
	JTextField textField=new JTextField();
	JRadioButton[] radioButton={new JRadioButton("Windows���"),new JRadioButton("java���"),
			new JRadioButton("Motif���"),new JRadioButton("liquid���"),new JRadioButton("nimrod���")
	};
	JCheckBox[] checkBox={new JCheckBox("A"),new JCheckBox("B"),new JCheckBox("C"),new JCheckBox("D"),
			new JCheckBox("E")
	};
	JMenu menu=new JMenu("������");//�����˵�
	JRadioButtonMenuItem[] menuItem={new JRadioButtonMenuItem("������",true),new JRadioButtonMenuItem("��ͨ���")};
	JMenuBar menuBar=new JMenuBar();//�����˵���
	
	ButtonGroup[] buttonGroup={new ButtonGroup(),new ButtonGroup(),new ButtonGroup()};
	
	//������Ӧ������۷���ȫ�������ַ���
	String windows="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	String javaMetal="javax.swing.plaf.metal.MetalLookAndFeel";
	String motif="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	String liquid="com.birosoft.liquid.LiquidLookAndFeel";
	String nimrod="com.nilo.plaf.nimrod.NimrodLookAndFeel";
	public LookAndFeelTest(){
		menuBar.add(menu);//���˵���ӽ��˵���
		//���˵�����ӽ��˵���ѡ����۷��Ĳ˵��Ϊ�˵���ע�ᶯ���¼�������
		for(int i=0;i<menuItem.length;i++){
			menu.add(menuItem[i]);
			buttonGroup[0].add(menuItem[i]);
			menuItem[i].addActionListener(this);
		}
		this.setJMenuBar(menuBar);//���˵�����ӽ�����
		this.setLayout(null);//���ô���Ĳ��ֹ�����Ϊ��
		//�Ը��������������
		for(int i=0;i<radioButton.length;i++){
			radioButton[i].setBounds(10+i*150,10,120,26);//���õ�ѡ��ť�Ĵ�Сλ��
			checkBox[i].setBounds(10+i*150,40,120,26);//���ø�ѡ��Ĵ�Сλ��
			this.add(radioButton[i]);
			this.add(checkBox[i]);
			buttonGroup[1].add(radioButton[i]);//Ϊ��ѡ��ť����
//			buttonGroup[2].add(checkBox[i]);//Ϊ��ѡ�����
			radioButton[i].addActionListener(this);//Ϊ��ѡ��ťע�������	
		}
		//�����ı��򡢰�ť�Ĵ�Сλ�ò�������ӽ�������
		textField.setBounds(10,70,230,26);
		button.setBounds(250,70,80,26);
		this.add(textField);
		this.add(button);
		//���ô��ڵ�����
		this.setTitle("��̬�޸���۷��ʾ��");
		this.setBounds(100,100,750,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==radioButton[0]){//ѡ��Windows��۵�ѡ��
			this.setLAF(windows);
			menu.setEnabled(false);
		}else if(e.getSource()==radioButton[1]){//ѡ��Java��۵�ѡ��
			this.setLAF(javaMetal);
			menu.setEnabled(true);
		}else if(e.getSource()==radioButton[2]){//ѡ��Motif��۵�ѡ��
			this.setLAF(motif);
			menu.setEnabled(false);
		}else if(e.getSource()==radioButton[3]){//ѡ��liquid��۵�ѡ��
			this.setLAF(liquid);
			menu.setEnabled(true);
		}else if(e.getSource()==radioButton[4]){//ѡ��nimrod��۵�ѡ��
			this.setLAF(nimrod);
			menu.setEnabled(true);
		}else if(e.getSource()==menuItem[0]){//�������󳡾��˵���
			this.setTheme(0);
		}else if(e.getSource()==menuItem[1]){//������ͨ�����˵���
			this.setTheme(1);
		}
	}
	//������۷��ķ���
	public void setLAF(String s){
		try{
			UIManager.setLookAndFeel(s);
			SwingUtilities.updateComponentTreeUI(this);//������������
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//�������ⳡ���ķ���
	public void setTheme(int i){
		try{
			if(i==0){//���i����0���Ǻ��󳡾�
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
			}else{//����ΪĬ�ϳ���
				MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			}
			SwingUtilities.updateComponentTreeUI(this);//������������
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new LookAndFeelTest();
	}
}
