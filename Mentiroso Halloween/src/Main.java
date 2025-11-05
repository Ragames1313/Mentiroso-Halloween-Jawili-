import java.util.Random;
import java.util.Scanner;

public class Main {

    // Creamos un objeto random global para no tenier que designar random en cada momento
    static Random rnd = new Random();

    // Para generar numeros aleatorios entre min y max
    public static int generarNumAleatorioRangos(int minValue, int maxValue) {

        // Dado que hemos designado random como global anteriormente podemos simplificar en una linea
        return rnd.nextInt((maxValue + 1) - minValue) + minValue;
    }

    // Para generar nummeros aleatorios en la manos (de calabazas)
    public static void rellenarManos (int[][] manos) {
        for (int i = 0; i < manos.length; i++) {    // Recorre la columna
            for (int j = 0; j < manos[i].length; j++) {   // Ponemos manos[i].length en vez de 5 (recorre la fila)
                manos[i][j] = generarNumAleatorioRangos(0, 6);
            }
        }
    }

    // Para mostrar en pantalla las manos a los jugadores
    public static void  mostrarManos(int[][] mano){
        for (int i = 0; i < mano.length; i++) {
            System.out.print("Mano del jugador " + (i + 1) + ": ");
            for (int j = 0; j < mano[i].length; j++) {
                // No muestra cartas que sean -1
                if  (mano[i][j] != -1) {
                    System.out.print(mano[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // For para enseñar las cartas en la mesa
    public static void mostrarMesa(int[][] mesa) {
        System.out.print("Mesa: ");
        for (int j = 0; j < mesa[0].length; j++) {  // Recorremos las columnas de la fila 0.
            System.out.print(mesa[0][j] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definimos los dogmas del codigo "final" bloquea la cantidad asignada de la constante
        final int numJugadores = 2;
        final int cartasPorJugador = 5;


        // Matriz mano [2][5]
//        int[][] mano = new int[2][5];
        int[][] mano = new int [numJugadores][cartasPorJugador];

        // Matriz mesa [1][5]
//        int[][] mesa = new int[1][5];
        int[][] mesa = new int[1][cartasPorJugador];

        int turno = 1;

        // Inicializamos la mesa con -1 porq 0 es una calabaza fantasma
        for (int j = 0; j < cartasPorJugador; j++){
            mesa[0][j] = -1;
        }

//        int otra = 1;

        // Reglas del juego
        System.out.println("\n--¡BIENVENIDO A MENTIROSO HALLOWEEN!--");
        System.out.println("   Reglas del juego (resumen):");
        System.out.println(" * Cada jugador tiene 5 calabazas (0 a 6).");
        System.out.println(" * Por turnos, los jugadores ponen calabazas en la mesa.");
        System.out.println(" * El siguiente jugador decide si es mentira.\n"); //Salto de linea "\n"

        // Repartimos cartas
        rellenarManos(mano);

        // Mostrar manos
        mostrarManos(mano);

//        Un do para los turnos
//        do (Ya irá)
//        {
        // Marcar el turno del jugador uno o el jugador dos
           if (turno % 2 != 0){
               System.out.print("Turno del jugador 1! \n");

               // Pregunta al usuario si quiere sacar las cartas y asignarlas a la mesa en cuyo caso
               // Deberia ser sustituido por un boleano para mejorar el codigo
               for (int j = 0; j < cartasPorJugador; j++) {
                   System.out.print("Tu " + (j+1) + "ª calabaza vale" + mano[0][j] + " ¿Quieres tirarla? \n (1 = SI 2 = NO) ");
                   int respuesta = sc.nextInt();

                   if (respuesta == 1)
                   {
                       // Pasamos de la mano a la mesa (Requiere 2 al ser un array de 2D)
                        mesa[0][j] = mano[0][j];
                       // Eliminamos la carta que lanza a la mesa de la mano
                       mano[0][j] = -1;
                   }
               }

           }
//        } while (tambien irá luego)

        // Mostramos como ha quedado la mesa
        System.out.println();
        mostrarMesa(mesa);

        // Mostramos mano
        mostrarManos(mano);

        // Cerramos Scanner
        sc.close();
    }
}

    /*static boolean respuestaSi(Scanner sc) {
        while (true) {
            String r = sc.nextLine().trim().toLowerCase();
            if (esPeticionAyuda(r)) { imprimirInstrucciones(); System.out.print("¿Quieres jugar otra partida? (s/n): "); continue; }
            if (r.equals("s") || r.equals("si") || r.equals("sí")) return true;
            if (r.equals("n") || r.equals("no")) return false;
            System.out.print("   Responde s/n: ");
        }
    }*/
