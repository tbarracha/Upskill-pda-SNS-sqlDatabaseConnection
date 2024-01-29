package pt.org.upskill.domain;

import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTOable;

public class Brand implements DTOable<BrandDTO> {
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public BrandDTO toDTO() {
        BrandDTO dto = new BrandDTO.Builder()
                .withName(name())
                .build();
        return dto;
    }
}
