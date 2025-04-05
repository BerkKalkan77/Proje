import java.util.Scanner;

public class CinemaSystem {

    // Film sınıfı
    static String[] filmAdlari = new String[10];
    static int[] filmSuresi = new int[10];
    static String[] filmTurleri = new String[10];
    static int filmSayisi = 0;

    // Müşteri sınıfı
    static String[] musteriAdlari = new String[20];
    static String[] musteriEmail = new String[20];
    static int musteriSayisi = 0;

    // Biletler sınıfı
    static String[][] biletler = new String[20][10];  // Maksimum 20 müşteri ve her birinin 10 filmi seçebilmesi
    static int[] secilenFilmSayisi = new int[20];  // Her müşteri için seçilen film sayısı

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secim;

        while (true) {
            System.out.println("\nSinema Musteri Kayit Sistemi");
            System.out.println("1. Film Ekle");
            System.out.println("2. Musteri Ekle");
            System.out.println("3. Bilet Olustur");
            System.out.println("4. Filmleri Listele");
            System.out.println("5. Musterileri Listele");
            System.out.println("6. Biletleri Listele");
            System.out.println("7. Cikis");
            System.out.print("Seciminizi yapiniz (1-7): ");
            secim = scanner.nextInt();
            scanner.nextLine();  // Boşluk karakterini temizlemek için

            switch (secim) {
                case 1:
                    filmEkle(scanner);
                    break;
                case 2:
                    musteriEkle(scanner);
                    break;
                case 3:
                    biletOlustur(scanner);
                    break;
                case 4:
                    filmleriListele();
                    break;
                case 5:
                    musterileriListele();
                    break;
                case 6:
                    biletleriListele();
                    break;
                case 7:
                    System.out.println("Cikiliyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Gecersiz secim! Lutfen tekrar deneyin.");
            }
        }
    }

    // Film ekleme işlemi
    public static void filmEkle(Scanner scanner) {
        if (filmSayisi >= 10) {
            System.out.println("Maksimum film sayisına ulasildi.");
            return;
        }

        System.out.print("Film Adi: ");
        filmAdlari[filmSayisi] = scanner.nextLine();
        System.out.print("Film Suresi (dakika): ");
        filmSuresi[filmSayisi] = scanner.nextInt();
        scanner.nextLine();  // Boşluk karakterini temizlemek için
        System.out.print("Film Turu: ");
        filmTurleri[filmSayisi] = scanner.nextLine();
        filmSayisi++;
        System.out.println("Film basariyla eklendi.");
    }

    // Müşteri ekleme işlemi
    public static void musteriEkle(Scanner scanner) {
        if (musteriSayisi >= 20) {
            System.out.println("Maksimum musteri sayisina ulasildi.");
            return;
        }

        System.out.print("Musteri Adı: ");
        musteriAdlari[musteriSayisi] = scanner.nextLine();
        System.out.print("Musteri Email: ");
        musteriEmail[musteriSayisi] = scanner.nextLine();
        musteriSayisi++;
        System.out.println("Musteri başarıyla eklendi.");
    }

    // Bilet oluşturma işlemi
    public static void biletOlustur(Scanner scanner) {
        if (musteriSayisi == 0 || filmSayisi == 0) {
            System.out.println("Film veya musteri bulunmamaktadir. Lütfen once film ve musteri ekleyin.");
            return;
        }

        System.out.println("Musteri Listesi:");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println((i + 1) + ". " + musteriAdlari[i]);
        }
        System.out.print("Bilet almak isteyen musterinin numarasini giriniz: ");
        int musteriIndex = scanner.nextInt() - 1;

        if (musteriIndex < 0 || musteriIndex >= musteriSayisi) {
            System.out.println("Gecersiz musteri secimi.");
            return;
        }

        System.out.println("Film Listesi:");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println((i + 1) + ". " + filmAdlari[i] + " - " + filmSuresi[i] + " dakika - " + filmTurleri[i]);
        }
        System.out.print("Musterinin izlemek istedigi film numarasini giriniz: ");
        int filmIndex = scanner.nextInt() - 1;

        if (filmIndex < 0 || filmIndex >= filmSayisi) {
            System.out.println("Gecersiz film secimi.");
            return;
        }

        biletler[musteriIndex][secilenFilmSayisi[musteriIndex]] = filmAdlari[filmIndex];
        secilenFilmSayisi[musteriIndex]++;
        System.out.println("Bilet basariyla olusturuldu.");
    }

    // Filmleri listeleme işlemi
    public static void filmleriListele() {
        if (filmSayisi == 0) {
            System.out.println("Henuz eklenmis film bulunmamaktadir.");
            return;
        }

        System.out.println("Film Listesi:");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println((i + 1) + ". " + filmAdlari[i] + " - " + filmSuresi[i] + " dakika - " + filmTurleri[i]);
        }
    }

    // Müşterileri listeleme işlemi
    public static void musterileriListele() {
        if (musteriSayisi == 0) {
            System.out.println("Henuz eklenmis musteri bulunmamaktadir.");
            return;
        }

        System.out.println("Musteri Listesi:");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println((i + 1) + ". " + musteriAdlari[i] + " - " + musteriEmail[i]);
        }
    }

    // Biletleri listeleme işlemi
    public static void biletleriListele() {
        if (musteriSayisi == 0) {
            System.out.println("Henuz bilet alinmamıstir.");
            return;
        }

        System.out.println("Biletler:");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println(musteriAdlari[i] + " adlı musteri su filmleri izlemek icin bilet aldi:");
            for (int j = 0; j < secilenFilmSayisi[i]; j++) {
                System.out.println("- " + biletler[i][j]);
            }
        }
    }
}
