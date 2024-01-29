package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Vaccine;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.VaccineRepository;

import java.util.List;

public class VaccineController implements UIable {
    VaccineRepository vaccineRepository = Repositories.getInstance().vaccineRepository();

    private Vaccine vaccine;

    @Override
    public void register(DTO dto) {
        vaccine = vaccineRepository.createVaccine(dto);
    }

    @Override
    public boolean save() {
        return vaccineRepository.save(vaccine);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() { return vaccineRepository.keyValueDTOList(); }

}
