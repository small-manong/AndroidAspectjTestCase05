import java.awt.*;
import javax.swing.*;


class inframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar jpb=new JProgressBar();//������
	JLabel value=new JLabel();//��ʾ��������ֵ��ǩ
	JLabel news=new JLabel();//��ʾ��ʾ��Ϣ��ǩ
	public inframe()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();//��ȡ��Ļ��С
		Dimension screenSize=kit.getScreenSize();
		int Height=screenSize.height;
		int Width=screenSize.width;
		
		setLocation(Width/2-250,Height/2-80);//��ܶ�λ
		setSize(500,160);
		setTitle("���ڽ���");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);//������ı��ܴ�С
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
		
		jpb.setPreferredSize(new Dimension(360,40));
		jpb.setMinimum(0);				//��������Сֵ
		jpb.setMaximum(100);			//���������ֵ
		add(jpb);
		add(value);
		add(news);
	}
}

class outframe extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar jpb=new JProgressBar();//������
	JLabel value=new JLabel();//��ʾ��������ֵ��ǩ
	JLabel news=new JLabel();//��ʾ��ʾ��Ϣ��ǩ
	public outframe()
	{
		Toolkit kit=Toolkit.getDefaultToolkit();//��ȡ��Ļ��С
		Dimension screenSize=kit.getScreenSize();
		int Height=screenSize.height;
		int Width=screenSize.width;
		
		setLocation(Width/2-250,Height/2-80);//��ܶ�λ
		setSize(500,160);
		setTitle("���ڷ���");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);//������ı��ܴ�С
		setVisible(true);
		setLayout(new FlowLayout(FlowLayout.LEFT,20,30));
		
		jpb.setPreferredSize(new Dimension(360,40));
		jpb.setMinimum(0);				//��������Сֵ
		jpb.setMaximum(100);			//���������ֵ
		add(jpb);
		add(value);
		add(news);
	}
}
