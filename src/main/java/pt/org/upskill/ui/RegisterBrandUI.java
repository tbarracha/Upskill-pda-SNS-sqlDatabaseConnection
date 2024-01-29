package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.BrandController;
import pt.org.upskill.dto.BrandDTO;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterBrandUI extends UI {
    private final BrandController brandController = new BrandController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE BRAND (Manufacturer)");
        System.out.println("-----------");

        try {
            String name = readLineFromConsole("Brand Name: ");

            //DTO
            BrandDTO dto = new BrandDTO.Builder()
                    .withName(name)
                    .build();

            //Set data
            brandController.register(dto);

            //Confirm
            brandController.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
