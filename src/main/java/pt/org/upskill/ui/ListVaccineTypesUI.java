package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTypeController;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;

public class ListVaccineTypesUI extends UI {
    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINE TYPES");
        System.out.println("-----------");

        showKeyValueList("", new VaccineTypeController().keyValueDTOList());
    }
}
