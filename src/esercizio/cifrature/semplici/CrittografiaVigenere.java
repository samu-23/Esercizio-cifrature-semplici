package esercizio.cifrature.semplici;

/**
 *
 * @author Samuele Esposito
 */
public class CrittografiaVigenere {
    
    //Altri metodi 
    public static String decrittaMessaggio (String messaggio, String chiave) {
        String messaggioDecrittato = messaggio;
        char [] vetCharM = messaggio.toCharArray();
        char tempCharM;
        int lunghezzaM = messaggio.length();
        int [] vetAsciiM = new int [lunghezzaM];
        
        char [] vetCharC = chiave.toCharArray();
        char tempCharC;
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
    public static String crittaMessaggio (String messaggio, String chiave) {
        String messaggioCrittato = messaggio;
        char [] vetCharM = messaggio.toCharArray();
        char tempCharM;
        int lunghezzaM = messaggio.length();
        int [] vetAsciiM = new int [lunghezzaM];
        
        char [] vetCharC = chiave.toCharArray();
        char tempCharC;
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
    
}