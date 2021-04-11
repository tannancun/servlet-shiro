package cn.infop.mode;

public class File {

	private int id;
	private String md5;
	private String filename;
	private String filetype;

	public File() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	@Override
	public String toString() {
		return "File [md5=" + md5 + ", filename=" + filename + ", filetype=" + filetype + "]";
	}

}
