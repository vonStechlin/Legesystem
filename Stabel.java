class Stabel<T> extends Lenkeliste<T> {
  // denne metoden speiler leggTil metoden i Lenkeliste.
  public void leggPaa(T x){
    this.leggTil(x);
  }
  // denne metoden er annerledes. Her fjernes elementet som er bakerst i lista. 
  public T taAv(){
    Node p = start;
    if (start == null){
      System.out.println("lista er tom");
    }
    else{
      while (p.hentNeste()!=null){
        p = p.hentNeste();
      }
      returneres = p.hentData();
      p = null;
      stoerrelse--;
    }
    return returneres;
  }
}
