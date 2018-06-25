package  com.fulan.application.filter;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

  

    private boolean enableXssProtect = true;

    private ParameterMap<String, String[]> params;

    /**
     * 跨站脚本漏洞 需要过滤的字符串
     */
    private static Map<String, String> xssMap = new HashMap<>();

    private static final String OUYEEL_URL_REG = ".*[a-zA-z]+://.*ouyeelintl.com.*";
    private static final String URL_REG = ".*[a-zA-z]+://.*";
    private static final String IGNORE_PATTERN = "table";

    static {
        initXssMap();
    }


    @SuppressWarnings("all")
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        params = (ParameterMap) request.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    /**
     * 增加参数
     *
     * @param name  属性名
     * @param value 属性值
     */
    public void addParameter(String name, Object value) {
        if (value != null) {

            String[] values = null;

            params.setLocked(false);
            if (value instanceof String[]) {
                values = (String[]) value;
            } else if (value instanceof String) {
                values = new String[]{(String) value};
            } else {
                values = new String[]{String.valueOf(value)};
            }

            params.put(name, values);
            params.setLocked(true);
        }
    }

    public void delParameter(String name) {// 去掉参数
        if (name != null && !"".equals(name)) {
            params.setLocked(false);
            params.remove(name);
            params.setLocked(true);
        }
    }

    /**
     * 初始化需要过滤的字符
     */
    private static void initXssMap() {
        // 含有脚本： script javascript
        xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
        xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
        // 含有函数： onload onmouseover eval
        xssMap.put("[o|O][n|N][l|L][o|O][a|A][d|D]", "");
        xssMap.put("[o|O][n|N][m|M][o|O][u|U][s|S][e|E][o|O][v|V][e|E][r|R]", "");
        xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
        // 含有符号
        xssMap.put("<", "&lt;");
        xssMap.put(">", "&gt;");
        xssMap.put("&", "&amp;");
        xssMap.put("\"", "&quot;");
        xssMap.put("'", "‘");
        xssMap.put("%", "％");
        xssMap.put("\\)", ")");
        xssMap.put("\\(", "(");

    }

    /**
     * 清除恶意的XSS脚本
     *
     * @param values
     * @return
     */
    private String[] cleanXSS(String... values) {

        String[] retValues = new String[values.length];
        String xssValue = "";

        for (int i = 0; i < values.length; i++) {
            retValues[i] = values[i];
        }

        Set<String> xssKeySet = xssMap.keySet();

        for (int i = 0; i < retValues.length; i++) {
            if (values[i].matches(URL_REG) && !values[i].matches(OUYEEL_URL_REG)) {     // 如果是网址类型的参数
                retValues[i] = "";
            } else {
                for (String xssKey : xssKeySet) {
                    xssValue = xssMap.get(xssKey);
                    retValues[i] = retValues[i].replaceAll(xssKey, xssValue);
                }
            }
        }

        return retValues;

    }

    /**
     * 对所有字段进行检测,清楚xss脚本
     */
    public void cleanXss() {
        if (enableXssProtect) {

            params.setLocked(false);
            Set<String> keys = params.keySet();
            for (String key : keys) {
                if (IGNORE_PATTERN.contains(key)) {
                    continue;
                }
                params.put(key, cleanXSS(params.get(key)));
            }
            params.setLocked(true);
        }
    }
}
