package gymSystem.test;/*2021/4/17*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.TimeSlot;
import gymSystem.entity.TimeTable;
import gymSystem.entity.Trainer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * A set of test
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class TestSet1 {

    @ParameterizedTest
    @MethodSource
    public void testCheckInput(String name, String rule) {
        assertTrue(name.matches(rule));
    }


    public boolean checkInput(String name, String rule) {
        if (name.matches(rule)) {
            return true;
        } else {
            return false;
        }
    }

    static List<Arguments> testCheckInput() {
        "ss".matches("[A-Z][a-z]{1,20}\\s[A-Z][a-z]{1,20}");
        return List.of( // arguments:
                Arguments.arguments("Asc@qwe.as.sc", "[0-9a-zA-Z]{1,20}@[0-9a-zA-Z.]{6,20}") //
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"../userInterface/membershiptest.fxml"})
    public void replaceSceneContent(String fxml) throws Exception {

        //  private ViewAlter viewAlter;
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
//        assertTrue(loader.load(inputStream));
        try {
            Parent page = loader.load(inputStream);

        } catch (Exception e) {

        } finally {
            inputStream.close();
        }
    }

    @Test
    public void testTimeTable() {
        Map<String, Object> stringObjectMap = FileController.parseFile(getClass().getResource("../../files/timeTable.json").getPath());
        //  JSONObject timeTable = (JSONObject) FileController.parseFile(".\\src\\gymSystem\\gymSystem.files\\testTimeTable.json");
        assert stringObjectMap != null;
        JSONArray timeTableList = (JSONArray) stringObjectMap.get("002");
        if (timeTableList == null) {
            System.out.println("holy moly ");
        }
        List<TimeTable> timeTableList1 = JSONArray.parseArray(timeTableList.toJSONString(), TimeTable.class);
        //    TimeTable timeTable001 = JSON.toJavaObject((JSON) o, TimeTable.class);
        TimeTable timeTable1 = timeTableList1.get(0);
        List<TimeSlot> timeSlotList = timeTable1.getTimeSlotList();
        timeSlotList.get(1).getDuration();

    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    public void loadTrainerInfo(int trainerSeq) {
        Map<String, Object> trainerMap = FileController.parseFile(getClass().getResource("../../files/trainer.json").getPath());
        //assert form != null;
        //    System.out.println(form.get(userName).getClass().toString());

        Trainer trainer = null;
        for (Map.Entry<String, Object> entry : trainerMap.entrySet()) {
            trainer = JSONObject.toJavaObject((JSON) entry.getValue(), Trainer.class);
            if (trainer.getSeqNum() == trainerSeq) {
                System.out.println("!!!!!");
                break;
            }
        }
        List<TimeTable> timeTableList = trainer.getTimeTableList();
        System.out.println(timeTableList.get(1));

//        List<TimeTable> timeTableList1 = JSONArray.parseArray(timeTableList.toJSON, );
//        TimeTable timeTable = JSONObject.toJavaObject(timeTableList.get(1),TimeTable.class);
        System.out.println(timeTableList.getClass().toString());
        TimeTable timeTable = timeTableList.get(1);
    }


    @Test
    public void writeTrainerInfo() {
        Trainer trainer = new Trainer("1", "1", "1", "1", "1", "1", "1", new ArrayList<TimeTable>(), 1);
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/trainer.json").getPath());
        System.out.println(form.getClass().toString());
        //    if (form != null) {
        form.put("22", trainer);// TODO 改！！！！！
        //    }
        //    else {
        //       form = new HashMap<>();
        //   }
        FileController.updateFile(getClass().getResource("../../files/trainer.json").getPath(), form);

    }

}
