import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimuladorTenis {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n-----SIMULADOR DE TENIS-----");

        System.out.println("\nA continuación ingrese los nombres de los jugadores");
        System.out.println("Nombre Jugador 1:");
        String jugador1 = teclado.nextLine();
        System.out.println("Nombre Jugador 2:");
        String jugador2 = teclado.nextLine();

        System.out.println("\nA continuación ingrese el nombre del torneo");
        System.out.println("Nombre del torneo:");
        String torneo = teclado.nextLine();

        System.out.println("\nA continuación ingrese la cantidad de sets");
        int sets = -1;
        while (sets != 5 && sets != 3) {
            System.out.println("Elija solo 3 o 5 sets:");
            sets = teclado.nextInt();
        }

        System.out.println("\nA continuación ingrese la probabilidad de ganar del Jugador 1");
        int probJug1 = -1;
        while (probJug1 < 1 || probJug1 > 100) {
            System.out.println("Ingrese un valor de 1 a 100:");
            probJug1 = teclado.nextInt();
        }

        int probJug2 = 100 - probJug1;

        System.out.println("\nProbabilidad de ganar del Jugador 1: " + probJug1 + "%");
        System.out.println("Probabilidad de ganar del Jugador 2: " + probJug2 + "%");

        int opc = -1;

        while (opc != 2) {
            opc = -1;
            Scanner teclado2 = new Scanner(System.in);

            System.out.println("\nSORTEAR EL JUGADOR QUE SACA PRIMERO (PRESIONAR ENTER)");
            teclado2.nextLine();
            
            //JUGADOR QUE HACE EL SAQUE
            int sorteoSacaPrimero = (int)((Math.random()*100)+1);
            boolean sacaJug1 = false;
            boolean sacaJug2 = false;
            if (sorteoSacaPrimero < 50) {
                sacaJug1 = true;
                System.out.println("El Jugador 1 '" + jugador1 + "' saca primero.");
            } else {
                sacaJug2 = true;
                System.out.println("El Jugador 2 '" + jugador2 + "' saca primero.");
            }

            System.out.println("\nINICIAR PARTIDO (PRESIONAR ENTER)");
            teclado2.nextLine();

            List<String> resultadosJugador1 = new ArrayList<String>();
            List<String> resultadosJugador2 = new ArrayList<String>();
            
            int puntSetJug1 = 0;
            int puntSetJug2 = 0;
    
            int setsJugados = 0;
            boolean partidoFinalizado = false;
            while (!partidoFinalizado) {
    
                System.out.println("\nSET " + (setsJugados + 1) + " |-----------------------------------------------------------");
                System.out.println("(PRESIONAR ENTER)");
                teclado2.nextLine();

                boolean tieBreakActivo = false;
    
                int puntGameJug1 = 0;
                int puntGameJug2 = 0;
                
                boolean setFinalizado = false;
                while (!setFinalizado) {
                    
                    String[] puntajes = {"0", "15", "30", "40", "AD"}; 
                    int nivelesJug1 = 0;
                    int nivelesJug2 = 0;
                    String puntajeJug1 = puntajes[nivelesJug1];
                    String puntajeJug2 = puntajes[nivelesJug2];
                    
                    //System.out.println("\n*ENTER para continuar*");
                    //teclado.nextLine();
    
                    if (sacaJug1) {
                        System.out.println("\nJugador que tiene el saque es: " + jugador1);
                        sacaJug1 = false;
                        sacaJug2 = true;
                    } else if (sacaJug2) {
                        System.out.println("\nJugador que tiene el saque es: " + jugador2);
                        sacaJug2 = false;
                        sacaJug1 = true;
                    }
        
                    boolean gameFinalizado = false;
                    while (!gameFinalizado) {
                        int rndPuntos = (int)((Math.random()*100)+1);
                
                        if (rndPuntos < probJug1) {
                            //System.out.println("Ganó " + jugador1);
                            if (nivelesJug1 < 3) {
                                nivelesJug1++;
                                puntajeJug1 = puntajes[nivelesJug1];
                            } else if (nivelesJug1 == 3) { //Si gano y tengo 40p
                                if(nivelesJug2 < 3) { // y mi contrincante menos de 40, GANO EL GAME
                                    gameFinalizado = true;
                                    puntGameJug1++;
                                    System.out.println("\nGANADOR DEL PUNTO: " + jugador1);
                                } else if(nivelesJug2 == 3) { // y mi contrincante 40, VOY A AD
                                    nivelesJug1++;
                                    puntajeJug1 = puntajes[nivelesJug1]; //AD
                                } else if(nivelesJug2 == 4) { // y mi contrincante AD, mi contrincante baja a 40
                                    nivelesJug2--;
                                    puntajeJug2 = puntajes[nivelesJug2];
                                }
                            } else if (nivelesJug1 == 4) { //Si gano y estoy en AD
                                gameFinalizado = true; //GANO EL GAME
                                puntGameJug1++;
                                System.out.println("\nGANADOR DEL PUNTO: " + jugador1);
                            }
                        } else {
                            //System.out.println("Ganó " + jugador2);
                            if (nivelesJug2 < 3) {
                                nivelesJug2++;
                                puntajeJug2 = puntajes[nivelesJug2];
                            } else if (nivelesJug2 == 3) { //Si gano y tengo 40p
                                if(nivelesJug1 < 3) { // y mi contrincante menos de 40, GANO EL GAME
                                    gameFinalizado = true;
                                    puntGameJug2++;
                                    System.out.println("\nGANADOR DEL PUNTO: " + jugador2);
                                } else if(nivelesJug1 == 3) { // y mi contrincante 40, VOY A AD
                                    nivelesJug2++;
                                    puntajeJug2 = puntajes[nivelesJug2]; //AD
                                } else if(nivelesJug1 == 4) { // y mi contrincante AD, mi contrincante baja a 40
                                    nivelesJug1--;
                                    puntajeJug1 = puntajes[nivelesJug1];
                                }
                            } else if (nivelesJug2 == 4) { //Si gano y estoy en AD
                                gameFinalizado = true;
                                puntGameJug2++;
                                System.out.println("\nGANADOR DEL PUNTO: " + jugador2);
                            }
                        }
                
                        if(!gameFinalizado) {
                            System.out.println(jugador1 + " " + puntajeJug1 + "-" + puntajeJug2 + " " + jugador2);
                        }
                    }
                    
                    
                    
                    if (puntGameJug1 == 6) {
                        if (puntGameJug2 <= 4) {
                            setFinalizado = true;
                            puntSetJug1++;
                            resultadosJugador1.add(Integer.toString(puntGameJug1));
                            resultadosJugador2.add(Integer.toString(puntGameJug2));
                            System.out.println("\nGANADOR DEL SET: " + jugador1);
                        }
                    } else if (puntGameJug1 == 7) {
                        if (puntGameJug2 == 5) {
                            setFinalizado = true;
                            puntSetJug1++;
                            puntGameJug1 = 6;
                            resultadosJugador1.add(Integer.toString(puntGameJug1));
                            resultadosJugador2.add(Integer.toString(puntGameJug2));;
                            System.out.println("\nGANADOR DEL SET: " + jugador1);
                        }
                    }

                    if (puntGameJug2 == 6) {
                        if (puntGameJug1 <= 4) {
                            setFinalizado = true;
                            puntSetJug2++;
                            resultadosJugador1.add(Integer.toString(puntGameJug1));
                            resultadosJugador2.add(Integer.toString(puntGameJug2));
                            System.out.println("\nGANADOR DEL SET: " + jugador2);
                        }
                    } else if (puntGameJug2 == 7) {
                        if (puntGameJug1 == 5) {
                            setFinalizado = true;
                            puntSetJug2++;
                            puntGameJug2 = 6;
                            resultadosJugador1.add(Integer.toString(puntGameJug1));
                            resultadosJugador2.add(Integer.toString(puntGameJug2));
                            System.out.println("\nGANADOR DEL SET: " + jugador2);
                        }
                    }

                    if (tieBreakActivo) {
                        System.out.println("\nGames Ganados por " + jugador1 + ": 6 (" + (puntGameJug1 - 6) + ")");
                        System.out.println("Games Ganados por " + jugador2 + ": 6 (" + (puntGameJug2 - 6) + ")");
                    } else {
                        System.out.println("\nGames Ganados por " + jugador1 + ": " + puntGameJug1);
                        System.out.println("Games Ganados por " + jugador2 + ": " + puntGameJug2);
                    }
                    
                    if (puntGameJug1 >= 6 && puntGameJug2 >= 6) {
                        if (puntGameJug1 == 6 && puntGameJug2 == 6) {
                            System.out.println("\n******TIE BREAK******");
                            tieBreakActivo = true;
                        }
                        if (puntGameJug1 == 13) {
                            if (puntGameJug2 <= 11) {
                                setFinalizado = true;
                                puntSetJug1++;
                                tieBreakActivo = false;
                                resultadosJugador1.add("7(" + (puntGameJug1 - 6) + ")");
                                resultadosJugador2.add("6(" + (puntGameJug2 - 6) + ")");
                                System.out.println("\nGANADOR DEL SET: " + jugador1);
                            }
                        }
        
                        if (puntGameJug1 >= 12 && puntGameJug2 >= 12) {
                            if (Math.abs(puntGameJug1 - puntGameJug2) == 2 ) {
                                setFinalizado = true;
                                tieBreakActivo = false;
                                if (puntGameJug1 > puntGameJug2) {
                                    puntSetJug1++;
                                    System.out.println("\nGANADOR DEL SET: " + jugador1);
                                    resultadosJugador1.add("7(" + (puntGameJug1 - 6) + ")");
                                    resultadosJugador2.add("6(" + (puntGameJug2 - 6) + ")");
                                } else {
                                    puntSetJug2++;
                                    System.out.println("\nGANADOR DEL SET: " + jugador2);
                                    resultadosJugador1.add("6(" + (puntGameJug1 - 6) + ")");
                                    resultadosJugador2.add("7(" + (puntGameJug2 - 6) + ")");
                                }
                            }
                        }
        
                        if (puntGameJug2 == 13) {
                            if (puntGameJug1 <= 11) {
                                setFinalizado = true;
                                puntSetJug2++;
                                tieBreakActivo = false;
                                resultadosJugador1.add("6(" + (puntGameJug1 - 6) + ")");
                                resultadosJugador2.add("7(" + (puntGameJug2 - 6) + ")");
                                System.out.println("\nGANADOR DEL SET: " + jugador2);
                            }
                        }
        
                    }
        
                    
                }
                
                System.out.println("\nSETS Ganados por " + jugador1 + ": " + puntSetJug1);
                System.out.println("SETS Ganados por " + jugador2 + ": " + puntSetJug2);

                setsJugados++;
    
                if (setsJugados == sets) {
                    partidoFinalizado = true;
                } 
                if (sets == 3) {
                    if (Math.abs(puntSetJug1 - puntSetJug2) == 2 ) {
                        partidoFinalizado = true;
                    }
                }
                if (sets == 5) {
                    if (setsJugados == 4 && Math.abs(puntSetJug1 - puntSetJug2) == 2) {
                        partidoFinalizado = true;
                    }
                    if (Math.abs(puntSetJug1 - puntSetJug2) == 3 ) {
                        partidoFinalizado = true;
                    }
                }

            }

            String resJug1 = "";
            for(int i = 0; i < resultadosJugador1.size(); i++) {
                resJug1 += resultadosJugador1.get(i) + " ";
            }
            String resJug2 = "";
            for(int i = 0; i < resultadosJugador2.size(); i++) {
                resJug2 += resultadosJugador2.get(i) + " ";
            }

            System.out.println("\n-----RESULTADOS DEL PARTIDO-----");
            System.out.println(jugador1 + "\t" + resJug1);
            System.out.println(jugador2 + "\t" + resJug2);
            
            System.out.println("\nTorneo: " + torneo);
            if(puntSetJug1 > puntSetJug2) {
                System.out.println("GANADOR DEL PARTIDO: " + jugador1);
            } else {
                System.out.println("GANADOR DEL PARTIDO: " + jugador2);
            }

            System.out.println("\n-----PARTIDO FINALIZADO-----");
            System.out.println("\nDESEA JUGAR LA REVANCHA?");
            while (opc != 1 && opc != 2) {
                System.out.println("Marque (1) para SI, o (2) para NO:");
                opc = teclado.nextInt();
            }
        }

        System.out.println("\n!HASTA LUEGO Y QUE TENGAS UN BUEN DÍA!");
        System.out.println("\n-----SIMULACIÓN FINALIZADA-----");
    }
}
