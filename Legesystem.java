// import java.io.*;
// import java.util.*;
//
// public class Legesystem {
//   Lenkeliste<Legemiddel> legemiddelliste;
//   Lenkeliste<Resept> reseptliste;
//   SortertLenkeliste<Lege> legeliste;
//   Lenkeliste<Pasient> pasientliste;
//
  // public void lesFraFiL(File fil) throws FileNotFoundException {
  //   Scanner in = new Scanner(fil);
  //   Scanner in2 = new Scanner(fil);
  //   while (in.hasNextLine()) {
  //     String linje = in.nextLine();
  //     if (linje.split(" ")[1] == "Pasienter") {
  //       while (in2.nextLine().split(" ")[0] != "#") {
  //         linje = in2.nextLine();
  //         String navn = linje.split(" ")[0];
  //         String foedselsnr = linje.split(" ")[1];
  //         Pasient pasient = new Pasient(navn, foedselsnr);
  //         pasientliste.leggTil(pasient);
        // if (linje.split(" ")[1] == "Legemidler") {
        //   while (in2.nextLine().split(" ")[0] != "#") {
        //     linje = in2.nextLine();
        //     navn = linje.split(" ")[0];
        //     foedselsnr = linje.split(" ")[1];
        //     pasient = new Pasient(navn, foedselsnr);
        //     pasientliste.leggTil(pasient);
        //   }
        // }
  //       }
  //     }
  //   }
  // }

import java.io.*;
import java.util.*;
import java.lang.*;

public class Legesystem {
  static Lenkeliste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
  static SortertLenkeliste<Resept> reseptliste = new SortertLenkeliste<Resept>();
  static SortertLenkeliste<Lege> legeliste = new SortertLenkeliste<Lege>();
  static Lenkeliste<Pasient> pasientliste = new Lenkeliste<Pasient>();

  public static void main(String[] args) throws Exception {
    try {
      File fil = new File("inndata.txt");
      Scanner in3 = new Scanner(fil);
      Scanner in2 = new Scanner(fil);
      while (in3.hasNextLine()) {
        String[] linje = in3.nextLine().split(" ");
        // System.out.println(in3.nextLine());
        if (linje[0].equals("#")) {

          if (linje[1].equals("Pasienter")) {
            String[] nesteLinje = in3.nextLine().split(",");
            while (!(nesteLinje[0].charAt(0) == '#')) {
              Pasient pasient = new Pasient(nesteLinje[0], nesteLinje[1]);
              pasientliste.leggTil(pasient);
              nesteLinje = in3.nextLine().split(",");
            }
          }

          if (linje[1].equals("Leger")) {
            String[] nesteLinje = in3.nextLine().split(",");
            while (!(nesteLinje[0].charAt(0) == '#')) {
              if (!(nesteLinje[1].equals("0"))) {
                String spesialistIDStr = nesteLinje[1].trim();
                int spesialistID = Integer.parseInt(spesialistIDStr);
                Lege spesialist = new Spesialist(nesteLinje[0], spesialistID);
                legeliste.leggTil(spesialist);
              } else {
                Lege lege = new Lege(nesteLinje[0]);
                legeliste.leggTil(lege);
              }
              nesteLinje = in3.nextLine().split(",");
            }
          }
        }
      }
    } catch (Exception exception) {System.out.println(exception);}

    Scanner in = new Scanner(System.in);
    System.out.println("Starter legesystem.");
    System.out.print(".");
    Thread.sleep(500);
    System.out.print(".");
    Thread.sleep(500);
    System.out.println(".");
    Thread.sleep(500);
    System.out.println();
    System.out.println("HOVEDMENY");
    boolean avslutte = false;
    while (!avslutte) {
      System.out.println();
      System.out.println("0: Skriv ut oversikt");
      System.out.println("1: Opprett og legg til lege");
      System.out.println("2: Opprett og legg til pasient");
      System.out.println("3: Opprett og legg til resept");
      System.out.println("4: Opprett og legg til legemiddel");
      System.out.println("5: Bruk resept");
      System.out.println("6: Skriv ut statistikk");
      System.out.println("7: Skriv data til fil");
      System.out.println("8: Avslutt program");
      String svar = in.next();
      if (svar.equals("0")) skrivUtOversikt();
      else if (svar.equals("1")) leggTilLege();
      else if (svar.equals("2")) leggTilPasient();
      else if (svar.equals("3")) {
        try { leggTilResept(); }
        catch (Exception exception) {
          System.out.println(exception);
        }
      }
      else if (svar.equals("4")) leggTilLegemiddel();
      else if (svar.equals("5")) brukResept();
      else if (svar.equals("6")) skrivUtStatistikk();
      // else if (svar.equals("7")) skrivTilFil();
      else if (svar.equals("8")) avslutte = true;
      else {
        System.out.println();
        System.out.println("Ugyldig input! Proev igjen.");
        Thread.sleep(1000);
      }
    }
    System.out.println("Programmet avsluttes.");
    Thread.sleep(2000);
  }

  private static void skrivUtLeger() {
    int teller = 0;
    for (Lege l : legeliste) {
      System.out.println(teller + ": " + l.hentNavn());
      teller++;
    }
  }

  private static void skrivUtPasienter() {
    int teller = 0;
    for (Pasient p : pasientliste) {
      System.out.println(teller + ": " + p);
      teller++;
    }
  }

  private static void skrivUtLegemidler() {
    int teller = 0;
    for (Legemiddel l : legemiddelliste) {
      System.out.println(teller + ": " + l.hentNavn());
      teller++;
    }
  }

  private static void skrivUtResepter() {
    int teller = 0;
    for (Resept r : reseptliste) {
      System.out.println(teller + ": " + r);
      teller++;
    }
  }

  static void skrivUtOversikt() {
    System.out.println("### LEGER ###");
    skrivUtLeger();
    System.out.println();
    System.out.println("### LEGEMIDLER ###");
    skrivUtLegemidler();
    System.out.println();
    System.out.println("### PASIENTER ###");
    skrivUtPasienter();
    System.out.println();
    System.out.println("### RESEPTER ###");
    skrivUtResepter();
    System.out.println();
  }

  static void leggTilLege() {
    Scanner in = new Scanner(System.in);
    String navn;
    System.out.println("Du har valgt aa legge til en lege. Vennligst oppgi legens navn.");
    navn = in.next();
    Lege nyLege = new Lege(navn);
    legeliste.leggTil(nyLege);
    System.out.println("Legen " + nyLege.hentNavn() + " er lagt inn i systemet!");
  }

  static void leggTilResept() throws UlovligUtskrift, NumberFormatException {
    Scanner in = new Scanner(System.in);
    int svar;

    System.out.println("Hvilken lege skal skrive ut resept?");
    skrivUtLeger();
    svar = Integer.parseInt(in.next());
    Lege utskrivendeLege = legeliste.hent(svar);

    System.out.println("For hvilket legemiddel skal resepten gjelde?");
    skrivUtLegemidler();
    svar = Integer.parseInt(in.next());
    Legemiddel legemiddel = legemiddelliste.hent(svar);

    System.out.println("For hvilken pasient skal resepten gjelde?");
    skrivUtPasienter();
    svar = Integer.parseInt(in.next());
    Pasient pasient = pasientliste.hent(svar);

    System.out.println("Tast 'b' for blaa resept. Tast 'h' for hvit resept.");
    String input = in.next().trim(); //hvordan gjoere om til smaa bokstaver???

    if (input.equals("b")) {
      System.out.println("Hvor mange reit skal resepten ha?");
      int reit = Integer.parseInt(in.next());
      Resept resept = utskrivendeLege.skrivBlaaResept(legemiddel, pasient, reit);
      reseptliste.leggTil(resept);
    }
    else if (input.equals("h")) {
      System.out.println("Tast 'v' for vanlig resept; tast 'm' for militaerresept" +
      " tast 'p' for p-resept.");
      input = in.next().trim();

      if (input.equals("p")) {
        Resept resept = utskrivendeLege.skrivPResept(legemiddel, pasient);
        reseptliste.leggTil(resept);
      }
      else {
        System.out.println("Hvor mange reit skal resepten ha?");
        int reit = Integer.parseInt(in.next());

        if (input.equals("v")) {
          Resept resept = utskrivendeLege.skrivHvitResept(legemiddel, pasient, reit);
          reseptliste.leggTil(resept);
        }

        if (input.equals("m")) {
          Resept resept = utskrivendeLege.skrivMilitaerResept(legemiddel, pasient, reit);
          reseptliste.leggTil(resept);
        }
      }
    }
  }

  static void leggTilPasient() {
    Scanner in = new Scanner(System.in);
    System.out.println("Du har valg å legge til ny pasient. Oppgi pasientens navn.");
    String navn = in.next();
    System.out.println("Oppgi pasientens foedselsnr.");
    String fnr = in.next();
    Pasient nyPasient = new Pasient(navn, fnr);
    pasientliste.leggTil(nyPasient);
    System.out.println("Pasienten " + nyPasient + " er lagt inn i systemet!");
  }

  static void leggTilLegemiddel() throws NumberFormatException {
    Scanner in = new Scanner(System.in);

    System.out.println("Du har valgt aa legge til et nytt legemiddel. Oppgi legemidlets navn.");
    String navn = in.next();

    System.out.println("Oppgi mengden virkestoff i ml.");
    double virkestoff = Double.parseDouble(in.next());

    System.out.println("Oppgi prisen.");
    double pris = Double.parseDouble(in.next());

    System.out.println("Tast 0 for vanlig legemiddel. \nTast 1 for vanedannende legemiddel. \nTast 2 for narkotisk legemiddel.");
    String svar = in.next();

    if (svar.equals("0")) {
      Legemiddel vanlig = new Vanlig(navn, pris, virkestoff);
      legemiddelliste.leggTil(vanlig);
    }

    else if (svar.equals("1")) {
      System.out.println("Oppgi vanedannnde styrke");
      int styrkeV = Integer.parseInt(in.next());
      Legemiddel vanedannende = new Vanedannende(navn, pris, virkestoff, styrkeV);
      legemiddelliste.leggTil(vanedannende);
    }

    else if (svar.equals("2")) {
      System.out.println("Oppgi narkotisk styrke");
      int styrkeN = Integer.parseInt(in.next());
      Legemiddel narkotisk = new Narkotisk(navn, pris, virkestoff, styrkeN);
      legemiddelliste.leggTil(narkotisk);
    }
  }

  static void brukResept() {
    Scanner in = new Scanner(System.in);

    System.out.println("Hvilken pasient vil du se resepter for?");
    skrivUtPasienter();
    int pasnr = Integer.parseInt(in.next());
    Pasient pasient = pasientliste.hent(pasnr);

    System.out.println("Valgt pasient: " + pasient + ".");
    System.out.println("Angi ID-nr. på resepten du vil bruke.");
    pasient.skrivResepter();
    int reseptID = Integer.parseInt(in.next());

    /*
      Resept-IDen stemmer overense med reseptens indeks i reseptlisten, fordi
      reseptene i listen er sortert fra lavest ID-nr. til hoeyest ID-nr.
    */
    Resept resept = reseptliste.hent(reseptID);
    boolean brukt = resept.bruk(); //metoden returnerer false hvis ingen reit igjen
    if (!brukt) {
      System.out.println("Kunne ikke bruke resept på " + resept.hentLegemiddel() +
      " (ingen gjenvaerende reit).");
    } else {
      System.out.println("Brukte resept paa " + resept.hentLegemiddel() +
      ". Antall gjenvaerende reit: " + resept.hentReit() + ".");
    }
  }

  static void skrivUtStatistikk() throws Exception {
    Scanner in = new Scanner(System.in);
    boolean avslutte = false;

    while (!avslutte) {
      System.out.println();
      System.out.println("0: Antall utskrevne resepter paa vanedannende legemidler.");
      System.out.println("1: Antall utskrevne resepter paa narkotiske legemidler.");
      System.out.println("2: Liste over leger som har skrevet ut resepter paa narktosike legemidler.");
      System.out.println("3: Liste over pasienter med gyldige resepter paa narkoktiske legemidler.");
      System.out.println("4: Gaa tilbake til hovedmenyen.");

      String svar = in.next();

      if (svar.equals("0")) {
        int sumNarkotisk = 0;

        for (Lege l : legeliste) {
          sumNarkotisk += l.antVanedannendeResepter();
        }

        System.out.println("Det er blitt skrevet ut " + sumNarkotisk + " resepter paa vanedannnde legemidler.");
      }

      if (svar.equals("1")) {
        int sumNarkotisk = 0;

        for (Lege l : legeliste) {
          sumNarkotisk += l.antNarkResepter();
        }

        System.out.println("Det er blitt skrevet ut " + sumNarkotisk + " resepter paa narkotiske legemidler.");
      }

      if (svar.equals("2")) {
        if (legeliste.stoerrelse() == 0) {
          System.out.println("Finner ingen leger med utskrevne resepter paa narkotiske legemidler.");
        }
        for (Lege l : legeliste) {
          if (l.harNark()) {
            System.out.println(l.hentNavn() + " har skrevet ut " + l.antNarkResepter() + " resepter paa narkotiske legemidler");
          }
        }
      }

      if (svar.equals("3")) {
        if (pasientliste.stoerrelse() == 0) {
          System.out.println("Finner ingen pasienter med gyldige resepter paa narkotiske legemidler.");
        }
        for (Pasient p : pasientliste) {
          if (p.harNark()) {
            System.out.println(p.hentNavn() + " har " + p.antNarkResepter() + " antall gyldige resepter paa narkotiske legemidler.");
          }
        }
      }

      if (svar.equals("4")) {
        avslutte = true;
      }
    }
  }
}
