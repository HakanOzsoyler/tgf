package com.example_tgf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
              try {

           // `String apiUrl = "http://universities.hipolabs.com/search?name=suleyman&country=Turkey";`
           // satırı, `apiUrl` değişkenine bir URL dizesi atıyor. Bu URL, Türkiye'deki üniversiteleri
           // aramak için çağrılan "süleyman" isimli API'nin uç noktasıdır.
            String apiUrl = "http://universities.hipolabs.com/search?name=suleyman&country=Turkey";

            // 'URL url = new URL(apiUrl);' satırı, belirtilen 'apiUrl' dizesiyle yeni bir 'URL'
            // nesnesi yaratıyor. Bu "URL" nesnesi, bağlanmak istediğiniz API uç noktasının URL'sini
            // temsil eder.
            URL url = new URL(apiUrl);

          // 'HttpURLConnection bağlantısı = (HttpURLConnection) url.openConnection();' kodu, 'url'
          // nesnesinde 'openConnection()' yöntemini çağırarak yeni bir 'HttpURLConnection' nesnesi
          // oluşturur. Bu, belirtilen URL'ye bağlantı kurar.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
           
           // `int ResponseCode = Connection.getResponseCode();` satırı sunucudan HTTP yanıt kodunu
           // alıyor. Yanıt kodu, sunucuya yapılan HTTP isteğinin durumunu gösterir. İsteğin başarılı
           // olup olmadığını veya bir hata olup olmadığını belirlemek için kullanılabilir. Yaygın
           // yanıt kodları arasında başarılı bir istek için 200, bulunamayan bir kaynak için 404 ve
           // bir sunucu hatası için 500 bulunur.
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
               
               // 'BufferedReader in = new BufferedReader(new
               // inputStreamReader(connection.getInputStream()));' satırı, API'den gelen yanıtı okumak
               // için bir 'BufferedReader' nesnesi yaratıyor.
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

               
                // `System.out.println("İstek servis cevabı:");` satırı "İstek servis cevabı:" mesajını
                // konsola yazdırıyor. Bu, aşağıdaki kod satırının API hizmetinden gelen yanıtı
                // yazdıracağını belirtmek için kullanılır.
                System.out.println("İstek servis cevabı:");
                System.out.println(response.toString());
            } else {
               // `System.out.println("İstek servis başarısız. Hata kodu: " + ResponseCode);` satırı,
               // hata koduyla birlikte hizmet isteğinin başarısız olduğunu belirten bir mesajı konsola
               // yazdırıyor. Mesaj Türkçedir ve "Hizmet isteği başarısız oldu. Hata kodu: " ve
               // ardından 'responseCode' değişkeninin değeri anlamına gelir. Bu, hata ayıklamak ve
               // kullanıcıya API isteğinin durumu hakkında geri bildirim sağlamak için kullanışlıdır.
                System.out.println("İstek servis çağrısı başarısız. Hata kodu: " + responseCode);
            }

           
            // API uç noktasına olan bağlantıyı kapatmak için `connection.disconnect();` yöntemi
            // kullanılır. Ağ soketleri gibi bağlantıyla ilişkili tüm kaynakları serbest bırakır ve
            // bunların diğer uygulamalar tarafından yeniden kullanılmasına olanak tanır. Sistem
            // kaynaklarını boşaltmak için bağlantıyı kullanmayı bitirdikten sonra kapatmak iyi bir
            // uygulamadır.
            connection.disconnect();

        } catch (IOException e) {
           // `e.printStackTrace();`, Java'da bir istisnanın yığın izini yazdıran bir yöntemdir.
           // İstisnanın oluştuğu sınıf, yöntem ve satır numarası dahil olmak üzere bir istisnanın
           // ayrıntılarını konsola yazdırmak için kullanılır. Bu, istisnanın nedeni hakkında bilgi
           // sağladığından ve hatanın kaynağının belirlenmesine yardımcı olduğundan, hata ayıklama
           // amacıyla yararlı olabilir.
            e.printStackTrace();
        }
    }
}