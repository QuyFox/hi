// db
// RMI BYTE
// 1 XUẤT HIỆN MIN
package RMI;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ByteService;
public class XuatHienMin {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B23DCCN698", "KNA3mteC");
        for(byte x: a) System.out.print(x + " ");
        System.out.println("");
        //b. Đếm
        int[] cnt = new int[10005]; 
        for (byte x : a) cnt[x]++;
        byte ptuMin = a[0];
        int slMin = cnt[a[0]];
        for (byte x : a) {
            if (cnt[x] < slMin) {
                ptuMin = x;
                slMin = cnt[x];
            }
        }
        System.out.println(ptuMin + " " + slMin);
        // c. Gửi
        byte[] res = {ptuMin, (byte)slMin};
        sv.submitData("B23DCCN698", "KNA3mteC", res);
    }
}
//2 xuất hiện max
package RMI;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ByteService;
public class XuatHienMax {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B23DCCN698", "oXiv1PFL");
        for(byte x: a) System.out.print(x + " ");
        System.out.println("");
        //b. Đếm
        int[] cnt = new int[128]; 
        for (byte x : a) cnt[x]++;
        byte ptuMax = a[0];
        int slMax = cnt[ptuMax];
        for (byte x : a) {
            if (cnt[x] > slMax) {
                ptuMax = x;
                slMax = cnt[x];
            }
        }
        System.out.println(ptuMax + " " + slMax);
        // c. Gửi
        byte[] res = {ptuMax, (byte) slMax};
        sv.submitData("B23DCCN698", "oXiv1PFL", res);
    }
}
//3 DATA VỊ TRÍ ĐỈNH CỤC BỘ
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;

public class RMI_Data_ViTriDinhCucBo {
    public static void main(String[] args) throws Exception {       // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int[] a = (int[]) sv.requestData("B22DCCN902", "vC4VFknJ");   // b. Xử lý
        List<Integer> res = new ArrayList<>();
        int n = a.length;
        if (n == 1) {
            res.add(1);
        } else {           // Phần tử đầu
            if (a[0] > a[1])
                res.add(1);           // Các phần tử giữa
            for (int i = 1; i < n - 1; i++) {
                if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                    res.add(i + 1); // đánh số từ 1
                }
            }           // Phần tử cuối
            if (a[n - 1] > a[n - 2])
                res.add(n);
        }      // c. Gửi kết quả
        sv.submitData("B22DCCN902", "vC4VFknJ", res);
    }
}
//

//BYTE SERVICE
//XOR2
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ByteService;
public class PhepXOR2 {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN012", "4BraNTI5");
        for(byte x: a) System.out.print(x + " ");
        System.out.println();
        //b. Mã hoá
        String tmp = "PTIT";
        byte[] tmpB = tmp.getBytes(); // Chuyển khóa thành mảng byte
        byte[] maHoa = new byte[a.length];
        for(int i = 0;i<a.length;i++) maHoa[i] = (byte) (a[i] ^ tmpB[i % tmpB.length]);
        for(byte x: maHoa) System.out.print(x + " ");
        //c. Gửi
        sv.submitData("B21DCCN012", "4BraNTI5", maHoa);
    }
}
//
// CHUYỂN ĐỔI BÁT PHÂN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ByteService;
public class ChuyenDoiBatPhan {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN008", "HhPAxeDw");
        for (byte x : a) System.out.print(x + " ");
        System.out.println();
        // b. Chuyển đổi mảng byte thành chuỗi bát phân
        String res = "";
        for (byte x : a) res+=String.format("%03o", x & 0xFF);
        System.out.println(res);
        byte[] ans = res.toString().getBytes();
        sv.submitData("B21DCCN008", "HhPAxeDw", ans);
    }
}
// GIẢI MÃ BASE64
package RMI.B22DCCN902;
import RMI.ByteService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Base64;
public class GiaiMaBase64 {
    public static void main(String[] args) throws Exception{
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN015", "oeJljCIf");
        // b. Giải mã 
        String s = new String(a);  //Byte -->String
        System.out.println(s);
        byte[] ans = Base64.getDecoder().decode(s);  // Giải mã Base64
        for (byte x: ans) System.out.print(x + " ");
        System.out.println();
        // c. 
        sv.submitData("B21DCCN015", "oeJljCIf", ans);
    }
}
//LỚN THỨ K
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.ByteService;
public class LonThuK {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN066", "uIKHCTWG");
        for(byte x: a) System.out.print(x + " ");  
        System.out.println();
        //b.
        byte []b = Arrays.copyOf(a, a.length);
        int k = a[a.length - 1], pos = 0;
        Arrays.sort(a);
        for(int i = 0;i<b.length;i++){
            if(b[i]==a[a.length - k]){
                pos = i;
                break;
            }
        }
        byte []ans = {(byte)a[a.length - k], (byte)(pos + 1)};
        for(byte x: ans) System.out.print(x + " ");
        //c.
        sv.submitData("B21DCCN066", "uIKHCTWG", ans);
    }
}
//MÃ HÓA CAESAR 2
package RMI.B22DCCN902;
import RMI.ByteService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class MaHoaCaesar2 {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN028", "i0EVI2TB");
        for(byte x: a) System.out.print(x + " ");
        System.out.println("");
        int doDich = a.length;
        for (int i = 0; i < a.length; i++) a[i]+=doDich;
        for(byte x: a) System.out.print(x + " ");
        //c.
        sv.submitData("B21DCCN028", "i0EVI2TB", a);
    }
}
//MA HÓA HEXA
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ByteService;
public class MaHoaHexa {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN032", "pK0IZNnt");
        for (byte x : a) System.out.print(x + " ");
        System.out.println();
        // b. Chuyển đổi mảng byte thành chuỗi hexa
        String res = "";
        for (byte x : a) res+=String.format("%02x", x & 0xFF);
        System.out.println(res);
        byte[] ans = res.toString().getBytes();
        sv.submitData("B21DCCN032", "pK0IZNnt", ans);
    }
}
//NÉN RLE
package RMI.B22DCCN902;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ByteService;
public class NenRLE {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN036", "2uG0lQGi");
        for (byte x : a) System.out.print(x + " ");
        System.out.println();
        // b. 
        int cnt = 1;
        ArrayList<Byte>res = new ArrayList<>();
        for(int i = 1;i<a.length;i++){
            if(a[i] == a[i - 1]) cnt++;
            else{
                res.add(a[i - 1]); res.add((byte)cnt);
                cnt = 1;
            }
        }
        res.add(a[a.length - 1]); res.add((byte)cnt);
        //Cóp sang mảng
        byte []ans = new byte[res.size()];
        int idx = 0;
        for(byte x: res) ans[idx++] = x;
        for(byte x: ans) System.out.print(x + " ");
        sv.submitData("B21DCCN036", "2uG0lQGi", ans);        
    }
}
//SẮP XẾP CHẴN LẺ
package RMI.B22DCCN902;
import RMI.ByteService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class SapXepChanLe {
    public static void main(String[] args) throws Exception{
        //a.
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN029", "A7hytb1V");
        for(byte x: a) System.out.print(x + " ");
        System.out.println("");
        //b. sx
        byte []res = new byte[a.length];
        int idx = 0;
        for(byte x: a){
            if(x % 2==0)  res[idx++] = x;
        }
        for(byte x: a){
            if(x % 2==1)  res[idx++] = x;
        }
        for(byte x: res) System.out.print(x + " ");
        //c.
        sv.submitData("B21DCCN028", "A7hytb1V", res);
    }
}





// CHARACTER SERVICE
//MÃ HÓA URL
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import java.net.URLEncoder;
import RMI.CharacterService;
public class MaHoaURL {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN053", "KkihaRAB");
        System.out.println(s);
        //b.
        String ans = URLEncoder.encode(s, "UTF-8");
        System.out.println(ans);
        //c.
        sv.submitCharacter("B21DCCN053", "KkihaRAB", ans);
    }
}
//URL ???
package RMI.B22DCCN902;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import RMI.CharacterService;

public class RMI_Character_MaHoaURL {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService sv =   (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B22DCCN902", "vFmOFNkt");
        System.out.println(s);
        String ans = URLEncoder.encode(s, StandardCharsets.UTF_8)
                               .replace("+", "%20");
        System.out.println(ans);
        sv.submitCharacter("B22DCCN902", "vFmOFNkt", ans);
    }
}
// MÃ HÓA VIGEN
package RMI.B22DCCN902;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.CharacterService;
public class MaHoaVigen {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN021", "Y7YMXHs4");
        System.out.println(s);
        int idx = s.indexOf(";");
        String keyW = s.substring(0, idx), text = s.substring(idx + 1), ans = "";
        for(int i = 0;i<text.length();i++){
            char x = text.charAt(i), y = keyW.charAt(i % keyW.length());
            char z;
            if (Character.isUpperCase(x)) z = (char) ((x - 'A' + y - 'A') % 26 + 'A');
            else z = (char) ((x - 'a' + y - 'a') % 26 + 'a');
            ans+=z;
        }
        System.out.println(ans);
        sv.submitCharacter("B21DCCN021", "Y7YMXHs4", ans);
    } 
}
//

//MÃ HÓA BASE64
package RMI.B22DCCN902;
import RMI.CharacterService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Base64;
public class MaHoaBase64 {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN029", "psd4Jmnt");
        System.out.println(s);
        //
        byte []tmp = Base64.getEncoder().encode(s.getBytes());
        String ans = new String(tmp);
        System.out.println(ans);
        //
        sv.submitCharacter("B21DCCN029", "psd4Jmnt", ans);
    }
}
//MÃ HÓA CAESAR1
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class MaHoaCaesar1 {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN022", "j4rStb2a");
        System.out.println(s);
        //b.
        String ans = "";
        int dich = s.length()%7;
        for(char x: s.toCharArray()){
            char base = Character.isUpperCase(x) ? 'A' : 'a';
            x = (char) ((x - base - dich + 26) % 26 + base);
            ans+=x;
        }
        System.out.println(ans);
        //c.
        sv.submitCharacter("B21DCCN022", "j4rStb2a", ans);
    }
}


// CHUYỂN ĐỔI LA MÃ THẬP PHÂN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class ChuyenDoiLaMaThapPhan {
    public static long trans(char c){
        if (c=='I') return 1;
        else if (c=='V') return 5;
        else if (c=='X') return 10;
        else if (c=='L') return 50;
        else if (c=='C') return 100;
        else if (c=='D') return 500;
        else return 1000;
    }
    public static long change(String s){
        long tong = 0;
        tong+= trans(s .charAt(s.length()-1));
        for(int i = s.length() - 2;i>=0;i--){
            if(trans(s.charAt(i+ 1))>trans(s.charAt(i))) tong-=trans(s.charAt(i));
            else tong+=trans(s.charAt(i));
        }
        return tong;
    }
    public static void main(String[] args) throws Exception{
        //a.
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN015", "0JaasIw6");
        System.out.println(s);
        //c. Gửi
        System.out.println(change(s));
        sv.submitCharacter("B21DCCN015", "0JaasIw6", String.valueOf(change(s)));
    }
}
// CHUYỂN ĐỔI THẬP PHÂN LA MÃ
package RMI.B22DCCN902;
import RMI.CharacterService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class ChuyenDoiThapPhanLaMa {
    public static void main(String[] args)throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN032", "ADu6zRYE");
        System.out.println(s);
        //b.
        String rm = "";
        int dec = Integer.parseInt(s);
        int[] tp = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};//thập phân
        String[] lm = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};// la mã
        for(int i = 0;i<tp.length;i++){
            while(dec >=tp[i]){
                rm+=lm[i];
                dec-=tp[i];
            }
        }
        System.out.println(rm);     
        //c.
        sv.submitCharacter("B21DCCN032", "ADu6zRYE", rm);
    }
}
//ĐẾM SỐ LẦN XUẤT HIỆN 1
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class DemSoLanXH1 {
    public static void main(String[] args) throws Exception {
        // a. Nhận chuỗi từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN564", "U5BEBBOW");
        System.out.println(s);
        // b. Xử lý xâu
        String res = "";
        int[] cnt = new int[256]; 
        for (char x : s.toCharArray()) cnt[x]++;
        for(char x: s.toCharArray()){
            if(cnt[x] > 0){
                res+=String.format("%c%d", x, cnt[x]);
                cnt[x] = 0;
            }
        }
        System.out.println(res);
        // c. Gửi kết quả lại server
        sv.submitCharacter("B21DCCN564", "U5BEBBOW", res);
    }
}
// ĐẾM SỐ LẦN XUẤT HIỆN 2
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class DemSoLanXH2 {
    public static void main(String[] args) throws Exception {
        // a. Nhận chuỗi từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN012", "ctRfIejL");
        System.out.println(s);
        int[] cnt = new int[256]; 
        for (char x : s.toCharArray()) cnt[x]++;
        String res = "";
        boolean first = true;
        for (char x : s.toCharArray()) {
            if (cnt[x] > 0) {
                if (!first) res+=", ";
                res+=String.format("\"%c\": %d", x, cnt[x]); 
                cnt[x] = 0; 
                first = false;
            }
        }
        res = "{" + res + "}";
        System.out.println(res);
        // c. Gửi kết quả lại server
        sv.submitCharacter("B21DCCN012", "ctRfIejL", res);
    }
}
//XỬ LÍ VĂN BẢN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.CharacterService;
public class XuLyVanBan {
    public static void main(String[] args) throws Exception {
        // a. Nhận chuỗi từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN319", "NFldNPp6");
        System.out.println(s);
        // b. Xử lý xâu
        s = s.replace("\"", "");  s = s.replace("{", "");  s = s.replace("}", "");   
        String[] tmp = s.trim().split(",");// Tách chuỗi thành các cặp key-value
        String chan = "", le = "";
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].trim(); 
            if (i % 2 == 0) {
                if (chan.length() > 0) chan+=", ";
                chan+=tmp[i];
            } else {
                if (le.length() > 0) le+=", ";
                le+=tmp[i];
            }
        }
        String res = chan + "; " + le;
        System.out.println(res);
        // c. Gửi kết quả lại server
        sv.submitCharacter("B21DCCN319", "NFldNPp6", res);
    }
}

//XOR1
package RMI.B22DCCN902;
import RMI.CharacterService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class PhepXOR1 {
    public static void main(String[] args) throws Exception{
        // a. 
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String s = sv.requestCharacter("B21DCCN008", "Xidb0Thk");
        System.out.println(s);
        // b. Xử lý xâu
        String []tmp = s.split(";");
        String xorKey =tmp[0], inp = tmp[1], res = "";
        for(int i = 0;i<inp.length();i++) res+=(char)(inp.charAt(i) ^ xorKey.charAt(i % xorKey.length()));
        System.out.println(res);
        // c. Gửi kết quả lại server
        sv.submitCharacter("B21DCCN008", "Xidb0Thk", res);
    }
}
//



// DATA SERVICE
//THỐNG KÊ SÓ THỰC
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import RMI.DataService;
public class RMI_Data_ThongKeSoThuc {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String) sv.requestData("B22DCCN902", "pl8YVlmE");      // b. Xử lý
        String[] arr = s.split(",");
        int n = arr.length;
        double[] a = new double[n];
        double sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Double.parseDouble(arr[i]);
            sum += a[i];
        }
        double avg = sum / n;
        double variance = 0;
        for (double x : a) {
            variance += (x - avg) * (x - avg);
        }
        double stddev = Math.sqrt(variance / n);
        Arrays.sort(a);
        int pos = (int) Math.ceil(n * 0.95) - 1;
        double p95 = a[pos];
        String res = String.format(
                "average=%.2f;stddev=%.2f;p95=%.2f",
                avg, stddev, p95);

        System.out.println(res);
        sv.submitData("B22DCCN902", "pl8YVlmE", res);
    }
}
//pHƯƠNG SAI
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class PhuongSai {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String) sv.requestData("B21DCCN016", "uZMEY3Zg");
        System.out.println(s);
        // b. Xử lý: Tìm danh sách các bộ ba số Pythagore
        ArrayList<Double> a = new ArrayList<>();
        s = s.replace(",", " ");
        String[] tmp = s.trim().split("\\s+");
        for (String x : tmp) a.add(Double.parseDouble(x));
        int n = a.size(); 
        double tong = 0, tongTmp = 0;
        for (double x : a) tong += x;
        double tbc = tong / (double) n;
        for (double x : a) tongTmp += (x - tbc) * (x - tbc);
        double pSai = tongTmp / n;  
        double doLechChuan = Math.sqrt(pSai);
        System.out.println(pSai);
        System.out.println(doLechChuan);
        // c. Gửi kết quả
        String res = String.format("%.2f : %.2f", pSai, doLechChuan);
        System.out.println(res);
        sv.submitData("B21DCCN016", "uZMEY3Zg", res);
    }
}
//

// BỘ BA PYTAGO
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class BoBaPytago {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int N = (int) sv.requestData("B21DCCN319", "NMATI6Zw");
        System.out.println(N);
        // b. Xử lý: Tìm danh sách các bộ ba số Pythagore
        List<List<Integer>> res = new ArrayList<>();
        for (int a = 1; a <= N; a++) {
            for (int b = a + 1; b <= N; b++) {
                for (int c = b + 1; c <= N; c++) {
                    if (a * a + b * b == c * c) {
                        List<Integer> triple = new ArrayList<>();
                        triple.add(a); triple.add(b); triple.add(c);
                        res.add(triple);
                    }
                }
            }
        }
        System.out.println(res);
        // c. Gửi kết quả
        sv.submitData("B21DCCN319", "NMATI6Zw", res);
    }
}
// ĐỔI TIỀN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class DoiTien {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int n = (int) sv.requestData("B21DCCN012", "Iz06p8Zw"), res = 0;
        System.out.println(n);
        // b. Xử lý: Tìm số đồng tiền
        String ans = "";
        int[] a = {1, 2, 5, 10}; 
        for (int i = 3; i >= 0; i--) {
            int p = n / a[i]; 
            if (p > 0) {
                res += p; 
                n -= p * a[i]; 
                for (int j = 0; j < p; j++) ans+=a[i] + ",";
            }
        }
        if (n > 0) ans = "-1";
        else {
            ans = ans.substring(0, ans.length() - 1);
            ans= String.format("%d; ", res) + ans;
        }
        System.out.println(ans);
        // c. Gửi kết quả
        sv.submitData("B21DCCN012", "Iz06p8Zw", ans);
    }
}

//HOÁN VỊ TIẾP THEO
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class HoanViTiepTheo {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String) sv.requestData("B21DCCN564", "3Qe0Qb5w");
        System.out.println(s);
        //Đẩy hết vào mảng để xử lý
        s = s.replace(",", " ");
        String[] tmp = s.trim().split("\\s+");
        ArrayList<Integer> a = new ArrayList<>();
        for (String x : tmp) a.add(Integer.parseInt(x));
        int n = a.size();
        //b. Sinh hoán vị kế tiếp
        // Bước 1: Tìm vị trí đầu tiên từ cuối mà tại đó nó nhỏ hơn số đằng sau nó
        int i = n - 2;
        while (i >= 0 && a.get(i) >= a.get(i + 1)) i--;
        // Bước 2: Kiểm tra nếu không còn hoán vị nào lớn hơn
        if (i < 0) Collections.sort(a); // Đưa về hoán vị đầu tiên
        else {
            // Bước 3: Tìm vị trí đầu tiên từ cuối lớn hơn a[i]
            int j = n - 1;
            while (a.get(j) <= a.get(i)) j--;
            Collections.swap(a, i, j);// Hoán đổi a[i] và a[j]
            // Bước 4: Lật ngược đoạn từ i + 1 đến cuối mảng
            int l = i + 1, r = n - 1;
            while (l < r) {
                Collections.swap(a, l, r);
                l++;
                r--;
            }
        }
        String res = "";
        for(int x: a) res+=String.format("%d,", x);
        res = res.substring(0, res.length() - 1);
        System.out.println(res);
        // c. Gửi kết quả
        sv.submitData("B21DCCN564", "3Qe0Qb5w", res);
    }
}
//LIỆT KÊ SỐ CHÍNH PHƯƠNG
package RMI.B22DCCN902;
import RMI.DataService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
public class LietKeSoCP {
    public static boolean check(int x){
        double y = Math.sqrt(x);
        int y1 = (int)y;
        return y == y1;
    }
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int n = (int) sv.requestData("B21DCCN028", "eTHcAYZh");
        System.out.println(n);
        List<Integer>a = new ArrayList<>();
        for(int i = 1;i<n;i++){
            if(check(i)) a.add(i);
        }
        System.out.println(a);
        sv.submitData("B21DCCN028", "eTHcAYZh", a);
    }
}
//lIỆT KÊ SỐ ĐỐI XỨNG
package RMI.B22DCCN902;
import RMI.DataService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
public class LietKeSoDoiXung {
    public static boolean check(int x){
        String s = x+"";
        for(int i = 0;i<=s.length()/2;i++){
            if(s.charAt(i)!=s.charAt(s.length() - i - 1)) return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String) sv.requestData("B21DCCN021", "t3cBY2uk");
        System.out.println(s);
        s = s.replace(";", "");
        String []tmp = s.trim().split("\\s+");
        int n = Integer.parseInt(tmp[0].trim()), k = Integer.parseInt(tmp[1].trim());
        List<Integer>a = new ArrayList<>();
        for(int i = n;i<k;i++){
            if(check(i)) a.add(i);
        }
        System.out.println(a);
        sv.submitData("B21DCCN021", "t3cBY2uk", a);
    }
}
//lIỆT KÊ SÓ NGUYÊN TỐ
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class LietKeSoNguyenTo {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int N = (int) sv.requestData("B21DCCN015", "nD8MdtME");
        System.out.println(N);
        // b. Xử lý: Tìm list các SNT
        List<Integer> res = new ArrayList<>();
        for(int i = 2;i<=N;i++){
            if(check(i)==1) res.add(i);
        }
        System.out.println(res);
        // c. Gửi kết quả
        sv.submitData("B21DCCN015", "NMATI6Zw", res);
    }
    public static int check(int n){//ktra snt
        for(int i = 2;i*i<=n;i++){
            if(n%i==0) return 0;
        }
        return 1;
    }
}
//PHÂN TÍCH TSNT
package RMI.B22DCCN902;
import java.util.*;
import RMI.DataService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class PhanTichTSNT {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        int n = (int) sv.requestData("B21DCCN022", "zzmmquoc");
        System.out.println(n);
        List<Integer>a = new ArrayList<>();
        for(int i = 2;i*i<=n;i++){
            while(n%i==0){
                a.add(i);
                n/=i;
            }
        }
        if(n>1) a.add(n);
        System.out.println(a);
        sv.submitData("B21DCCN022", "zzmmquoc", a);
    }
}
//SINH TỔ HỢP
package RMI.B22DCCN902;
import RMI.DataService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
public class SinhToHop {
    static int ok; 
    public static void kt(int a[], int k) {
        for (int i = 1; i <= k; i++) a[i] = i;
    }
    public static void sinh(int a[], int n, int k) {
        int i = k;
        while (i >= 1 && a[i] == n - k + i) i--;
        if (i == 0) {
            ok = 0;
        } else {
            a[i]++;
            for (int j = i + 1; j <= k; j++) a[j] = a[j - 1] + 1;
        }
    }
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");
        String s = (String)sv.requestData("B21DCCN053", "juC3u7C6");
        //String s = "2, 0, 5, 8, 1 ;3"; 
        System.out.println(s);
        int idx = s.indexOf(";");
        String s1 = s.substring(0, idx), s2 = s.substring(idx + 1);
        int k = Integer.parseInt(s2.trim());// Lấy số k
        // Lấy ra mảng và sắp xếp
        ArrayList<Integer> a = new ArrayList<>();
        s1 = s1.replace(",", " ");
        String[] tmp = s1.trim().split("\\s+");
        for (String x : tmp) a.add(Integer.parseInt(x));
        // Bắt đầu sinh tổ hợp. Khởi tạo
        int n = a.size();
        int[] genIdx = new int[k + 1];
        kt(genIdx, k);
        ok = 1;
        // Duyệt qua từng tổ hợp chỉ số
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (ok == 1) {
            ArrayList<Integer> tmp1 = new ArrayList<>();
            for (int i = 1; i <= k; i++) tmp1.add(a.get(genIdx[i] - 1));
            ans.add(tmp1);
            sinh(genIdx, n, k);
        }
        //c.
        System.out.println(ans);
        sv.submitData("B21DCCN053", "juC3u7C6", ans);
    }
}
// SÓ LỚN THỨ K
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import RMI.DataService;
public class SoLonThuK {
    public static void main(String[] args) throws Exception {
        //a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService)rg.lookup("RMIDataService");
        String s = (String) sv.requestData("B21DCCN008", "wzG6G9ji");
        System.out.println(s);
        //b. Xử lý dữ liệu
        int x = s.indexOf(";");
        String list = s.substring(0, x), k = s.substring(x + 1);
        ArrayList<Integer>a = new ArrayList<>();
        int k1 = Integer.parseInt(k.trim());
        String []tmp = list.trim().split(",");
        for(String x1: tmp) a.add(Integer.parseInt(x1.trim()));
        Collections.sort(a);
        int ans = a.get(a.size() - k1);
        //c. Gửi dữ liệu
        sv.submitData("B21DCCN008", "wzG6G9ji", ans);
        System.out.println(ans);
    }
}
//


// OBJECT
//QUẢN LÝ NHÂN VIÊN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Employee;
public class QuanLyNhanVien {
    public static void main(String[] args) throws Exception{
        //a.
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Employee employee = (Employee) sv.requestObject("B21DCCN038", "7fSWnlHB");
        System.out.println(employee);
        //b.tổng cs
        int tongCS = 0, x = employee.getExperienceYears(), y = employee.getExperienceYears();
        while(x>0){
            tongCS+=x%10;
            x/=10;
        }
        //tổng ước
        int soUoc = 0;
        for(int i = 1;i<=y;i++){
            if(y%i==0) soUoc++;
        }
        //factor
        double factor = (double)(employee.getExperienceYears() + tongCS + soUoc)/100.0;
        double Final = (employee.getBaseSalary()) * (1 + factor);
        //
        employee.setFinalSalary(Final);
        System.out.println(employee);
        //c.
        sv.submitObject("B21DCCN038", "7fSWnlHB", employee);
    }
}
//QUẢN LÍ SINH VIÊN
package RMI.B22DCCN902;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Student;
public class QuanLySinhVien {
    public static String chuanhoa(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
    public static String chuanhoa2(String s){
        String []tmp = s.trim().split("\\s+");
        String res = "";
        for(int i = 0;i<tmp.length - 1;i++) res+=chuanhoa(tmp[i]) + " ";
        res+=tmp[tmp.length - 1].toUpperCase();
        return res;
    }
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Student student = (Student) sv.requestObject("B21DCCN023", "lNV6xzmk");
        System.out.println(student);
        student.setName(chuanhoa2(student.getName()));
        String code = String.format("B%d", student.getEnrollmentYear()%100);
        String []tmp = student.getName().split("\\s+");
        code+=tmp[tmp.length - 1].toUpperCase() + "_";
        for(int i = 0;i<tmp.length - 1;i++) code+=Character.toUpperCase(tmp[i].charAt(0));
        student.setCode(code);
        System.out.println(student);
        //c.
        sv.submitObject("B21DCCN023", "lNV6xzmk", student);
    }
}
//QUẢN LÍ SỰ KIỆN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Ticket;
import java.util.*;
public class QuanLySuKien {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Ticket ticket = (Ticket) sv.requestObject("B21DCCN048", "Uct8bABt");
        //Ticket ticket = new Ticket(1, "Charity Concert", "15/06/2024");
        System.out.println(ticket);
        //Lấy tên
        String s = ticket.getEventName().toUpperCase();
        String res = "";
        res+=String.format("%c%c", Character.toUpperCase(s.charAt(0)), (char)Character.toUpperCase(s.charAt(s.length() - 1)));
        //Lấy ngày sinh
        String s1 = ticket.getSaleDate();
        s1 = s1.replace("/", " ");
        String []tmp = s1.trim().split("\\s+");
        res+=tmp[1] + tmp[0];
        //Lấy 2 chữ số
        Set<Integer>si = new HashSet<>();
        for(char x: s1.toCharArray()){
            if(Character.isDigit(x)) si.add(x - '0');
        }
        for(int i = 9;i >= 0;i--){
            if(!si.contains(i)){
                res+=i;
                break;
            }
        }
        for(int i = 0;i<=9;i++){
            if(!si.contains(i)){
                res+=i;
                break;
            }
        }
        ticket.setTicketCode(res);
        System.out.println(ticket);
        //c.
        sv.submitObject("B21DCCN048", "Uct8bABt", ticket);
    }
}

//QUẢN LÍ THƯ VIỆN 1
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Book;
public class QuanLyThuVien1 {
    public static void main(String[] args) throws Exception {
        //a. Nhận sản phẩm từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Book book = (Book) sv.requestObject("B21DCCN008", "nCOdwcKJ");
        System.out.println(book);
        //Tạo mã
        String code = "";
        String[] tmp = book.getAuthor().split(" ");
        code+=String.format("%c%c", Character.toUpperCase(tmp[0].charAt(0)), Character.toUpperCase(tmp[tmp.length - 1].charAt(0)));
        code+=String.format("%02d%d%03d", book.getYearPublished()%100, 
                book.getTitle().trim().length(), book.getPageCount());
        book.setCode(code);
        System.out.println(book);
        // c. 
        sv.submitObject("B21DCCN008", "nCOdwcKJ", book);
    }
}

// QUẢN LÍ THƯ VIỆN 2
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.BookX;
public class QuanLyThuVien2 {
    public static void main(String[] args) throws Exception {
        //a. Nhận sản phẩm từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        BookX book = (BookX) sv.requestObject("B21DCCN012", "CoWosBho");
        System.out.println(book);
        //Tạo mã
        String code = "";
        String[] tmp = book.getAuthor().split(" ");
        code+=tmp[0].charAt(0);code+=tmp[tmp.length - 1].charAt(tmp[tmp.length - 1].length() - 1);
        code+=String.format("%02d%d%d", book.getYearPublished()%100, 
                book.getGenre().trim().length(), book.getTitle().length()%10);
        book.setCode(code);
        // c. 
        sv.submitObject("B21DCCN012", "CoWosBho", book);
    }
}
//TICKET SLA
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.TicketSla;

public class RMI_Object_TicketSla {
    public static void main(String[] args) throws Exception {        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        TicketSla t = (TicketSla) sv.requestObject("B22DCCN902", "4HT0We7x");        // b. Xử lý
        boolean breached = false;
        String priority = t.getPriority();
        int hours = t.getOpenedHoursAgo();
        if (priority.equals("CRITICAL") && hours > 2)
            breached = true;
        else if (priority.equals("HIGH") && hours > 8)
            breached = true;
        else if (priority.equals("MEDIUM") && hours > 24)
            breached = true;
        else if (priority.equals("LOW") && hours > 72)
            breached = true;
        t.setBreached(breached);
        if (!breached) {
            t.setAction("MONITOR");
        } else {
            if (priority.equals("CRITICAL") || hours > 96)
                t.setAction("ESCALATE_L2");
            else
                t.setAction("ESCALATE_L1");
        }
        sv.submitObject("B22DCCN902", "4HT0We7x", t);
    }
}
//TMDT
package RMI.B22DCCN902;
import RMI.Order;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
public class TDMT {
    public static void main(String[] args) throws Exception{
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Order order = (Order) sv.requestObject("B21DCCN021", "vLJWvWpf");
        System.out.println(order);
        //b.
        String x = order.getShippingType().substring(0, 2).toUpperCase(); // Hai ký tự đầu của shippingType
        String y = order.getCustomerCode().substring(order.getCustomerCode().length() - 3); // Ba ký tự cuối của customerCode
        String z = order.getOrderDate().substring(8, 10) + order.getOrderDate().substring(5, 7); // ddMM từ orderDate
        String orderCode = x + y + z;
        order.setOrderCode(orderCode);
        //c
        System.out.println(order);
        sv.submitObject("B21DCCN021", "vLJWvWpf", order);
    }
}

//TO CHỨC SỰ KIỆN
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Event;
public class ToChucSuKien {
    public static void main(String[] args) throws Exception{
        //a.
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Event event = (Event) sv.requestObject("B21DCCN029", "FKrGvwLM");
        System.out.println(event);
        //b.
        String ans = "";
        int x = event.getExpectedAttendance();
        //
        if(x>=1000) ans = "L";
        else if (x>=500 && x<=999) ans = "M";
        else ans = "S";
        //
        String []tmp = event.getEventName().trim().split("\\s+");
        ans+=Character.toUpperCase(tmp[0].charAt(0));
        ans+=Character.toUpperCase(tmp[tmp.length - 1].charAt(tmp[tmp.length - 1].length() - 1));
        //
        String tmp1 = event.getEventDate();
        tmp1 = tmp1.replace("-", " ");
        String []tmp2 = tmp1.trim().split("\\s+");
        ans+=tmp2[2] + tmp2[1];
        event.setEventCode(ans);
        //c.
        System.out.println(event);
        sv.submitObject("B21DCCN029", "FKrGvwLM", event);
    } 
}
//XỬ LÍ SP 2
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.ProductX;
public class XuLySanPham2 {
    public static void main(String[] args) throws Exception {
        //a. Nhận sản phẩm từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        ProductX product = (ProductX) sv.requestObject("B21DCCN564", "PY43T66m");
        System.out.println(product);
        // b. Thực hiện chuẩn hóa sản phẩm:
        int tong = 0;
        for(char x: product.getDiscountCode().toCharArray()){
            if(Character.isDigit(x)) tong+=Character.getNumericValue(x);
        }
        product.setDiscount(tong);
        // c. Triệu gọi phương thức submitObject để gửi đối tượng đã chuẩn hóa trở lại server
        System.out.println(product);
        sv.submitObject("B21DCCN564", "PY43T66m", product);
    }
}
// XỬ LÍ SP 1
package RMI.B22DCCN902;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService;
import RMI.Product;
public class XuLySanPham1 {
    public static void main(String[] args) throws Exception {
        //a. Nhận sản phẩm từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Product product = (Product) sv.requestObject("B21DCCN319", "dx3nt4Ij");
        System.out.println(product);
        // b. Thực hiện chuẩn hóa sản phẩm:
        product.setCode(product.getCode().toUpperCase());// Chuyển mã sản phẩm thành in hoa
        product.setExportPrice(product.getImportPrice() * 1.2f);
        System.out.println("Normalized product: " + product);
        // c. Triệu gọi phương thức submitObject để gửi đối tượng đã chuẩn hóa trở lại server
        sv.submitObject("B21DCCN319", "dx3nt4Ij", product);
    }
}





