package 第一节Robot类;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RobotTest extends JFrame implements ActionListener{
	//创建标签提示信息
	JLabel label=new JLabel("请点击以下4个按钮获取报酬，完毕后输入验证码“bcdef”！");
	JButton[] jb={new JButton("0.02$"),new JButton("0.05$"),new JButton("0.1$"),new JButton("0.5$")};//创建按钮数组
	JTextField textField=new JTextField();
	JTextField textFieldMsg=new JTextField();//创建文本框提示
	JTextArea textArea=new JTextArea();//创建文本区
	JScrollPane scrollPane=new JScrollPane(textArea);
	Robot robot;
	public RobotTest(){
		this.setLayout(null);//设置布局管理器为空
		label.setBounds(10, 10,380,26);
		this.add(label);
		textFieldMsg.setBounds(10,40,380,26);
		this.add(textFieldMsg);
		textFieldMsg.setEditable(false);//设置文本框提示为不可编辑状态
		for(int i=0;i<jb.length;i++){
			jb[i].setBounds(10+i*90,80,80,26);
			this.add(jb[i]);
			jb[i].addActionListener(this);
		}
		textField.setBounds(10,120,380,26);
		this.add(textField);
		textField.addActionListener(this);
		for(int i=0;i<100;i++){
			textArea.append("请观察滚动条进行滚动的操作！！！！！！");
		}
		textArea.setLineWrap(true);//设置文本区为自动换行
		scrollPane.setBounds(10,160,380,130);
		this.add(scrollPane);
		
		this.setTitle("模拟鼠标、键盘自动操作");
		this.setBounds(200,200,400,350);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.creatRobot();
	}
	
	//creatRobot方法，创建对象并对其进行初始化
	public void creatRobot(){
		try{
			robot=new Robot();
			robot.delay(1000);//延迟1秒
			for(int i=0;i<jb.length;i++){//调用鼠标点击方法单击4个按钮
				this.clickMouse(jb[i]);//单击按钮
				robot.delay(1000);//延迟1秒
			}
			//调用clickMouse方法选中文本框并循环输入文本
			this.clickMouse(textField);
			for(int i=0;i<6;i++){
				robot.keyPress(KeyEvent.VK_A+i);
				robot.keyRelease(KeyEvent.VK_A+i);
				robot.delay(1000);//延迟200毫秒
			}
			//模拟按下回车键
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1000);
			//调用clickMouse方法，模拟点击文本区
			this.clickMouse(scrollPane);
			robot.delay(1000);
			for(int i=0;i<15;i++){//模拟滑动滚动条
				robot.mouseWheel(1);
				robot.delay(100);
			}
			robot.delay(1000);
			//将鼠标移动到窗体关闭按钮处
			robot.mouseMove(this.getX()+380, this.getY()+8);
			robot.delay(3000);
			//模拟点击鼠标左键按下关闭窗体的按钮
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//模拟鼠标点击指定控件的方法
	public void clickMouse(JComponent c){
		//确定点击组件的位置
		int x=this.getX()+c.getX()+c.getWidth()/2;
		int y=this.getY()+c.getY()+c.getHeight()/2+20;
		robot.mouseMove(x, y);//将鼠标移动到指定位置
		//模拟点击和释放鼠标左键
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	//实现ActionListener中的方法
	public void actionPerformed(ActionEvent e){
		for(int i=0;i<jb.length;i++){
			//显示按下按钮的提示信息
			if(e.getSource()==jb[i]){
				textFieldMsg.setText("感谢点击，您获得了"+jb[i].getText()+"！");
			}
		}
		if(e.getSource()==textField){//显示文本框输入后回车的提示信息
			textFieldMsg.setText("您输入的验证码是"+textField.getText()+"！");
		}
	}
	
	
	public static void main(String[] args){
		new RobotTest();
	}
}
