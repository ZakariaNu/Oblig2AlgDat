package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;

    }

    public DobbeltLenketListe(T[] a) {
        antall = 0;
        endringer = 0;
        Objects.requireNonNull(a, "\"Tabellen a er\n" + "null!\" ");  //exception hvis tabellen har/er bare nullverdier.

        if (a.length == 0) { //hvis lengden er null så returnerer jeg blankt.
            return;
        }

        Node forrige = null;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) { //finner første ikke null så - lager en ny node.
                Node noden = new Node(a[i]);
                if (hode == null) {
                    hode = noden;
                    hode.forrige = null;
                }
                hale = noden;
                noden.forrige = forrige;
                if (forrige != null) {
                    forrige.neste = noden;
                }
                forrige = noden;
                antall++;
                endringer++;
                hale.neste = null;
            }
        }
    }


    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til); //fra til kontroll for å se de er lovlige.

        DobbeltLenketListe<T> liste = new DobbeltLenketListe<>(); //instans av klassen DobbeltlenkedListe

        for (int i = fra; i < til; i++) {
            liste.leggInn(this.hent(i));
        }

        liste.endringer = 0;
        return liste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "\"Tabellen a er\n" + "null!\" ");  //exception hvis tabellen

        if (tom()) {
            hale = hode = new Node<>(verdi, null, null);  // tom liste
        } else {
            hale = hale.neste = new Node<>(verdi, hale, null); //ikke tom liste
        }
        antall++; //inkrimenterer antall++ og endringer++.
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        // Kontroll
        Objects.requireNonNull(verdi, "\"Tabellen a er\n" + "null!\" ");  // Sjekkes for null verdier
        indeksKontroll(indeks, true); // indekskontroll

        Node<T> node = hode;
        //
        if (tom()) {
            hode = hale = new Node<>(verdi);  // 1 ) tom liste
        } else if (indeks == 0) {
            hode = new Node<>(verdi, null, hode); // 2 ) legges forrerst
            hode.neste.forrige = hode;    // rikig plass
        } else if (indeks == antall) {
            hale = hale.neste = new Node<>(verdi, hale, null); // 3 ) ikke tom liste
        } else {
            node = finnNode(indeks);
            node.forrige = node.forrige.neste = new Node<>(verdi, node.forrige, node);
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {

        if (indeksTil(verdi) != -1) { // hvis den inneholder i listen ? - >
            return true; // returnerer den true - >
        } else { //ellers returnerer den false.
            return false;
        }
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false); //kjører indexkontroll på det indeksen
        Node<T> funnetnode;
        funnetnode = finnNode(indeks); // finner noden.
        return funnetnode.verdi; // returnerer nodens verdi.
    }

    @Override
    public int indeksTil(T verdi) {

        if (verdi == null) {
            return -1;
        }


        Node node = this.hode;
        for (int i = 0; i < this.antall; i++) {
            if (verdi.equals(node.verdi)) {
                return i;
            }
            node = node.neste;

        }
        return -1;
    }


    @Override
    public T oppdater(int indeks, T nyverdi) {
        //sjekker indeks
        indeksKontroll(indeks, false);
        if (nyverdi == null) { //kan ikke legge inn nullverdi
            throw new NullPointerException();
        }
        Node<T> funnetnode = finnNode(indeks); //finne indeksen
        T gammelnode = funnetnode.verdi; // lager ny node med den gamle verdien
        funnetnode.verdi = nyverdi; //bytter gamle verdien my nyverdi.
        endringer++;  //inkrimentere endringer
        return gammelnode;  //returnerer gammelnoden

    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }

        Node<T> node = hode;

        if (hode.verdi.equals(verdi)){
            if (node.neste != null){
                hode = node.neste;
                hode.forrige = null;
            }
            else {
                hode = null;
                hale = null;
            }
            antall--;
            endringer++;
            return true; 
            }
            else if (hale.verdi.equals(verdi)){
                node = hale;
                hale = node.forrige;
                hale.neste = null;
                antall--;
                endringer++;
                return true;
            }
            else {
                node = hode.neste;
                while(node !=null){
                    if (verdi.equals(node.verdi)){
                        node.forrige.neste = node.neste;
                        node.neste.forrige = node.forrige;
                        antall--;
                        endringer++;
                        return true; 
                    }
                    node = node.neste;
                }

        }
         return false;
    }

    @Override
    public T fjern(int indeks) {
        //Kontroll
        indeksKontroll(indeks, false);

        T retur;
        Node<T> node = hode;

        if (indeks == 0) {     //  skal første verdi fjernes?
            retur = node.verdi; // tar vare på verdien
            if (antall == 1) { // bare en node i lista?
                hale = null;
                hode = null;
            }
            else {
                hode = node.neste;  // fjerner node forrerst
                hode.forrige = null;
            }
        } else if (indeks == antall -1){
            node = hale;
            retur = hale.verdi;  // lagrer verdi

            hale = node.forrige;
            hale.neste = null;  // sletting av siste verdi
        }
        else {
            node = finnNode(indeks);
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        T verdi = node.verdi;
        antall--; // fjerning, så antall noder i liste går ned.
        endringer++; // endring
        return verdi;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (tom()) {
            return "[]"; // Hvis listen er tom retuneres det []
        }
        StringBuilder tegnestreng = new StringBuilder();
        tegnestreng.append("[");


        if (!tom()) {
            Node<T> h = hode;
            tegnestreng.append(h.verdi);
            h = h.neste;

            while (h != null) {
                tegnestreng.append(',').append(' ');
                tegnestreng.append(h.verdi);
                h = h.neste;
            }

        }
        tegnestreng.append("]");
        return tegnestreng.toString();

    }
    // Utskrift: [] [A] [A, B] [] [A] [B, A]


    public String omvendtString() {
        if (tom()) {
            return "[]"; // Hvis listen er tom retuneres det []
        }
        StringBuilder tegnestreng = new StringBuilder();
        tegnestreng.append("[");


        if (!tom()) {
            Node<T> h = hale;
            tegnestreng.append(h.verdi);
            h = h.forrige;


            while (h != null) {
                tegnestreng.append(',').append(' ');
                tegnestreng.append(h.verdi);
                h = h.forrige;
            }

        }
        tegnestreng.append("]");
        return tegnestreng.toString();
    }


    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();

    }

    //funnet fra kompendiet. Kap. 1.2.3 "feil og unntak"
    public static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks) {
        Node<T> noden;
        if (indeks < antall / 2) {  //Hvis indeks er mindre enn antall/2, så skal letingen etter noden starte fra hode og gå mot høyre ved hjelp av neste-pekere.
            noden = hode;
            for (int i = 0; i < indeks; i++) { //trevarsere gjennom nodene.
                noden = noden.neste; //node - neste, siden oppgaven spurte om det.
            }
        } else {
            noden = hale; // starter fra halen
            for (int i = antall - 1; i > indeks; i--) { //traverserer fra halen baklengs
                noden = noden.forrige;  //node  - forrige siden oppgaven spurte om det.
            }
        }
        return noden; //returnere noden
    }


    public static void main(String[] args) {
        Character[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3, 8)); // [D, E, F, G, H]
        System.out.println(liste.subliste(5, 5)); // []
        System.out.println(liste.subliste(8, liste.antall())); // [I, J]
        System.out.println(liste.subliste(0, 11)); // skal kaste unntak

    }

} // class DobbeltLenketListe


