package Otomat;

import Music.CommonFileUtil;
import com.google.gson.Gson;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriterReaderHelper {//json
    public static String OTOMAT_CONFIG_FOLDER_PATH = CommonFileUtil.getOtomatAppConfigFolder();

    static public boolean writeToFile(ConfigModel model) {
        String jsonStr = new Gson().toJson(model);
        try (BufferedWriter write = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(OTOMAT_CONFIG_FOLDER_PATH + "config.json"), StandardCharsets.UTF_8))) {
            write.write(jsonStr, 0, jsonStr.length());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static public ConfigModel readFromFile() {
        try (
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(OTOMAT_CONFIG_FOLDER_PATH +"config.json"),StandardCharsets.UTF_8))
        ){
            return new Gson().fromJson(reader,ConfigModel.class);
        }catch (Exception ex){
            return new ConfigModel();
        }
    }
}
