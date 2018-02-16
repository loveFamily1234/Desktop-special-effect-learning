package �ڶ���Desktop��;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class DesktopTest extends JFrame implements ActionListener{
	JButton[] jb={new JButton("���ҷ����ʼ�"),new JButton("���Ҵ���վ"),new JButton("�򿪱��ص��Ե��ļ�")};//������ť����
	//������������URI���ı�������
	JTextField[] jtf={new JTextField("lixiangguo_2001@163.com"),new JTextField("lixiangguo_2001@163.com"),new JTextField("���ã��ҵ����ѣ�"),
		new JTextField("�þò����ˣ�����ɺã�")	
	};
	JTextField urlTextField=new JTextField("www.itxx8.cn");//��������������ַ���ı���
	//������ǩ���顢д�ʼ��ĺ���վ��ǩ
	JLabel[] jl={new JLabel("�����ˣ�"),new JLabel("���ͣ�"),new JLabel("���⣺"),new JLabel("�ʼ����ݣ�")};
	JLabel urllabel=new JLabel("��վ��ַ��");
	
	JFileChooser fileChooser=new JFileChooser();//�����ļ�ѡ��������
	Desktop d;//����Desktop�������d
	public DesktopTest(){
		this.setLayout(null);
		//ѭ�����ʼ���صı�ǩ���ı�����д���
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
		if(Desktop.isDesktopSupported()){//����ƽ̨�Ƿ�֧��Desktop
			d=Desktop.getDesktop();//���֧�֣���ȡ����
		}
		
		this.setTitle("Desktop�๦����ʾ");
		this.setBounds(200,200,280,380);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//ʵ��ActionListener�ӿ��еķ���
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==jb[0]){//���û������ʼ���Ϣ�����������ҷ����ʼ�����ť
			try{//��ȡ��Ϣ�����ʼ��ͻ���
				String mailTo=jtf[0].getText().trim();
				String cc=jtf[1].getText().trim();
				String subject=jtf[2].getText().trim();
				String body=jtf[3].getText().trim();
				if(mailTo.length()>0){//���������˵�ַ����Ĭ���ʼ��ͻ��˲���ȫ����
					d.mail(new URI("mailto:"+mailTo+"?cc="+cc+"&SUBJECT="+subject+"&BODY="+body));
				}else{//��û�������˵�ַ����򿪿հ�Ĭ���ʼ��ͻ���
					d.mail();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(ae.getSource()==jb[1]){//���û����������Ҵ���վ����ť����ȡ�û��������ַ��ʹ��Ĭ�ϵ�����������������ַ
			String url=urlTextField.getText().trim();
			try{
				if(url.length()>0){
					d.browse(new URI(url));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(ae.getSource()==jb[2]){//���û��������򿪱��ص��Ե��ļ�����ť�������ļ�ѡ����
			int c=fileChooser.showDialog(this, "��ѡ���ļ�");
			if(c==JFileChooser.OPEN_DIALOG){
				try{//��ȡ�ļ����ڵ�Ŀ¼·������ʹ�õ�ǰƽ̨Ĭ�ϵĹ��������
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
