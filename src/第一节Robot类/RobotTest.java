package ��һ��Robot��;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RobotTest extends JFrame implements ActionListener{
	//������ǩ��ʾ��Ϣ
	JLabel label=new JLabel("��������4����ť��ȡ���꣬��Ϻ�������֤�롰bcdef����");
	JButton[] jb={new JButton("0.02$"),new JButton("0.05$"),new JButton("0.1$"),new JButton("0.5$")};//������ť����
	JTextField textField=new JTextField();
	JTextField textFieldMsg=new JTextField();//�����ı�����ʾ
	JTextArea textArea=new JTextArea();//�����ı���
	JScrollPane scrollPane=new JScrollPane(textArea);
	Robot robot;
	public RobotTest(){
		this.setLayout(null);//���ò��ֹ�����Ϊ��
		label.setBounds(10, 10,380,26);
		this.add(label);
		textFieldMsg.setBounds(10,40,380,26);
		this.add(textFieldMsg);
		textFieldMsg.setEditable(false);//�����ı�����ʾΪ���ɱ༭״̬
		for(int i=0;i<jb.length;i++){
			jb[i].setBounds(10+i*90,80,80,26);
			this.add(jb[i]);
			jb[i].addActionListener(this);
		}
		textField.setBounds(10,120,380,26);
		this.add(textField);
		textField.addActionListener(this);
		for(int i=0;i<100;i++){
			textArea.append("��۲���������й����Ĳ���������������");
		}
		textArea.setLineWrap(true);//�����ı���Ϊ�Զ�����
		scrollPane.setBounds(10,160,380,130);
		this.add(scrollPane);
		
		this.setTitle("ģ����ꡢ�����Զ�����");
		this.setBounds(200,200,400,350);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.creatRobot();
	}
	
	//creatRobot�������������󲢶�����г�ʼ��
	public void creatRobot(){
		try{
			robot=new Robot();
			robot.delay(1000);//�ӳ�1��
			for(int i=0;i<jb.length;i++){//�����������������4����ť
				this.clickMouse(jb[i]);//������ť
				robot.delay(1000);//�ӳ�1��
			}
			//����clickMouse����ѡ���ı���ѭ�������ı�
			this.clickMouse(textField);
			for(int i=0;i<6;i++){
				robot.keyPress(KeyEvent.VK_A+i);
				robot.keyRelease(KeyEvent.VK_A+i);
				robot.delay(1000);//�ӳ�200����
			}
			//ģ�ⰴ�»س���
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1000);
			//����clickMouse������ģ�����ı���
			this.clickMouse(scrollPane);
			robot.delay(1000);
			for(int i=0;i<15;i++){//ģ�⻬��������
				robot.mouseWheel(1);
				robot.delay(100);
			}
			robot.delay(1000);
			//������ƶ�������رհ�ť��
			robot.mouseMove(this.getX()+380, this.getY()+8);
			robot.delay(3000);
			//ģ�������������¹رմ���İ�ť
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//ģ�������ָ���ؼ��ķ���
	public void clickMouse(JComponent c){
		//ȷ����������λ��
		int x=this.getX()+c.getX()+c.getWidth()/2;
		int y=this.getY()+c.getY()+c.getHeight()/2+20;
		robot.mouseMove(x, y);//������ƶ���ָ��λ��
		//ģ�������ͷ�������
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	//ʵ��ActionListener�еķ���
	public void actionPerformed(ActionEvent e){
		for(int i=0;i<jb.length;i++){
			//��ʾ���°�ť����ʾ��Ϣ
			if(e.getSource()==jb[i]){
				textFieldMsg.setText("��л������������"+jb[i].getText()+"��");
			}
		}
		if(e.getSource()==textField){//��ʾ�ı��������س�����ʾ��Ϣ
			textFieldMsg.setText("���������֤����"+textField.getText()+"��");
		}
	}
	
	
	public static void main(String[] args){
		new RobotTest();
	}
}
