package pt.org.upskill.ui;

import pt.org.upskill.controller.BrandController;
import pt.org.upskill.controller.VaccineController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.VaccineDTO;
import pt.org.upskill.dto.VaccineTypeDTO;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;
import static pt.org.upskill.ui.utils.UITools.showKeyValueList;

public class RegisterVaccineUI extends UI {

    private final VaccineController vaccineController = new VaccineController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE");
        System.out.println("-----------");

        try {
            //System asks: vaccine type, brand, vaccine name
            showKeyValueList("Vaccine Types", new VaccineTypeController().keyValueDTOList());
            String vaccineTypeCode = readLineFromConsole("Select a vaccine type: ");

            showKeyValueList("Brands", new BrandController().keyValueDTOList());
            String brandName = readLineFromConsole("Select a brand: ");

            String vaccineName = readLineFromConsole("Vaccine Name: ");

            //DTO
            VaccineDTO dto = new VaccineDTO.Builder()
                    .withName(vaccineName)
                    .withVaccineTypeDTO(new VaccineTypeDTO.Builder()
                            .withCode(vaccineTypeCode)
                            .build())
                    .withBrandDTO(new BrandDTO.Builder()
                            .withName(brandName)
                            .build())
                    .build();

            //Registration
            vaccineController.register(dto);

            //Confirmation
            vaccineController.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
