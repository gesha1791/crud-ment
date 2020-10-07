package chaplinskiy.crud.repository.impl.csv;

import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.AccountStatus;
import chaplinskiy.crud.model.Specialty;
import chaplinskiy.crud.repository.SpecialtyRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static chaplinskiy.crud.util.Constants.accountRepositoryPathCsv;
import static chaplinskiy.crud.util.Constants.specialtyRepositoryPathCsv;

public class SpecialtyRepositoryCsvIO implements SpecialtyRepository {
    @Override
    public Specialty create(Specialty specialty) {
        List<Specialty> allSpecialty = getAll();

        if (allSpecialty.isEmpty()){
            specialty.setId(0L);
        } else {
            Specialty specialtyWithMaxId =
                    allSpecialty.stream().max(Comparator.comparing(Specialty::getId)).get();
            specialty.setId(specialtyWithMaxId.getId() + 1);
        }

        String spec = specialty.getId() + "," + specialty.getName() +"\n";

        try (Writer writerSpecialty = new FileWriter(specialtyRepositoryPathCsv, true)){
            writerSpecialty.write(spec);
        }  catch (IOException e){
            System.out.println("Exception IO");
        }

        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        List<Specialty> all = getAll();

        for (int i = 0; i < all.size(); i++) {
            Specialty curentSpecialty = all.get(i);
            if (curentSpecialty.getId().equals(specialty.getId())) {
                curentSpecialty.setName(specialty.getName());
            }
        }
        StringBuilder specialties = new StringBuilder();

        Iterator<Specialty> iterator = all.iterator();

        while (iterator.hasNext()) {
            Specialty nextSpecialty = iterator.next();
            specialties.append(nextSpecialty.getId() + "," + nextSpecialty.getName() + "\n");
        }

        String trim = specialties.toString();

        try (Writer writerSpecialty = new FileWriter(specialtyRepositoryPathCsv)) {
            writerSpecialty.write(String.valueOf(trim));
        } catch (IOException e) {
            System.out.println("Exception IO");
        }
        return specialty;
    }


    @Override
    public Specialty getById(Long id) {
        List<Specialty> all = getAll();

        all = all.stream().filter(a->{
            return a.getId().equals(id);
        }).collect(Collectors.toList());

        return all.get(0);
    }

    @Override
    public List<Specialty> getAll() {
        List<String> rawSpecialty = new ArrayList<>();
        List<Specialty> specialties = new ArrayList<>();
        try {
            rawSpecialty = Files.readAllLines(Paths.get(specialtyRepositoryPathCsv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rawSpecialty.stream().forEach(a -> {
            String[] attributes = a.split(String.valueOf(','));
            specialties.add(makeSpecialty(attributes));
        });

        return specialties;
    }

    private Specialty makeSpecialty(String[] attributes) {
        return new Specialty(Long.valueOf(attributes[0]), attributes[1]);
    }

    @Override
    public void deleteById(Long id) {
        List<Specialty> all = getAll();

        for (int i = 0; i < all.size(); i++) {
            Specialty curentSpecialty = all.get(i);
            if(curentSpecialty.getId().equals(id)){
                all.remove(curentSpecialty);
            }
        }
        StringBuilder specialties = new StringBuilder();

        Iterator<Specialty> iterator = all.iterator();

        while (iterator.hasNext()){
            Specialty nextSpecialty = iterator.next();
            specialties.append(nextSpecialty.getId() + "," + nextSpecialty.getName() + "\n");
        }

        String trim = specialties.toString();


        try (Writer writerSpecialty = new FileWriter(specialtyRepositoryPathCsv)){
            writerSpecialty.write(String.valueOf(trim));
        }  catch (IOException e){
            System.out.println("Exception IO");
        }
    }
}
