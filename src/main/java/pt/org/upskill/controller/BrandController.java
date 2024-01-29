package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.BrandRepository;
import pt.org.upskill.repository.Repositories;

import java.util.List;

public class BrandController implements UIable {
    BrandRepository brandRepository = Repositories.getInstance().brandRepository();

    Brand brand;

    @Override
    public void register(DTO dto) {
        brand = brandRepository.createBrand(dto);
    }

    @Override
    public boolean save() {
        return brandRepository.save(brand);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() {
        return brandRepository.keyValueDTOList();
    }
}
