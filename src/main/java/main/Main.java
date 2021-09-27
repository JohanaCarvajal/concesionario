package main;

import integration.database.mysql.MySqlOperations;
import org.apache.log4j.PropertyConfigurator;
import java.sql.SQLException;
import java.util.Scanner;

import static util.enums.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static util.enums.SystemProperties.USER_DIR;

public class Main {
    private static Scanner lc = new Scanner(System.in);
    private static final String SERVER = "sofka-training.cpxphmd1h1ok.us-east-1.rds.amazonaws.com";
    private static final String DATA_BASE_NAME = "leidyCarvajal_concesionario_10092021";
    private static final String USER = "sofka_training";
    private static final String PASSWORD = "BZenX643bQHw";

    private static final String SELECT_ALL_FROM_SUCURSAL = String.format("select * from %s.sucursal", DATA_BASE_NAME);
    private static final String SELECT_ALL_FROM_COLOR= String.format("select * from %s.color", DATA_BASE_NAME);
    private static final String CALL_LISTAR_PERSONA= String.format("call listar_persona()");
    private static final String VISTA_VEHICULO= String.format("select * from %s.vista_vehiculo",DATA_BASE_NAME);

    private static final MySqlOperations mySqlOperations = new MySqlOperations();

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        Scanner cg = new Scanner(System.in);
        login();
        int option;
        do {
            System.out.println("Opciones:\n"+
                    "1: Ver sucursales \n" +
                    "2: Listar personas \n" +
                    "3: Insertar nombre de color del carro \n" +
                    "4: Borrar un color \n" +
                    "5: Vista vehiculo\n" +
                    "0: SALIR");
            System.out.println("Ingrese la opción que desea ejecutar");
            option = cg.nextInt();

            switch (option){
                case 1:
                    selectAllFromSucursal();
                break;
                case 2:
                    CallListarPersona();
                break;
                case 3:
                    callInsertarColor();
                break;
                case 4:
                    callBorrarColor ();
                break;
                case 5:
                    vista_vehiculo();
                break;
                case 0:
                break;
            }
        }while (option != 0);
        logout();
    }

    private static void login(){
        mySqlOperations.setServer(SERVER);
        mySqlOperations.setDataBaseName(DATA_BASE_NAME);
        mySqlOperations.setUser(USER);
        mySqlOperations.setPassword(PASSWORD);
    }

    private static void selectAllFromSucursal() throws SQLException {
        mySqlOperations.setSqlStatement(SELECT_ALL_FROM_SUCURSAL);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }


    private static void selectAllFromColor()  throws SQLException {
        mySqlOperations.setSqlStatement(SELECT_ALL_FROM_COLOR);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void callInsertarColor() throws SQLException {
        String auxColor = "";
        System.out.println("Ingrese un nuevo color, por ejemplo: Verde");
        auxColor = lc.nextLine();
        auxColor = "call insertar_color('"+auxColor+")";
        mySqlOperations.setSqlStatement(auxColor);
        mySqlOperations.executeSqlStatement();

    }

    private static void CallListarPersona() throws SQLException {
        mySqlOperations.setSqlStatement(CALL_LISTAR_PERSONA);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void callBorrarColor()  throws SQLException {
        String auxIdColor = "";
        System.out.println("Ingrese el id del color que desea eliminar, ejemplo: 1");
        auxIdColor = lc.nextLine();
        auxIdColor = "call borrar_color('"+auxIdColor+")";
        mySqlOperations.setSqlStatement(auxIdColor);
        mySqlOperations.executeSqlStatement();
    }

    private static void vista_vehiculo() throws SQLException {
        mySqlOperations.setSqlStatement(VISTA_VEHICULO);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void logout(){
        mySqlOperations.close();
    }

}


