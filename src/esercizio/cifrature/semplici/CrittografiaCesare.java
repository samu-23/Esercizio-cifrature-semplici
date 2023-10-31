package esercizio.cifrature.semplici;

/**
 *
 * @author Samuele Esposito
 */
public class CrittografiaCesare {
    
    //Altri metodi 
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
    
}
