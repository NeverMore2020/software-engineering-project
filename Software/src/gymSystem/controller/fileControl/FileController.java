package gymSystem.controller.fileControl;/*2021/4/6*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import gymSystem.entity.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class is used to read Json file converting to Java Object and write Json File and deal with other issues related to file operation
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 1.0.0
 */
public class FileController {
    public static String getDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static void updateTimeTable(){
        // for user
        Map<String, Object> usersInfo = FileController.parseFile(FileController.class.getResource("../../files/userInformation.json").getPath());
        if(usersInfo != null) {
            for (Map.Entry<String, Object> entry : usersInfo.entrySet()) {
                User user = JSONObject.parseObject(entry.getValue().toString(), User.class);

                List<BookInfo> oldBookInfoList = user.getBookInfoList();
                oldBookInfoList.removeIf(bookInfo -> Integer.parseInt(bookInfo.getDate().replaceAll("-", "")) < Integer.parseInt(getDate().replaceAll("-", "")));
                usersInfo.put(entry.getKey(), user);
            }
            FileController.updateFile(FileController.class.getResource("../../files/userInformation.json").getPath(), usersInfo);
        }

    }



    /**
     * Read the json file
     * @param path the json file path
     * @return Map<String, Object> , the Map data structure of the stored data in JSON file
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public static Map<String, Object> parseFile(String path) {
        try {
            Gson gson = new Gson();
            File file = new File(path);
            if (file.isFile() && file.exists() && file.canRead()) {
                //    String encoding = "GBK";
                InputStreamReader in;
                in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(in);
                String lineTxt = "";
                StringBuilder sb = new StringBuilder(lineTxt);
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.trim().equals("")) {
                        sb.append(lineTxt);
                    }
                }
                lineTxt = sb.toString();
                in.close();
                //    return gson.fromJson();
                return (Map<String, Object>) JSONObject.parse(lineTxt);
            }else {
                return null;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Wrte an Java Object into JSON file
     * @param path the defined path to be written
     * @param newContent the Java Object to be written
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public static void updateFile(String path, Map<String, Object> newContent) {
        try {

            File file = new File(path);
            if (file.isFile() && file.canRead() && file.exists()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                String jsonString = new Gson().toJson(newContent);
                bw.write(jsonString);
                bw.newLine();
                bw.close();
            }else {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                bw.write(JSON.toJSONString(newContent));
                bw.newLine();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get an entity User by user ID
     *
     * @param path, String userID
     * @return User
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */

    public static User getInfo(String path, String userID){
        Map<String, Object> form = FileController.parseFile(path);
        JSONObject userJson = (JSONObject) form.get(userID);
        User user = JSONObject.toJavaObject(userJson, User.class);
        return user;
    }


    /**
     * This method is used to change user information by given user ID
     *
     * @param path, User u1
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public static void writeFile(String path, User u1){
        Gson gson = new Gson();
        Map<String, Object> form = parseFile(path);
        form.put(u1.getUserID(),u1);
        updateFile(".\\src\\gymSystem\\gymSystem.files\\user_information.json",form);
    }

    /**
     * This method is used to get an entity Video by video ID
     *
     * @param path, String videoID
     * @return Video
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public static Video getVideoInfo(String path, String videoID){
        Map<String, Object> form = FileController.parseFile(path);
        //assert form != null;
        JSONObject videoJson = (JSONObject) form.get(videoID);
        Video video = JSONObject.toJavaObject(videoJson, Video.class);

        return video;
    }

    /**
     * This method is used to change video information by given video ID
     *
     * @param  path, u1
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public static void writeVideoFile(String path, Video v1){
        Gson gson = new Gson();
        Map<String, Object> form = parseFile(path);
        form.put(v1.getVideoID(),v1);
        updateFile(FileController.class.getResource("../../files/video_information.json").getPath(),form);
    }


}
