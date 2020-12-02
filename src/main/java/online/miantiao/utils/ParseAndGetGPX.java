package online.miantiao.utils;

import online.miantiao.bean.Point;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: miantiao
 * @date: 2020/12/1
 */
public class ParseAndGetGPX {

    public static void readTXT(String filePath) {
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null;
                List<Point> pointList = new ArrayList<Point>();
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
                //添加首个gps点
                if((lineTxt = br.readLine()) != null){
                    Point point = new Point();
                    String[] temp = lineTxt.trim().split(",");
                    point.setLatitude(temp[0].trim());
                    point.setLongitude(temp[1].trim());
                    point.setTime(sdf.parse(temp[2]));
                    pointList.add(point);
                }
                int count  = 1;
                //添加后续gps点，并且去除重复的点
                while ((lineTxt = br.readLine()) != null) {
                    Point point = new Point();
                    String[] temp = lineTxt.trim().split(",");
                    point.setLatitude(temp[0].trim());
                    point.setLongitude(temp[1].trim());
                    point.setTime(sdf.parse(temp[2]));
                    if(!pointList.get(pointList.size()-1).equals(point)){
                        pointList.add(point);
                        System.out.println("Parsing "+(count++)+"th "+point.toString());
                    }
                }
                writeGPX(pointList, filePath);
                br.close();
            } else {
                System.out.println("The TXT file does not exist!");
            }
        } catch (Exception e) {
            System.out.println("File read error!");
            System.out.println(e.getMessage());
        }
    }

    public static void writeGPX(List<Point> pointList, String filePath){
        try {
            //创建document对象
            Document document = DocumentHelper.createDocument();

            //1、创建根节点gpx
            Element gpx = document.addElement("gpx");
            //向gpx节点添加属性
            gpx.addAttribute("xmlns", "http://www.topografix.com/GPX/1/1");
            gpx.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            gpx.addAttribute("xmlns:gh", "https://graphhopper.com/public/schema/gpx/1.1");

            //1.1、创建metadata节点
            Element metadata = gpx.addElement("metadata");
            //1.1.1、创建metadata子节点copyright
            Element copyright = metadata.addElement("copyright");
            copyright.addAttribute("author", "OpenStreetMap contributors");
            //1.1.2、创建metadata子节点link
            Element link = metadata.addElement("link");
            link.addAttribute("href", "http://graphhopper.com");
            //1.1.3、创建metadata子节点link
            Element time = metadata.addElement("time");
            time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            //1.2、创建trk节点
            Element trk = gpx.addElement("trk");
            //1.2.1 创建trk的子节点name
            Element name = trk.addElement("name");
            name.setText("taxi track");
            //1.2.2 创建trk的子节点trkseg
            Element trkseg = trk.addElement("trkseg");
            //1.2.2.1 创建trkseg的子节点trkpt
            for(Point point : pointList){
                Element trkpt = trkseg.addElement("trkpt");
                trkpt.addAttribute("lat", point.getLatitude());
                trkpt.addAttribute("lon", point.getLongitude());
                Element trkptTime = trkpt.addElement("time");
                trkptTime.setText(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(point.getTime()));
            }

            //设置生成gpx的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");

            //生成gpx文件
            File file = new File(filePath.replaceFirst(".txt", ".gpx"));
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("The GPX file was generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to generate the GPX file!");
        }

    }
}

