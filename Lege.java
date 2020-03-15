public class Lege implements Comparable<Lege> {

  private String navn;
  private Lenkeliste<Resept> utskrevedeResepter;

  public Lege(String n) {
    navn = n;
  }

  public String hentNavn() {
    return navn;
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    if (legemiddel instanceof Narkotisk && !(this instanceof Lege)) throw new UlovligUtskrift(this, legemiddel, pasient.hentID()); //dette virker helt gaerent
    return new HvitResept(legemiddel, this, pasient, reit);
  }

  public Militaerresept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    if (legemiddel instanceof Narkotisk && !(this instanceof Lege)) throw new UlovligUtskrift(this, legemiddel, pasient.hentID()); //dette virker helt gaerent
    return new Militaerresept(legemiddel, this, pasient, reit);
  }

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
    if (legemiddel instanceof Narkotisk && !(this instanceof Lege)) throw new UlovligUtskrift(this, legemiddel, pasient.hentID()); //dette virker helt gaerent
    return new PResept(legemiddel, this, pasient);
  }

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
    if (legemiddel instanceof Narkotisk && !(this instanceof Lege)) throw new UlovligUtskrift(this, legemiddel, pasient.hentID()); //dette virker helt gaerent
    return new BlaaResept(legemiddel, this, pasient, reit);
  }

  @Override
  public String toString() {
    String str = "Klasse: " + getClass().getName() + "\nNavn: " + navn;
    return str;
  }

  public int compareTo(Lege annen) {
    if (navn.compareTo(annen.hentNavn()) > 0) return 1;
    if (navn.compareTo(annen.hentNavn()) < 0) return -1;
    return 0;
  }
}
