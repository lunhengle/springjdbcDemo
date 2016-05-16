package com.lhl.dao;

import com.lhl.AbstractBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lenovo on 2016/4/23.
 */
public class ImageTest extends AbstractBaseTest {
    @Autowired
    private IimageDao iimageDao;

    @Test
    public void testImage() {
        //更新国画
        int i = 1;
        String url;
        while (i <= 52) {
            url = "/subject/defalutImage/chinesepainting/";
            File file = new File("D:\\subject\\chinesepainting\\" + i + ".jpg");
            modifyWidthAndFile(file, 1, 1, url);
            i++;
        }
        //更新油画
        i = 1;
        while (i <= 92) {
            url = "/subject/defalutImage/oilpainting/";
            File file = new File("D:\\subject\\oilpainting\\" + i + ".jpg");
            modifyWidthAndFile(file, 1, 2, url);
            i++;
        }
        //更新雕塑
        i = 1;
        while (i <= 58) {
            url = "/subject/defalutImage/sculpture/";
            File file = new File("D:\\subject\\sculpture\\" + i + ".jpg");
            modifyWidthAndFile(file, 1, 3, url);
            i++;
        }
        //更新摄影
        i = 1;
        while (i <= 35) {
            url = "/subject/defalutImage/shoot/";
            File file = new File("D:\\subject\\shoot\\" + i + ".jpg");
            modifyWidthAndFile(file, 1, 4, url);
            i++;
        }
        //更新其他
        while (i <= 87) {
            url = "/subject/defalutImage/others/";
            File file = new File("D:\\subject\\others\\" + i + ".jpg");
            modifyWidthAndFile(file, 1, 99, url);
            i++;
        }
    }

    private void modifyWidthAndFile(File file, int targetType, int type, String url) {
        try {
            BufferedImage bi = ImageIO.read(file);
            System.out.println(">>>>name:" + (url + file.getName()) + ">>>>width: " + bi.getWidth() + ">>>>height; " + bi.getHeight());
            iimageDao.addDefaultImage(bi.getWidth(), bi.getHeight(), targetType, type, url + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
