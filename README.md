# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Zakariya Ahmed Nur Gutale S362082, s362082@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Zakariya  har hatt hovedansvar for oppgave 1,2,3,4,5,6,8.

# Oppgavebeskrivelsef

i **Oppgave 1** så sjekket jeg først om tabellen er null. etter det ser jeg om [i] er null. hvis ikke, finner første "ikke null" så - lager en ny node. Så ser jeg om hode ikke er laget hittill. Hode = noden, fordi det er den eneste noden og derfor "hode". så kjører jeg det samme som linjen før men nå for halen. så tilslutt inkrimenterer jeg antall og endringer.

i **Oppgave 2** så brukte jeg StringBuilder for å sette sammen en tegnstreng. I metodene toString og omvendtString bruker de samme prinsippene, toString starter fra hode og omvendtSting starter fra hale. alstå at de kommer ut i motsatt rekkefølge. Dette skjer ved å endre at hode blir til hale, og at neste blir til forrige.I metoden legginn() så har jeg to ulike utfall, enten en tom liste eller ikke en tom liste. hvis ikke tom legges det en ny node bakerst. På oppdater() sjekkes indeksen på nytt. hvis nyverdi == null så kaster vi en exception. lager nye noder, setter den funnede noden sin verdi = nyverdi og tilslutt returnerer gamlenoden.

i **Oppgave 3a** ) så lagde jeg finnNode(). Hvis indeks er mindre enn antall/2, så skal letingen etter noden starte fra hode og gå mot høyre ved hjelp av neste-pekere. starter fra hode og traversrer gjennom nodene. hvis ikke starter jeg fra halen og traverersrer bakover og setter noden.forrige og tilslutt returnerer noden. i hent() kjører jeg indekskontroll på indeksen, finner noden og returnerer nodens.verdi.

i **Oppgave 3b** ) starter vi med en fratilkontroll for å se de er lovlige tall. her brukes en for-loop og det legges inn nye elemeter i listen. så returnerer vi lista.

i **Oppgave 4**  i indeksTil() så sjekker jeg først om det er noen nullverdieer. returnerer -1 hvis den har nullverdier. en for-loop for å finne posijonen til verdi hvis den finnes i lista. metoden inneholder() returnerer true hvis den inneholder i listen og ellers returnerer false.

i **Oppgave 5**  først sjekkes det for null verdier og en indekskontroll. så tar jeg for meg de forskjellige tilfellene: 1 ) tom liste, 2) legges forrest, 3) legges bakkerst 4) mellom to verdier. så inkrimenterer antall og endringer.

i **Oppgave 6** løste jeg ved ved å implentere if tester for å se om man kan fjerne mellom forrerst eller bakkerste node. Den andre fjern metoden sjekker først med if setning om verdien finnes eller ikke med noen spesialtilfeller om det er bare er en node i lista eller om verdien ikke finnes i det hele tatt. Så en else for når verdien som skal fjernes ligger mellom to noder.

i **Oppgave 8** 
