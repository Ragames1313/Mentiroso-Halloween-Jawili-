import java.util.Random;
import java.util.Scanner;

public class Main {

//Para generar numeros aleatorios
    public static int generarnumaleatoriorangos(int p_iMinValue, int p_iMaxValue) {
        int iRet = p_iMaxValue;
        Random rnd = new Random();
        iRet = rnd.nextInt((p_iMaxValue + 1) - p_iMinValue) + p_iMinValue;
        return iRet;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] mano = new int[2][5];
        int[][] mesa = new int[1][5];
        int turno = 1;
        int otra = 1;

//Reglas del juego
        System.out.println("Bienvenido de MENTIROSO HALLOWEEN");
        System.out.println("�� Reglas del juego\n" +
                "�� Preparación \n" +
                "∙ Cada jugador tiene una mano representada por una fila de la matriz  mano[2][5]. \n" +
                "∙ Las cartas son números del 0 al 6, asignados aleatoriamente al inicio: o 1–5: calabazas normales \n" +
                "o 6: calabaza maldita \n" +
                "o 0: fantasma (comodín) \n" +
                "∙ La mesa se representa con una matriz mesa[1][5], donde se colocan las  cartas declaradas. \n" +
                "��♂\uFE0F Turnos \n" +
                "1. El jugador activo declara que pone X cartas del mismo valor sobre la  mesa. \n" +
                "2. El otro jugador puede:  \n" +
                "o Creerle y continuar el juego. \n" +
                "o Dudar y comprobar si decía la verdad. \n" +
                "�� Resolución \n" +
                "∙ Si el jugador mentía, pierde una carta. \n" +
                "∙ Si decía la verdad, el que dudó pierde una carta. \n" +
                "∙ Si se juega una calabaza maldita (6) y el otro duda, pierde 2 cartas. \n" +
                "∙ Si se juega un fantasma (0) y se descubre, el jugador pierde toda su  mano. \n" +
                "∙ El juego termina cuando un jugador se queda sin cartas. ");

        //Para generar la mano
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                mano[i][j] = generarnumaleatoriorangos(0, 6);
            }
        }

        //Para enseñarles las manos a los jugadores
        for (int i = 0; i < 2; i++) {
            System.out.print("mano del jugador " + (i+1) + ": ");
            for (int j = 0; j < 5; j++) {
                System.out.print(mano[i][j] + " ");
            }
            System.out.println();
        }
//Un do para los turnos
        //do (Ya irá)
        //{
        //if para marcar el turno del jugador uno o el jugador dos
           if (turno % 2 != 0)
           {
               //Preguntarle al usuario si quiere sacar las cartas y asignarlas a la mesa en cuyo caso
               System.out.print("Turno del jugador 1! \n");
               for (int j = 0; j < 5; j++) {
                   System.out.println("Deseas tirar tu carta " + j + " ? (1 = SI 2 = NO)");
                   int respuesta = sc.nextInt();
                   if (respuesta == 1)
                   {
                       mesa[j] = mano[j];
                   }
               }

           }
        //} while (tambien irá luego)

        //for para enseñar las cartas en la mesa
        for (int i = 0; i < 1; i++) {
            System.out.print("mesa: ");
            for (int j = 0; j < 5; j++) {
                System.out.print(mesa[j] + " ");
            }
        }
            System.out.println();
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
