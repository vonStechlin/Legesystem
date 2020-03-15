class Lege{
  String navn;
  int kontrollId;
  public Lege(String navn){
    navn = navn;
  }

  public String hentNavn(){
    return navn;
  }
  // toString metode for oversiktlig utskrift.
  @Override
  public String toString(){
    return navn + ", kontrollId: " + kontrollId;
  }
}
