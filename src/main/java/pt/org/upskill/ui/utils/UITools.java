package pt.org.upskill.ui.utils;

import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public class UITools {
    public static void showKeyValueList(String caption, List<KeyValueDTO> dtoList) {
        System.out.println(caption);
        for (KeyValueDTO item : dtoList) {
            System.out.println(item.key + " - " + item.value);
        }
    }
}
