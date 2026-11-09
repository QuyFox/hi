// BAI TREN DB

// 1 DATA SERVICE SỐ LỚN NHẤT
package WSSources;
import WS.DataService;
import WS.SoapDataService;

import java.util.*;
public class DS_SoLonNhat {
    public static void main(String[] args) throws Exception{
        String msv = "B23DCCN698", qCode = "769WzUvr"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
            
        List<Integer> a = port.getData(msv, qCode);

        List<String> a1 = new ArrayList<>();
        for (int x : a) {
            a1.add(x + "");
        }
        Collections.sort(a1, new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            String th1 = s1 + s2;
            String th2 = s2 + s1;
            return th2.compareTo(th1); 
            }
        });
        String ans = "";
        for (String x : a1) {
            ans += x; 
        }
        port.submitDataString(msv, qCode, ans);
    }
}

// 2 DS TÍNH TỔNG

public class DS_Tinhtong {
    public static void main(String args[]) {
        String msv = "B23DCCN698"; String qC = "2ms0EllN";
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        
        List<Integer> a = port.getData(msv, qC);
        
        List<Integer> data = new ArrayList<>();
        int sum = 0;
        for ( int x : a){
            sum += x;
        }
        port.submitDataInt(msv, qC, sum);
    }
}

// 3 CS XOAY KÍ TỰ
package WSSources;
import WSCharacter.CharacterService;
import WSCharacter.SoapCharacterService;
import java.util.*;

public class CS_XoayKiTu {
    public static void main(String[] args) throws Exception {
        String msv = "B23DCCN698", qCode = "2ZYXdCBK"; 
                
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        List<Integer>a = port.requestCharacter(msv, qCode);
        int timeRot = a.get(0)%(a.size());
        Collections.rotate(a, timeRot);
        port.submitCharacterCharArray(msv, qCode, a);
    }
}
// 4 CS NGUYÊN ÂM
package WSSources;

import WSCharacter.CharacterService;
import WSCharacter.SoapCharacterService;
import java.util.*;

public class CS_NguyenAm {
    public static int countVowels(String s) {
        int cnt = 0;
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCCN698";
        String qCode = "4Z6fDVpI";
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        List<String> words = port.requestStringArray(studentCode, qCode);

        if (words == null || words.isEmpty()) {
            return;
        } 
        Map<Integer, List<String>> map = new TreeMap<>(); 
        for (String word : words) {
            int vowel = countVowels(word);
            map.computeIfAbsent(vowel, k -> new ArrayList<>()).add(word);
        }
        List<String> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            Collections.sort(list);
            result.addAll(list); 
        }
        port.submitCharacterStringArray(studentCode, qCode, result);
    }
}

// 5 OS KHACH HÀNG TIỀM NĂNG ( > 500 , >=3)
package WSSources;

import WSObject.Customer;
import WSObject.ObjectService;
import WSObject.SoapObjectService;
import java.util.*;

public class OS_KhachHangTiemNang {
    public static void main(String args[]) throws Exception{
        String msv = "B23DCCN698", qCode = "bpTCoL6d"; 
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Customer> a = port.requestListCustomer(msv, qCode);
        if (a == null || a.isEmpty()) {
            System.out.println("No customer data received from server.");
            return;
        }
        for (Customer x : a) {
            System.out.println(x);
        }
        List<Customer> ans = new ArrayList<>();
        for (Customer x : a) {
            if (x.getTotalSpent() > 500 && x.getPurchaseCount() >= 3) {
                ans.add(x);
            }
        }
        System.out.println();
        for (Customer x : ans) {
            System.out.println(x);
        }
        port.submitListCustomer(msv, qCode, ans);
    }
}

// 6 THÂM NIÊN EMPLOYEE
package WSSources;

import WSObject.ObjectService;
import WSObject.SoapObjectService;
import java.util.*;
import WSObject.EmployeeY;

public class OS_ThamNien {
 public static void main(String[] args) throws Exception {
        String msv = "B23DCCN698";
        String qCode = "Az1aKcjP";

        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();

        List<EmployeeY> a = port.requestListEmployeeY(msv, qCode);
        if (a == null || a.isEmpty()) {
            System.out.println("No employee data received from server.");
            return;
        }
        for (EmployeeY x : a) {
            System.out.println(x);
        }
        Collections.sort(a, new Comparator<EmployeeY>(){
            @Override
            public int compare(EmployeeY o1, EmployeeY o2) {
                return o1.getStartDate().compare(o2.getStartDate());
            }
        });
        System.out.println();
        for(EmployeeY x: a) System.out.println(x);
        port.submitListEmployeeY(msv, qCode, a);
    }
}

// BÀI CHƯA CÓ DB

// DATA SERVICE
// DECTOBIN (HỆ CƠ SỐ 10 SANG 8 THÌ PHẢI)
package WS;
import java.util.*;
public class DecToBin {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN021", qCode = "2jS1DTpU"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a) ans.add(Integer.toBinaryString(x));
        System.out.println(ans);
        port.submitDataStringArray(msv, qCode, ans);
    }
}
// DS ĐẾM CHẴN LẺ
package WS;
import java.util.*;
public class DemChanVaLe {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902";
        String qCode = "BAfzuAJi";
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer> a = port.getData(msv, qCode);
        int even = 0;
        int odd = 0;
        // Đếm số chẵn và số lẻ
        for (int x : a) {
            if (x % 2 == 0)
                even++;
            else
                odd++;
        }
        List<String> ans = new ArrayList<>();
        ans.add("EVEN=" + even);
        ans.add("ODD=" + odd);
        System.out.println(ans);
        port.submitDataStringArray(msv, qCode, ans);
    }
}
// ĐẾM SỐ LẦN XUẤT HIỆN
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class DemSoLanXuatHien {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN002", qCode = "TU4ULIgh"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a); Collections.sort(a);
        System.out.println(a);
        int []cnt = new int[10005];
        for(int x: a) cnt[x]++;
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a){
            if(cnt[x]>0){
                String tmp = String.format("%d, %d", x, cnt[x]);
                ans.add(tmp);
                cnt[x] = 0;
            }
        }
        for(String x: ans){
            System.out.println(x);
        }
        port.submitDataStringArray(msv, qCode, ans);
    }
}
// FRAC TO DEC CÁI GÌ SANG 8 KO BIẾT
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class FractoDec {
    public static int gcd(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN011", qCode = "ny8Fb8BU"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        double a = port.getDataDouble(msv, qCode);
        a = Math.round(a * 100) / 100.0;
        System.out.println(a);
        int tu, mau = 100;
        tu = (int) (a * 100.0);
        int tmp = gcd(tu, mau); tu/=tmp; mau/=tmp;
        List<Integer> ans = new java.util.ArrayList<>();
        ans.add(tu); ans.add(mau);
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
// HỆ CƠ SỐ 8
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class HeCoSo8 {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN025", qCode = "QBDJ1GGL"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>res = new java.util.ArrayList<>();
        for(int n: a){
            String oct = Integer.toOctalString(n), hex = Integer.toHexString(n).toUpperCase();
            String tmp = oct + "|" + hex;
            res.add(tmp);
        }
        System.out.println(res);
        port.submitDataStringArray(msv, qCode, res);
    }
}
// LIỆT KÊ ƯỚC
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class LietKeUoc {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN004", qCode = "nhFjYg0F"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        int n = (int)port.getDataDouble(msv, qCode);
        System.out.println(n);
        List<Integer>ans = new java.util.ArrayList<>();
        for(int i = 1;i<=n;i++){
            if(n%i==0) ans.add(i);
        }
        ans.add(0, ans.size());
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
// LOẠI BỎ TRÙNG NHAU
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class LoaiBoTrungNhau {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN033", qCode = "E2Axwwf3"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        int []cnt = new int[10005];
        for(int x: a) cnt[x]++;
        List<Integer>ans = new java.util.ArrayList<>();
        for(int x: a){
            if(cnt[x]>0){
                ans.add(x);
                cnt[x] = 0;
            }
        }
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
//lỚN NHỎ THỨ K
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class LonNhoThuK {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN003", qCode = "WpaUTFMu"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        int k = a.get(0); a.remove(0);
        System.out.println(a + " " + k);
        Collections.sort(a);
        int nhoThuK = a.get(k - 1), lonThuK = a.get(a.size() - k);
        List<Integer>ans = new java.util.ArrayList<>(Arrays.asList(lonThuK, nhoThuK));
        port.submitDataIntArray(msv, qCode, ans);
    }
}
// NGUYÊN TỐ VÀ CHECKSUM
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class NguyenToVaChecksum {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902";
        String qCode = "y3Kr7vEG";
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();

        List<Integer> a = port.getData(msv, qCode);
        int primeCount = 0;
        long checksum = 0;
        for (int i = 0; i < a.size(); i++) {
            int x = a.get(i);
            if (isPrime(x)) primeCount++;
            checksum = (checksum + (long)(i + 1) * x) % 100000;
        }
        String ans = "primeCount=" + primeCount + ";checksum=" + checksum;
        System.out.println(ans);
        port.submitDataString(msv, qCode, ans);
    }
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
//pHÂN TÍCH THỪA SỐ NGUYÊN TỐ
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class PhanTichThuaSoNguyenTo {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN001", qCode = "TwZpbqUg"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer> a = port.getData(msv, qCode);
        System.out.println(a);
        List<String>ans = new java.util.ArrayList<>();
        for(int x: a){
            String tmp = "";
            for(int i = 2;i*i<=x;i++){
                while(x%i==0){
                    x/=i;
                    tmp+=String.format("%d, ", i);
                }
            }
            if(x > 1) tmp+=String.format("%d, ", x);
            tmp = tmp.trim();
            tmp = tmp.substring(0, tmp.length() - 1);
            ans.add(tmp);
            System.out.println(tmp);
        }
        port.submitDataStringArray(msv, qCode, ans);
    }
}
//sẮP XẾP CHẴN LẺ
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class SapXepChanLe {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN088", qCode = "0BEtJAT9"; 
        DataService service = new DataService();
        SoapDataService port = service.getSoapDataServicePort();
        List<Integer>a = port.getData(msv, qCode);
        System.out.println(a);
        List<Integer>chan = new java.util.ArrayList<>(), le = new java.util.ArrayList<>(), ans = new java.util.ArrayList<>();
        for(int x: a){
            if(x%2==0) chan.add(x);
            else le.add(x);
        }
        int k = Integer.min(chan.size(), le.size());
        for(int i = 0;i < k;i++){
            ans.add(chan.get(i)); ans.add(le.get(i));
        }
        int k1 = chan.size() - k, k2 = le.size() - k;
        if(k1 > 0){
            for(int i = k;i<chan.size();i++) ans.add(chan.get(i));
        }
        if(k2 > 0){
            for(int i = k;i<le.size();i++) ans.add(le.get(i));
        }
        System.out.println(ans);
        port.submitDataIntArray(msv, qCode, ans);
    }
}
//sẮP XẾP CHUỖI
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class SapXepChuoi {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B21DCCN002", qCode = "x5TIDg1S"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        // a. Triệu gọi phương thức requestStringArray từ server 
        List<String> a = port.requestStringArray(msv, qCode);
        // b. Sắp xếp các chuỗi theo số lượng nguyên âm
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return dem(s1) - dem(s2);
            }
        });
        // c. Triệu gọi phương thức submitCharacterStringArray để gửi mảng đã sắp xếp
        port.submitCharacterStringArray(msv, qCode, a);
        System.out.println(a);
    }
    // Hàm đếm số nguyên âm trong chuỗi
    private static int dem(String str) {
        int cnt = 0;
        // Các ký tự nguyên âm
        String ngAm = "aeiouAEIOU";
        for (int i = 0; i < str.length(); i++) {
            if (ngAm.indexOf(str.charAt(i)) != -1) cnt++;
        }
        return cnt;
    }
}
//sẮP XẾP MẢNH LOG
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;

public class SapXepMangLog {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902";
        String qCode = "dp1n9dyx";
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        List<String> list = port.requestStringArray(msv, qCode);
        List<String> result = new ArrayList<>();
        for (String s : list) {
            s = s.replaceAll("email=\\S+", "email=[EMAIL]");
            s = s.replaceAll("phone=\\S+", "phone=[PHONE]");
            s = s.replaceAll("token=\\S+", "token=[TOKEN]");
            result.add(s);
        }
 // Sắp xếp ERROR -> WARN -> INFO
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return priority(s1) - priority(s2);
            }
        });
        port.submitStringArray(msv, qCode, result);
        System.out.println(result);
    }
    static int priority(String s) {
        if (s.startsWith("ERROR")) return 0;
        if (s.startsWith("WARN")) return 1;
        return 2; // INFO
    }
}
// SỐ DÀI NHẤT
package WS;

import ptit.dblab.judge.network.server.soap.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SoDaiNhat {
    public static void main(String[] args) {
        System.out.println("Hello");
        String studentCode="B22DCCN902";
        String qCode="PKSZIrFl";
        DataService service = new DataService();
        List<Integer> list_numbers= service.getSoapDataServicePort().getData(studentCode,qCode);
        for (Integer number:list_numbers){
            System.out.println(number);
        }   
        List<String> strList = new ArrayList<>();
            for (Integer num : list_numbers) {
                strList.add(String.valueOf(num));
            }      
        Collections.sort(strList, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String s : strList) {
            sb.append(s);
        }
        String result = sb.toString();
        System.out.println(result);    
        service.getSoapDataServicePort().submitDataString(studentCode, qCode, result);     
    }  
}
// CHARACTER SERVICE
// CHUẨN HÓA CHUỖI
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class ChuanHoaChuoi {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B21DCCN007", qCode = "sBQjqANT"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService service = new CharacterService();
		SoapCharacterService port = service.getSoapCharacterServicePort();
        // a. Triệu gọi phương thức 
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        //b.
        s = s.replace('_', ' ');
        String []tmp = s.trim().split("\\s+");
        String p = "", c = tmp[0].toLowerCase(), sn = "";
        for(String x: tmp) p+=chuanhoa(x); 
        for(int i = 1;i<tmp.length;i++) c+=chuanhoa(tmp[i]);
        for(String x: tmp) sn+=x.toLowerCase()+"_"; sn = sn.substring(0, sn.length() - 1);
        List<String>ans = new java.util.ArrayList<>();
        ans.add(p); ans.add(c); ans.add(sn);
        System.out.println(ans);
        //c
        port.submitCharacterStringArray(msv, qCode, ans);
    }
    public static String chuanhoa(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }
}
//ĐẢO NGƯỢC
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class DaoNguoc {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B22DCCN902", qCode = "bTDaZ8iI"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService service = new CharacterService();
		SoapCharacterService port = service.getSoapCharacterServicePort();
        // a. Triệu gọi phương thức 
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        //b.
        String ans = "";
        for(char x: s.toCharArray()){
            if(Character.isAlphabetic(x)) ans = x + ans;
        }
        System.out.println(ans);
        //c
        port.submitCharacterString(msv, qCode, ans);
    }
}
// LÊN MAX MIN
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class LenMaxMin {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN005", qCode = "9n2rfqST"; 
        CharacterService service = new CharacterService();
	    SoapCharacterService port = service.getSoapCharacterServicePort();
        String s = port.requestString(msv, qCode);
        System.out.println(s);
        String []tmp = s.trim().split("\\s+");
        int lenMax = tmp[0].length(), lenMin  = tmp[0].length();
        String strMax = tmp[0], strMin = tmp[0];
        for(String x: tmp){
            if(x.length() < lenMin){
                strMin = x;
                lenMin = x.length();
            }
            if(x.length() > lenMax){
                strMax = x;
                lenMax = x.length();
            }
        }
        String ans = strMax + ";" + strMin;
        System.out.println(ans);
        port.submitCharacterString(msv, qCode, ans);
    }
}
// LÊN MAX MIN 2
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class LenMaxMin2 {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN010", qCode = "sGJc3iD5"; 
        CharacterService service = new CharacterService();
		SoapCharacterService port = service.getSoapCharacterServicePort();
        List<String> a = port.requestStringArray(msv, qCode);
        String Max = a.get(0), Min = a.get(0);
        for(String x: a){
            if(x.length() > Max.length()) Max = x;
            if(x.length() < Min.length()) Min = x;
        }
        String ans = Max + ";" + Min;
        port.submitCharacterString(msv, qCode, ans);
    }
}
//SẮP XẾP THEO LÊN
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class SapXepTheoLen {
    public static void main(String[] args) throws Exception {
        // Cấu hình thông tin
        String msv = "B21DCCN003", qCode = "mpu9xCxR"; 
        // Tạo đối tượng dịch vụ từ web service
        CharacterService service = new CharacterService();
        SoapCharacterService port = service.getSoapCharacterServicePort();
        // a. Triệu gọi phương thức requestStringArray từ server 
        List<String> a = port.requestStringArray(msv, qCode);
        // b. Sắp xếp các chuỗi theo số lượng nguyên âm
        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        // c. Triệu gọi phương thức submitCharacterStringArray để gửi mảng đã sắp xếp
        port.submitCharacterStringArray(msv, qCode, a);
        System.out.println(a);
    }
}

// OBJECT
//ĐƠN HÀNG 2
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class DonHang2 {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN021", qCode = "CuW1L0ev"; 
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Order>a = (List<Order>)port.requestListOrder(msv, qCode);
        for(Order x: a) System.out.println(x);
        HashMap<String, Float>mp = new HashMap<>();
        for(Order x: a){
            String maKH = x.getCustomerId(); float giaTri = x.getAmount();
            if(!mp.containsKey(maKH)) mp.put(maKH, giaTri);
            else mp.put(maKH, mp.get(maKH) + giaTri);
        }
        float maxGiaTri = 0; String maKHmax = "";
        for (String maKH: mp.keySet()) {
            float giaTri = mp.get(maKH);
            if (giaTri > maxGiaTri) {
                maxGiaTri = giaTri;
                maKHmax = maKH;
            }
        }
        List<Order>ans = new java.util.ArrayList<>();
        for(Order x: a){
            if(x.getCustomerId().equals(maKHmax)) ans.add(x);
        }
        port.submitListOrder(msv, qCode, ans);
    }
}
// PHÂN LOẠI HỌC LỰC
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class PhanLoaiHocLuc {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN003", qCode = "qNfIMvid"; // Mã câu hỏi
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Student> a = (List<Student>) port.requestListStudent(msv, qCode);
        for(Student x: a) System.out.println(x);
        System.out.println();
        List<Student>ans = new java.util.ArrayList<>();
        for(Student x: a){
            if(x.getScore() >=8.0) ans.add(x);
            else if (x.getScore() < 5) ans.add(x);
        }
        for(Student x: ans) System.out.println(x);
        port.submitListStudent(msv, qCode, ans);
    }
}
//qUẢN LÍ DỰ ÁN
package WS;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class QuanLyDuAn {
    public static void main(String[] args) throws Exception {
        String msv = "B21DCCN010", qCode = "R6UOuyyE";
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Project>a = (List<Project>)port.requestListProject(msv, qCode);
        for(Project p: a) System.out.println(p);
        System.out.println();
        List<Project> send = new java.util.ArrayList<>();
        LocalDate today = LocalDate.now();
        for(Project x : a){
            String t = x.getDueDate().toString();
            t = t.substring(0, 10);
            LocalDate future = LocalDate.parse(t);
            long ngay = ChronoUnit.DAYS.between(today, future);
            if(ngay <=15 && ngay >=0 && x.getCompletionPercentage() >=80.00) send.add(x);
        }
        port.submitListProject(msv, qCode, send);
        for(Project p: send) System.out.println(p);
    }
}
//QUẢN LÍ KHÁCH HÀNG 2
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class QuanLyKhachHang2 {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902";
        String qCode = "yfyrOJUP";
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<Customer> list = port.requestListCustomer(msv, qCode);
        if (list == null || list.isEmpty()) {
            System.out.println("No customer data received.");
            return;
        }
        List<Customer> ans = new ArrayList<>();
        for (Customer c : list) {
            if (c.getPurchaseCount() >= 6 && c.getTotalSpent() >= 4000) {
                ans.add(c);
            }
        }
        Collections.sort(ans, new Comparator<Customer>() {
            @Override
            public int compare(Customer a, Customer b) {
                if (a.getTotalSpent() != b.getTotalSpent()) {
                    return Float.compare(b.getTotalSpent(), a.getTotalSpent()); // giảm dần
                }
                return a.getCustomerId().compareTo(b.getCustomerId()); // tăng dần
            }
        });
        port.submitListCustomer(msv, qCode, ans);
    }
}
//QUẢN LÍ NHÂN VIÊN 2
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class QuanLyNhanVien2 {
    public static void main(String[] args) throws Exception{
        String msv = "B21DCCN084", qCode = "L8CainEX"; 
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        Employee e = port.requestEmployee(msv, qCode);
        System.out.println(e);
        String bd = e.getStartDate().toString(), kt = e.getEndDate().toString();
        bd = bd.substring(0, 10); kt = kt.substring(0, 10);
        LocalDate stDay = LocalDate.parse(bd), enDay = LocalDate.parse(kt);
        long tmp = ChronoUnit.DAYS.between(stDay, enDay);
        int cnt = 0;
        for(int i = 0;i<=tmp;i++){
            LocalDate curDay = stDay.plusDays(i);
            if(curDay.getDayOfWeek().getValue()<=5) cnt++;
        }
        e.setWorkingDays(cnt);
        System.out.println(e);
        port.submitEmployee(msv, qCode, e);
    }
}
//QUẢN LÍ SẢN PHẨM
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class QuanLySanPham {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902", qCode = "SwiEmI9T"; // Mã câu hỏi
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        ProductY product = (ProductY) port.requestProductY(msv, qCode);
        System.out.println(product);
        float price = product.getPrice(), taxRate = product.getTaxRate(),discount = product.getDiscount();
        float finalPrice = price * (1 + taxRate / 100) * (1 - discount / 100);
        product.setFinalPrice(finalPrice);
        System.out.println(product);
        port.submitProductY(msv, qCode, product);
    }
}
// QUẢN LÍ SV
package WS;
import ptit.dblab.judge.network.server.soap.*;
import java.util.*;
public class QuanLySinhVien {
    public static void main(String[] args) throws Exception {
        String msv = "B22DCCN902", qCode = "2RjVBzv0"; // Mã câu hỏi
        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        List<StudentY> a = (List<StudentY>) port.requestListStudentY(msv, qCode);
        for (StudentY x : a) System.out.println(x);
        System.out.println();
        Map<String, Float> hsg = new HashMap<>(); //key: tên môn, value: điểm thi cao nhất môn đó
        for(StudentY x: a){
            String mon = x.getSubject();
            Float diemMon = x.getScore();
            if ((!hsg.containsKey(mon)) || (diemMon > hsg.get(mon))) hsg.put(mon, diemMon);
        }
        List<Float>diemMax = new java.util.ArrayList<>(hsg.values());
        List<StudentY>ans = new java.util.ArrayList<>();
        for(StudentY x: a){
            if(diemMax.contains(x.getScore())) ans.add(x);
        }
        port.submitListStudentY(msv, qCode, ans);
    }
} 

// HẾT
