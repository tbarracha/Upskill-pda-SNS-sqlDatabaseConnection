package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.VaccineTechDTO;
import pt.org.upskill.dto.VaccineTypeDTO;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;
import static pt.org.upskill.ui.utils.Utils.readIntegerFromConsole;
import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterVaccineTypeUI extends UI {
    private final VaccineTypeController vaccineTypeController = new VaccineTypeController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE TYPE");
        System.out.println("-----------");

        try {
            //System asks: code, short description, vaccine technology
            String code = readLineFromConsole("Vaccine Type Code: ");
            String shortDescription = readLineFromConsole("Vaccine Type Short Description: ");
            showKeyValueList("Vaccine Technologies", new VaccineTechController().keyValueDTOList());
            int key = readIntegerFromConsole("Select a vaccine technology: ");

            //DTO creation
            VaccineTypeDTO dto = new VaccineTypeDTO.Builder()
                    .withCode(code)
                    .withShortDescription(shortDescription)
                    .withVaccineTechDTO(new VaccineTechDTO.Builder()
                            .withId(key)
                            .build())
                    .build();

            //Registration
            vaccineTypeController.register(dto);

            //confirmation
            vaccineTypeController.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
