class Lenkeliste<T> implements Liste<T> {
  Node start;
  Node nodePos;
  Node sist;
  T returneres;
  int stoerrelse;

  // jeg lager en klasse for noder til den lenkede listen.
  class Node{
    T data;
    Node neste;

    public Node(T data){
      this.data = data;
    }

    public void settNeste(Node n){
      neste = n;
    }

    public Node hentNeste(){
      return neste;
    }

    public T hentData(){
      return data;
    }
  }
  // setter instansvariabler til verdiene som behandles i koden nedenfor.
  public Lenkeliste(){
    start = null;
    stoerrelse = 0;
    nodePos = start;
  }

  // denne metoden legger til nye elementer i slutten av listen.
  public void leggTil(T x){
    Node nyNode = new Node(x);
    // haandterer begynnelsen av lista paa spesiell maate.
    Node p = start;
    if (start == null){
      start = new Node(x);
      stoerrelse++;
    }
    // bruker while lokke for aa finne siste plassen i den lenkede lista.
    else{
      while (p.hentNeste()!=null){
        p = p.hentNeste();
      }
      p.settNeste(nyNode);
      stoerrelse++;
    }
  }

  // denne metoden fjerner den foerste noden som ble lagt inn, og returnerer dataverdien
  public T fjern(){
    // hvis listen er tom returneres en streng som informerer om dette
    if(start == null) {
  		System.out.println("listen er tom");
  		System.exit(1);
  	}
      // soerger for aa sette startreferansen til neste etter noden som slettes.
      // reduserer stoerrelse.
      returneres = start.hentData();
      start = start.neste;
      stoerrelse--;
      return returneres;
	}

  // overlaster leggTil metoden ovenfor dersom det blir gitt to argumenter.
  // traverserer listen ved nestemetoden fra nodeklassen antall ganger gitt i argumentplassen
  // legger inn node og setter nye referanser slik at lenken er ubrutt.
  public void leggTil(int pos, T x) throws UgyldigListeIndeks {
    Node p = start;
    Node nyNode = new Node(x);

    try {
    // kaster unntak dersom indeksen overstiger stoerrelsen
    if (pos>stoerrelse) throw new UgyldigListeIndeks(pos);

    // begynnelsen og slutten av listen er spesialtilfeller som gir
    // nullpekerfeil dersom de ikke tar haand om. Derfor har jeg laget en
    // rekke if-sjekker som soerger for at referanser blir gitt paa en god maate.
    if (start==null){
      start = nyNode;
      stoerrelse++;
      return;
    }

    else if (pos==0){
      nyNode.settNeste(p);
      start = nyNode;
      stoerrelse++;
      return;
    }

    else {
      if (pos<stoerrelse){
        for (int i=0; i<pos-1; i++){
          p = p.neste;
        }
        nodePos = p.neste;
        p.settNeste(nyNode);
        nyNode.settNeste(nodePos);
        stoerrelse++;
        return;
      }
      else if (pos==stoerrelse){
        for (int i=0; i<pos-1; i++){
          p = p.neste;
        }
        p.settNeste(nyNode);
        stoerrelse++;
        return;
      }
    }
    }
    catch (UgyldigListeIndeks e){
      System.out.println("feil indeks");
    }
  }

  // returnerer stoerrelsen på listen
  public int stoerrelse(){
    return stoerrelse;
  }

  // gaar gjennom liste og finner riktig element
  public T hent(int pos) throws UgyldigListeIndeks {
    Node p = start;

    // kaster unntak dersom det ikke er noen elementer aa slette
    if (p==null || pos<0 || pos>stoerrelse) throw new UgyldigListeIndeks(pos);


    if (pos==0){
      returneres = p.hentData();
      return returneres;
    }

    else{
      if (pos<=stoerrelse){
        for (int i=0; i<=pos; i++){
          returneres = p.hentData();
          p = p.neste;
        }
      }
      return returneres;
    }
  }


  // overskriver det som var på denne plassen fra foer.
  public void sett(int pos, T x) throws UgyldigListeIndeks {
    Node p = start;
    Node nyNode = new Node(x);

    // kaster unntak dersom bruker forsoeker aa sette paa feil indeks.
    if (pos>stoerrelse) throw new UgyldigListeIndeks(pos);


    if (pos==0) {
      nodePos = start.neste;
      start = nyNode;
      start.settNeste(nodePos);
    }
    // her er det viktig aa soerge for at referansene blir riktig.
    // if-sjekkene identifiserer plassen foer den som overskrives.
    // plassen foer brukes til aa finne noden to plasser foran,
    // deretter brukes denne noden til aa sette referanse til den nye noden,
    // som deretter lenkes til noden to plasser foran slik at lenken er ubrutt.
    else if (pos<stoerrelse){
      for (int i=0; i<pos-1; i++){
        p = p.neste;
      }
      nodePos = p.neste.neste;
      p.settNeste(nyNode);
      p.neste.settNeste(nodePos);
    }
    else if (pos==stoerrelse){
      for (int i=0; i<stoerrelse-1; i++){
        p = p.neste;
      }
      p.settNeste(nyNode);
    }
  }

  // traverserer på samme måte som de andre indeksmetodene. Bruker samme
  // if-test maskineri som i metodene ovenfor. Minker ogsaa stoerrelse.
  public T fjern(int pos) throws UgyldigListeIndeks {
    Node p = start;

    // kaster unntak dersom det ikke er noen elementer aa slette.
    if (p==null || pos<0 || pos>stoerrelse) throw new UgyldigListeIndeks(pos);

    if (p.neste==null){
      returneres = p.hentData();
      p = null;
    }

    else {
      if (pos<stoerrelse){
        for (int i=0; i<pos-1; i++){
          p = p.neste;
        }
        returneres = p.neste.hentData();
        p.settNeste(p.neste.hentNeste());
      }

      else if (pos==stoerrelse){
          while (p.neste.hentNeste()!=null){
            p = p.neste;
          }
          returneres = p.neste.hentData();
          p.settNeste(null);
      }
    }
    stoerrelse--;
    return returneres;
  }
}
