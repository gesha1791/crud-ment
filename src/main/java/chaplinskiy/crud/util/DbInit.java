package chaplinskiy.crud.util;

import java.io.File;
import java.io.IOException;

import static chaplinskiy.crud.util.Constants.*;

public class DbInit {
    public static void initDatabase(){
        File accountFile = new File(accountRepositoryPathCsv);
        File customerFile = new File(customerRepositoryPathCsv);
        File specialtyFile = new File(specialtyRepositoryPathCsv);
        checkExistFile(accountFile);
        checkExistFile(customerFile);
        checkExistFile(specialtyFile);

    }

    private static void checkExistFile(File file) {
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Cant create repository file from: " + file.toString());
            }
        }
    }
}
