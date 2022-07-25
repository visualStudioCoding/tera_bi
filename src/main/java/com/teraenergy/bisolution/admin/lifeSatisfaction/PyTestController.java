package com.teraenergy.bisolution.lifeSatisfaction;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
//import org.python.core.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.servlet.http.HttpServletResponse;

//import org.python.util.PythonInterpreter;

@Controller
public class PyTestController {
    private static final String PROGRAM_ID = "LifeSatisfaction";
    private static final String DIRECTORY = "lifeSatisfaction/";
    private ResourceLoader resourceLoader;

    @RequestMapping(value="/test1.do")
    public String test1(Model model) {
        System.out.println("자이썬테스트11");
        try {
            System.setProperty("python.cachedir.skip", "true");
            System.setProperty("python.import.site", "false");

            String path = System.getProperty("user.dir");
            System.out.println("dd : " + path);

            //File file = new File("./src/main/resources/java1.py");
            //이거 됨
            File file = new File("C:\\Users\\tera\\IdeaProjects\\tera_bi\\build\\libs\\bi-solution-0.1.1\\BOOT-INF\\classes\\java1.py");
            if(file.exists()) {
                System.out.println("111111111111 : " + file.getAbsolutePath());
            } else {
                System.out.println("0000000000000");
            }
            //PythonInterpreter interpreter = new PythonInterpreter();
            /*String filePath = "C:\\Users\\tera\\IdeaProjects\\tera_bi\\build\\libs\\bi-solution-0.1.1\\BOOT-INF\\classes\\java1.py";
            //ClassPathResource classPathResource = new ClassPathResource(filePath);
            InputStream instream = new ClassPathResource(filePath).getInputStream();
            System.out.println("dd  : " + instream.available());
            //interpreter.execfile(instream);
*/
            String filePath = "C:\\Users\\tera\\IdeaProjects\\tera_bi\\build\\libs\\bi-solution-0.1.1\\BOOT-INF\\classes\\java1.py";
            String filePath2 = "bi-solution-0.1.1\\BOOT-INF\\classes\\java1.py";

            InputStream in =  this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(filePath);

            //System.out.println("dd : " + resourceLoader.getResource("classpath:bi-solution-0.1.1\\BOOT-INF\\classes\\java1.py").getInputStream().available());

// InputStream 을 이용해 File 생성 후 사용하는 방법
            //File somethingFile = File.createTempFile("test", ".txt");
            //System.out.println("dd: " + inputstream.get());
           /* try {
                FileUtils.copyInputStreamToFile(inputstream, somethingFile);
            } finally {
                IOUtils.closeQuietly(inputstream);
            }*/

            //interpreter.execfile(String.valueOf(inpu));
            //interpreter.execfile("C:\\apache-tomcat-9.0.56\\webapps\\src\\main\\resources\\java1.py");
            //interpreter.execfile("C:\\eGovFrameDev-4.0.0-64bit\\workspace\\testpy\\java1.py");  //이거됨

            //System.out.println(resourceLoader.getResource("classpath:java1.py"));

            //System.out.println("dd : " + resourceLoader.getResource("classpath:java1.py").getInputStream().available());
            // String filepath = "jar://C:\\Users\\tera\\IdeaProjects\\tera_bi\\src\\main\\resources\\java1.py";
            //InputStream instream = new ClassPathResource(filepath).getInputStream();
            /*
            //jar
            InputStream is = PyTestController.class.getClassLoader().getResourceAsStream("src\\main\\resources\\java1.py");
            //String filePath = "\\templates\\java1.py";
            //ClassPathResource classPathResource = new ClassPathResource(filePath);
            //interpreter.execfile(String.valueOf(new InputStreamReader(classPathResource.getInputStream(),"UTF-8")));
            interpreter.execfile(String.valueOf(new InputStreamReader(is,"UTF-8")));
            //interpreter.execfile("java1.py");  //
            //interpreter.execfile("src/main/reousrces/java1.py");
            //interpreter.execfile("java1.py");
            interpreter.exec("testFunc(1,2)");

            //리턴값 받아오기
            PyFunction pyfunction = (PyFunction)interpreter.get("testFunc",PyFunction.class);
            int a = 20;
            int b = 20;

            PyObject pyobj = pyfunction.__call__(new PyInteger(a), new PyInteger(b));
            int re = Integer.parseInt(pyobj.toString());
            System.out.println(pyobj.toString());
            System.out.println("re : " + re);
 */
            model.addAttribute("test", "1");


        } catch(Exception e) {
            e.printStackTrace();
        }

        //return "main/test/test";
        return DIRECTORY + "pytest";
    }
/*
    @RequestMapping(value="/test2.do")
    public String test2(HttpServletResponse response,Model model) {
        System.out.println("자이썬테스트2");  //test.py
        try {
            System.setProperty("python.cachedir.skip", "true");
            System.setProperty("python.import.site", "false");

            //그냥 실행
            PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.execfile("C:/eGovFrameDev-4.0.0-64bit/workspace/testpy/test.py");

            System.out.println("====================");

            interpreter.execfile("C:/eGovFrameDev-4.0.0-64bit/workspace/testpy/java1.py");
            interpreter.exec("testFunc(1,2)");

            System.out.println("====================");

            //리턴값 받아오기
            PyFunction pyfunction = (PyFunction)interpreter.get("testFunc",PyFunction.class);
            int a = 20;
            int b = 20;

            PyObject pyobj = pyfunction.__call__(new PyInteger(a), new PyInteger(b));
            //int re = Integer.parseInt(pyobj.toString());
            System.out.println(pyobj.toString());
            //System.out.println("re : " + re);

            model.addAttribute("test", pyobj.toString());

        } catch(Exception e) {
            e.printStackTrace();
        }

        return DIRECTORY + "pytest";
    }
*/

    @RequestMapping(value="/test3.do")
    public String test3() {
        System.out.println("자바파이썬테스트3");
        try {
            //그냥 실행
//		ProcessBuilder pb = new ProcessBuilder("python","C:\\eGovFrameDev-4.0.0-64bit\\workspace\\testpy\\test.py");
            //ProcessBuilder pb = new ProcessBuilder("python","C:\\eGovFrameDev-4.0.0-64bit\\workspace\\testpy\\java1.py");
            ProcessBuilder pb = new ProcessBuilder("python","/home/terabi/py/java1.py");
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "utf-8"));


            String line = "";
            //함수호출이(return)이 아니고 파이썬 함수에서 print된걸 받아오는것이다
            while((line = br.readLine()) != null) {
                System.out.println("dd : " + line);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return "main/test/test";
    }
}
