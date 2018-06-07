package com.xmduruo.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by @Author tachai
 * date 2018/6/4 13:15
 *
 * @Email 1206966083@qq.com
 */
public class AddCsvTime {

    public void addTime() {
        String path = Class.class.getClass().getResource("/").getPath();
//        System.out.println(path);
        log.println(path);
        String filePath = Class.class.getClass().getResource("/").getPath() + "/static/assets/file/right.csv";
        //String filePath=System.getProperty("user.dir")+"/src/main/resources/static/assets/file/right.csv";

        String endFilePath = Class.class.getClass().getResource("/").getPath() + "/static/assets/file/endRight.csv";
        //String endFilePath=System.getProperty("user.dir")+"/src/main/resources/static/assets/file/endRight.csv";
//        System.out.println(filePath);
//        System.out.println(endFilePath);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss:SSS");
        format.setTimeZone(TimeZone.getTimeZone("GMT+0"));//**TimeZone时区，加上这句话就解决啦**
        CsvReader csvReader = null;
        CsvWriter csvWriter = null;
        String[] stringList;
        String time;
        int temp;
        int j = 0;
        try {
            // 创建CSV读对象
            csvReader = new CsvReader(filePath);
            csvWriter = new CsvWriter(endFilePath);
//            // 读表头
//            csvReader.readHeaders();
//            //写表头
//            csvWriter.writeRecord(csvReader.getValues());
            while (csvReader.readRecord()) {
                // 读一整行
                stringList = csvReader.getValues();
                String[] stringList2 = new String[stringList.length + 1];

                stringList2 = Arrays.copyOf(stringList, stringList.length + 1);

//                stringList
                temp = Integer.parseInt(stringList[0]);

                time = format.format(temp * 100);
                stringList2[10] = time.substring(0, 10);
                csvWriter.writeRecord(stringList2);
                csvWriter.flush();
                // 读这行的某一列
                j++;

                System.out.println(stringList2[0] + "====" + j);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            getError(csvReader, csvWriter);
        }
        //修改文件名
        changeFileName(filePath, endFilePath);
    }


    public void modifyAtoB(String name, String startTime, String endTime, int a, int b) {
//windows中 target路径
//        String path = Class.class.getClass().getResource("/").getPath() + "static/assets/file/";
//        System.out.println(path);
//        服务器中文件存放的路径
        String path="/usr/java/modifyCsv_jar/file/";
        String filePath = path + name + ".csv";
        //String filePath=System.getProperty("user.dir")+"/src/main/resources/static/assets/file/right.csv";
        File file = new File(path, name + "Temp.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String endFilePath = path + name + "Temp.csv";
//        String endFilePath=Class.class.getClass().getResource("/").getPath() + "static/assets/file/endRight.csv";

        String[] stringList;
        int start = Integer.parseInt(startTime);
        int end = Integer.parseInt(endTime);
        CsvReader csvReader = null;
        CsvWriter csvWriter = null;
        try {
            // 创建CSV读对象
            csvReader = new CsvReader(filePath);
            csvWriter = new CsvWriter(endFilePath);
            while (csvReader.readRecord()) {
                // 读一整行
                stringList = csvReader.getValues();
                String[] stringList2 = new String[stringList.length + 1];
                /*for(int i=0;i<stringList.length;i++){
                    stringList2[i]=stringList[i];
                }*/
                stringList2 = Arrays.copyOf(stringList, stringList.length);
                if (Integer.parseInt(stringList[0]) >= start
                        && Integer.parseInt(stringList[0]) <= end) {
                    if (Integer.parseInt(stringList[1]) == a) {
                        System.out.println("有************" + stringList[1]);
                        stringList2[1] = b + "";
                    }
                }
                csvWriter.writeRecord(stringList2);
                csvWriter.flush();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            getError(csvReader, csvWriter);
        }
        //修改文件名
        changeFileName(filePath, endFilePath);
    }

    /**
     * 修改名字 把2改为1
     *
     * @param path1
     * @param path2
     */
    public void changeFileName(String path1, String path2) {
        //先删除目标文件
        delFile(path1);

        FileWriter fw = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path2);//读
            fw = new FileWriter(path1);//写
            char[] buf = new char[1024];//缓冲区
            int len;
            while ((len = fr.read(buf)) != -1) {
                fw.write(buf, 0, len);//读几个写几个
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        //删除临时文件
        delFile(path2);
    }

    //删除文件
    public void delFile(String path) {
        File f = new File(path);
        if (f.delete()) {
            System.out.println("删除文件" + f + "成功！");
        }
    }

    // try-catch csv文件流里面的错误
    public void getError(CsvReader csvReader,
                         CsvWriter csvWriter) {
        if (csvReader != null) {
            csvReader.close();
        }
        if (csvWriter != null) {
            try {
                csvWriter.flush();
                csvWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
