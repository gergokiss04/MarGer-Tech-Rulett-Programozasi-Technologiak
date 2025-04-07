# Rendszerterv
## 1. A rendszer célja
A "Mini Rulett" egy szórakoztató, interaktív játék, amely lehetőséget biztosít a felhasználóknak arra, hogy coinokat fogadjanak a kiválasztott színre. A játék során a felhasználó választhat egy színt, majd a rulett pörgetése alapján nyerhet vagy veszthet coinokat a választása helyességétől függően. A program célja a szórakoztatás mellett a felhasználók számára egy egyszerű, izgalmas módot kínál a szerencsejátékos élmény átélésére, miközben figyelemmel kísérhetik a fogadásaik eredményét.

## 2. Projektterv

### 2.1 Projektszerepkörök, felelőségek:
  * Üzleti szereplő:
	  -   Megrendelő:
		  -  Herbák Marcell
     
### 2.2 Projektmunkások és felelőségek:
   * Frontend és backend:
     - Kiss Gergő Zsolt
     - Zsiga Marcell
   * Tesztelés:
     - Kiss Gergő Zsolt
     - Zsiga Marcell
  
     
### 2.3 Ütemterv:

|Funkció                  | Feladat                                | Prioritás | Becslés (nap) | Aktuális becslés (nap) | Eltelt idő (nap) | Becsült idő (nap) |
|-------------------------|----------------------------------------|-----------|---------------|------------------------|------------------|---------------------|
|Rendszerterv             |Megírás                                 |         1 |            10 |                     10 |                1 |                  10 |
|Program                  |Prototípus elkészítése                  |         2 |            10 |                     10 |                1 |                  10 |
|Program                  |Tesztelés                               |         3 |             5 |                      5 |                1 |                   5 |


### 2.4 Mérföldkövek:
   *   04.15. Projekt elkezdése
   *   04.15. Alap prototípus elkészítése
   *   04.25. Végleges prototípus elkészítése
   *   04.30. Tesztelés
   *   05.07. Bemutatás és átadás

## 3. Üzleti folyamatok modellje

### 3.1 Üzleti szereplők
Regisztráció vagy Bejelentkezés után lehet az alkalmazást használni. A felhasználók jögkörei megegyeznek


### 3.2 Üzleti folyamatok
  - Átltalános folyamatok:
    - Regisztráció a megfelelő adatok megadásával
    - Bejelentkezés a regisztrációkor megadtott adatokkal
    - Kilépés bármikor
  - Roulett folyamatok:
    - Tét megtétele
    - Fekete, fehér vagy zöld megtippelése


## 4. Követelmények

### Funkcionális követelmények

| ID | Megnevezés               | Leírás                                                                                                                                                                                   |
|----|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| K1 | Bejelentkezés/Regisztráció     | A felhasználó itt tud regisztrálni majd később bejelentkezni a profiljába                                                                                        |
| K2 | Üdvözlő oldal             | A program elindításakor üdvözlő lap ami köszönti a felhasználót                                                                                                                                            |
| K3 | Mini Rulett       | A Mini rulett játékban a felhasználó megteheti tétjét a kiválasztott színre és vagy nyer vagy veszít.                                                                                                                        |
| K4 | Kapcsolat        | Fejlesztőkről néhány információ, hibabejelentés lehetősége |

 
### Nemfunkcionális követelmények

| ID | Megnevezés                             | Leírás                                                                                                              |
|----|----------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| K5 | Átlátható, könnyen kezelhető felület   | A felületek könnyen használhatóak, átláthatóak legyenek,hogy könnyen tudja bárki használni              |
| K6 | Tervezési minták használata            | Az alkalmazás forráskódja tartalmazzon legalább 2 tervezési mintát. |

 


### Támogatott eszközök

 * Bármely Java alkalmazás futtatásra képes eszköz amin Windows operációs rendszerrel rendelkezik.


## 5. Funkcionális terv

### 5.1 Rendszerszereplők
 - Felhasználó
   - Tétet tehet meg az általa kiválasztott színre
 - "Gép"
   - Generál egy színt

### 5.2 Menühierarchiák
- Főoldal 
- Bejelentkezés/Regisztráció
- Mini Rulett
- Kapcsolat

## 6. Fizikai környezet

### Vásárolt szoftverkomponensek, valamint esetleges külső rendszerek
- Nincsenek megvásárolt szoftverkomponenseink, valamint nem használunk külső rendszereket

### Hardver topológia
Az alkalmazás futtatásra olyan pc lesz alkalmas, ami Windows 10 vagy 11 operációs rendszert használ

### Fizikai alrendszerek
- Kliens gépek: A követelményekben leírtaknak megfelelően Windows 10 vagy 11 operációs rendszerrel működő számítógépek.
- Host: A háttérben zajló műveletek és az adatbázis itt található, illetve ezzel kommunkiál a kliens.

### Fejlesztő eszközök
- XAMPP: MySQL
- IntelliJ IDEA
- VS Code




## 8. Architekturális terv

### Webszerver
- XAMPP



### Adatbázis rendszer
- Adatbázis rendszer, ami MySQL-lel működik.



### A program elérése, kezelése
Szükséges XAMPP ami az adatbáziskapcsolatot segíti elő.



## 9. Adatbázis terv
Az adatbázisban két táblával fogunk dolgozni. Az egyik a felhasználó nevét, jelszavát valamint az azonosítóját fogja tárolni, amit automatikusan fogunk generálni.
A másik táblában szintén az azonosítót fogjuk tárolni, valamint a játékos aktuális zsetonjainak az összegét.


## 10. Implementációs terv
A felhasználói felület (frontend) JavaFX-szel készül, míg a háttérrendszer (backend) Java alapú keretrendszeren fut. A programkód írásához elsősorban angol nyelvet használunk.
Az adatbázis kezelését egy külön csomag végzi, amely összeköti a szervert az adatbázissal.A fejlesztéshez szükség van az IntelliJ fejlesztőkörnyezet telepítésére és megfelelő beállítására, a szükséges eszközökkel és bővítményekkel együtt.

## 11. Tesztterv
A tesztek elvégzésének célja, teljeskörűen megvizsgájuk a rendszert és minden hozzátartozó komponenst és jóvájagyásra kerüljön minden a rendszer által biztosított szolgáltatás. Minden fejlesztő végez majd teszteket és ezeknek eredményeit dokumnetáni is fogják.

A tesztek során a szoftver működését vizsgáljuk minden lehetséges szempontból annak érdekében, hogy miinden eshetőségnél a megfelelő eredményt kapjuk.



### Tesztesetek

#### Tesztelés módja: Unit Teszt

 | Teszteset      | Elvárt eredmény                                                                                            | 
 |----------------|------------------------------------------------------------------------------------------------------------| 
 |  Regisztráció  | A felhasználó sikeresen regisztrálni tudja magát, a szükséges adatok megadásával                           |
 |  Bejelentkezés | A már a regisztrációnal megadatott adatok alapján a felhasznló sikeresen be tudjon jelentkezni             |
 |  Fogadás       | Amennyiben a egyenlege lehetővé teszi, úgy az általa választott színre tudja megtenni a tippjét a szintén altala választott összeggel|
 | Játék indítása | Megkezdődik a játék és az előzetesen leadott tipp alapján a felhasználó megtudja az eredményt és a zsetonjai is eszerint vaáltoznak  |


## 12. Telepítési terv

**Fizikai telepítési terv**:
- Internet kapcsolattal rendelkező, működő számítógépre van a felhasználónak szüksége.
- A program sikeres működéséhez egy szerverre van szükség, melynek a megfelelő hálózathoz kell kapcsolódnia, hogy elérhetó legyen.


**Szoftver telepítési terv**:
- Windows 10 vagy 11 operációs rendszerre van szüksége a felhasználóknak mely kompatibilis az ilyen alkalmazásokkal
- Szükség van adatbázisra, például MySQL-re:
  - Ehhez a(z) XAMPP szoftver telepítése szükséges
- Szabadon karbantarható, fejleszthető az alkalmazás a fejlesztők számára
- Amennyiben minden szükséges beállítás megtörtént, az alkalmazás futtatható.


## 13. Karbantartási terv
Fontos ellenőrzési szempontok:

 * Az alkalmazás biztonságosan kezeli a kritikus információkat. Az érzékeny adatok mint például a bejelentkezési információk és a felhasználók személyes adatai csak a megfelelő jogosultsággal rendelkező felhasználók számára legyenek elérhetők.

 * A felhasználói visszajelzéseket figyelembe kell venni. Ha valaki hibát jelez, azt a lehető leghamarabb ki kell javítani függetlenül attól, hogy az a működéssel vagy a megjelenéssel kapcsolatos.
