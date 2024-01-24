import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static class Malzeme {
        int SiparisNumarası;
        int MalNumarası;
        int Miktar;
        double BirimFiyat;

        public Malzeme(int SiparisNumarası, int MalNumarası, int Miktar, double BirimFiyat) {
            this.SiparisNumarası = SiparisNumarası;
            this.MalNumarası = MalNumarası;
            this.Miktar = Miktar;
            this.BirimFiyat = BirimFiyat;
        }
    }

    public static void main(String[] args) {
        List<Malzeme> malzemeler = new ArrayList<>();
        malzemeler.add(new Malzeme(1000, 2000, 12, 100.51));
        malzemeler.add(new Malzeme(1000, 2001, 31, 200));
        malzemeler.add(new Malzeme(1000, 2002, 22, 150.86));
        malzemeler.add(new Malzeme(1000, 2003, 41, 250));
        malzemeler.add(new Malzeme(1000, 2004, 55, 244));

        malzemeler.add(new Malzeme(1001, 2001, 88, 44.531));
        malzemeler.add(new Malzeme(1001, 2002, 121, 88.11));
        malzemeler.add(new Malzeme(1001, 2004, 74, 211));
        malzemeler.add(new Malzeme(1001, 2002, 14, 88.11));

        malzemeler.add(new Malzeme(1002, 2003, 2, 12.1));
        malzemeler.add(new Malzeme(1002, 2004, 3, 22.3));
        malzemeler.add(new Malzeme(1002, 2003, 8, 12.1));
        malzemeler.add(new Malzeme(1002, 2002, 16, 94));
        malzemeler.add(new Malzeme(1002, 2005, 9, 44.1));
        malzemeler.add(new Malzeme(1002, 2006, 19, 90));

        System.out.println("--------------------------------------------");
        ToplamTutar(malzemeler);
        System.out.println("--------------------------------------------");
        OrtalamaFiyat(malzemeler);
        System.out.println("--------------------------------------------");
        MalBazlıOrtalama(malzemeler);
        System.out.println("--------------------------------------------");
        SiparisMalAdet(malzemeler);
        System.out.println("--------------------------------------------");
    }

    private static void ToplamTutar(List<Malzeme> malzemeler) {
        Map<Integer, Double> toplamSiparis = new HashMap<>();
        for (Malzeme malzeme : malzemeler) {
            toplamSiparis.put(malzeme.SiparisNumarası,
                    toplamSiparis.getOrDefault(malzeme.SiparisNumarası, 0.0) + malzeme.Miktar * malzeme.BirimFiyat);
        }

        toplamSiparis.forEach((siparis, toplam) -> System.out.println("Sipariş Numarası " + siparis + " - Toplam Tutar: " + toplam));
    }

    private static void OrtalamaFiyat(List<Malzeme> malzemeler) {
        double toplamFiyat = 0;
        int toplamAdet = 0;
        for (Malzeme malzeme : malzemeler) {
            toplamFiyat += malzeme.BirimFiyat * malzeme.Miktar;
            toplamAdet += malzeme.Miktar;
        }
        double ortalamaFiyat = toplamFiyat / toplamAdet;
        System.out.println("Ortalama Fiyat: " +toplamFiyat +" / "+ toplamAdet+ " = "+ ortalamaFiyat);
    }

    private static void MalBazlıOrtalama(List<Malzeme> malzemeler) {
        Map<Integer, double[]> urunFiyat = new HashMap<>();
        for (Malzeme malzeme : malzemeler) {
            double[] birimMiktar = urunFiyat.getOrDefault(malzeme.MalNumarası, new double[2]);
            birimMiktar[0] += malzeme.BirimFiyat * malzeme.Miktar;
            birimMiktar[1] += malzeme.Miktar;
            urunFiyat.put(malzeme.MalNumarası, birimMiktar);
        }

        urunFiyat.forEach((siparis, birimMiktar) -> {
            double ortalama = birimMiktar[0] / birimMiktar[1];
            System.out.println("Şiparis Numarası " + siparis + " - Ortalama: " + ortalama);
        });
    }

    private static void SiparisMalAdet(List<Malzeme> malzemeler) {
        Map<Integer, Map<Integer, Integer>> ürünAdet = new HashMap<>();
        for (Malzeme malzeme : malzemeler) {
            Map<Integer, Integer> adet = ürünAdet.getOrDefault(malzeme.SiparisNumarası, new HashMap<>());
            adet.put(malzeme.MalNumarası,
                    adet.getOrDefault(malzeme.MalNumarası, 0) + malzeme.Miktar);
            ürünAdet.put(malzeme.SiparisNumarası, adet);
        }

        System.out.println(ürünAdet);
    }
}