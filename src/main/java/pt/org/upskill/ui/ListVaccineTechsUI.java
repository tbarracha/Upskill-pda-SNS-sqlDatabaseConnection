package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;

public class ListVaccineTechsUI extends UI {
    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINE TECHNOLOGIES");
        System.out.println("-----------");

        showKeyValueList("", new VaccineTechController().keyValueDTOList());

    }
}
