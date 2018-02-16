package 第三节桌面程序的外观风格;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.plaf.metal.*;

public class LookAndFeelTest extends JFrame implements ActionListener{
	//依次创建按钮、文本框、单选框、复选框、菜单、菜单项、菜单栏、按钮数组
	JButton button=new JButton("按钮");
	JTextField textField=new JTextField();
	JRadioButton[] radioButton={new JRadioButton("Windows外观"),new JRadioButton("java外观"),
			new JRadioButton("Motif外观"),new JRadioButton("liquid外观"),new JRadioButton("nimrod外观")
	};
	JCheckBox[] checkBox={new JCheckBox("A"),new JCheckBox("B"),new JCheckBox("C"),new JCheckBox("D"),
			new JCheckBox("E")
	};
	JMenu menu=new JMenu("主题风格");//创建菜单
	JRadioButtonMenuItem[] menuItem={new JRadioButtonMenuItem("海洋风格",true),new JRadioButtonMenuItem("普通风格")};
	JMenuBar menuBar=new JMenuBar();//创建菜单栏
	
	ButtonGroup[] buttonGroup={new ButtonGroup(),new ButtonGroup(),new ButtonGroup()};
	
	//创建对应各个外观风格的全称类名字符串
	String windows="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	String javaMetal="javax.swing.plaf.metal.MetalLookAndFeel";
	String motif="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	String liquid="com.birosoft.liquid.LiquidLookAndFeel";
	String nimrod="com.nilo.plaf.nimrod.NimrodLookAndFeel";
	public LookAndFeelTest(){
		menuBar.add(menu);//将菜单添加进菜单栏
		//将菜单项添加进菜单，选择外观风格的菜单项，为菜单项注册动作事件监听器
		for(int i=0;i<menuItem.length;i++){
			menu.add(menuItem[i]);
			buttonGroup[0].add(menuItem[i]);
			menuItem[i].addActionListener(this);
		}
		this.setJMenuBar(menuBar);//将菜单栏添加进窗体
		this.setLayout(null);//设置窗体的布局管理器为空
		//对各个组件进行设置
		for(int i=0;i<radioButton.length;i++){
			radioButton[i].setBounds(10+i*150,10,120,26);//设置单选按钮的大小位置
			checkBox[i].setBounds(10+i*150,40,120,26);//设置复选框的大小位置
			this.add(radioButton[i]);
			this.add(checkBox[i]);
			buttonGroup[1].add(radioButton[i]);//为单选按钮编组
//			buttonGroup[2].add(checkBox[i]);//为复选框编组
			radioButton[i].addActionListener(this);//为单选按钮注册监听器	
		}
		//设置文本框、按钮的大小位置并将其添加进窗体中
		textField.setBounds(10,70,230,26);
		button.setBounds(250,70,80,26);
		this.add(textField);
		this.add(button);
		//设置窗口的属性
		this.setTitle("动态修改外观风格示例");
		this.setBounds(100,100,750,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==radioButton[0]){//选中Windows外观单选项
			this.setLAF(windows);
			menu.setEnabled(false);
		}else if(e.getSource()==radioButton[1]){//选中Java外观单选项
			this.setLAF(javaMetal);
			menu.setEnabled(true);
		}else if(e.getSource()==radioButton[2]){//选中Motif外观单选项
			this.setLAF(motif);
			menu.setEnabled(false);
		}else if(e.getSource()==radioButton[3]){//选中liquid外观单选项
			this.setLAF(liquid);
			menu.setEnabled(true);
		}else if(e.getSource()==radioButton[4]){//选中nimrod外观单选项
			this.setLAF(nimrod);
			menu.setEnabled(true);
		}else if(e.getSource()==menuItem[0]){//单击海洋场景菜单项
			this.setTheme(0);
		}else if(e.getSource()==menuItem[1]){//单击普通场景菜单项
			this.setTheme(1);
		}
	}
	//设置外观风格的方法
	public void setLAF(String s){
		try{
			UIManager.setLookAndFeel(s);
			SwingUtilities.updateComponentTreeUI(this);//更新组件的外观
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//设置主题场景的方法
	public void setTheme(int i){
		try{
			if(i==0){//如果i等于0，是海洋场景
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
			}else{//否则，为默认场景
				MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			}
			SwingUtilities.updateComponentTreeUI(this);//更新组件的外观
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new LookAndFeelTest();
	}
}
