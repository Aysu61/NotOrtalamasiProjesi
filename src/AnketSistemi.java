import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnketSistemi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int evetSayisi = 0;
        int hayirSayisi = 0;

        System.out.println("Anket Sistemine Hoş Geldiniz!");
        System.out.println("Lütfen soruları 'evet' veya 'hayır' şeklinde cevaplayın.\n");

        String[] sorular = {
            "1. Günlük en az 1 saat ders çalışıyor musunuz?",
            "2. Sosyal medyada günde 2 saatten fazla zaman geçiriyor musunuz?",
            "3. Kendinizi programlamaya yatkın hissediyor musunuz?"
        };

        StringBuilder cevaplar = new StringBuilder();

        for (String soru : sorular) {
            System.out.println(soru);
            String cevap = scanner.nextLine().toLowerCase();
            cevaplar.append(soru).append(" Cevap: ").append(cevap).append("\n");
            if (cevap.equals("evet")) {
                evetSayisi++;
            } else if (cevap.equals("hayır") || cevap.equals("hayir")) {
                hayirSayisi++;
            } else {
                cevaplar.append("(Geçersiz cevap sayılmadı)\n");
            }
        }

        System.out.println("\nAnket Tamamlandı.");
        System.out.println("Toplam 'evet' sayısı: " + evetSayisi);
        System.out.println("Toplam 'hayır' sayısı: " + hayirSayisi);

        // Dosyaya yazma
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("anket_sonuclari.txt", true)); // true = üzerine ekle
            writer.write("Yeni Katılım:\n");
            writer.write(cevaplar.toString());
            writer.write("Toplam Evet: " + evetSayisi + "\n");
            writer.write("Toplam Hayır: " + hayirSayisi + "\n");
            writer.write("------\n");
            writer.close();
            System.out.println("Cevaplar dosyaya yazıldı: anket_sonuclari.txt");
        } catch (IOException e) {
            System.out.println("Dosyaya yazarken hata oluştu: " + e.getMessage());
        }

        scanner.close();

        // Önceki kayıtları okuyup göster
        dosyayiOku();
    }

    // Dosya okuma fonksiyonu
    public static void dosyayiOku() {
        System.out.println("\n📂 Önceki anket kayıtları:");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("anket_sonuclari.txt"));
            String satir;
            while ((satir = reader.readLine()) != null) {
                System.out.println(satir);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
        }
    }
}
