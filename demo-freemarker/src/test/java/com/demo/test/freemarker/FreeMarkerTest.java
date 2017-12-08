package com.demo.test.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.*;

public class FreeMarkerTest {

    private Configuration configuration;

    @Before
    public void init() {
         configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(FreeMarkerTest.class,"/ftl");
    }

    /*
    *
     * @author Li Jin Min
     * @Description   输出到控制台
     * @CreateDate 2017/12/8 11:13
     * @Param []
     * @return void
      **/
    @Test
    public void test1() throws IOException, TemplateException {
        //创建配置对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        //配置模板路径
        cfg.setClassForTemplateLoading(FreeMarkerTest.class,"/ftl");
        //获取模板文件
        Template template = cfg.getTemplate("hello.ftl");
        //设置值
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "lijinming");

        template.process(map,new PrintWriter(System.out));

    }

    /*
    *
     * @author Li Jin Min
     * @Description    生成文件
     * @CreateDate 2017/12/8 11:13
     * @Param []
     * @return void
      **/
    @Test
    public void test2() throws Exception{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(FreeMarkerTest.class,"/ftl");

        Template template = cfg.getTemplate("hello.ftl");
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "lijinming");
        FileWriter fileWriter = new FileWriter("d:\\hello_2.html");
        template.process(map,fileWriter);
    }

    @Test
    public void test3() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(FreeMarkerTest.class,"/ftl");
        Template template = cfg.getTemplate("command.ftl");

        Map<String,Object> map = new HashMap<>();
        map.put("emp", new Empolyee(1,"小马",11,true,new Date(),9));
        FileWriter fileWriter = new FileWriter("f:\\command.html");
        template.process(map,fileWriter);
    }

    @Test
    public void test4() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(FreeMarkerTest.class,"/ftl");
        Template template = cfg.getTemplate("command2.ftl");
        Map<String,Object> map = new HashMap<>();
        List<Empolyee> list = new ArrayList<>();
        for(int i = 1;i<=10;i++) {
            list.add(new Empolyee(i, "小马" + i, (10 + i), i % 2 == 0 ? true : false, new Date(), (1 + i)));
        }
        map.put("empList", list);
        FileWriter fileWriter = new FileWriter("f:\\command2.html");
        template.process(map, fileWriter);
    }

    @Test
    public void test5() throws IOException, TemplateException {
        Template template = configuration.getTemplate("assign.ftl");
        FileWriter fileWriter = new FileWriter("f:\\assign.html");
        template.process(null,fileWriter);
    }
    @Test
    public void test6() throws IOException, TemplateException {
        Template template = configuration.getTemplate("test_includ.ftl");
        FileWriter fileWriter = new FileWriter("f:\\test_includ.html");
        template.process(null,fileWriter);
    }
    @Test
    public void test7() throws IOException, TemplateException {
        Template template = configuration.getTemplate("test_null.ftl");
        FileWriter fileWriter = new FileWriter("f:\\test_null.html");
        Empolyee em = new Empolyee(1, "null", 11, true, new Date(), 9);
        Map<String,Object> map = new HashMap<>();
        map.put("emp", em);
        template.process(map,fileWriter);
    }

    @Test
    public void test8() throws IOException, TemplateException {
        Template template = configuration.getTemplate("test_map&list.ftl");
        FileWriter fileWriter = new FileWriter("f:\\test_map&list.html");
        template.process(null,fileWriter);
    }
    @Test
    public void test9() throws IOException, TemplateException {
        Template template = configuration.getTemplate("test_marco.ftl");
        FileWriter fileWriter = new FileWriter("f:\\test_marco.html");
        template.process(null,fileWriter);
    }
}
