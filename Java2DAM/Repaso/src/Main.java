import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //bucles();
        colecciones();
        Alumno a2 = new Alumno("paco");

    }

    private static void asignaciones() {
        int a = 5;
        byte b = 0;
        char c = 'a';
        String d = "gola mundo";
        System.out.println(d);

    }

    private static void condiciones() {
        int a = 5;
        byte b = 0;
        char c = 'a';
        String d = "gola mundo";
        System.out.println(d);
        if (a == b) {
            System.out.println("Son iguales");
        } else {
            System.out.printf(String.format("%d y %d son diferentes", a, b));
        }
        System.out.println();
        String e = "Hola mundo";
        String f = "Hola Badajoz";
        String g = "Hola Cáceres";
        String code = "06";
        switch (code) {
            case "06":
                System.out.println(f);
                break;
            case "10":
                System.out.printf(g);
                break;
            default:
                System.out.println(e);
        }
        String resultado = a == b ? "Son iguales" : "Son diferentes";
        System.out.println(resultado);
    }

    private static void bucles() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " gatetes");
        }
        int i = 0;
        while (i < 10) {
            System.out.println(i + "perretes");
            i++;
        }
        i = 0;
        do {
            System.out.println(i + "pajaretes");
        } while (i++ < 10);
    }

    private static void colecciones() {
        String[] nombres = {"Pepe", "Maria"};
        System.out.println(nombres[1]);
        nombres[0] = "Raulito";
        List<String> dias = new ArrayList<>();
        dias.add("Lunes");
        dias.addAll(Arrays.asList("Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"));
        System.out.println(dias.get(2));
        dias.set(dias.indexOf("Martes"), "Miercoles-1");
        System.out.printf(dias.toString());
    }
    private static void gola(){
        String a = "Lunes";
        String b = "Martes";
        String c = "Miercoles";
        


    }
}