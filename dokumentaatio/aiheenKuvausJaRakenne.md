# Projektin kuvaus
**Aihe:** syntetisaattori
Toteutetaan syntetisaattori, jonka avulla käyttäjä voi käyttöliittymän kautta tuottaa ääntä ja muokata syntetisaattorin eri komponenttien parametreja.

Syntetisaattori koostuu erilaisista komponenteista, joihin lukeutuvat mm. oskillaattori, suodatin ja vahvistin. Nämä komponentit muodostavat *signaalipolun*, jossa oskillaattori toimii äänilähteenä ja suodatin ja vahvistin muovaavat oskillaattorin generoimaa aaltomuotoa. Ajallisesti suodattimia ja vahvistimia voidaan hallita ns. *vaipoilla* tai verhokäyrillä.

Projektin laajuus kattaa käyttöliittymän syntetisaattorille, jossa komponentit ovat ennaltamääritellyt, ts. signaalipolku koostuu kuudesta oskillaattorista joista jokaisella on suodatin ja vahvistin verhokäyrillä varustettuina. Syntetisaattori tukee eri synteesin muotoja, kuten esim:
- Subtraktiivinen synteesi, jossa oskillaattori tuottaa eri aaltomuotoja joita suodatetaan. Eri aaltomuotoihin lukeutuu mm. siniaalto, pulssiaalto sekä saha-aalto.
- (Pseudo)Taajuusmodulaatiosynteesi, jossa oskillaattorit muuntavat lennosta toisten oskillaattoreiden taajuutta.
- Additiivinen synteesi, jossa usean eri oskillaattorin tuottamia aaltomuotoja summataan.
Syntetisaattori tukee myös amplitudimodulaatiota, joskin tämä ei ole yhtä yleinen pohja äänisynteesille kuin yllämainitut.

**Käyttäjät:** Muusikko tai äänisynteesin opiskelija

**Toiminnot:**

- Oskillaattorin virityksen säätö senteillä kuvattuna
- Oskillaattorin eri tilojen kuten taajuus-/amplitudimodulaatio, ohitus ja additio tilojen valitseminen
- Suodattimen sulkutila-, resonanssi- sekä taajuuskaista-parametrien säätö
- Vahvistimen voimakkuuden säätö
- Vaippojen eri toteutusten aikaparametrien säätö


## Luokkakaavio
![Luokkakaavio](luokkakaavio.png)
## Sekvenssikaavio yksi oskillaattoriselle monosyntetisaattorille
![Sekvenssikaavio](sekvenssikaavio.png)


