package pt.org.upskill.controller;

import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public interface UIable {
    void register(DTO dto) throws Exception;
    boolean save();
    List<KeyValueDTO> keyValueDTOList();
}
