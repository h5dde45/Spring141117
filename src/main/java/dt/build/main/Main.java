package dt.build.main;

import dt.build.impl.rob.Mod1000;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("contex.xml");

        Mod1000 t1000= (Mod1000) context.getBean("t1000");
        System.out.println(t1000);

        t1000= (Mod1000) context.getBean("t1000");
        System.out.println(t1000);

    }
}
