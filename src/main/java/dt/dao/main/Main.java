package dt.dao.main;

import dt.dao.impls.SQLiteDao;
import dt.dao.objects.Author;
import dt.dao.objects.MP3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        Author author = new Author();
        author.setId(7);
        author.setName("a7");

        MP3 mp3 = new MP3();
        mp3.setAuthor(author);
        mp3.setName("n7");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDao sqLiteDao = (SQLiteDao) context.getBean("SQLiteDao");

        System.out.println(sqLiteDao.insertMP3(mp3));
//        System.out.println(sqLiteDao.getStat());
//        System.out.println(sqLiteDao.getMP3ById(3));
//        System.out.println(sqLiteDao.getMP3ListByName("n4"));
//        System.out.println(sqLiteDao.getMP3ListByAuthor("a1"));
    }
}
