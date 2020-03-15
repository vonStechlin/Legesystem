class SortertLenkeliste <T extends Comparable<T>> extends Lenkeliste<T> {
  // her bestemmer jeg hvordan compareTo metoden skal fungere. Jeg velger
  // aa ta to argumenter i funksjonen. Metoden fungerer paa strenger ogsaa.
  public int compareTo(T y){
    if (this.compareTo(y)>0) return 1;
    if (this.compareTo(y)<0) return -1;
    return 0;
  }
  // overskriver leggTil metoden med en sorteringsmekanisme som anvender
  // compareTo. Her rakk jeg ikke aa finne en god maate for aa lose problemet.
  // haaper jeg har et godt utgangspunkt i alle fall.
  public void leggTil(T x) throws UgyldigListeIndeks {
    Node p = start;

    if (stoerrelse == 0) {
      start= new Node(x);
      stoerrelse++;
      return;
    }
    //traverserer listen og henter verdiene fra sammenligningene. Finner
    //ut hvor hoyt i listen noden skal ligge.
    else {
      for (int i=0; i<stoerrelse; i++) {
        if (x.compareTo(p.hentData())<0){
          super.leggTil((i), x);
          return;
        }
      p = p.neste;
      }
      super.leggTil(stoerrelse, x);
    }
  }


  public T fjern() throws UgyldigListeIndeks {
    Node p = start;
    int verdi=0;
    int plass=0;
    //foerste element i lista maa spesialbehandles
    if (p==null) throw new UgyldigListeIndeks(0);
      if (p.neste==null){
        return super.fjern();
      }

      else{
        return super.fjern(stoerrelse);
      }
    }

    public void sett(int pos, T x) throws UnsupportedOperationException{
      throw new UnsupportedOperationException();
    }

    public void leggTil(int pos, T x) throws UnsupportedOperationException{
      throw new UnsupportedOperationException();
    }

  }
