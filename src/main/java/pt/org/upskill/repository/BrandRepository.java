package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.ArrayList;
import java.util.List;

public class BrandRepository implements PersistableRepo {

    public BrandRepository() {}

    private List<Brand> brandList = new ArrayList<Brand>();

    private boolean validateSave(Object object) {
        return true;
    }

    public boolean validateDelete(Object object) { return true; }

    public Brand createBrand(DTO dto) {
        BrandDTO brandDTO = (BrandDTO) dto;
        return new Brand(brandDTO.name());
    }

    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            brandList.add((Brand) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            brandList.remove(object);
            return true;
        }
        return false;
    }

    public List<Brand> brandList() {
        return brandList;
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (Brand item : brandList()) {
            BrandDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.name(), ""));
        }
        return dtoList;
    }
    public Brand getByName(String name) {
        for (Brand brand : brandList) {
            if (brand.name().equals(name)) {
                return brand;
            }
        }
        return null;
    }
}