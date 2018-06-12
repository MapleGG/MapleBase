package com.scd.excelData.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scd.excelData.services.ExcelService;

import net.sf.json.JSONObject;

@Controller
public class ExcelController {

	
	@Autowired
	private ExcelService excel;
	/**
	 * excel保存的路径
	 */
	@Value(value="${upload.save.rootpath}")
	String filePath;
	/** 
     * Excel 2003 
     */  
    private final static String XLS = "xls";  
    /** 
     * Excel 2007 
     */  
    private final static String XLSX = "xlsx";  
    /** 
     * 数据的分隔符 
     */  
    private final static String SEPARATOR = "|";  
      
    /** 
     * 由Excel文件的Sheet导出至List 
     *  
     * @param file 
     * @param sheetNum 
     * @return 
     */  
    public static List<String> exportListFromExcel(File file, int sheetNum)  
            throws IOException {  
        return exportListFromExcel(new FileInputStream(file),  
                FilenameUtils.getExtension(file.getName()), sheetNum);  
    }  
    /** 
     * 由Excel流的Sheet导出至List 
     *  
     * @param is 
     * @param extensionName 
     * @param sheetNum 
     * @return 
     * @throws IOException 
     */  
    public static List<String> exportListFromExcel(InputStream is,  
            String extensionName, int sheetNum) throws IOException {  
  
        Workbook workbook = null;  
  
        if (extensionName.toLowerCase().equals(XLS)) {  
            workbook = new HSSFWorkbook(is);  
        } else if (extensionName.toLowerCase().equals(XLSX)) {  
            workbook = new XSSFWorkbook(is);  
        }  
  
        return exportListFromExcel(workbook, sheetNum);  
    }  
    /** 
     * 由指定的Sheet导出至List 
     *  
     * @param workbook 
     * @param sheetNum 
     * @return 
     * @throws IOException 
     */  
    @SuppressWarnings("deprecation")//
	private static List<String> exportListFromExcel(Workbook workbook,  
            int sheetNum) {  
  
        Sheet sheet = workbook.getSheetAt(sheetNum);  
  
        // 解析公式结果  
        FormulaEvaluator evaluator = workbook.getCreationHelper()  
                .createFormulaEvaluator();  
  
        List<String> list = new ArrayList<String>();  
  
        int minRowIx = sheet.getFirstRowNum();  
        int maxRowIx = sheet.getLastRowNum();  
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {  
            Row row = sheet.getRow(rowIx);  
            StringBuilder sb = new StringBuilder();  
  
            short minColIx = row.getFirstCellNum();  
            short maxColIx = row.getLastCellNum();  
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {  
                Cell cell = row.getCell(new Integer(colIx));  
                CellValue cellValue = evaluator.evaluate(cell);  
                if (cellValue == null) {  
                    continue;  
                }  
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了  
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html  
                switch (cellValue.getCellType()) {  
                case Cell.CELL_TYPE_BOOLEAN:  
                    sb.append(SEPARATOR + cellValue.getBooleanValue());  
                    break;  
                case Cell.CELL_TYPE_NUMERIC:  
                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理  
                    if (DateUtil.isCellDateFormatted(cell)) {  
                        sb.append(SEPARATOR + cell.getDateCellValue());  
                    } else {  
                        //把手机号码转换为字符串  
                         DecimalFormat df = new DecimalFormat("#");  
                        sb.append(SEPARATOR + df.format(cellValue.getNumberValue()));  
                    }  
                    break;  
                case Cell.CELL_TYPE_STRING:  
                    sb.append(SEPARATOR + cellValue.getStringValue());  
                    break;  
                case Cell.CELL_TYPE_FORMULA:  
                    break;  
                case Cell.CELL_TYPE_BLANK:  
                    break;  
                case Cell.CELL_TYPE_ERROR:  
                    break;  
                default:  
                    break;  
                }  
            }  
            list.add(sb.toString());  
        }  
        return list;  
    }
	@RequestMapping("/")
	public String toMain() {
		return "ExcelData";
	}
	File tempPathFile; 
	@RequestMapping("test")
	@ResponseBody
	public JSONObject read(HttpServletRequest request,MultipartFile file) {
        File file1=null;
        try {
			//String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        	 //String filePath = "/u1/upload/";
			 File dir = new File(filePath);
	            if(! dir.exists()) {
	                dir.mkdir();
	            }
	            String path = filePath + file.getOriginalFilename();
	            
			System.out.println("=============="+path);
			file1=new File(path);
			FileUtils.copyInputStreamToFile(file.getInputStream(), file1);//文件copy
			//FileUtils.copyFile( file1, new File(path));
			List<String> listS =exportListFromExcel(new File(path),0);//解析excel文件
			for(int i=0;i<listS.size();i++){
				if(listS.get(i).toString()==null || "".equals(listS.get(i).toString())) {
					break;
				}
				String str = listS.get(i).toString();
                System.out.println(str);
                String [] strs=str.split("\\|");//|特殊字符需要转
                for (int j = 0; j < strs.length; j++) {
                	System.out.println(j+":"+strs[j]);
				}
                System.out.println("=================================================");
                
            }
	       /* String str=listS.get(5).toString();
	        String[] strs=str.split("\\|");
	        System.out.println(str);
	        System.out.println(strs[5]);*/
		} catch ( IOException e) {
			e.printStackTrace();
		}// 得到所有的文件 
        JSONObject jb=new JSONObject();
        if(1==1) {
        	jb.put("data", "成功");
        }else {
        	jb.put("data", "失败,请联系咨询部");
        }
		return jb;
	}
}
