# XO Clojure aplikacija
Ova aplikacija predstavlja implementaciju igre XO na tabli 3x3. Aplikacija je kreirana u programskom jeziku Clojure, koristeći se IntelliJ IDEA razvojnim okruženjem.

## Clojure
Clojure je dinamički, funkcionalni programski jezik koji kombinuje pristupačnost i interaktivni razvoj skriptnog jezika sa efikasnom infrastrukturom za višenitno programiranje. Iako se kompajlira, ostaje potpuno dinamičan - svaka funkcionalnost podržana od strane Clojure-a dostupna je u toku izvršavanja. Clojure omogućava jednostavan pristup Java okvirima, uz nagovestaje tipova kako bi se osiguralo da pozivi Java okvirima mogu izbeći refleksiju.

Takođe, Clojure je nalik LISP programskom jeziku, deleći s njim filozofiju *code-as-data* i moćan sistem makroa. Pretežno je funkcionalni jezik, sa bogatim setom nepromenljivih, perzistentnih struktura podataka. Kada je potrebno, Clojure nudi sistem softverske transakcione memorije i reaktivni Agent sistem koji garantuju čiste, ispravne, višenitne dizajne.

## XO igra
XO igra, poznata i kao "Iks-Oks", je jednostavna strategijska igra za dva igrača koja se igra na kvadratnoj mreži od 3x3 polja, ali se može proširiti na veće dimenzije. Jedan igrač igra sa znakom "X", dok drugi igra sa znakom "O".

Igrači naizmenično postavljaju svoje simbole (X ili O) na prazna polja table, po jedan u svakom potezu. Prvi igrač postavlja X, dok drugi postavlja O. Igra se nastavlja dok jedan od igrača ne uspe da postavi tri svoja simbola u nizu ili dok sva polja ne budu popunjena, što rezultira remijem. Pobednik je onaj igrač koji prvi kompletira niz svojih simbola.

Iako je XO igra jednostavna, zahteva razmišljanje i strategiju kako bi se izbegle zamke protivnika i postigla pobeda. Zanimljiva je i kao tema za proučavanje algoritama veštačke inteligencije, jer pruža izazov za razvoj algoritama koji mogu donositi pametne odluke u igri.

## Igranje igre
### Pokretanje igre
Aplikacija se pokreće tako što se pokrene klijenstki deo aplikacije (xo_client) iz terminala:
1. Pokrenuti terminal
2. Navigirati do foldera xo_clojure/xo_client
3. Pokrenuti komandu `lein run`

### Moduli igre
Nakon pokretanja aplikacije korisnik bira jedan od dva modula igre:
  1. Igrač protiv drugog igrača, gde naizmenično unose svoje poteze
  2. Igrač protiv kompjutera (algoritam koji je kreiran u sklopu aplikacije).

#### Igrač protiv igrača
Ako korisnik izabere prvu opciju (p), odnosno da igra protiv drugog igrača, biće mu prikazana tabela sa poljima koje je moguće odigrati. Igrač odigrava potez tako što unosi broj polja na koje želi da postavi svoj simbol. Prvi igrač postavlja X, dok drugi postavlja O. Igrači naizmenično unose poteze. Igra se završava kada jedan od igrača postavi tri simbola u nizu (horizontalno, vertikalno ili dijagonalno) i taj igrač je pobednik, ili kada se popune sva polja što znači da niko nije pobedio

#### Igrač protiv kompjutera
Ako korisnik izabere drugu opciju (c), odnosno da igra protiv kompjutera, potrebno je da izabere da li želi da bude prvi ili drugi na potezu. Ako izabere opciju 1 njegov simbol će biti X, a ako izabere opciju 2 njegov simbol će biti O. Igrač odigrava potez tako što unosi broj polja na koje želi da postavi svoj simbol. Nakon toga odigrava kompjuter itd. Igra se završava kada jedan od igrača postavi tri simbola u nizu (horizontalno, vertikalno ili dijagonalno) i taj igrač je pobednik, ili kada se popune sva polja što znači da niko nije pobedio.

### Minimax algoritam
Minimax algoritam je algoritam koji se često koristi u teoriji igara i veštačkoj inteligenciji za donošenje odluka u nulto-sumnim igrama, gde se jedan igrač trudi da maksimizuje svoju korist ili da minimizuje korist protivnjčkog igrača. Osnovna ideja Minimax algoritma je da svaki igrač, u svakom trenutku igre, procenjuje najbolji potez koji može da napravi, uzimajući u obzir poteze protivnika i očekivanu korist koju bi protivnik mogao da ostvari.
Logika kojom se određuju potezi kompjutera implemenitra Minimax algoritam za pronalaženje optimalnog poteza. Ovim algoritmom se rekurzivno prolazi kroz stablo svih mogućih poteza da bi se došlo do najboljeg poteza.

