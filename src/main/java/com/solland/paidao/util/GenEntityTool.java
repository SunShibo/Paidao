package com.solland.paidao.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class GenEntityTool {
	//数据库
	private String db = "bskdoc";
	//数据库表名
	private String tablename = "doc_token_info";
	//生成model类别名
	private String tmpname = "TokenInfo";
	
	private String actionPath = "src/com/wyvs/pms/action/" ;
	private String packageActionPath = "package com.wyvs.pms.action.;" ;
	private String inportActionPath = "import com.wyvs.pms.action." ;
	
	private String servicePath = "src/com/wyvs/pms/service/" ;
	private String importServicePath = "import com.wyvs.pms.service." ;
	
	
	private String mapperPath = "src/com/wyvs/pms/persistence/" ;
	private String impartMapperPath = "import com.wyvs/pms.persistence.";
	
	
	private String modelPath = "src/com/wyvs/pms/model/" ;
	private String importModelPath = "import com.wyvs.pms.model.";
	
	private String packageModelPath = "package com.wyvs.pms.model;" ;
	
	
    private String[] colnames; // 列名数组

    private String[] colTypes; // 列名类型数组
    
    ArrayList<TableAnnotation>  tableAnnotationList = getTableAnnotation() ;    
    
    private int[] colSizes; // 列名大小数组

    private boolean f_util = false; // 是否需要导入包java.util.*

    private boolean f_sql = false; // 是否需要导入包java.sql.*
    
    public GenEntityTool() throws ClassNotFoundException, SQLException, IOException {
        Connection conn = this.getConnection(db); // 得到数据库连接
        
        String strsql = "select * from " + tablename;
        try {
            PreparedStatement pstmt = conn.prepareStatement(strsql);
            ResultSetMetaData rsmd = pstmt.getMetaData();
            int size = rsmd.getColumnCount(); // 共有多少列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                
                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("date")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image")
                        || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            
            String content = parse(colnames, colTypes, colSizes);
            String mapper  = mapper(tmpname,colnames,colTypes) ;
            String mapperXML  = mapperXML(tmpname,colnames,colTypes) ;
            String createService  = createServer(tmpname,colnames,colTypes) ;
            
            //首字母大写
            String bName = initcap(tmpname) ;
            
            /***********/
            String modelName = modelPath + bName + ".java" ;
            createFile(modelName,content,"实体类") ;
            String fileMapper = mapperPath + initcap(tmpname)+"Mapper.java" ;
            createFile(fileMapper,mapper,"接口类") ;
            String xmlName = mapperPath + initcap(tmpname)+"Mapper.xml" ;
            createFile(xmlName,mapperXML,"xml类") ;
            String service = servicePath +initcap(tmpname)+"Service.java" ;
            createFile(service,createService,"Service类") ;
            System.out.println(mapperXML);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //DBSession.closeConnection(conn);
        	conn.close() ;
        }
    }

	
	private ArrayList<TableAnnotation> getTableAnnotation() throws ClassNotFoundException, SQLException {
        Connection systemConn = this.getConnection("information_schema"); // 得到数据库连接
        String strsql = "select ORDINAL_POSITION,COLUMN_NAME,COLUMN_COMMENT from COLUMNS where TABLE_SCHEMA='"+db+"' and TABLE_NAME='"+tablename+"' ";
        Statement stmt = (Statement) systemConn.createStatement();
        ResultSet rs = stmt.executeQuery(strsql);
        ArrayList<TableAnnotation> list = new ArrayList<TableAnnotation>() ;
    	while (rs.next()) {
        	TableAnnotation a = new TableAnnotation();
        	a.setCOLUMN_COMMENT(rs.getString("COLUMN_COMMENT")) ;
        	a.setCOLUMN_NAME(rs.getString("COLUMN_NAME")) ;
        	a.setORDINAL_POSITION(rs.getString("ORDINAL_POSITION")) ;
        	list.add(a) ;
		}        
        rs.close() ;
        stmt.close() ;
        systemConn.close() ;
        
        return list ;
	}

	private void createFile(String modelName, String content,String typeName) throws IOException {
        File file = new File(modelName) ;
        if(!file.exists()){
            FileWriter fw1 = new FileWriter(modelName);
            PrintWriter pw1 = new PrintWriter(fw1);
            pw1.println(content.toString());
            pw1.flush();
            pw1.close();
            System.out.println(typeName+"已经生成");
        }else{
        	System.out.println(typeName+"已经存在");
        }
		
	}

	private String createServer(String tmpname2, String[] colnames2,String[] colTypes2) {
		String bName = initcap(tmpname2) ;
		String bMapperName= bName+"Mapper" ;
		String mapperName= bName+"Mapper" ;
		
		
        StringBuffer sb = new StringBuffer(); 
		sb.append(packageModelPath + "\r\n") ;
		sb.append("import java.util.ArrayList;\r\n") ;
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n") ;
		sb.append("import org.springframework.stereotype.Service;\r\n") ;
		sb.append("import org.springframework.transaction.annotation.Transactional;\r\n") ;
		sb.append("import java.util.List;\r\n") ;
		sb.append("import java.util.HashMap;\r\n") ;
		sb.append(importModelPath + bName+";\r\n") ;
		sb.append(impartMapperPath + bMapperName+";\r\n") ;
		
		
		
		sb.append("@Service(value=\""+tmpname+"\")\r\n\n") ;
		sb.append("@Transactional\r\n") ;
		
		sb.append("public class "+bName+"Service {\r\n\n\n") ;
		sb.append("    @Autowired private "+bMapperName+" "+mapperName+" ;\r\n\n\n") ;
		
		sb.append("/**\r *插入数据\r */") ;
		sb.append("    public int insert"+tmpname+"("+bName+" "+tmpname2+"){\r\n\n") ;
		sb.append("        return "+mapperName+".insert("+tmpname2+");\r\n\n") ;
		sb.append("    }\r\n") ;
		
		sb.append("/**\r *更新数据\r */") ;
		sb.append("    public int update"+tmpname+"("+bName+" "+tmpname2+"){\r\n\n") ;
		sb.append("        return "+mapperName+".update("+tmpname2+");\r\n\n") ;
		sb.append("    }\r\n") ;
		
		sb.append("/**\r *通过map查询数据\r */") ;
		sb.append("    public ArrayList<"+bName+"> selectByMap(HashMap<String , Object> map){\r\n\n") ;
		sb.append("	       return "+mapperName+".selectByMap(map);\r\n\n") ;
		sb.append("    }\r\n") ;
		
		sb.append("/**\r *通过对象查询数据\r */") ;
		sb.append("    public ArrayList<"+bName+"> selectBy"+bName+"("+bName+" "+tmpname2+"){\r\n\n") ;
		sb.append("	       return "+mapperName+".selectBy"+bName+"("+tmpname2+");\r\n\n") ;
		sb.append("    }\r\n") ;
		
		sb.append("}\r\n") ;
		
		return sb.toString();
	}

	private String mapperXML(String tablename, String[] colnames2,String[] colTypes2) {
		String btablename = initcap(tablename) ;
		String  insertSql = this.outInsert(tablename, colnames2) ;
        String  updateSql = this.outUpdate(tablename,colnames,colTypes) ;
        String  selectSql = this.select(tablename,colnames,colTypes) ;
        
        StringBuffer sb = new StringBuffer(); 
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \r\n") ;
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n") ;
        sb.append("<mapper namespace=\"com.bsk.persistence.doctor."+btablename+"Mapper\">\r\n") ;
        sb.append("			<insert id=\"insert"+tmpname+"\" parameterType=\"com.bsk.model.doctor."+btablename+"\" useGeneratedKeys=\"true\" keyProperty=\"id\" >\r\n") ;
        sb.append("            "+insertSql+"\r\n") ;
        sb.append("         </insert>\r\n") ;
        sb.append("         <update id=\"update"+tmpname+"\" parameterType=\"com.bsk.model.doctor."+btablename+"\">\r\n") ;
        sb.append("            "+updateSql+"\r\n") ;
        sb.append("         </update>\r\n") ;
        sb.append("         <select id=\"select"+tmpname+"ByMap\" parameterType=\"map\" resultType=\"com.bsk.model.doctor."+btablename+"\">\r\n") ;
        sb.append("            "+selectSql+"\r\n") ;
        sb.append("         </select>\r\n") ;
        sb.append("         <select id=\"select"+tmpname+"ByObj\" parameterType=\"com.bsk.model.doctor."+btablename+"\" resultType=\"com.bsk.model.doctor."+btablename+"\">\r\n") ;
        sb.append("            "+selectSql+"\r\n") ;
        sb.append("         </select>\r\n") ;
        
        
        sb.append("</mapper>") ; 
       	
		return sb.toString();
	}

	private String select(String tablename2, String[] colnames2,
			String[] colTypes2) {
        StringBuffer sb = new StringBuffer(); 
        sb.append(" select * from ") ;
        sb.append(tablename2 +" tmp ") ;
        sb.append(" where 1=1 ") ;
        for (int i = 0; i < colnames2.length; i++) {
			sb.append(" <if test=\"tmp."+colnames2[i]+" != null\"> and tmp."+colnames2[i]+"=#{tmp."+colnames2[i]+",jdbcType="+sqlType3JavaType(colTypes2[i])+"} </if> ") ;
		}
        sb.append(" <include refid=\"limit\"/> ") ;
        
		return sb.toString();
	}

	private String mapper(String tablename2, String[] colnames2, String[] colTypes2) {
		String btablename2 = initcap(tablename2) ;
        StringBuffer sb = new StringBuffer(); 
        sb.append(impartMapperPath + "\r\n") ;
        sb.append("import java.util.List;\r\n") ;
        sb.append("import java.util.Map;\r\n") ;
        sb.append("import java.util.ArrayList;\r\n") ;
        sb.append("import java.util.HashMap;\r\n") ;
        
        sb.append(importModelPath+btablename2+";\r\n") ;
        
        sb.append("public interface "+btablename2+"Mapper{\r\n\n") ;
        sb.append("/**\r *插入数据\r */") ;
        sb.append("    public int insert"+tmpname+"("+btablename2+" "+tablename2+");\r\n\n") ;
        sb.append("/**\r *修改数据\r */") ;
        sb.append("    public int update"+tmpname+"("+btablename2+" "+tablename2+");\r\n\n") ;
        sb.append("/**\r *通过map查找数据\r */") ;
        sb.append("    public ArrayList<"+btablename2+"> select"+tmpname+"ByMap(HashMap<String,Object> map);\r\n\n") ;
        sb.append("/**\r *通过对象查找数据\r */") ;
        sb.append("    public ArrayList<"+btablename2+"> select"+tmpname+"ByObj("+btablename2+" obj);\r\n\n") ;
        
        sb.append("}") ;
        
		return sb.toString();
	}

	private String outUpdate(String tablename, String[] colnames,
			String[] colTypes) {
        //生成update 语句
        StringBuffer sb = new StringBuffer(); 
        
        sb.append(" update  "+tablename+" set ") ;
        for (int i = 0; i < colnames.length; i++) {
        	if(!colnames[i].equals("id")){
	            sb.append("              <if test=\""+colnames[i]+" != null\"> "+colnames[i]+"=#{"+colnames[i]+",jdbcType="+sqlType3JavaType(colTypes[i])+"} , </if>\r\n") ;
	            
        	}
		}
        sb.append("              id = id  where id = #{id,jdbcType=INTEGER}") ; 
		return sb.toString();
	}

	private String outInsert(String tablename2, String[] colnames2) {
        //生成insert 语句
        StringBuffer sb = new StringBuffer(); 
        sb.append(" insert into "+tablename+" (") ;
        for (int i = 0; i < colnames.length; i++) {
        	if(!colnames[i].equals("id")){
        		sb.append(colnames[i]) ;
                if(i<colnames.length-1){
                    sb.append(",") ;            	
                }        		
        	}
		}
        sb.append(") values (") ;
        for (int i = 0; i < colnames.length; i++) {
        	if(!colnames[i].equals("id")){
	            sb.append("#{"+colnames[i]+",jdbcType="+sqlType3JavaType(colTypes[i])+"}") ;
	            if(i<colnames.length-1){
	                sb.append(",") ;            	
	            }
        	}
		}
        sb.append(") ") ;
        //生成insert 结束
        return sb.toString() ;
	}

	public Connection getConnection(String db) throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.1.114/"+db;
		String password = "root";
		String user = "admin" ;
		Class.forName(driver);
		Connection conn = (Connection) DriverManager.getConnection(url, user, password);
		return conn;
	}
    /**
    * 解析处理(生成实体类主体代码)
    */
    private String parse(String[] colNames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        sb.append(packageModelPath + "\r\n") ;
        
        sb.append("import java.io.Serializable;\r\n") ;
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n\r\n\r\n");
        }
        sb.append("public class " + initcap(tmpname) + "  implements Serializable {\r\n");
        processAllAttrs(sb);
        processAllMethod(sb);
        sb.append("}\r\n");
        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
    * 生成所有的方法
    * 
    * @param sb
    */
    private void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "("
                    + sqlType2JavaType(colTypes[i]) + " " + colnames[i]
                    + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");

            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }
    }

    /**
    * 解析输出属性
    * 
    * @return
    */
    private void processAllAttrs(StringBuffer sb) {
    	sb.append("\tprivate static final long serialVersionUID = 1L;\r\n") ;
    	
        for (int i = 0; i < colnames.length; i++) {
        	sb.append("\t//"+tableAnnotationList.get(i).getCOLUMN_COMMENT()+"\r\n") ;        	
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + ";\r\n");

        }
    }
    

	/**
    * 把输入字符串的首字母改成大写
    * 
    * @param str
    * @return
    */
    private String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    private String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "bool";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            //return "int";
        	return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal")
                || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        }else if (sqlType.equalsIgnoreCase("DATE")) {
            return "Date";
        }
        else if (sqlType.equalsIgnoreCase("image")) {
            return "Blob";
        } else if (sqlType.equalsIgnoreCase("text")) {
            return "Clob";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "double";
        }else if (sqlType.equalsIgnoreCase("double")) {
            return "double";
        }
        
        
        return null;
    }
    
    private String sqlType3JavaType(String sqlType) {
    	if (sqlType.equalsIgnoreCase("int")) {
            return "INTEGER";
        } else if (sqlType.equalsIgnoreCase("DATETIME")) {
            return "TIMESTAMP";
        } else if (sqlType.equalsIgnoreCase("VARCHAR")) {
            return "VARCHAR";
        } else if (sqlType.equalsIgnoreCase("DATE")) {
            return "DATE";
        } else if (sqlType.equalsIgnoreCase("double")) {
            return "DOUBLE";
        } else if (sqlType.equalsIgnoreCase("decimal")) {
            return "DOUBLE";
        }
    	
        return null;
    }
    
    

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        new GenEntityTool();
    }
}