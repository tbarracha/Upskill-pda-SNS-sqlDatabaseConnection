package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.VaccineType;

import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineTypeRepository;

import java.util.List;

public class VaccineTypeController implements UIable {
    VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();

    private VaccineType vaccineType;

    @Override
    public void register(DTO dto) {
        vaccineType = vaccineTypeRepository.createVaccineType(dto);
    }

    @Override
    public boolean save() {
        return vaccineTypeRepository.save(vaccineType);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() { return vaccineTypeRepository.keyValueDTOList(); }
}
