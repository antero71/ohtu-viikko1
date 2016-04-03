package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Kauppa implements KauppaInterface {

    private VarastoInterface varasto;
    private PankkiInterface pankki;
    private Ostoskori ostoskori;
    private ViitegenInterface viitegeneraattori;
    private String kaupanTili;

    
    @Autowired
    public Kauppa(VarastoInterface varasto,PankkiInterface pankki,ViitegenInterface generaattori) {
        this.varasto = varasto;
        this.pankki = pankki;
        viitegeneraattori = generaattori;
        kaupanTili = "33333-44455";
    }

   
    
    @Override
    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    @Override
    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id); 
        varasto.palautaVarastoon(t);
    }

    @Override
    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    @Override
    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
