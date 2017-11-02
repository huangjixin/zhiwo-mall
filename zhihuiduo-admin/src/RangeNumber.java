import java.util.*;

public class RangeNumber {
	public static void main(String[] args) {
		/*System.out.println("请输入一个字符串：");

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(permutation(str).length);
		System.out.println(Arrays.toString(permutation(str)));*/
		
		 String[] s = new String[]{"a","b","c"};
	        permutation(s, 0, 2);
	}

	private static String[] permutation(String orginal) {
		ArrayList<String> list = new ArrayList<String>();
		if (orginal.length() == 1) {
			return new String[] { orginal };
		} else {
			for (int i = 0; i < orginal.length(); i++) {
				String s = orginal.charAt(i) + "";
				String result = "";
				String resultA = result + s;
				String leftS = orginal.substring(0, i)
						+ orginal.substring(i + 1, orginal.length());
				for (String element : permutation(leftS)) {
					result = resultA + element;
					list.add(result);
				}
			}
			return (String[]) list.toArray(new String[list.size()]);
		}
	}
	
	public static void permutation(String[] s,int from,int to) {
        if(to <= 1)
            return;
        if(from == to) {
        	StringBuffer sb = new StringBuffer();
        	int length = s.length;
        	for (int i = 0; i < length; i++) {
        		sb.append(s[i]);
			}
            System.out.println(sb.toString());
        } else {
            for(int i=from; i<=to; i++) {
                swap(s,i,from); //交换前缀，使其产生下一个前缀
                permutation(s, from+1, to);
                swap(s,from,i); //将前缀换回，继续做上一个前缀的排列
            }
        }
    }
	
	public static void swap(String[] s,int i,int j) {
		String tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
	
}