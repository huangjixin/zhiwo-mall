package com.fulan.application.util.attachment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fulan.application.util.date.DateUtil;



public class FileUtil
{
    
    //static Logger log = Logger.getLogger(FileUtil.class);
    
    private FileUtil()
    {
    }
    
    public static String readFile(File file)
    {
        String line = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buf = new StringBuffer();
            
            while ((line = reader.readLine()) != null)
            {
                buf.append(line + "\n");
            }
            reader.close();
            return buf.toString();
            
        }
        catch (FileNotFoundException ex)
        {
            throw new RuntimeException("没有找到文件:" + file.getAbsolutePath());
        }
        catch (Exception ex)
        {
           /* if (log.isDebugEnabled())
            {
                log.debug(line);
            }*/
            throw new RuntimeException(ex);
        }
        
    }
    
    public static String readFile(String file)
    {
        return readFile(new File(file));
    }
    
    public static void writeFile(String content, String file)
    {
        try
        {
            File f = new File(file);
            f = f.getParentFile();
            if (!f.exists())
            {
                f.mkdirs();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.flush();
            bw.write(content);
            bw.close();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
   
    
    public static void writeW3cDocument(org.w3c.dom.Document doc, String fileName)
        throws Exception
    {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        // 设置输出的encoding为改变gb2312
        
        // transformer.setOutputProperty("encoding", "GB2312");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(fileName);
        transformer.transform(source, result);
    }
    
    public static InputStream getResource(String fileName)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
    
    /**
     * 得到WebContent的物理路径
     * 
     * @return
     * @deprecated
     * @see com.fulan.pub.util.FileUtil#getAppPhysicalPath()
     * 
     * @author Tab
     */
    public static String getWebRoot()
    {
        String clsPath = getClassRoot();
        clsPath = clsPath.substring(0, clsPath.length() - 16);
        return clsPath;
    }
    
    protected String getWebClassesPath()
    {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        return path;
    }
    
    /**
     * 得到WebContent的物理路径
     * 
     * @return
     * @throws IllegalAccessException
     * 
     * @author Tab
     */
    public static String getAppPhysicalPath()
        throws IllegalAccessException
    {
        String path = new FileUtil().getWebClassesPath();
        if (path.indexOf("WEB-INF") > 0)
        {
            path = path.substring(0, path.indexOf("WEB-INF/classes"));
        }
        else
        {
            throw new IllegalAccessException("路径获取错误");
        }
        return path;
    }
    
    /**
     * 得到Tomcat的temp目录
     * 
     * @return
     */
    public static String getTomcatTempPhysicalPath()
    {
        String binPath = System.getProperty("user.dir");// tomcat的bin目录路径
        binPath = binPath.replaceAll("\\\\", "/");
        return binPath.substring(0, binPath.lastIndexOf("/")) + "/temp";
    }
    
    public static String getSrc()
    {
        String path = FileUtil.getWebRoot();
        path = path.substring(0, path.lastIndexOf("/")) + "/JavaSource";
        return path;
    }
    
    public static String getFileExtName(String fileName)
    {
        int pos = fileName.lastIndexOf(".");
        if (pos == -1)
        {
            return "NO_EXT_NAME";
        }
        return fileName.substring(pos + 1);
    }
    
    public static String getClassRoot()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("daoConfig/sql-map-config.xml");
        String path;
        try
        {
            path = URLDecoder.decode(url.getFile(), "gbk");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
       /* if (log.isDebugEnabled())
        {
            log.debug(path);
        }*/
        path = path.substring(1);
        path = path.substring(0, path.indexOf("classes") + 7);
        return path;
    }
    
    public static void createFolder(String file)
    {
        file = file.replaceAll("\\\\", "/");
        int n = file.lastIndexOf("/");
        int m = file.lastIndexOf(".");
        if (m > n)
        {
            file = file.substring(0, n);
        }
       /* if (log.isDebugEnabled())
        {
            log.debug(file);
        }*/
        new File(file).mkdirs();
    }
    
    
    
    
    
    public static boolean moveFile(File sourceFile, File targetFile)
    {
        targetFile.getParentFile().mkdirs();
        if (targetFile.exists())
            targetFile.delete();
       /* if (log.isDebugEnabled())
        {
            log.debug(targetFile.getPath());
        }*/
        return sourceFile.renameTo(targetFile);
    }
    
    public static void copyFile(File sourceFile, File targetFile)
        throws IOException
    {
        targetFile.getParentFile().mkdirs();
        
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetFile);
        
        byte[] buf = new byte[1024];
        while (fis.read(buf) != -1)
        {
            fos.write(buf);
        }
        
        fis.close();
        fos.flush();
        fos.close();
    }
    
    public static URL generate(URL url, File file)
        throws Exception
    {
        
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String line;
        StringBuffer buf = new StringBuffer();
        while ((line = in.readLine()) != null)
        {
            buf.append("\n" + line);
        }
        in.close();
        
        FileWriter fw = new FileWriter(file);
        fw.write(buf.toString());
        fw.flush();
        fw.close();
        
        return url;
    }
    
    public static String getYearWeekFolder() {
		Date now = new Date();
		int year = DateUtil.getTimeField(now, Calendar.YEAR);
		int week = DateUtil.getTimeField(now, Calendar.WEEK_OF_YEAR);
		return year + "-" + format(week);
	}
    private static String format(int i) {
		return i < 10 ? "0" + i : "" + i;
	}
}