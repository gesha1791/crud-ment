package chaplinskiy.crud.controllers;

import chaplinskiy.crud.model.Specialty;
import chaplinskiy.crud.repository.SpecialtyRepository;
import chaplinskiy.crud.repository.impl.csv.SpecialtyRepositoryCsvIO;

import java.util.List;

public class SpecialtyController {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyController() {
        specialtyRepository = new SpecialtyRepositoryCsvIO();
    }

    public List<Specialty> getAllSpecialty() {
        return specialtyRepository.getAll();
    }

    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.create(specialty);
    }

    public void deleteSpecialtyById(Long id) {
        specialtyRepository.deleteById(id);
    }

    public void updateSpecialtyById(Specialty specialty) {
        specialtyRepository.update(specialty);
    }

    public Specialty geSpecialtyById(Long id) {
        return specialtyRepository.getById(id);
    }
}
