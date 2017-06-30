import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

//���е�UI��ƶ������.java�ļ��У������඼���ǹ�����

public class Main
{
	public static void main(String[] args)//������
	{
		EventQueue.invokeLater(new Runnable()
		{     
             public void run()									//������
             {         	 
            	 mainFrame frame=new mainFrame();//�����
            	 frame.setTitle("�鶯�촫");//���ñ���
            	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	 frame.setVisible(true);
             }
		});
	}
}

class mainFrame extends JFrame//�������
{
	private static final long serialVersionUID = 1L;
	private static final int width=400;
	private static final int height=320;
	private JFileChooser fc = new JFileChooser();//�����ļ�ѡ�����
	private final Dimension BUTTONSIZE = new Dimension(90,50);
	private final Dimension PANELSIZE = new Dimension(260,320);
	public static String pathdir = "c:\\download";//�����ļ�·��

	//������
	private MyPanel cardPanel=new MyPanel();//����ʹ�ÿ�Ƭ���ֵ�����
	private JPanel ctrlPanel=new JPanel();//������ƿ�Ƭ�л�������
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private CardLayout card=new CardLayout();//���忨Ƭ����
	private String cardName[]={"0","1","2","3"};

	private JButton Phone = new JButton("�����ֻ�");
	private JButton Online = new JButton("�����ļ�");
	private JButton Custom = new JButton("�Զ���");
	private JButton Recent = new JButton("��������");
	//p1
	private MyButton Connect = new MyButton("��������");
	public static JLabel Msg = new JLabel();
	public static MyButton Send1 = new MyButton("�����ļ�");
	//p2
//	private MyButton Internet = new MyButton("������");
	private MyButton Upload = new MyButton("�ϴ��ļ�");
	private MyButton Download = new MyButton("�����ļ�");
	public static JLabel Msg2 = new JLabel();
	private JPanel p2_1 = new JPanel();//ƽʱ���أ�������ذ���ʱ����
	private JLabel Tip = new JLabel("�������ļ���ȡ�룺");
	private JTextField Password = new JTextField(10);//��ȡ�������
	private JButton p2_ok = new JButton("ȷ��");
	private JButton p2_cancel = new JButton("ȡ��");
	//p3
	private JLabel labelIP = new JLabel("Ŀ��IP��ַ��");
	private JLabel labelPort = new JLabel("Ŀ��˿ںţ�");
	private JTextField txtIP = new JTextField(9);
	private JTextField txtPort=new JTextField(6);
	private MyButton Send2=new MyButton("�����ļ�");
	//p4
	private JLabel t=new JLabel("����Ŀ¼");
	private JTextField txtpath=new JTextField(15);//���ñ༭�򳤶�
	private MyButton select=new MyButton("...");
	private MyButton p4_ok=new MyButton("ȷ��");
	private MyButton p4_cancel=new MyButton("ȡ��");
	private JPanel p4_1=new JPanel();
	private JPanel p4_2=new JPanel();
	
	public mainFrame()
	{
		//���������
		setSize(width,height);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());//������óɱ߿򲼾�
		setResizable(false);//������ı��ܴ�С
		
		Phone.setFocusable(false);//�����۽�����
		Phone.setPreferredSize(BUTTONSIZE);
		
		Online.setFocusable(false);//�����۽�����
		Online.setPreferredSize(BUTTONSIZE);
		
		Custom.setFocusable(false);//�����۽�����
		Custom.setPreferredSize(BUTTONSIZE);
		
		Recent.setFocusable(false);//�����۽�����
		Recent.setPreferredSize(BUTTONSIZE);

		ctrlPanel.setBackground(new Color(14,232,58));
		ctrlPanel.add(Phone);
		ctrlPanel.add(Online);
		ctrlPanel.add(Custom);
		ctrlPanel.add(Recent);
		ctrlPanel.setLayout(new  FlowLayout(FlowLayout.CENTER,20,18));
		ctrlPanel.setPreferredSize(new Dimension(120,400));
		add(ctrlPanel,BorderLayout.WEST);
		
		cardPanel.setLayout(card);
		cardPanel.add(cardName[0],p1);
		cardPanel.add(cardName[1],p2);
		cardPanel.add(cardName[2],p3);
		cardPanel.add(cardName[3],p4);
		add(cardPanel,BorderLayout.CENTER);
		
		//p1���
		Send1.setVisible(false);//��ʼ����	
		Msg.setForeground(Color.WHITE);
		p1.setOpaque(false);
		p1.setBackground(new Color(29,148,237));
		p1.add(Connect);
		p1.add(Msg);
		p1.add(Send1);
		p1.setPreferredSize(PANELSIZE);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,1000,40));
		
		//p2���
/*		Internet.setPreferredSize(BUTTONSIZE);
		Internet.setBackground(null);
		Internet.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Internet.setForeground(Color.WHITE);
		Internet.setFocusable(false);
		Internet.setOpaque(false);//����͸��
		p2.add(Internet);
*/		
		Upload.setBounds(95, 40, 90, 50);
		p2.add(Upload);
		Download.setBounds(95, 190, 90, 50);
		p2.add(Download);
		Msg2.setHorizontalAlignment(JLabel.CENTER);
		Msg2.setForeground(Color.WHITE);
		Msg2.setBounds(15, 250, 250, 30);
		p2.add(Msg2);
		
		p2_1.add(Tip);
		p2_1.add(Password);
		p2_1.add(p2_ok);
		p2_1.add(p2_cancel);
		p2_1.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		p2_1.setBounds(60, 85,160, 110);
		p2_1.setVisible(false);
		
		p2.add(p2_1);
		p2.setOpaque(false);
		p2.setBackground(new Color(29,148,237));
		p2.setPreferredSize(PANELSIZE);
		p2.setLayout(null);
		
		//p3���
		txtIP.setText("xxx.xxx.xxx.xxx");
		txtIP.setHorizontalAlignment(JTextField.CENTER);
		txtIP.setOpaque(false);
		txtIP.setForeground(Color.WHITE);
		txtPort.setText("9999");
		txtPort.setHorizontalAlignment(JTextField.CENTER);
		txtPort.setOpaque(false);
		txtPort.setForeground(Color.WHITE);
		labelIP.setForeground(Color.WHITE);
		labelPort.setForeground(Color.WHITE);
		setForeground(Color.WHITE);
		p3.add(labelIP);
		p3.add(txtIP);
		p3.add(labelPort);
		p3.add(txtPort);
		p3.add(Send2);
		p3.setOpaque(false);
		p3.setBackground(new Color(29,148,237));
		p3.setPreferredSize(PANELSIZE);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,1000,25));
		
		//p4���
		txtpath.setText(mainFrame.pathdir);
		txtpath.setOpaque(false);
		txtpath.setForeground(Color.WHITE);
		t.setForeground(Color.WHITE);
		p4_1.add(t);
		p4_1.add(txtpath);
		select.setPreferredSize(new Dimension(40,26));
		p4_1.add(select);
		p4_1.setLayout(new FlowLayout(FlowLayout.RIGHT,15,30));//�����֣��������
		p4_1.setBackground(new Color(29,148,237));
		p4_1.setPreferredSize(new Dimension(280,180));
		p4_1.setOpaque(false);
		p4.add(p4_1);
		
		p4_ok.setPreferredSize(new Dimension(50,30));
		p4_2.add(p4_ok);
		p4_cancel.setPreferredSize(new Dimension(50,30));
		p4_2.add(p4_cancel);
		p4_2.setLayout(new FlowLayout(FlowLayout.RIGHT,20,40));//ˮƽ���20����ֱ���30
		p4_2.setOpaque(false);
		p4.add(p4_2);
		p4_2.setBackground(new Color(29,148,237));
		p4_2.setPreferredSize(new Dimension(280,200));
		p4.setLayout(new FlowLayout(FlowLayout.CENTER,1000,0));
		p4.setOpaque(false);
		
		
		ButtonAction buttonaction=new ButtonAction();			//�������������
		Phone.addActionListener(buttonaction);
		Online.addActionListener(buttonaction);
		Custom.addActionListener(buttonaction);
		Recent.addActionListener(buttonaction);
		Connect.addActionListener(buttonaction);
		Send1.addActionListener(buttonaction);
//		Internet.addActionListener(buttonaction);
		Upload.addActionListener(buttonaction);
		Download.addActionListener(buttonaction);
		p2_ok.addActionListener(buttonaction);
		p2_cancel.addActionListener(buttonaction);
		Send2.addActionListener(buttonaction);
		select.addActionListener(buttonaction);
		p4_ok.addActionListener(buttonaction);
		p4_cancel.addActionListener(buttonaction);
		
		try {
			pathdir=new pathmanager().getpath();//��ȡ�ļ��洢·��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���������߳�
		Server r=new Server();
		r.setPathdir(pathdir);
		Thread t=new Thread(r);
		t.start();
		
		try {
			txtpath.setText(new pathmanager().getpath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		drag();
	}
	
	
	public void drag()//�������ק����
	{
	    //panel��ʾҪ������ק�Ŀؼ�
	    new DropTarget(p1, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
	    {
	        public void drop(DropTargetDropEvent dtde)//��д��������drop����
	        {
	            try
	            {
	                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
	                List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
	                String temp="";
	                for(File file:list)
	                    temp=file.getAbsolutePath();
	                
	                if(new File(temp).isFile())
	                {
		                Client r=new Client();
						r.setFilepath(temp);
						r.setIpArr(Msg.getText().trim());
						r.setPort(9999);
						Thread t=new Thread(r);
						t.start();//��ʼ����
	                }
	                
	                dtde.dropComplete(true);//ָʾ��ק���������
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    });
	    
	    new DropTarget(p2, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
	    {
	        public void drop(DropTargetDropEvent dtde)//��д��������drop����
	        {
	            try
	            {
	                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
	                List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
	                String temp="";
	                for(File file:list)
	                    temp=file.getAbsolutePath();
	                
	                if(new File(temp).isFile())
	                {
	                	String uploadUrl = "http://115.28.101.196/AndroidUploadAction.php";
		                new HttpThread_UpLoad(uploadUrl, temp).start();
	                }
	                
	                dtde.dropComplete(true);//ָʾ��ק���������
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    });
	    
	    new DropTarget(p3, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
	    {
	        public void drop(DropTargetDropEvent dtde)//��д��������drop����
	        {
	            try
	            {
	                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
	                List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
	                String temp="";
	                for(File file:list)
	                    temp=file.getAbsolutePath();
	                
	                if(new File(temp).isFile())
	                {
	                	Client r=new Client();
						r.setFilepath(temp);
						r.setIpArr(txtIP.getText().trim());
						r.setPort(Integer.parseInt(txtPort.getText().trim()));
						Thread t=new Thread(r);
						t.start();//��ʼ����
	                }
	                
	                dtde.dropComplete(true);//ָʾ��ק���������
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	

	class ButtonAction implements ActionListener				//��������
	{
		public void actionPerformed(ActionEvent event)			//����������
		{						
			if(event.getSource() == Phone)
				card.show(cardPanel,cardName[0]);
			else if(event.getSource() == Online)
				card.show(cardPanel,cardName[1]);
			else if(event.getSource() == Custom)
				card.show(cardPanel,cardName[2]);
			else if(event.getSource() == Recent)
				card.show(cardPanel,cardName[3]);
			else if(event.getSource() == Connect)
			{
				Msg.setText("����������...");
				UdpReceive udpreceive = new UdpReceive();
				udpreceive.start();
			}
			else if(event.getSource() == Send1)
			{
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int intRetVal = fc.showOpenDialog(null);		//���ļ�������
				if (intRetVal == JFileChooser.APPROVE_OPTION)
				{
					Client r=new Client();
					r.setFilepath(fc.getSelectedFile().getPath());
					r.setIpArr(Msg.getText().trim());
					r.setPort(9999);
					Thread t=new Thread(r);
					t.start();//��ʼ����
				}
			}
/*			else if(event.getSource() == Internet)
			{
				Desktop desktop = Desktop.getDesktop();
		           try {
					desktop.browse(new URI("http://115.28.101.196/"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
*/			else if(event.getSource() == Upload)//�ϴ��ļ�
			{
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int intRetVal = fc.showOpenDialog(null);
				if (intRetVal == JFileChooser.APPROVE_OPTION)
				{
					String uploadUrl = "http://115.28.101.196/AndroidUploadAction.php";
	                new HttpThread_UpLoad(uploadUrl, fc.getSelectedFile().getPath()).start();
				}
			}
			else if(event.getSource() == Download)//�����ļ�
			{
				p2_1.setVisible(true);
				Upload.setVisible(false);
				Download.setVisible(false);
			}
			else if(event.getSource() == p2_ok)
			{
				p2_1.setVisible(false);
				Upload.setVisible(true);
				Download.setVisible(true);
				String url = "http://115.28.101.196/AndroidDownloadAction.php";
                String DownLoadNumber = Password.getText().toString().trim();
                new HttpThread_DownLoad(url, DownLoadNumber).start();//�����ļ����ص��߳�
			}
			else if(event.getSource() == p2_cancel)
			{
				p2_1.setVisible(false);
				Upload.setVisible(true);
				Download.setVisible(true);
			}
			else if(event.getSource() == Send2)
			{
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int intRetVal = fc.showOpenDialog(null);		//���ļ�������
				if (intRetVal == JFileChooser.APPROVE_OPTION)
				{
					Client r=new Client();
					r.setFilepath(fc.getSelectedFile().getPath());
					r.setIpArr(txtIP.getText().trim());
					r.setPort(Integer.parseInt(txtPort.getText().trim()));
					Thread t=new Thread(r);
					t.start();//��ʼ����
				}
			}
			else if(event.getSource() == select)
			{
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int intRetVal = fc.showOpenDialog(null);
				if (intRetVal == JFileChooser.APPROVE_OPTION)
					txtpath.setText(fc.getSelectedFile().getPath());//��ʾѡ�е�·��
			}
			else if(event.getSource() == p4_ok)
			{
				mainFrame.pathdir=txtpath.getText().trim();//���ñ���
				try {
					new pathmanager().setpath(mainFrame.pathdir);//��pathdirд�������ļ�
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Server.interrupted();//�����ж�
				Server.pathdir=mainFrame.pathdir;//�޸�pathdir
			}
			else if(event.getSource() == p4_cancel)
			{
				try {
					txtpath.setText(new pathmanager().getpath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

//·��������
class pathmanager
{
	static File f = new File("c:\\path.txt");
	static FileOutputStream out;
	static byte b[];
	public pathmanager() throws IOException
	{
		if(f.exists()==false)//����ļ�������
		{
			out = new FileOutputStream(f);
			byte b[] = mainFrame.pathdir.getBytes();
			out.write(b);
			out.close();
		}	
	}
	public void setpath(String pathdir) throws IOException//����·��
	{
		out = new FileOutputStream(f);
		byte b[] = pathdir.getBytes();
		out.write(b);
		out.close();
	}
	public String getpath() throws IOException//��ȡ·��
	{
        File file = new File("c:\\path.txt");
		FileReader reader = new FileReader(file);
        int fileLen = (int)file.length();
        char[] chars = new char[fileLen];
        reader.read(chars);
        String txt = String.valueOf(chars);
        reader.close();
        return txt;
	}
}

class MyPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color startColor;
	private Color endColor;
	  
	public MyPanel()
	{
		startColor = new Color(15,89,231);
		endColor = new Color(166, 255, 210);
	}
	  
	public void paintComponent(Graphics g)
	{	
		Color stopColor = endColor;
		Color starterColor = startColor;
		
		Color customStartColor = (Color)UIManager.get("myPanel.startColor");
		Color customEndColor = (Color)UIManager.get("myPanel.endColor");
		
		if (customEndColor != null)
		{
		    stopColor = customEndColor;
		}
		
		if (customStartColor != null)
		{
		    starterColor = customStartColor;
		}
		stopColor = MyColorUtil.lighter(stopColor, 0.05);
		
		Graphics2D g2 = (Graphics2D)g;
		
		int w = getWidth();
		int h = getHeight();
		
		GradientPaint gradient = new GradientPaint(0, 0, starterColor, w, h, stopColor, true);
		g2.setPaint(gradient);
		g2.fillRect(0, 0, w, h);
	}
}

class MyButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Dimension BUTTONSIZE = new Dimension(90,50);
	public MyButton(String title)
	{
		setPreferredSize(BUTTONSIZE);
		setBackground(null);
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setForeground(Color.WHITE);
		setFocusable(false);
		setOpaque(false);
		setText(title);
	}
}
