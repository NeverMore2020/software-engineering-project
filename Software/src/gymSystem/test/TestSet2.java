package gymSystem.test;/*2021/6/1*/

import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.Person;
import gymSystem.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class TestSet2 {
    FileController test=new FileController();
    User usr = new User();

    @Test
    void testParseFile() {
        test.parseFile("/gymSystem/files/timeTable.json");
    }

    @Test
    void testUpdateFile() {
        Map<String,Object> map=new HashMap<String,Object>();
        test.updateFile("/gymSystem/files/timeTable.json", map);
    }

    @Test
    void testGetInfo() {
        test.getInfo("/gymSystem/files/user_information.json","asd");
    }

    @Test
    void testWriteFile() {
        test.writeFile("/gymSystem/files/user_information.json", usr);
    }

    @Test
    void testGetVideoInfo() {
        test.getVideoInfo("./src/gymSystem/gymSystem.files/video_information.json","123");
    }
    Person testPerson = new Person("213","231","188103092323","premier","123@qq.com","12","19920310","23","1");

    @Test
    void isNickName() {
        assertEquals(testPerson.getUserName(),"213");
    }

    @Test
    void isPassWord() {
        assertEquals(testPerson.getPassWord(),"231");

    }

    @Test
    void isTelephone() {
        assertEquals(testPerson.getTel(),"188103092323");
    }

    @Test
    void isMembership() {
        assertEquals(testPerson.getMembership(),"premier");
    }

    @Test
    void isEmail() {
        assertEquals(testPerson.getEmail(),"123@qq.com");
    }

    @Test
    void isExpireDate() {
        assertEquals(testPerson.getExpireDate(),"12");
    }
    @Test
    void isBirthday() {
        assertEquals(testPerson.getBirthday(),"19920310");
    }

    @Test
    void isBalance() {
        assertEquals(testPerson.getBalance(),"23");
    }

    @Test
    void isFirstTime() {
        assertEquals(testPerson.getFirstTime(),"1");
    }


}
