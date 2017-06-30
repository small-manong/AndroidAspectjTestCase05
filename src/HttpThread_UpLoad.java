import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpThread_UpLoad extends Thread {

    public String uploadUrl;
    public String path;
    public static String result;

    //����HttpThread_Upload�Ĺ��췽��
    public HttpThread_UpLoad(String uploadUrl, String path) {
        this.uploadUrl = uploadUrl;
        this.path = path;
    }

    /**
     * �ϴ������ļ��ķ���myuploadFile()
     */

    public void myuploadFile() {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "******";
        try {
            /**��ʾ�����ϴ�������*/
        	mainFrame.Msg2.setText("�����ϴ�... ...���Ե�");

            URL url = new URL(uploadUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // ����ÿ�δ��������С��������Ч��ֹ�ֻ���Ϊ�ڴ治�����
            // �˷���������Ԥ�Ȳ�֪�����ݳ���ʱ����û�н����ڲ������ HTTP �������ĵ�����
            //httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
            // �������������
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            // ʹ��POST����
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + end);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\"; filename=\""
                    + path.substring(path.lastIndexOf("/") + 1)
                    + "\""
                    + end);
            dos.writeBytes(end);
            FileInputStream fis = new FileInputStream(path);
            System.out.println("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + path);
            byte[] buffer = new byte[8192]; // ��������СΪ8k
            int count = 0;
            // ��ȡ�ļ�
            while ((count = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, count);
            }
            fis.close();
            dos.writeBytes(end);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
            dos.flush();
            InputStream is = httpURLConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            result = br.readLine();

            System.out.println("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" + result);
            //Toast.makeText(getClass(), result, Toast.LENGTH_LONG).show();
            //����Ӧ������ʾ����ʾ����Ϊ�ַ���result
            //MainActivity.handler.sendEmptyMessage(0);//������Ϣ��handler��֪ͨ�ϴ�����
            dos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainFrame.Msg2.setText(HttpThread_UpLoad.result);//����UI����
    }


    @Override
    public void run() {
        myuploadFile();
        super.run();
    }
}