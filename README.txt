Tema POO – Sisteme de facturi fiscale

Detalii rulare
	Pentru rezolvarea primului task se poate rula main-ul din clasa tema.
	Pentru rularea aplicatiei cu interfata se ruleaza main-ul din clasa Interfata.LogIn. Se va deschide o fereastra de logare. Pentru logare se poate crea un cont nou sau va puteti loga cu:
	username: andrei
	password: 123456789

Implementare
	Pentru primul task am creat clasele cerute si am mai adaugat cateva functii pentru a-mi usura munca. Functiile au nume sugestive si am comentat in cod la ce ma ajuta.
Interfata grafica
	Clasa Interfata.LogIn deschide o fereastra noua (JFrame). Aici aveti posibilitatea de logare sau de creare a unui nou cont. Daca numele si parola sunt gresite se va afisa un mesaj de avertizare. Dupa logarea cu succes se va deschide aplicatia propriu zisa. Conturile sunt stocate in fisierul login.txt din directorul Resurse.
	Dupa logare se deschide aplicatia (o noua fereastra instantiata cu ajutorul clasei interfata.Interfata). Clasa Interfata instantiaza un obiect de tip JTabbedPane cu 3 tab-uri si pentru fiecare tab se adauga informatiile dorite.
	Prima pagina: Upload. Aici se realizeaza upload-ul fisierelor: produse.txt, facturi.txt, taxe.txt, precum si fisierul in care dorim sa scriem rezultatul: out.txt. Initial sunt incarcate fisierele din directorul Resurse. Dupa selectarea fisierelor se apasa butonul Load pentru a se realiza incarcarea.
	A doua pagina: Afisare si Administrare contine un tabel (JTable) pus intr-un obiect JScrollPane (pentru a se putea derula) si un set de butoane sugestive:
	Adaugare produs: deschide o noua fereastra unde se introduc informatiile produsului pe care dorim sa il adaugam. Adaugarea se realizeaza prin parcurgerea fisierului cu produse linie cu linie. Daca se gaseste un produs cu acelasi nume si aceeasi categorie, se verifica daca produsul exista si pentru tara dorita (daca pretul produsului este diferit de -1). Daca pretul pentru tara dorita este -1, atunci inseamna ca produsul nu exista pentru acea tara si se adauga pretul dorit. In cazul in care produsul exista se afiseaza un mesaj corespunzator. Daca nu ai exista niciun produs cu acelasi nume, noul produs se adauga la sfarsitul fisierului, iar pentru tarile in care nu se gaseste se pune pretul -1.
	Stergere Produs: se selecteaza produsul din tabel si se apasa butonul de stergere. Pentru a se realiza stergerea se parcurge fisierul cu produse si cand se gaseste produsul se pune pretul -1 pentru tara respectiva. Daca un produs are pretul -1 pentru toate tarile din baza de date, atunci se sterge linia respectiva din fisier.
	Editare produs: mai intai se sterge din fisier produsul selectat, apoi se adauga noul produs
	Sortarea: se sorteaza lista de produse dupa nume si tara si se actualizeaza tabelul
	Cautare: Se afiseaza in tabel doar produsele cu denumirea introdusa
	-Reload: reimprospateaza datele din tabel (de ex dupa o cautare)

A treia pagina: Statistici afiseaza statisticile cerute. Pentru asta am facut un combo box sugestiv, iar pentru calculul statisticilor am creat functii in clasa gestiune.

Observatii:
•	Tema a fost facuta si testata pe Windows 10
•	IDE folosit: NetBeans 8.2

