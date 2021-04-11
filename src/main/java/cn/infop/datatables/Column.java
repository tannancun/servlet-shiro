package cn.infop.datatables;
/**
 * @author wsh
 */
public class Column {

	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private Search search;

	public String getData() {
		if (data == "" || data == null)
			return name;
		else
			return data;
	}

	public Column(){}

	public Column(String data, String name, boolean searchable, boolean orderable, Search search) {
		this.data = data;
		this.name = name;
		this.searchable = searchable;
		this.orderable = orderable;
		this.search = search;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

}
