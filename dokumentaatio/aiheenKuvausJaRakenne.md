# Projektin kuvaus
**Aihe:** Syntetisaattori.

Toteutetaan syntetisaattori, jonka avulla käyttäjä voi käyttöliittymän kautta tuottaa ääntä ja muokata syntetisaattorin eri komponenttien parametreja.

Syntetisaattori koostuu erilaisista komponenteista, joihin lukeutuvat mm. oskillaattori, suodatin ja vahvistin. Nämä komponentit muodostavat *signaalipolun*, jossa oskillaattori toimii äänilähteenä ja suodatin ja vahvistin muovaavat oskillaattorin generoimaa aaltomuotoa. Ajallisesti suodattimia ja vahvistimia voidaan hallita ns. *vaipoilla* tai verhokäyrillä.

Projektin laajuus kattaa käyttöliittymän syntetisaattorille, jossa komponentit ovat ennaltamääritellyt, ts. signaalipolku koostuu neljästä oskillaattorista joista jokaisella on suodatin ja vahvistin verhokäyrällä varustettuna. Syntetisaattori tukee eri synteesin muotoja, kuten esim:
- Subtraktiivinen synteesi, jossa harmonisesti rikkaita aaltomuotoja "yksinkertaistetaan"
- Taajuusmodulaatiosynteesi, jossa oskillaattorit muuntavat toisten oskillaattoreiden taajuutta.
- Additiivinen synteesi, jossa usean eri oskillaattorin tuottamia aaltomuotoja summataan.
Syntetisaattori tukee myös amplitudimodulaatiota, joskin tämä ei ole yhtä yleinen pohja äänisynteesille kuin yllämainitut.

**Käyttäjät:** Muusikko tai äänisynteesin opiskelija

**Toiminnot:**

- Oskillaattorin virityksen säätö senteillä kuvattuna
- Oskillaattorin eri tilojen kuten taajuus-/amplitudimodulaatio, ohitus ja additio tilojen valitseminen
- Vahvistimen voimakkuuden säätö
- Suodattimen voimakkuuden säätö
- Vaippojen eri aikaparametrien säätö


## Luokkakaavio
![Luokkakaavio](luokkakaavio.png)
- Player luokalla on oliomuuttujana SignalSource-interfacen toteuttava olio.
Kun Player käynnistetään, se avaa uuden SwingWorker säikeen joka pyytää SignalSourcen toteuttavalta oliota generoimaan amplitudin luotuun SignalStatus-olioon, joka kirjoitetaan puskuroituna Javan tarjoaman Sound APIn SourceDataLine-olioon.
- SignalStatus olio pitää sisällään tietoa signaalille ominaisesta tiedosta signaalipolussa, kuten amplitudista, kirjoitettavasta taajuudesta, ja aallon vaiheesta.
- Operator toteuttaa SignalSource-interfacen, sekä omistaa oliomuuttujana SignalSource-interfacen toteuttavan olion. Operaattorit pyytävät SignalSourceaan muuttamaan SignalStatusta, jonka jälkeen ne itse muokkaavat SignalStatusta omien asetuksiensa mukaisesti. Operator pitää sisällään Oscillator, SpectrumFilter ja ADSR oliot.
- EnvelopeGenerator oliot toteuttavat metodin generateEnvelope, joka ottaa parametrina SignalStatuksen ja palauttaa amplitudin SignalStatuksen vaiheeseen perustuen. Oscillator käyttää EnvelopeGeneratorilta saatua amplitudia omien parametriensa säätöön.
- ADSR on toteutus EnvelopeGeneratorista, joka pitää sisällään kolme vaihetta joiden sisällä amplitudi generoidaan SignalStatuksen vaiheesta, sekä pysyvän amplituditason, joka säilytetään kahden viimeisen vaiheen välillä.
- EnvelopeSettings on abstrakti luokka joka sisältää EnvelopeGeneratoreille liittyvät parametrit. Eri EnvelopeGeneratoreiden toteutuksilla on omaa toteutustaan vastaava EnvelopeSettingsin toteuttava Settings luokka.
- ADSRSettings toteuttaa EnvelopeSettingsin ADSR luokkaa varten, pitäen sisällään attack, decay, sustain ja release parametrit.
- Filter ottaa vastaan amplitudin sekä aallon vaiheen ja palauttavat suodatetun amplitudin.
- SpectrumFilter on naiivi implementaatio suodattimesta joka lisää äänen fundamentaalisen taajuuden määrää.
- FilterSettings enkapsuloi filtterin voimakkuusparametrin, joka säätää suodattamisen voimakkuutta.
- Patch säilyttää kaikki Operaattoriin ja operaattorin komponentteihin liittyvät Settings oliot.

## Sekvenssikaaviot

### Yksi-operaattorisen monosyntetisaattorin ajo

![Sekvenssikaavio1](sekvenssikaavio1.png)

### Patch-olion lataaminen Operaattorille

![Sekvenssikaavio2](sekvenssikaavio2.png)
