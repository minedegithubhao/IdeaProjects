import java.util.*;

public class StringSortTest {

    public static void main(String[] args) {
        String str1 = "BH_20231022_000001";
        String str2 = "BH_20231021_000004";
        String str3 = "BH_20231022_000004";
        String str4 = "BH_20231021_000001";
        String str5 = "BH_20231106_000001";
        String str6 = "BH_20231107_000001";

        List<String> strings = new ArrayList<>();
        strings.add(str1);
        strings.add(str2);
        strings.add(str3);
        strings.add(str4);
        strings.add(str5);
        strings.add(str6);

//        Collections.sort(strings, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                String o1Str1 = o1.replace("BH_", "").split("_")[0];
//                String o1Str2 = o1.replace("BH_", "").split("_")[1];
//
//                String o2Str1 = o2.replace("BH_", "").split("_")[0];
//                String o2Str2 = o2.replace("BH_", "").split("_")[1];
//
//                int dateCompare = o1Str1.compareTo(o2Str1);
//                if (dateCompare == 0){
//                    return o1Str2.compareTo(o2Str2);
//                }
//                return dateCompare;
//                return o1.compareTo(o2);
//            }
//        });

        //[BH_20230907_000004, BH_20231007_000004, BH_20231106_000001, BH_20231106_000004, BH_20231107_000001, BH_20231107_000004]
        //[BH_20230907_000004, BH_20231007_000004, BH_20231106_000001, BH_20231106_000004, BH_20231107_000001, BH_20231107_000004]
        Collections.sort(strings, String::compareTo);

        System.out.println(strings);
    }
}
