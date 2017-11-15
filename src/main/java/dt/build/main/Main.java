package dt.build.main;

import dt.build.impl.rob.Mod1000;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("context.xml");
//        RobConv t1000Conv= (RobConv) context.getBean("t1000Conv");
//
//        Rob t1=t1000Conv.createRob();
//        Rob t2=t1000Conv.createRob();
//        Rob t3=t1000Conv.createRob();
//
//        System.out.println(t1);
//        System.out.println(t2);
//        System.out.println(t3);



        Mod1000 t1000= (Mod1000) context.getBean("t1000");
        t1000.action();
//        System.out.println(t1000.getHand());
//
//        t1000= (Mod1000) context.getBean("t1000");
//        System.out.println(t1000);
//        System.out.println(t1000.getHand());
//
//        t1000= (Mod1000) context.getBean("t1000");
//        System.out.println(t1000);
//        System.out.println(t1000.getHand());

    }
}
