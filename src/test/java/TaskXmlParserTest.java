/**
 * 宝龙电商
 * PACKAGE_NAME
 * TaskXmlParser.java
 * <p/>
 * 2016-02-19
 * 2016宝龙公司-版权所有
 */

import com.plocc.framework.support.DataHelper;
import com.plocc.framework.support.FileHelper;
import com.plocc.framework.support.XpathHelper;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * TaskXmlParser
 *
 * @author Zhanggj
 * @version 1.0.0
 * @time 13:00
 * @email zhanggj@powerlong.com
 * @description 职责描述
 */
public class TaskXmlParserTest {
    private Document document = null;

    @Before
    public void before() {
        document = XpathHelper.parseDocumentByString(FileHelper.readStream(this.getClass().getResourceAsStream("/xml/tasks.xml")));
    }

    /**
     * Xml 解析
     */
    @Test
    public void credentialsParser() {
        String interval = XpathHelper.xpath("//credentials/@interval", document);
        String url = XpathHelper.xpath("//credentials/@url", document);
        String initial = XpathHelper.xpath("//credentials/@initial", document);
        System.out.println();
        System.out.println("interval:" + interval + "\turl:" + url + "\tinitial:" + initial);
        String count = XpathHelper.xpath("count(//credential)", document);
        for (int i = 1; i < DataHelper.getInt(count) + 1; i++) {
            String title = XpathHelper.xpath("//credential[" + i + "]/@title", document);
            String id = XpathHelper.xpath("//credential[" + i + "]/@id", document);
            String secret = XpathHelper.xpath("//credential[" + i + "]/@secret", document);
            String refresh_token = XpathHelper.xpath("//credential[" + i + "]/@refresh_token", document);
            String is_master = XpathHelper.xpath("//credential[" + i + "]/@is_master", document);
            System.out.println("title:" + title + "\tid:" + id + "\tsecret:" + secret + "\trefresh_token:" + refresh_token + "\tis_master:" + is_master);
        }
    }
}
