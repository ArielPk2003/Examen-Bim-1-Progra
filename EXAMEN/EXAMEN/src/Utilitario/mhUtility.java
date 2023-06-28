package Utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class mhUtility {
    static ArrayList<String> Materias = new ArrayList<>();
    static ArrayList<String> Codigo = new ArrayList<>();

    public static final String mhCedula = "1722966650";
    public static final String mhNombre = "Harryson Ariel Montesdeoca Rhea";
    public static final String mhCorreo = "harryson.montesdeoca@epn.edu.ec";
    
    static String mhUsuarioLogeado;
    static Scanner mh = new Scanner(System.in);

    private static void hmPresentarNombreArchivoCSV(int opc) {
    String directorioActual = System.getProperty("user.dir");
    String rutaDirectorio = directorioActual + File.separator + "Horarios";
    File folder = new File(rutaDirectorio);
    File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
    if (files != null) {
        for (File file : files) {
            if(opc==1){
            String nombreArchivo = file.getName().replace(".csv", "");
            System.out.println(nombreArchivo);
            }
            List<List<String>> datosArchivo = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] datos = line.split(";");
                    if(opc==2){
                        String codigo = datos[1];
                        String materia = datos[2];
                    System.out.println(codigo+"\t\t"+materia);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}


    public static void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void borrarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int mhValidarNumMenu(String mensaje) {
        int numero;
        System.out.print(mensaje);
        do {
            try {
                numero = Integer.parseInt(mh.nextLine());
                if (numero < 0 || numero > 4) {
                    System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcion invalida. Intente nuevamente.");
                numero = -1; 
            }
        } while (numero < 0 || numero > 4);

        return numero;
    }

    public static boolean mhLogin() {
        String mhUsuarioProfe = "profe";
        String mhClaveProfe = "1234";
        int Intentos = 1;
        while (Intentos <= 3) {
            borrarConsola();
            System.out.println(mhCedula);
            System.out.println(mhCorreo.toLowerCase());
            System.out.println(mhNombre.toUpperCase());
            System.out.println();
            System.out.println("------------------------");
            System.out.print("+ Usuario: ");
            mhUsuarioLogeado = mh.nextLine();
            System.out.println();
            System.out.print("+ Clave: ");
            String Clave = mh.nextLine();
            System.out.println();
            System.out.println("------------------------");
            System.out.print("* Numero de Intentos: " + Intentos);
            System.out.println();
            pausa(1000);
            if ((mhUsuarioLogeado.equals(mhCorreo) && Clave.equals(mhCedula))
                    || mhUsuarioLogeado.equals(mhUsuarioProfe) && Clave.equals(mhClaveProfe)) {
                System.out.println("Bienvenido " + mhUsuarioLogeado.toUpperCase());
                System.out.println("\nPress Any Key to Continue...");
                mh.nextLine();
                return true;
            }
            Intentos++;
        }
        System.out.println("\nLo sentimos, tu usuario y clave son incorrectos..!");
        System.out.println("Gracias!");

        pausa(1000);
        System.exit(-1);
        return false;
    }

    public static void mhPrentarEncabezadoHorario() {
        System.out.println("Hora\t\t(l)Lunes\t\t(m)Martes\t\t(x)Miercoles\t\t(j)Jueves\t\t(v)Viernes\t\t(s)Sabado");
    }

    public static void mhMenu() {
        int opcion;
        do {
            borrarConsola();

            System.out.println("1. Visualizar Medicos");
            System.out.println("2. Visualizar Especialidad");
            System.out.println("3. Visualizar Horario");
            System.out.println("4. Visualizar Horario de una Especialidad");
            System.out.println("0. SALIR");

            opcion = mhValidarNumMenu("\n" + "<+> Ingrese Opc: ");

            switch (opcion) {
                case 1:
                    System.out.println("[+] Listado de Medicos");
                    hmPresentarNombreArchivoCSV(1);
                    System.out.println("Press Any Key To Continue...");
                    mh.nextLine();
                    break;
                case 2:
                    borrarConsola();
                    System.out.println("[+] Listado de Especialidad");
                    hmPresentarNombreArchivoCSV(2);
                    System.out.println("Press Any Key To Continue...");
                    mh.nextLine();
                    break;
                case 3:
                    borrarConsola();
                    System.out.println("[+] Visualizar Horario");
                    mhPrentarEncabezadoHorario();
                    hmPresentarNombreArchivoCSV(3);
                    System.out.println("Press Any Key To Continue...");
                    mh.nextLine();
                    break;
                case 4:
                    borrarConsola();
                    System.out.println("[+] Visualizar Horario de una Especialidad");
                    System.out.println("Press Any Key To Continue...");
                    mh.nextLine();
                    break;
                case 0:
                    borrarConsola();
                    System.out.println("Regrese pronto, " + mhUsuarioLogeado.toUpperCase());
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    pausa(1000);
                    break;
            }
        } while (opcion != 0);
    }


}
    
