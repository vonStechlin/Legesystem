import java.util.ArrayList;

class Legemiddel{
  // lager en static variabel for aa telle nye instanser og gi ut ID.
  private static int idRegister = 0;
  // lager instansvariabler for navn, pris og virkestoff og ID.
  private String navn;
  private Double pris;
  private Double virkestoff;
  private int id;

  // konstruktør for å ta inn verdier til instansvariablene
  public Legemiddel(String n, Double p, Double v) {
    navn = n;
    pris = p;
    virkestoff = v;
    idRegister++;
    id = idRegister;
  }
  // offentlige metoder
  public int hentId(){
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public Double hentPris(){
    return pris;
  }

  public Double hentVirkestoff(){
    return virkestoff;
  }
  public Double settNyPris(Double nyPris){
    pris = nyPris;
    return pris;
  }

  @Override
  public String toString(){
    return navn + ", pris: " + pris + " kr, virkestoff: " + virkestoff + ", id: " + id;
  }
}
