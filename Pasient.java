public class Pasient {

  private String navn;
  private String foedselsnr;
  private int unikID;
  private static int idTeller;
  Stabel<Resept> reseptliste;

  public Pasient(String n, String fnr) {
    navn = n;
    foedselsnr = fnr;
    unikID = idTeller;
    idTeller++;
  }

  public int hentID() {
    return unikID;
  }

  public String hentNavnOgFdn(){
    return navn + "," + foedselsnr;
  }

}
