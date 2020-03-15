// import java.io.*;
// import java.util.*;
//
// public class Legesystem {
//   Lenkeliste<Legemiddel> legemiddelliste;
//   Lenkeliste<Resept> reseptliste;
//   SortertLenkeliste<Lege> legeliste;
//   Lenkeliste<Pasient> pasientliste;
//
//   public void lesFraFiL(File fil) throws FileNotFoundException {
//     Scanner in = new Scanner(fil);
//     Scanner in2 = new Scanner(fil);
//     while (in.hasNextLine()) {
//       String linje = in.nextLine();
//       if (linje.split(" ")[1] == "Pasienter") {
//         while (in2.nextLine().split(" ")[0] != "#") {
//           linje = in2.nextLine();
//           String navn = linje.split(" ")[0];
//           String foedselsnr = linje.split(" ")[1];
//           Pasient pasient = new Pasient(navn, foedselsnr);
//           pasientliste.leggTil(pasient);
//         // if (linje.split(" ")[1] == "Legemidler") {
//         //   while (in2.nextLine().split(" ")[0] != "#") {
//         //     linje = in2.nextLine();
//         //     navn = linje.split(" ")[0];
//         //     foedselsnr = linje.split(" ")[1];
//         //     pasient = new Pasient(navn, foedselsnr);
//         //     pasientliste.leggTil(pasient);
//         //   }
//         // }
//         }
//       }
//     }
//   }

import java.io.*;
import java.util.*;
import java.lang.*;

public class Legesystem {
  static Lenkeliste<Legemiddel> legemiddelliste;
  static Lenkeliste<Resept> reseptliste;
  static SortertLenkeliste<Lege> legeliste;
  static Lenkeliste<Pasient> pasientliste;

  public static void main(String[] args) throws Exception {
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
      // else if (svar.equals("0")) skrivUtOversikt();
      // else if (svar.equals("1")) leggTilLege();
      // else if (svar.equals("2")) leggTilPasient();
      // else if (svar.equals("3")) leggTilResept();
      // else if (svar.equals("4")) leggTilLegemiddel();
      // else if (svar.equals("5")) brukResept();
      // else if (svar.equals("6")) skrivUtStatistikk();
      // else if (svar.equals("7")) skrivTilFil();
      if (svar.equals("8")) avslutte = true;
      else if (svar.equals("9")) skrivUtLeger();
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
    for (Lege l : legeliste)
    System.out.println(teller + ": " + l);
  }

  private static void skrivUtPasienter() {
    int teller = 0;
    for (Pasient p : pasientliste)
    System.out.println(teller + ": " + p);
  }

  private static void skrivUtLegemidler() {
    int teller = 0;
    for (Legemiddel l : legemiddelliste)
    System.out.println(teller + ": " + l);
  }

  private static void skrivUtResepter() {
    int teller = 0;
    for (Resept r : reseptliste)
    System.out.println(teller + ": " + r);
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
    skrivUtPasienter();
    System.out.println();
  }

  static void leggTilLege() {
    Scanner in = new Scanner(System.in);
    String navn;
    System.out.println("Du har valgt aa legge til en lege. Vennligst oppgi legens navn.");
    navn = in.next();
    Lege nyLege = new Lege(navn);
    legeliste.leggTil(nyLege);
  }

  static void leggTilResept() throws UlovligUtskrift {
    Scanner in = new Scanner(System.in);
    int svar;

    System.out.println("Hvilken lege skal skrive ut resept?");
    skrivUtLeger();
    svar = Integer.parseInt(in.next());
    Lege utskrivendeLege = legeliste.hent(svar);

    System.out.println("For hvilket legemiddel skal resepten gjelde?");
    skrivUtResepter();
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
      utskrivendeLege.skrivBlaaResept(legemiddel, pasient, reit);
    }
    else if (input.equals("h")) {
      System.out.println("Tast 'v' for vanlig resept; tast 'm' for militaerresept" +
      " tast 'p' for p-resept.");
      input = in.next().trim();

      if (input.equals("p")) {
        utskrivendeLege.skrivPResept(legemiddel, pasient);
      }
      else {
        System.out.println("Hvor mang reit skal resepten ha?");
        int reit = Integer.parseInt(in.next());

        if (input.equals("v")) {
          utskrivendeLege.skrivHvitResept(legemiddel, pasient, reit);
        }

        if (input.equals("m")) {
          utskrivendeLege.skrivMilitaerResept(legemiddel, pasient, reit);
        }
      }
    }
  }
}
