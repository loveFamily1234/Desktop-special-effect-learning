package 第二节Desktop类;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class DesktopTest extends JFrame implements ActionListener{
	JButton[] jb={new JButton("点我发送邮件"),new JButton("点我打开网站"),new JButton("打开本地电脑的文件")};//创建按钮数组
	//创建用于输入URI的文本框数组
	JTextField[] jtf={new JTextField("lixiangguo_2001@163.com"),new JTextField("lixiangguo_2001@163.com"),new JTextField("您好，我的朋友！"),
		new JTextField("好久不见了，最近可好？")	
	};
	JTextField urlTextField=new JTextField("www.itxx8.cn");//创建用于输入网址的文本框
	//创建标签数组、写邮件的和网站标签
	JLabel[] jl={new JLabel("收信人："),new JLabel("抄送："),new JLabel("主题："),new JLabel("邮件内容：")};
	JLabel urllabel=new JLabel("网站地址：");
	
	JFileChooser fileChooser=new JFileChooser();//创建文件选择器对象
	Desktop d;//声明Desktop类的引用d
	public DesktopTest(){
		this.setLayout(null);
		//循环对邮件相关的标签、文本框进行处理
		for(int i=0;i<jl.length;i++){
			jl[i].setBounds(10,10+i*30,120,26);
			jtf[i].setBounds(100,10+i*30,155,26);
			this.add(jl[i]);
			this.add(jtf[i]);
		}
		for(int i=0;i<jb.length;i++){
			jb[i].setBounds(10,170+i*70,240,26);
			jb[i].addActionListener(this);
			this.add(jb[i]);
		}
		urllabel.setBounds(10, 210,120, 26);
		this.add(urllabel);
		urlTextField.setBounds(100,210,155,26);
		this.add(urlTextField);
		if(Desktop.isDesktopSupported()){//测试平台是否支持Desktop
			d=Desktop.getDesktop();//如果支持，获取对象
		}
		
		this.setTitle("Desktop类功能演示");
		this.setBounds(200,200,280,380);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//实现ActionListener接口中的方法
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==jb[0]){//当用户输入邮件信息，单击“点我发送邮件”按钮
			try{//获取信息传给邮件客户端
				String mailTo=jtf[0].getText().trim();
				String cc=jtf[1].getText().trim();
				String subject=jtf[2].getText().trim();
				String body=jtf[3].getText().trim();
				if(mailTo.length()>0){//若有收信人地址，打开默认邮件客户端并补全内容
					d.mail(new URI("mailto:"+mailTo+"?cc="+cc+"&SUBJECT="+subject+"&BODY="+body));
				}else{//若没有收信人地址，则打开空白默认邮件客户端
					d.mail();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(ae.getSource()==jb[1]){//当用户单击“点我打开网站”按钮，获取用户输入的网址并使用默认的浏览器浏览给出的网址
			String url=urlTextField.getText().trim();
			try{
				if(url.length()>0){
					d.browse(new URI(url));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(ae.getSource()==jb[2]){//当用户单击“打开本地电脑的文件”按钮，弹开文件选择器
			int c=fileChooser.showDialog(this, "请选择文件");
			if(c==JFileChooser.OPEN_DIALOG){
				try{//获取文件所在的目录路径，并使用当前平台默认的关联程序打开
					File f=fileChooser.getSelectedFile();
					d.open(f);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new DesktopTest();
	}
}
