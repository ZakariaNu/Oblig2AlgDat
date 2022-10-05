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
      this(); //kaller den andre konstruktøren
        
       /* if (a.length == 0){
        throw new NullPointerException("Tabellen er tom");  } */

        Objects.requireNonNull(a, "\"Tabellen a er\n" + "null!\" ");  //exception hvis tabellen

        hode = hale = new Node<>(null); //n
        
        int teller = 0;
         for (T t : a){
             if (t != null){ //finner første ikke null så - lager en ny node.
                 if (teller == 0){
                     hale=hale.neste = new Node<>(t, hale, null);
                     teller++;
                 }
                 else {
                     hale = hale.neste = new Node<>(t, hale, null); // ny verdi legges bak
                 }
                 antall++;
             }
         }
         if (antall == 0){
             hode=hale = null;
         }else {
             hode=hode.forrige = null;
         }
     }


    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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

        if (tom()){
            hale = hode = new Node<>(verdi, null,null);  // tom liste
        }
        else{
            hale = hale.neste = new Node<>(verdi, hale,null); //ikke tom liste
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {

        indeksKontroll(indeks, false); //kjører indexkontroll på det indeksen
       Node<T> funnetnode = finnNode(indeks); // finner noden.
        return funnetnode.verdi; // returnerer nodens verdi.
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //sjekker indeks
        indeksKontroll(indeks,false);
        if (nyverdi == null){ //kan ikke legge inn nullverdi
            throw new NullPointerException();
        }

            Node <T> funnetnode = finnNode(indeks); //finne indeksen
            T gammelnode = funnetnode.verdi; // lager ny node med den gamle verdien
            funnetnode.verdi = nyverdi; //bytter gamle verdien my nyverdi.
            endringer++;  //inkrimentere endringer
      return gammelnode;  //returnerer gammelnoden

    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (tom()){
            return "[]"; // Hvis listen er tom retuneres det []
        }
        StringBuilder tegnestreng = new StringBuilder();
        tegnestreng.append("[");


        if (!tom()) {
            Node<T> h = hode;
            tegnestreng.append(h.verdi);
            h = h.neste;

            while (h != null){
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
        if (tom()){
            return "[]"; // Hvis listen er tom retuneres det []
        }
        StringBuilder tegnestreng = new StringBuilder();
        tegnestreng.append("[");


        if (!tom()) {
            Node<T> h = hale;
            tegnestreng.append(h.verdi);
            h = h.forrige;


            while (h != null){
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
    private Node<T> finnNode(int indeks){

        Node <T> noden;
        if (indeks < antall/2){  //Hvis indeks er mindre enn antall/2, så skal letingen etter noden starte fra hode og gå mot høyre ved hjelp av neste-pekere.
            noden = hode;
            for (int i = 0; i < indeks; i++){ //trevarsere gjennom nodene.
                noden = noden.neste; //node - neste, siden oppgaven spurte om det.
            }
        }else {
            noden = hale; // starter fra halen
            for (int i = antall -1 ; i < indeks; i--){ //traverserer fra halen baklengs
                noden = noden.forrige;  //node  - forrige siden oppgaven spurte om det.
            }
        }
        return noden; //returnere noden
    }


    public static void main(String[] args) {

        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " " + l2.omvendtString() + " " + l3.omvendtString());
    }

} // class DobbeltLenketListe


