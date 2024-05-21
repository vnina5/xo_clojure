# XO Clojure aplikacija
Ova aplikacija predstavlja implementaciju igre XO na tabli 3x3. Aplikacija je kreirana u programskom jeziku Clojure, koristeći se IntelliJ IDEA razvojnim okruženjem.

## Clojure
Clojure je dinamički, funkcionalni programski jezik koji kombinuje pristupačnost i interaktivni razvoj skriptnog jezika sa efikasnom infrastrukturom za višenitno programiranje. Iako se kompajlira, ostaje potpuno dinamičan - svaka funkcionalnost podržana od strane Clojure-a dostupna je u toku izvršavanja. Clojure omogućava jednostavan pristup Java okvirima, uz nagovestaje tipova kako bi se osiguralo da pozivi Java okvirima mogu izbeći refleksiju.

Takođe, Clojure je nalik LISP programskom jeziku, deleći s njim filozofiju *code-as-data* i moćan sistem makroa. Pretežno je funkcionalni jezik, sa bogatim setom nepromenljivih, perzistentnih struktura podataka. Kada je potrebno, Clojure nudi sistem softverske transakcione memorije i reaktivni Agent sistem koji garantuju čiste, ispravne, višenitne dizajne.

## XO igra
XO igra, poznata i kao "Iks-Oks", je jednostavna strategijska igra za dva igrača koja se igra na kvadratnoj mreži od 3x3 polja, ali se može proširiti na veće dimenzije. Jedan igrač igra sa znakom "X", dok drugi igra sa znakom "O".

Igrači naizmenično postavljaju svoje simbole (X ili O) na prazna polja table, po jedan u svakom potezu. Prvi igrač postavlja X, dok drugi postavlja O. Igra se nastavlja dok jedan od igrača ne uspe da postavi tri svoja simbola u nizu ili dok sva polja ne budu popunjena, što rezultira remijem. Pobednik je onaj igrač koji prvi kompletira niz svojih simbola.

Iako je XO igra jednostavna, zahteva razmišljanje i strategiju kako bi se izbegle zamke protivnika i postigla pobeda. Zanimljiva je i kao tema za proučavanje algoritama veštačke inteligencije, jer pruža izazov za razvoj algoritama koji mogu donositi pametne odluke u igri.

### Moduli igre
Nakon pokretanja aplikacije korisnik bira jedan od dva modula igre:
  1. Igrač protiv kompjutera (algoritam koji je kreiran u sklopu aplikacije)
  2. Igrač protiv drugog igrača. gde naizmenično unose svoje poteze.

jdh
