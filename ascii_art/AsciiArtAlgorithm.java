package ascii_art;
/**
 * Responsible for a single run of the algorithm: Singleton Class
 * */
public class AsciiArtAlgorithm {
    private static AsciiArtAlgorithm artAlgorithm = new AsciiArtAlgorithm();

    //receives: pic, resolution, char set
    public static AsciiArtAlgorithm getInstance(){
        if (artAlgorithm == null){
            artAlgorithm = new AsciiArtAlgorithm();
        }
        return artAlgorithm;
    }

    //must not change the signature
    //returns array of chars representing pic (same pic received in the constructor)
    public char [][] run(){

    }
}
