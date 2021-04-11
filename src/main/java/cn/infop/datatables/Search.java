package cn.infop.datatables;

/**
 * @author wsh
 */
public class Search {

    private String value;

    private boolean regex;
    /**
     * 是否模糊查询，true是模糊查询，false是精确查询
     */
    private boolean blurry;

    public Search() {
    }

    public Search(String value, boolean regex, boolean blurry) {
        this.value = value;
        this.regex = regex;
        this.blurry = blurry;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    public boolean isBlurry() {
        return blurry;
    }

    public void setBlurry(boolean blurry) {
        this.blurry = blurry;
    }
}
