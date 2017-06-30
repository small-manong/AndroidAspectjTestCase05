import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.net.InetSocketAddress;
import java.net.ServerSocket;  
import java.net.Socket;
  
  
/** 
 * �����ļ����� 
 * @author admin_Hzw 
 * 
 */  
class Server extends Thread
{
    static public String pathdir;
    public void setPathdir(String s)//�����ļ�·������
    {
    	pathdir=s;
    }
    public void run()//run����
    {
		ServerSocket server = null;
		try
		{
			server = new ServerSocket(9999);
	        while (!Thread.currentThread().isInterrupted())//����߳�û���ж�
	        {
	            Socket socket = server.accept();//�ȴ����ӣ�һֱ����
	            byte[] inputByte = null;//���������ֽ�����
	            int length = 0;//���峤��
	            double sumL=0;//����������
	            long l;//�ļ�����
	            DataInputStream dis = null;  
	            FileOutputStream fos = null;  
	            String filePath;
	            try
	            {  
	                dis = new DataInputStream(socket.getInputStream());
	                File f = new File(pathdir);//�½��ļ�f
	                if(!f.exists())
	                {  
	                    f.mkdir();
	                }  
	                filePath=pathdir+"/"+dis.readUTF();//�õ��ļ�·��
	                l=dis.readLong();//��ȡ�ļ���С
	                fos = new FileOutputStream(new File(filePath));//�ļ������
	                inputByte = new byte[1024];
	                inframe in = new inframe();
	                in.news.setText("���ڽ����ļ�...   "+filePath);//��������ʾ��
	                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0)
	                { 
	                	sumL += length;
	                	in.value.setText("�Ѵ��䣺"+(int)((sumL/(l*1024))*100)+"%");
	                	in.jpb.setValue((int)((sumL/(l*1024))*100)+1);
	                    fos.write(inputByte, 0, length);  
	                    fos.flush();
	                }  
	                in.dispose();//�رս�������
	            }
	            finally//�ر�����socket
	            {  
	                if (fos != null)  
	                    fos.close();  
	                if (dis != null)  
	                    dis.close();  
	                if (socket != null)  
	                    socket.close();   
	            }
	        }
		}
        catch (Exception e)
        {  
        	Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}  


/** 
 * �ļ����Ϳͻ��������� 
 * @author admin_Hzw 
 * 
 */  
class Client implements Runnable
{
	private String filePath,ipArr;//�����ļ�·����ip
	private int port;
	public void setFilepath(String s)
	{
		this.filePath=s;
	}
	public void setIpArr(String s)
	{
		this.ipArr=s;
	}
	public void setPort(int p)
	{
		this.port = p;
	}
	public void run()
	{
		try
		{
			int length = 0;  
			double sumL = 0 ;  
	        byte[] sendBytes = null;  
	        Socket socket = null;  
	        DataOutputStream dos = null;  
	        FileInputStream fis = null;  
	        outframe out=new outframe();//��������ʾ
	        try
	        {  
	            File file = new File(filePath); //Ҫ������ļ�·��  
	            long l = file.length();   
	            socket = new Socket();    
	            socket.connect(new InetSocketAddress(ipArr,port));//192.168.191.2
	            dos = new DataOutputStream(socket.getOutputStream());  
	            fis = new FileInputStream(file);        
	            sendBytes = new byte[1024];
	            dos.writeUTF(file.getName());//�����ļ���
	            dos.flush();
	            dos.writeLong((long)file.length()/1024+1);
	            dos.flush();
	            
	            out.news.setText("���ڷ����ļ�...   "+filePath); 
	            while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0)
	            {  
	                sumL += length;    
	                out.value.setText("�Ѵ��䣺"+(int)((sumL/l)*100)+"%");
	                out.jpb.setValue((int)((sumL/l)*100));
	                dos.write(sendBytes, 0, length);
	                dos.flush();  
	            } 
	            out.dispose();
	        }
	        catch (Exception e)
	        {  
	        	out.news.setText("�ͻ����ļ������쳣");  
	            e.printStackTrace();
	        }
	        finally
	        {    
	            if (dos != null)
	            	dos.close();
	            if (fis != null)
					fis.close(); 
	            if (socket != null)
						socket.close();
	        }
        }
        catch (Exception e)
        {
        	Thread.currentThread().interrupt();
            e.printStackTrace();
        }
	}
}