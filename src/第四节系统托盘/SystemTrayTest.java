package 第四节系统托盘;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SystemTrayTest extends JFrame implements ActionListener{
	PopupMenu popup=new PopupMenu();//创建菜单、菜单项数组、消息类型
	Menu menu=new Menu("消息类型");
	MenuItem[] itemArray={new MenuItem("信息消息"),new MenuItem("常规消息"),new MenuItem("警告消息"),
			new MenuItem("错误消息"),new MenuItem("退出程序")
	};
	SystemTray tray;//定义系统托盘
	TrayIcon trayIcon;//定义托盘图标变量
	
	public SystemTrayTest(){
		//对菜单项添加监听并将菜单项添加到菜单中
		for(int i=0;i<itemArray.length;i++){
			if(i<4){
				itemArray[i].addActionListener(this);//为菜单项注册监听器
				//将菜单项数组中前4个菜单项添加进“弹出消息”菜单中
				menu.add(itemArray[i]);
			}
			itemArray[4].addActionListener(this);//添加监听
			popup.add(menu);//将弹出的消息菜单添加到菜单中
			popup.add(itemArray[4]);//将退出菜单添加到菜单中
		}
		//判断当前操作系统是否支持系统托盘
		if(SystemTray.isSupported()){
			tray=SystemTray.getSystemTray();//获取系统托盘
			//加载图标
			Image image=Toolkit.getDefaultToolkit().getImage("‪‪‪‪E:\\图片\\3.ico");
			trayIcon=new TrayIcon(image,"系统托盘测试",popup);//创建托盘图标
			trayIcon.setImageAutoSize(true);//托盘图标自动设置尺寸
			try{
				tray.add(trayIcon);//添加托盘图标到系统托盘中
			}catch(AWTException e){
				e.printStackTrace();
			}
			trayIcon.addActionListener(this);//为托盘图标注册监听器
		}
		//设置窗体关闭按钮所执行的动作
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				SystemTrayTest.this.setVisible(false);//隐藏窗体
			}
		});
		//设置窗体属性
		this.setTitle("系统托盘测试");
		this.setBounds(200,200,400,100);
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==itemArray[0]){//单击“信息消息”菜单项执行的动作
			trayIcon.displayMessage("信息消息", "程序最小化，仍在运行", TrayIcon.MessageType.INFO);
		}else if(e.getSource()==itemArray[1]){//单击“常规消息”菜单项执行的动作
			trayIcon.displayMessage("常规消息", "现在一切正常", TrayIcon.MessageType.NONE);
		}else if(e.getSource()==itemArray[2]){//单击“警告消息”菜单项执行的动作
			trayIcon.displayMessage("警告消息", "有不明来源的攻击", TrayIcon.MessageType.WARNING);
		}else if(e.getSource()==itemArray[3]){//单击“错误消息”菜单项执行的动作
			trayIcon.displayMessage("错误消息","程序发生严重错误",TrayIcon.MessageType.ERROR);
		}else if(e.getSource()==itemArray[4]){//单击“退出程序”菜单项执行的动作
			System.exit(0);
		}else if(e.getSource()==trayIcon){//双击托盘图标执行的动作
			this.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new SystemTrayTest();
	}
}
