class Narkotisk extends Legemiddel{
  int styrke;
  public Narkotisk(String n, Double p, Double v, int s){
    super(n, p, v);
    styrke = s;
  }

  public int hentNarkotiskStyrke(){
    return styrke;
  }
}
