import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpThread_DownLoad extends Thread
{

    String url;

    public static String DownLoadNumber;

    public static String echoFromPHP;

    //����HttpThread_DownLoad�Ĺ��췽��
    public HttpThread_DownLoad(String url, String DownLoadNumber) {
        this.url = url;
        HttpThread_DownLoad.DownLoadNumber = DownLoadNumber;
    }

    /**
     * ������Get��ʽ�ϴ����������Ե����ο�
     */
   /* private void doGet(){
        url = url+"?DownLoadNumber="+DownLoadNumber;
        try {
            URL httpUrl = new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str;
                StringBuffer sb = new StringBuffer();
                while ((str=reader.readLine())!=null){
                    sb.append(str);
                }
                System.out.print("resualt:"+sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * POST��ʽ����������Ͳ������ŵ��ǿ���������Ҫ���͵Ĳ�����ͬʱ���Է��ʹ����ݣ�get��ʽֻ�ܷ��ͼ�K�������ҷ��͵������ǲ������ص�
     */

    private void doPost() {
        try {

            URL httpUrl = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            OutputStream out = conn.getOutputStream();
            String content = "DownLoadNumber=" + DownLoadNumber;
            out.write(content.getBytes());


            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;

            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            echoFromPHP = sb.toString();
            //��logcat������ֵ���Լ����֤
            System.out.println("PHP���������ص��ַ�����ϢΪ:" + echoFromPHP);
            /**************�������ļ����صķ���**************/

            if (echoFromPHP.equals("This_downloadnumber_is_null")) {
            	mainFrame.Msg2.setText("û�������ļ���ȡ�룡");

            } else if (echoFromPHP.equals("This_downloadnumber_is_not_valid")) {
            	mainFrame.Msg2.setText("���ļ���ȡ����Ч��");

            } else if (true) {
                //Toast.makeText(getApplicationContext(), filemd5name+"�ļ����ں�̨���ء�����", Toast.LENGTH_LONG).show();
                //�������ذ�ť��������ʾ�û���������
                //mFileDownLoad.setText("�������أ��벻Ҫ�˳�������");
                //��ȡ�ַ���ResultFromPHP��ֻ�����ļ���
                String filemd5name = echoFromPHP.substring(34);//���ز��ԵĻ������ֵ�ǲ�ͬ�ģ���ȡ�ַ�����һ��

                File file = new File(mainFrame.pathdir+"/");
                //���Ŀ���ļ��Ѿ����ڣ���ɾ�����������Ǿ��ļ���Ч��
                if (!file.exists()) {
                    file.mkdir();//delete();
                }
                try {
                    /**��ʾ�������ء�����*/
                	mainFrame.Msg2.setText("��������... ...���Ե�");
                    // ����URL
                    //URL url = new URL("http://192.168.1.147/"+ResultFromPHP);
                    URL url = new URL(/*"http://115.28.101.196/FilesUpload/"+*/echoFromPHP);
                    //URL url = new URL("http://115.28.101.196/FilesUpload/63561648acbaf320b9d7b923e37ab8e1.jpg");
                    //Toast.makeText(getApplicationContext(), url.toString(), Toast.LENGTH_LONG).show();
                    // ������
                    URLConnection con = url.openConnection();
                    //����ļ��ĳ���
                    int contentLength = con.getContentLength();
                    System.out.println("���� :" + contentLength);
                    // ������
                    InputStream is = con.getInputStream();
                    // 1K�����ݻ���
                    byte[] bs = new byte[1024];
                    // ��ȡ�������ݳ���
                    int len;
                    // ������ļ���
                    /**********����д��Ĭ�ϴ洢��app�ڲ���data�ļ������棬��д��ʧ�ܣ�Ӧ��ָ��д���λ��*********/
                    //������仰��ʾ�ļ��洢λ��ΪEnvironment.getExternalStorageDirectory().getPath()+"/LingDong"+".jpg"
                    //���ļ�·���Լ��ļ���Ϊ  ������ʾ   sdcard/lingDong.jpg
                    System.out.println(mainFrame.pathdir.trim() + "\\" + filemd5name);
                    OutputStream os = new FileOutputStream(mainFrame.pathdir.trim() + "\\" + filemd5name);
                    // ��ʼ��ȡ
                    while ((len = is.read(bs)) != -1) {
                        os.write(bs, 0, len);
                    }
                    // ��ϣ��ر���������
                    os.close();
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainFrame.Msg2.setText("���" + HttpThread_DownLoad.DownLoadNumber + "�ļ�������ɣ�·��Ϊ����Ŀ¼");
            }
            /*********************************************/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    @Override
    public void run() {
        //doGet();
        doPost();

        super.run();
    }
}
