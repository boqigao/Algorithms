package cn.boqi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 读取文件并输出名字到csv
 */
public class test {
    public static void main(String[] args) throws IOException {
        ArrayList<String> fileNames = getFiles("D:\\chrome download\\【東リ】豊田自動織機分_画像データ\\亂搶儕亃朙揷帺摦怐婡暘_夋憸僨乕僞\\僲儞儚僢僋僗儕儏乕儉NW_1mm=1px_200dpi_儕僺乕僩僨乕僞偱偼偁傝傑偣傫");
        File writeName = new File("D:\\boqi_dropbox\\Dropbox\\tmp\\result.csv");
        FileWriter writer = new FileWriter(writeName, true);

        for (int i = 0; i < fileNames.size(); i++) {
            File picture = new File(fileNames.get(i));

            System.out.println(fileNames.get(i));
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
            System.out.println(String.format("%.1f", picture.length() / 1024.0));// 源图大小
            System.out.println(sourceImg.getWidth()); // 源图宽度
            System.out.println(sourceImg.getHeight()); // 源图高度
            StringBuilder sb = new StringBuilder();
            sb.append("" + fileNames.get(i) + "," + sourceImg.getWidth() + "x" + sourceImg.getHeight());
            writeName.createNewFile();
            writer.write(sb.toString());
            writer.write("\n");

        }
        writer.close();
    }

    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                //System.out.println("文     件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                //System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;

    }
}
