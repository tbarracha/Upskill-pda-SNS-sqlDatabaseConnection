package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineTechDTO;

import java.util.ArrayList;
import java.util.List;

public class VaccineTechRepository implements PersistableRepo {

    public VaccineTechRepository() {}

    private List<VaccineTech> vaccineTechList = new ArrayList<VaccineTech>();

    public int nextId() {
        int maxId = 0;
        for (VaccineTech vaccineTech : vaccineTechList) {
            if (vaccineTech.id() > maxId) {
                maxId = vaccineTech.id();
            };
        }
        return maxId+1;
    }

    public VaccineTech getById(int id) {
        for (VaccineTech vaccineTech : vaccineTechList) {
            if (vaccineTech.id() == id) {
                return vaccineTech;
            };
        }
        return null;
    }

    private Boolean validateSave(Object object) {
        return true;
    }

    private Boolean validateDelete(Object object) {
        return true;
    }

    public VaccineTech createVaccineTech(DTO dto) {
        VaccineTechDTO vaccineTechDTO = (VaccineTechDTO) dto;
        return new VaccineTech(nextId(), vaccineTechDTO.name(), vaccineTechDTO.description());
    }

    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            vaccineTechList.add((VaccineTech) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            vaccineTechList.remove(object);
            return true;
        }return false;
    }

    public List<VaccineTech> vaccineTechList() {
        return vaccineTechList;
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (VaccineTech item : vaccineTechList()) {
            VaccineTechDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.name()));
        }
        return dtoList;
    }
}