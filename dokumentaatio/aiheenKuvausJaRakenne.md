# Projektin kuvaus
**Aihe:** syntetisaattori
Toteutetaan syntetisaattori, jonka avulla käyttäjä voi käyttöliittymän kautta tuottaa ääntä ja muokata syntetisaattorin eri komponenttien parametreja.

Syntetisaattori koostuu erilaisista komponenteista, joihin lukeutuvat mm. oskillaattori, suodatin ja vahvistin. Nämä komponentit muodostavat *signaalipolun*, jossa oskillaattori toimii äänilähteenä ja suodatin ja vahvistin muovaavat oskillaattorin generoimaa aaltomuotoa. Ajallisesti suodattimia ja vahvistimia voidaan hallita ns. *vaipoilla* tai verhokäyrillä.

Projektin laajuus kattaa alustavasti käyttöliittymän syntetisaattorille, jossa komponentit ovat ennaltamääritellyt, ts. signaalipolku koostuu yhdestä oskillaattorista joka kulkee suodattimen ja sitten vahvistimen lävitse. Synteesin muotona toimii subtraktiivinen synteesi, jossa oskillaattori tuottaa eri aaltomuotoja joita suodatetaan. Eri aaltomuotoihin lukeutuu mm. siniaalto, pulssiaalto sekä saha-aalto.

Laajennuksena käyttöliittymä voisi tarjota editorin, jossa käyttäjä voi modulaarisesti luoda oman syntetisaattorin eri komponenteista. Projektia voi myös laajentaa tukemaan eri synteesin muotoja, kuten taajuusmodulaatiota.

**Käyttäjät:** Muusikko tai äänisynteesin opiskelija

**Toiminnot:**

- Oskillaattorin taajuuden säätö
  - Käyttäjä voi syöttää nuotin tai taajuuden
- Suodattimen sulkutila-, resonanssi- sekä taajuuskaista-parametrien säätö
- Vahvistimen voimakkuuden säätö
