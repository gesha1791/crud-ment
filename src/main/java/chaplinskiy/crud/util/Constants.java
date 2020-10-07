package chaplinskiy.crud.util;

import chaplinskiy.crud.model.Account;
import chaplinskiy.crud.model.Specialty;

import java.util.Set;

public final class Constants {
    public static final String accountRepositoryPathCsv = "accounts.csv";
    public static final String customerRepositoryPathCsv = "customer.csv";
    public static final String specialtyRepositoryPathCsv = "specialty.csv";

    public static final String runnerViewMessage = "Hello, please enter number: \n"
            + "1) Specialties\n" + "2) Accounts\n" + "3) Customers\n" + "4) Exit";
    public static final String wrongRunnerMessage = "Wrong number\n" + "Enter number from 1 to 4";

    public static final String accountStatusEnumValue = "ACTIVE, BANNED, DELETED";

    //// Account message
    public static final String accountViewMessage = "Choose option for accounts, please : \n"
            + "1) Show all accounts\n" + "2) Create new account\n" + "3) Delete account\n" + "4) Update account\n"
            + "5) Get account\n" + "6) Back to menu";
    public static final String wrongAccountMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String allAccountsMessage = "Print all accounts";
    public static final String createNewAccountMessage = "Create new account";
    public static final String updateAccountMessage = "Please enter status account";
    public static final String idAccountMessage = "Please enter id account";

    //// Specialty message
    public static final String specialtyViewMessage = "Choose option for specialty, please : \n"
            + "1) Show all specialties\n" + "2) Create new specialty\n" + "3) Delete specialty\n" + "4) Update specialty\n"
            + "5) Get specialty\n" + "6) Back to menu";
    public static final String wrongSpecialtyMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String createNewSpecialtyMessage = "Create new specialty";
    public static final String enterSpecialtyNameMessage = "Please, enter new specialty name";
    public static final String allSpecialtiesMessage = "Print all specialty";
    public static final String idSpecialtyMessage = "Please enter id specialty";
    public static final String updateSpecialtyMessage = "Please enter specialty name";

    //// Customer message
    public static final String customerViewMessage = "Choose option for customer, please : \n"
            + "1) Show all customers\n" + "2) Create new customer\n" + "3) Delete customer\n" + "4) Update customer\n"
            + "5) Get customer\n" + "6) Back to menu";
    public static final String wrongCustomerMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String createNewCustomerMessage = "Create new customer";
    public static final String enterCustomerNameMessage = "Please, enter customer name";
    public static final String enterCustomerSurnameMessage = "Please, enter customer name";
    public static final String enterCustomerAccountMessage = "Please, select an account from the provided";
    public static final String enterCustomerSpecialtyMessage = "Please, select a specialties from the provided";

    public static final String allCustomerMessage = "Print all customers";
    public static final String idCustomerMessage = "Please enter id customer";
    public static final String updateCustomerNameMessage = "Please, enter customer name";
    public static final String updateCustomerSurnameMessage = "Please, enter customer surname";
    public static final String updateCustomerAccountMessage = "Please, select an account from the provided";
    public static final String updateCustomerSpecialtyMessage = "Please, select a specialties from the provided";

}
