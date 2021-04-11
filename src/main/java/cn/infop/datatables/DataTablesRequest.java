package cn.infop.datatables;
/**
 * @author wsh
 */

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTablesRequest {

    private Integer draw;
    private Integer start;
    private Integer length;
    private Search search;
    private List<Order> orders;
    private List<Column> columns;

    public DataTablesRequest() {
    }

    public DataTablesRequest(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        Map<String, String> columnsMap = new HashMap<>();
        Map<String, String> ordersMap = new HashMap<>();
        Search s = new Search();

        for (String key : parameters.keySet()) {
            if (key.equals("draw"))
                this.setDraw(Integer.parseInt(parameters.get(key)[0]));

            if (key.startsWith("columns"))
                columnsMap.put(key, parameters.get(key)[0]);

            if (key.startsWith("order"))
                ordersMap.put(key, parameters.get(key)[0]);

            if (key.equals("start"))
                this.setStart(Integer.parseInt(parameters.get(key)[0]));

            if (key.equals("length"))
                this.setLength(Integer.parseInt(parameters.get(key)[0]));

            if (key.equals("search[value]"))
                s.setValue(parameters.get(key)[0]);
            if (key.equals("search[regex]"))
                s.setRegex(Boolean.parseBoolean(parameters.get(key)[0]));
        }

        setSearch(s);

        List<Column> columns = new ArrayList<>();

        for (int i = 0; i < columnsMap.size() / 6; i++) {
            Column column = new Column();
            column.setData(columnsMap.get("columns[" + i + "][data]"));
            column.setName(columnsMap.get("columns[" + i + "][name]"));
            column.setSearchable(Boolean.parseBoolean(columnsMap.get("columns[" + i + "][searchable]")));
            column.setOrderable(Boolean.parseBoolean(columnsMap.get("columns[" + i + "][orderable]")));
            Search srch = new Search();
            srch.setValue(columnsMap.get("columns[" + i + "][search][value]"));
            srch.setRegex(Boolean.parseBoolean(columnsMap.get("columns[" + i + "][regex]")));
            column.setSearch(srch);
            columns.add(column);
        }

        this.setColumns(columns);

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < ordersMap.size() / 2; i++) {
            Order order = new Order();
            order.setColumn(ordersMap.get("order[" + i + "][column]"));
            order.setDir(ordersMap.get("order[" + i + "][dir]"));
            orders.add(order);
        }

        this.setOrders(orders);

    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * 获取全局排序条件
     *
     * @return
     */

    public String getSingleOrder() {
        // 将列编号转换字段
        int index = Integer.parseInt(getOrders().get(0).getColumn());
        String field = getColumns().get(index).getData();
        String dir = getOrders().get(0).getDir();
        String result = "";
        if (dir.toUpperCase().equals("ASC"))
            result = " order by " + field + " asc ";
        else
            result = " order by " + field + " desc ";
        return result;
    }

    /**
     * 获取全局搜索字段
     *
     * @return
     */
    public String getGlobalSearchField() {
        return this.getSearch().getValue();
    }

    /**
     * 获取列搜索字
     *
     * @return
     */
    public String getColumnsSearchKey() {
        String condition = "";
        for (Column column : columns) {
            String s = column.getSearch().getValue();
            if (s != "") {
                if (column.getSearch().isBlurry()) {
                    condition += column.getData() + " like '%" + s + "%' or ";
                } else {
                    condition += column.getData() + " = '" + s + "' and ";
                }
            }
        }

        int length = condition.length();
        int and_position = condition.lastIndexOf("and");
        int or_position = condition.lastIndexOf("or");

        int and_length = "and ".length();
        int or_length = "or ".length();

        if (and_position >= (length - and_length))
            condition = condition == "" ? "" : condition.substring(0, length - and_length);
        if (or_position >= (length - or_length))
            condition = condition == "" ? "" : condition.substring(0, length - or_length);

        return condition == "" ? null : condition;
    }

}
