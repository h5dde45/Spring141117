package dt.dao.main;

import dt.dao.impls.SQLiteDao;
import dt.dao.objects.MP3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MP3 mp31 = new MP3();
        mp31.setName("n5");
        mp31.setAuthor("a5");
        MP3 mp32 = new MP3();
        mp32.setName("n6");
        mp32.setAuthor("a6");
        List<MP3> mp3List = new ArrayList<>();
        mp3List.add(mp31);
        mp3List.add(mp32);
        MP3 mp33 = new MP3();
        mp33.setId(8);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDao sqLiteDao = (SQLiteDao) context.getBean("SQLiteDao");

//        System.out.println(sqLiteDao.insert(mp31));
//        sqLiteDao.insert(mp3List);
//        sqLiteDao.delete(7);
//        sqLiteDao.delete(mp33);
//        System.out.println(sqLiteDao.getMP3ById(2));
//        System.out.println(sqLiteDao.getMP3ListByName("n2"));
//        System.out.println(sqLiteDao.getMP3ListByAuthor("a3"));
//        System.out.println(sqLiteDao.getMP3Count());
        System.out.println(sqLiteDao.getStat());

    }
}
