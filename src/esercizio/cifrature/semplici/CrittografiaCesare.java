package esercizio.cifrature.semplici;

/**
 *
 * @author Samuele Esposito
 */
public class CrittografiaCesare {
    
    //Attributi
    String messaggio;
    int chiave;
    
    //Costruttore
    public CrittografiaCesare (String messaggio, int chiave) {
        this.messaggio = messaggio;
        this.chiave = chiave;
    }
    
    //Metodi set & get
    public String getMessaggio () {
        return this.messaggio;
    }
    
    public void setMessaggio (String messaggio) {
        this.messaggio = messaggio;
    }
    
    public int getChiave () {
        return this.chiave;
    }
    
    public void setChiave (int chiave) {
        this.chiave = chiave;
    }
    
    //Altri metodi 
    public String decrittaMessaggio () {
        String messaggioDecrittato = messaggio;
        char [] vetChar = this.messaggio.toCharArray();
        char tempChar;
        int lunghezza = this.messaggio.length();
        int [] vetAscii = new int [lunghezza];
        
        for (int i = 0; i < lunghezza; i++) {
            vetAscii[i] = vetChar[i] - this.chiave;
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
    public String crittaMessaggio () {
        String messaggioCrittato = messaggio;
        char [] vetChar = this.messaggio.toCharArray();
        char tempChar;
        int lunghezza = this.messaggio.length();
        int [] vetAscii = new int [lunghezza];
        
        for (int i = 0; i < lunghezza; i++) {
            vetAscii[i] = vetChar[i] + this.chiave;
        }
        
        for (int i = 0; i < lunghezza; i++) {
            tempChar = (char) vetAscii[i];
            vetChar[i] = tempChar;
        }
        
        
        messaggioCrittato = String.valueOf(vetChar);
        
        return messaggioCrittato;
    }
    
}
