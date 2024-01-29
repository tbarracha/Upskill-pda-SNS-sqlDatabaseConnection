package pt.org.upskill.ui;

import pt.org.upskill.controller.VaccineController;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;

public class ListVaccinesUI extends UI {
    private final VaccineController vaccineController = new VaccineController();

    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINES");
        System.out.println("-----------");

        showKeyValueList("", new VaccineController().keyValueDTOList());
    }

}
