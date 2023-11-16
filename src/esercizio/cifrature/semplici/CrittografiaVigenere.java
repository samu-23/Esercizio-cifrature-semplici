package esercizio.cifrature.semplici;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Samuele Esposito
 */
public class CrittografiaVigenere {
    
    //Altri metodi 
    public static String crittaMessaggio (String messaggio, String chiave) {
        String messaggioCrittato = messaggio;
        char [] vetCharM = messaggio.toCharArray();
        char tempCharM;
        int lunghezzaM = messaggio.length();
        int [] vetAsciiM = new int [lunghezzaM];
        
        char [] vetCharC = chiave.toCharArray();
        int lunghezzaC = chiave.length();
        int [] vetAsciiC = new int [lunghezzaC];
        
        int j = 0;
        
        for (int i = 0; i < lunghezzaC; i++) {
            vetAsciiC[i] = vetCharC[i];
        }
        
        for (int i = 0; i < lunghezzaM; i++) {
            vetAsciiM[i] = vetCharM[i] + vetAsciiC[j];
            j += 1;
            if (j == lunghezzaC) {
                j = 0;
            }
        }
        
        for (int i = 0; i < lunghezzaM; i++) {
            tempCharM = (char) vetAsciiM[i];
            vetCharM[i] = tempCharM;
        }
        
        messaggioCrittato = String.valueOf(vetCharM);
        
        
        return messaggioCrittato;
    }
    public static String decrittaMessaggio (String messaggio, String chiave) {
        String messaggioDecrittato = messaggio;
        char [] vetCharM = messaggio.toCharArray();
        char tempCharM;
        int lunghezzaM = messaggio.length();
        int [] vetAsciiM = new int [lunghezzaM];
        
        char [] vetCharC = chiave.toCharArray();
        int lunghezzaC = chiave.length();
        int [] vetAsciiC = new int [lunghezzaC];
        
        int j = 0;
        
        for (int i = 0; i < lunghezzaC; i++) {
            vetAsciiC[i] = vetCharC[i];
        }
        
        for (int i = 0; i < lunghezzaM; i++) {
            vetAsciiM[i] = vetCharM[i] - vetAsciiC[j];
            j += 1;
            if (j == lunghezzaC) {
                j = 0;
            }
        }
        
        for (int i = 0; i < lunghezzaM; i++) {
            tempCharM = (char) vetAsciiM[i];
            if (tempCharM == '\u001d') {
                tempCharM = ' ';
            }
            vetCharM[i] = tempCharM;
        }
        
        messaggioDecrittato = String.valueOf(vetCharM);
        
        
        
        return messaggioDecrittato;
    }
    
    public static ArrayList<String> bruteForce(String messaggio) {
        
        ArrayList<String> listaDecifrata = new ArrayList<String>();
        
        String chiave = "AAAAA";
        int [] vetValidi = new int [3];
        int [][] matValidi = new int [3][10];
        boolean sentinel = true;
        char iniziale = '<';
        char finale = '>';
        int Z_val = 90;
        int X_val = 88;
        int R_val = 82;
        int a_val = 97;
        int r_val = 114;
        int z_val = 122;
        int temp;
        
        char [] vetCharM = messaggio.toCharArray();
        int lunghezzaM = messaggio.length();
        int [] vetAsciiM = new int [lunghezzaM];
        
        for (int i = 0; i < lunghezzaM; i++) {
            vetAsciiM[i] = vetCharM[i];
        }
        
        
        char [] vetCharC = chiave.toCharArray();
        int lunghezzaC = chiave.length();
        int [] vetAsciiC = new int [lunghezzaC];
        
        for (int i = 0; i < lunghezzaC; i++) {
            vetAsciiC[i] = vetCharC[i];
        }
        
        //Prima lettera della chiave
        do {
            if (vetAsciiM[5] - vetAsciiC[0] == 32) {
                vetCharC[0] = (char) vetAsciiC[0];
                iniziale = vetCharC[0];
                sentinel = false;
            } else vetAsciiC[0] += 1;
            
        } while (sentinel);
        
        sentinel = true;
        
        //Ultima lettera della chiave
        do {
            if (vetAsciiM[4] - vetAsciiC[4] == 58) {
                vetCharC[4] = (char) vetAsciiC[4];
                finale = vetCharC[4];
                sentinel = false;
            } else vetAsciiC[4] += 1;
            
        } while (sentinel);
         //Trova le lettere di mezzo che danno 0 come risultato alla sottrazione tra messaggio e chiave (caratteri interi dell'agente)
        for (int i = 0; i < 3; i++) {
            
            sentinel = true;
            do {
                if (vetAsciiM[i+1] - vetAsciiC[i+1] == 48) {
                    vetValidi[i] = vetAsciiC[i+1];
                    sentinel = false;
                } else if (vetAsciiC[i+1] == Z_val + 1) {
                    vetAsciiC[i+1] = a_val;
                } else vetAsciiC[i+1] += 1;
            } while (sentinel);
        }
        
        
        /*
        Partire dal valore della lettera che dà zero e controllare
        se minore di R (82) 
        altrimenti controllare se maggiore o uguale a 'R' (82) e minore di 'X' (88)
        altrimenti controllare se maggiore o uguale a 'X' (88) e minore di 'Z' (90) per gestire i possibili caratteri minuscoli
        altrimenti controllare se maggiore o uguale a 'a' (97) e minore di 'r' (114)
        altrimenti controllare se maggiore o uguale a 'r' (114)
        se uno dei caratteri calcolati non è considerato come possibile chiave viene inserito '0' nella matrice
        se un carattere della chiave risulta non appartenere ad una lettera viene inserito '1' nella matrice
        */
        
        for (int i = 0; i < 3; i++) {
            
            temp = vetValidi[i];
            if (temp < R_val) {
                for (int j = 0; j < 10; j++) {
                    matValidi[i][j] = temp + j;
                }
            } else if (temp >= R_val && temp < X_val){
                for (int j = 0; j < 10; j++) {
                    if (temp + j <= Z_val) {
                        matValidi[i][j] = temp + j;
                    } else {
                        matValidi[i][j] = 0;
                    }
                }
            } else if (temp >= X_val && temp <= Z_val) {
                for (int j = 0; j < 10; j++) {
                    if (temp + j <= Z_val || temp + j >= a_val) {
                        matValidi[i][j] = temp + j;
                    } else {
                        matValidi[i][j] = 0;
                    }
                }
            } else if (temp >= a_val && temp < r_val) {
                for (int j = 0; j < 10; j++) {
                    matValidi[i][j] = temp + j;
                }
            } else if (temp >= r_val) {
                for (int j = 0; j < 10; j++) {
                    if (temp + j <= z_val) {
                        matValidi[i][j] = temp + j;
                    } else {
                        matValidi[i][j] = 0;
                    }
                }
            } else {
                for (int j = 0; j < 10; j++) {
                    matValidi[i][j] = 0;
                }
            }
        }
        
        //Generazione di tutte le combinazioni
        
        listaDecifrata = CrittografiaVigenere.generaCombinazioni(matValidi, iniziale, finale);
        
        return listaDecifrata;
    } 
    
    private static ArrayList<String> generaCombinazioni(int[][] matValidi, char A, char B) {
        ArrayList<String> risultato = new ArrayList<>();

        // Usa una coda per gestire le combinazioni in modo iterativo
        Queue<String> coda = new LinkedList<>();
        coda.offer("");

        while (!coda.isEmpty()) {
            String parziale = coda.poll();
            int riga = parziale.length();

            // Se la combinazione è completa, aggiungila all'ArrayList
            if (riga == matValidi.length) {
                risultato.add(A + parziale + B);
            } else {
                // Genera le combinazioni per la riga corrente, evitando il valore 0
                for (int colonna = 0; colonna < matValidi[riga].length; colonna++) {
                    if (matValidi[riga][colonna] != 0) {
                        // Aggiungi la combinazione successiva alla coda
                        coda.offer(parziale + (char) matValidi[riga][colonna]);
                    }
                }
            }
        }

        return risultato;
    }
    
}