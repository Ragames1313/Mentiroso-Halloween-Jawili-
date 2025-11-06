import java.util.Random;
import java.util.Scanner;

public class Main {

    // Creamos un objeto random global para no tenier que designar random en cada momento
    static Random rnd = new Random();

    // Para generar numeros aleatorios entre min y max
    public static int generarNumAleatorioRangos(int minValue, int maxValue) {
        return rnd.nextInt((maxValue + 1) - minValue) + minValue;
    }

    // Para generar nummeros aleatorios en la manos
    public static void rellenarManos (int[][] manos) {
        for (int i = 0; i < manos.length; i++) {
            for (int j = 0; j < manos[i].length; j++) {
                manos[i][j] = generarNumAleatorioRangos(0, 6);
            }
        }
    }

    // Para saltar lineas
    public static void saltarLineas(int n) {
        System.out.println(System.lineSeparator().repeat(n));
    }

    // Para mostrar en pantalla las manos de los jugadores
    public static void mostrarManos(int[][] mano){
        for (int i = 0; i < mano.length; i++) {
//            System.out.print("Mano del jugador " + (i + 1) + ": "); //No las muestro por jugabilidad
            for (int j = 0; j < mano[i].length; j++) {
                if  (mano[i][j] != -1) {
//                    System.out.print(mano[i][j] + " ");   //No las muestro por jugabilidad
                }
            }

            System.out.println();
        }
    }

    // For para enseñar las cartas en la mesa
    public static void mostrarMesa(int[][] mesa) {
        System.out.print("Mesa: ");
        for (int j = 0; j < mesa[0].length; j++) {
            if (mesa[0][j] != -1) System.out.print(mesa[0][j] + " ");
        }
        System.out.println();
    }

    // Función para eliminar una cantidad concreta de cartas de un jugador
    public static void eliminarCartas(int[][] mano, int jugador, int cantidad) {
        int eliminadas = 0;
        for (int j = 0; j < mano[jugador].length && eliminadas < cantidad; j++) {
            if (mano[jugador][j] != -1) {
                mano[jugador][j] = -1;
                eliminadas++;
            }
        }
        System.out.println("El jugador " + (jugador + 1) + " pierde " + cantidad + " calabaza(s).");
    }

    // Comprobar si un jugador se queda sin cartas, utilizamos boolean en vez de void porque lo que queremos es que nos devuelva un valor false o true
    public static boolean sinCartas(int[][] mano, int jugador) {
        for (int j = 0; j < mano[jugador].length; j++) {
            if (mano[jugador][j] != -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {

        // Abrimos el Scanner
        Scanner sc = new Scanner(System.in);
        // Abrimos el boleano para que reiniciar el juego al terminar
        boolean jugarDeNuevo = true;

        // Insertamos el bucle while para que se siga jugando tras terminar
        while (jugarDeNuevo) {

            // Definimos los dogmas del codigo "final" bloquea la cantidad asignada de la constante
            final int numJugadores = 2;
            final int cartasPorJugador = 5;

            // Matriz mano [2][5]
            int[][] mano = new int[numJugadores][cartasPorJugador];
            // Matriz mesa [1][5]
            int[][] mesa = new int[1][cartasPorJugador];

            rellenarManos(mano);

            // Inicializamos la mesa con -1 porq 0 es una calabaza fantasma
            for (int j = 0; j < cartasPorJugador; j++) mesa[0][j] = -1;

            // Reglas del juego
            System.out.println("\n--¡BIENVENIDO A MENTIROSO HALLOWEEN!--");
            System.out.println(" * Cada jugador tiene 5 calabazas (0 a 6).");
            System.out.println(" * Por turnos, los jugadores ponen calabazas en la mesa.");
            System.out.println(" * El otro jugador decide si creer o dudar...");
            System.out.println(" ¡Y por ultimo... divertíos!");

            mostrarManos(mano);
            int turno = 0;
            boolean fin = false;

            do {
                int jugadorActual = turno % 2;
                int otroJugador = (jugadorActual == 0) ? 1 : 0;
                System.out.println("Turno del jugador " + (jugadorActual + 1));


                // Mostrar su mano
                System.out.print("Tus calabazas: ");
                for (int j = 0; j < cartasPorJugador; j++) {
                    if (mano[jugadorActual][j] != -1)
                        System.out.print(mano[jugadorActual][j] + " ");
                }
                System.out.println();

                // Vaciar mesa antes del turno
                for (int j = 0; j < cartasPorJugador; j++) mesa[0][j] = -1;

                // Tirar cartas una a una
                int tiradas = 0;
                for (int j = 0; j < cartasPorJugador; j++) {
                    if (mano[jugadorActual][j] != -1) {
                        System.out.print("Tu " + (j + 1) + "ª calabaza vale " + mano[jugadorActual][j] + " ¿Quieres tirarla, si o no? ");

                        String respuestaTexto = sc.next().trim().toLowerCase(); // Al usar toLowerCase abarca todas las minusculas y mayusculas del string y trim elimina espacios
                        int respuesta;

                        if (respuestaTexto.equals("1") || respuestaTexto.equals("si")) {
                            respuesta = 1;
                        } else if (respuestaTexto.equals("2") || respuestaTexto.equals("no")) {
                            respuesta = 2;
                        } else {
                            System.out.println("¿INTENTAS HACER TRAMPAS? interpretamos tu respuesta como un NO");
                            respuesta = 2;
                        }

                        if (respuesta == 1) {
                            mesa[0][tiradas] = mano[jugadorActual][j];
                            mano[jugadorActual][j] = -1;
                            tiradas++;
                        }
                    }
                }

                if (tiradas == 0) {
                    System.out.println("No tiras ninguna calabaza. El turno pasa al otro jugador...");
                    turno++;
                    continue;
                }

                //            // Declarar el valor de las calabazas jugadas
                //            System.out.print("Declara el valor de tus " + tiradas + " calabaza(s) (0-6): ");
                //            int valorDeclarado = sc.nextInt();

                // Hice testeo del juego y la sintetizacion de este codigo rompe el juego
                int valorDeclarado = -1;
                while (valorDeclarado < 0 || valorDeclarado > 6) {

                    System.out.print("Declara el valor de tus " + tiradas + " calabaza(s) (0-6): ");

                    // Comprueba si el siguiente valor es numero entero antes de leerlo "sc.hasNextIn"
                    // Es un tipo de validación que sustituye "try"
                    if (sc.hasNextInt()) {
                        // Lee el siguiente numero entero, si no da error "nextInt"
                        valorDeclarado = sc.nextInt();
                        if (valorDeclarado < 0 || valorDeclarado > 6) {
                            System.out.println("Valor fuera de rango. Debe estar entre 0 y 6.");
                        }
                    } else {
                        System.out.println("Eso no es un número válido.");
                        sc.next(); // consumir lo que sea que haya escrito
                    }
                }

                saltarLineas(10);

                mostrarMesa(mesa);

                int decision = 0;
                while (decision == 0) {
                    // El otro jugador decide
                    System.out.print("Jugador " + (otroJugador + 1) + ", ¿Crees al jugador número " + (jugadorActual + 1) + " o dudas? ");
                    String respuestaTexto = sc.next().trim().toLowerCase();

                    if (respuestaTexto.equals("1") || respuestaTexto.equals("creo")) {
                        decision = 1;
                    } else if (respuestaTexto.equals("2") || respuestaTexto.equals("dudo")) {
                        decision = 2;
                    } else {
                        System.out.println("¿INTENTAS HACER TRAMPAS? interpretamos tu respuesta como que le crees");
                        decision = 1;
                    }
                }
                // Comprobamos si el jugador decía la verdad (todas las jugadas coinciden con el valor declarado)
                boolean diceVerdad = true;
                for (int j = 0; j < mesa[0].length && mesa[0][j] != -1; j++) {
                    if (mesa[0][j] != valorDeclarado) diceVerdad = false;
                }

                // detectamos 0 o 6 en las cartas realmente tiradas
                boolean contiene0 = false;
                boolean contiene6 = false;
                for (int j = 0; j < mesa[0].length; j++) {
                    if (mesa[0][j] == 0) contiene0 = true;
                    if (mesa[0][j] == 6) contiene6 = true;
                }

                // Aplicar consecuencias
                if (decision == 2) { // duda
                    // Primero: si entre lo que tiraste había un 0, se descubre el fantasma y pierdes toda la mano
                    if (contiene0) {
                        System.out.println("Era un FANTASMA descubierto. El jugador " + (jugadorActual + 1) + " pierde TODA su mano!");
                        eliminarCartas(mano, jugadorActual, cartasPorJugador);
                    } else if (!diceVerdad) {
                        // Si no hay fantasma y no decía la verdad -> mentiroso penalizado
                        System.out.println("Era MENTIRA. El jugador " + (jugadorActual + 1) + " pierde 1 carta!");
                        eliminarCartas(mano, jugadorActual, 1);
                    } else {
                        // Decía la verdad y no había fantasma: comprobar si hay maldita (6)
                        if (contiene6) {
                            System.out.println("Era VERDAD y jugó una calabaza MALDITA. El jugador " + (otroJugador + 1) + " pierde 2 cartas!");
                            eliminarCartas(mano, otroJugador, 2);
                        } else {
                            System.out.println("Era VERDAD. El jugador " + (otroJugador + 1) + " pierde 1 carta!");
                            eliminarCartas(mano, otroJugador, 1);
                        }
                    }
                } else {
                    System.out.println("El jugador " + (otroJugador + 1) + " ha decidido CREER. La partida continúa...");
                }

                mostrarManos(mano);
                fin = sinCartas(mano, 0) || sinCartas(mano, 1);
                turno++;
            } while (!fin);

            if (sinCartas(mano, 0) && !sinCartas(mano, 1)) {
                System.out.println("El jugador 1 ha perdido todas sus calabazas. ¡Jugador 2 gana!");
            } else if (sinCartas(mano, 1) && !sinCartas(mano, 0)) {
                System.out.println("El jugador 2 ha perdido todas sus calabazas. ¡Jugador 1 gana!");
            } else {
                System.out.println("¡Empate!");
            }

            System.out.print("\n¿Quieres jugar otra partida, si o no? ");
            String respuestaTexto = sc.next().trim().toLowerCase();

            if (respuestaTexto.equals("1") || respuestaTexto.equals("si")) {
                jugarDeNuevo = true;
                System.out.println("\n¡Prueba otra vez!\n");
            } else if (respuestaTexto.equals("2") || respuestaTexto.equals("no")) {
                jugarDeNuevo = false;
                System.out.println("Gracias por jugar a MENTIROSO HALLOWEEN");
            } else {
                System.out.println("¿INTENTAS HACER TRAMPAS? interpretamos tu respuesta como un NO");
                jugarDeNuevo = false;
                System.out.println("Gracias por jugar a MENTIROSO JAWILIN");
            }
        }
        sc.close();
    }
}
