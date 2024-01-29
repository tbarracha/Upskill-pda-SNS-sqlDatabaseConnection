package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Facility;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.FacilityRepository;
import pt.org.upskill.repository.Repositories;

import java.util.List;

public class FacilityController implements UIable {
    FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();

    Facility facility;
    @Override
    public void register(DTO dto) throws Exception {
        facility = facilityRepository.createFacility(dto);
    }

    @Override
    public boolean save() {
        return facilityRepository.save(facility);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() {
        return facilityRepository.keyValueDTOList();
    }
}
