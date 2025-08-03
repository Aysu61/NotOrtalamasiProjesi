import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// diğer importlar (Scanner, ArrayList) zaten var

public class NotOrtalamasi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> notlar = new ArrayList<>();
        ArrayList<String> dersler = new ArrayList<>();
        
        System.out.println("Ders ve notlarınızı girin. Bitirmek için 'q' yazın.");

        while(true) {
            System.out.print("Ders adı: ");
            String ders = scanner.nextLine();
            if (ders.equalsIgnoreCase("q")) {
                break;
            }
            System.out.print("Not: ");
            String notStr = scanner.nextLine();
            if (notStr.equalsIgnoreCase("q")) {
                break;
            }
            try {
                double not = Double.parseDouble(notStr);
                dersler.add(ders);
                notlar.add(not);
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz not, lütfen sayı girin.");
            }
        }

        if (notlar.size() == 0) {
            System.out.println("Hiç not girilmedi.");
        } else {
            double toplam = 0;
            for (double n : notlar) {
                toplam += n;
            }
            double ortalama = toplam / notlar.size();
            System.out.println("Girilen notların ortalaması: " + ortalama);
            
            // Dosyaya yazma kısmı
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("notlar.txt"));
                for (int i = 0; i < dersler.size(); i++) {
                    writer.write(dersler.get(i) + ": " + notlar.get(i));
                    writer.newLine();
                }
                writer.write("Ortalama: " + ortalama);
                writer.newLine();
                writer.close();
                System.out.println("Notlar dosyaya yazıldı: notlar.txt");
            } catch (IOException e) {
                System.out.println("Dosyaya yazılırken hata oluştu: " + e.getMessage());
            }
        }
        scanner.close();
    }
}