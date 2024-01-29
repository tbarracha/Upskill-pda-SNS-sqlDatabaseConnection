package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.dto.VaccineTechDTO;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterVaccineTechUI extends UI {
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE TECHNOLOGY");
        System.out.println("-----------");

        try {
            //System asks: name, description
            String name = readLineFromConsole("Vaccine Technology Name: ");
            String description = readLineFromConsole("Vaccine Technology Description: ");

            //DTO creation
            VaccineTechDTO dto = new VaccineTechDTO.Builder()
                    .withName(name)
                    .withDescription(description)
                    .build();

            //Registration
            vaccineTechController.register(dto);

            //Confirmation
            vaccineTechController.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
