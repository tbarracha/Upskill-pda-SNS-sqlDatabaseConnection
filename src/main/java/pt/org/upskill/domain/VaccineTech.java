package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.VaccineTechDTO;

public class VaccineTech implements DTOable<VaccineTechDTO> {
    private Integer id;
    private String name;
    private String description;

    public VaccineTech(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int id() {
        return id;
    }
    public String name() {
        return name;
    }
    public String description() { return description; }

    @Override
    public VaccineTechDTO toDTO() {
        VaccineTechDTO dto = new VaccineTechDTO.Builder()
                .withId(id())
                .withName(name())
                .withDescription(description())
                .build();
        return dto;
    }

}