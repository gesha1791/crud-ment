package chaplinskiy.crud.view;

import chaplinskiy.crud.controllers.SpecialtyController;
import chaplinskiy.crud.model.Specialty;

import java.util.List;
import java.util.Scanner;

import static chaplinskiy.crud.util.Constants.*;
import static chaplinskiy.crud.util.PrintUtils.printMessage;
import static chaplinskiy.crud.util.ScannerSingle.getScanner;

public class SpecialtyView {
    private final Scanner scanner;
    private final SpecialtyController specialtyController;


    public SpecialtyView() {
        scanner = getScanner();
        specialtyController = new SpecialtyController();
    }

    public void run() {
        boolean start = true;
        while (start) {
            printMessage(specialtyViewMessage);
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    printAllSpecialty();
                    break;
                case 2:
                    createSpecialty();
                    break;
                case 3:
                    deleteSpecialty();
                    break;
                case 4:
                    updateSpecialty();
                    break;
                case 5:
                    getByIdSpecialty();
                    break;
                case 6:
                    start = false;
                    break;
                default:
                    printMessage(wrongSpecialtyMessage);
            }
        }
    }

    private void getByIdSpecialty() {
        printMessage(idSpecialtyMessage);
        Long id = Long.valueOf(scanner.next());
        Specialty specialty = specialtyController.geSpecialtyById(id);
        printMessage(specialty.toString());
    }

    private void updateSpecialty() {
        printMessage(idSpecialtyMessage);
        Long id = Long.valueOf(scanner.next());
        printMessage(updateSpecialtyMessage);
        String name = scanner.next();
        Specialty specialty = new Specialty(id, name);
        specialtyController.updateSpecialtyById(specialty);
    }

    private void deleteSpecialty() {
        printMessage(idSpecialtyMessage);
        Long id = Long.valueOf(scanner.next());
        specialtyController.deleteSpecialtyById(id);
    }

    private void createSpecialty() {
        printMessage(createNewSpecialtyMessage);
        printMessage(enterSpecialtyNameMessage);
        String name = scanner.next();

        Specialty specialty = new Specialty(name);
        Specialty specialtyNew = specialtyController.createSpecialty(specialty);
        printMessage(specialtyNew.toString());
    }

    private void printAllSpecialty() {
        List<Specialty> allSpecialty = specialtyController.getAllSpecialty();
        printMessage(allSpecialtiesMessage);
        allSpecialty.stream().forEach(System.out::println);
    }


}
