package esercizio.cifrature.semplici;

/**
 *
 * @author Samuele Esposito
 */
public class CrittografiaCesare {
    
    //Altri metodi 
    public static String crittaMessaggio (String messaggio, int chiave) {
        String messaggioCrittato = messaggio;
        char [] vetChar = messaggio.toCharArray();
        char tempChar;
        int lunghezza = messaggio.length();
        int [] vetAscii = new int [lunghezza];
        
        for (int i = 0; i < lunghezza; i++) {
            vetAscii[i] = vetChar[i] + chiave;
        }
        
        for (int i = 0; i < lunghezza; i++) {
            tempChar = (char) vetAscii[i];
            vetChar[i] = tempChar;
        }
        
        
        messaggioCrittato = String.valueOf(vetChar);
        
        return messaggioCrittato;
    }
    
    public static String decrittaMessaggio (String messaggio, int chiave) {
        String messaggioDecrittato = messaggio;
        char [] vetChar = messaggio.toCharArray();
        char tempChar;
        int lunghezza = messaggio.length();
        int [] vetAscii = new int [lunghezza];
        
        for (int i = 0; i < lunghezza; i++) {
            vetAscii[i] = vetChar[i] - chiave;
        }
        
        for (int i = 0; i < lunghezza; i++) {
            tempChar = (char) vetAscii[i];
            if (tempChar == '\u001d') {
                tempChar = ' ';
            }
            vetChar[i] = tempChar;
        }
        
        messaggioDecrittato = String.valueOf(vetChar);
        
        return messaggioDecrittato;
    }
    
    public static String bruteForce(String messaggio) {
        String messaggioOriginale = messaggio;
        boolean sentinel = true;
        int chiave = 0;
        char [] vetChar = messaggio.toCharArray();
        int lunghezza = messaggio.length();
        int [] vetAscii = new int [lunghezza];
        char tempChar;
        do {
            
            if (vetChar[4] - chiave == 58) {
                sentinel = false;
            } else chiave += 1;
            
        } while (sentinel);
        
        
        for (int i = 0; i < lunghezza; i++) {
            vetAscii[i] = vetChar[i] - chiave;
        }
        
        for (int i = 0; i < lunghezza; i++) {
            tempChar = (char) vetAscii[i];
            if (tempChar == '\u001d') {
                tempChar = ' ';
            }
            vetChar[i] = tempChar;
        }
        
        messaggioOriginale = String.valueOf(vetChar);
        
        return messaggioOriginale;
    }
    
}
