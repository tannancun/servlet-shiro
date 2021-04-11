package cn.infop.dao;

import java.util.List;

import cn.infop.mode.File;
import cn.infop.tools.JdbcUtils;

public class FileDao extends JdbcUtils<File> {

	private final String table = "(select * from files) t";

	public FileDao() {
		super(File.class);
		super.multiTable = table;
		super.singleTable = "files";
	}
	
	public File findByMd5(String md5) {
		String sql = "select * from files where md5 = ?";
		return getBean(sql, new Object[] {md5});
	}

	public List<File> findImages() {
		String sql = "select * from files where filetype like ?";
		return getBeans(sql, new Object[] { "image/%" });
	}

	public void save(File file) {
		String sql = "insert into files (md5,filename,filetype) values (?,?,?)";
		insertWithoutReturnId(sql, new Object[] { file.getMd5(), file.getFilename(), file.getFiletype() });
	}
	
	public List<File> getFileTypeQty(){
		String sql ="select sum(qty) as qty,filetype from (\r\n" + 
				"select  qty ,\r\n" + 
				"CASE \r\n" + 
				"WHEN filetype like 'application%'  THEN '文档'\r\n" + 
				"WHEN filetype like 'text%'   THEN '文本'\r\n" + 
				"WHEN filetype like 'image%'  THEN '图片'\r\n" + 
				"WHEN filetype like 'video%'  THEN '视频'\r\n" + 
				"WHEN filetype like 'audio%'   THEN '音频'\r\n" + 
				"WHEN filetype like 'message%'  THEN '邮件'\r\n" + 
				"WHEN filetype like 'x-world%'  THEN '设计'\r\n" + 
				"ELSE '其他' END as filetype\r\n" + 
				"from (select filetype,count(*) as qty from files group by filetype) t group by filetype,qty) a group by filetype";
		return getBeans(sql);
	}
}
