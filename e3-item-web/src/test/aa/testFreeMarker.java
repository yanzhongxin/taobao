package aa;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author www.yanzhongxin.com
 * @date 2019/1/9 18:53
 */
public class testFreeMarker {
    public static void main(String[] args)  throws  Exception{
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("D:/Program Files/Maven/apache-maven-3.5.4-bin/repository/repository/cn/yanzhongxin/e3-mall/e3-item-web/src/main/webapp/WEB-INF/ftl"));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("hello.ftl");
        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        Map dataModel = new HashMap<>();
        //向数据集中添加数据
        dataModel.put("hello", "this is my first freemarker test.");
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        Writer out = new FileWriter(new File("D:/下载视频/编程/jEE/就业/【阶段14】宜立方商城综合项目/freemarker/hello.txt"));
        // 第七步：调用模板对象的process方法输出文件。
        template.process(dataModel, out);
        // 第八步：关闭流。
        out.close();
    }


}

