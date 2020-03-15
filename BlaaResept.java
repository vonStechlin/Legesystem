class BlaaResept extends Resept{
  // utfyller fargemetoden
  public BlaaResept(Legemiddel dopRef, Lege legeRef, Pasient pasientRef, int reit){
    super(dopRef, legeRef, pasientRef, reit);
    farge = "blaa";
  }
  public String farge(){
    return farge;
  }
  public Double prisAaBetale(){
    return (legemiddelRef.hentPris()*0.25);
  }
}
