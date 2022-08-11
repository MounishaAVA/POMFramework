package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	// To read excel and store as excel hash table Key and value Configuration files
	@SuppressWarnings("resource")
	public HashMap<String, String> setMapData(String filepathConfig, String sheetIndex) throws IOException {

		FileInputStream fis = new FileInputStream(filepathConfig);

		Workbook workbook = new XSSFWorkbook(fis);

		int Index = Integer.parseInt(sheetIndex);

		Sheet sheet = workbook.getSheetAt(Index);

		int lastRow = sheet.getLastRowNum();

		HashMap<String, String> my_dict = new HashMap<String, String>();

		for (int i = 0; i <= lastRow; i++) {

			DataFormatter formatter = new DataFormatter();

			Row row = sheet.getRow(i);

			String key = formatter.formatCellValue(row.getCell(1));
			String value = formatter.formatCellValue(row.getCell(2));

			// Putting key & value in dataMap
			my_dict.put(key, value);

		}
		return my_dict;
	}
	
	// To get the row index by passing Test Script ID
		public int GetrowNum(String LookupVal, String filePath, String sheetIndex) {
			int Rowindex = 0;
			try {
				String toFind = LookupVal;
				Workbook wb = WorkbookFactory.create(new File(filePath));
				DataFormatter formatter = new DataFormatter();
				int Index = Integer.parseInt(sheetIndex);
				org.apache.poi.ss.usermodel.Sheet sheet1 = wb.getSheetAt(Index);
				for (Row row : sheet1) {
					for (Cell cell : row) {
						CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
						String text = formatter.formatCellValue(cell);

						// is it an exact match?
						if (toFind.equals(text)) {

							Rowindex = cellRef.getRow();
						}
					}
				}
			} catch (Exception e) {
			}
			return Rowindex;
		}

		// To read excel and store as hash map for test data
		private XSSFSheet getSheet(String filePathReadData, int sheetIndex) throws IOException {
			FileInputStream fis = new FileInputStream(filePathReadData);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			workbook.close();
			return sheet;
		}

		public Map<String, Map<String, String>> getExcelAsMap(String filePathReadData, String sheetIndex)
				throws IOException {

			int Index = Integer.parseInt(sheetIndex);
			XSSFSheet sheet = getSheet(filePathReadData, Index);
			Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
			List<String> columnHeader = new ArrayList<String>();
			Row row = sheet.getRow(0);
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				columnHeader.add(cellIterator.next().getStringCellValue());
			}
			int rowCount = sheet.getLastRowNum();
			int columnCount = row.getLastCellNum();
			for (int i = 1; i <= rowCount; i++) {
				Map<String, String> singleRowData = new HashMap<String, String>();
				Row row1 = sheet.getRow(i);
				for (int j = 0; j < columnCount; j++) {
					Cell cell = row1.getCell(j);
					singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
				}
				completeSheetData.put(String.valueOf(i), singleRowData);
			}
			return completeSheetData;
		}

		public String getCellValueAsString(Cell cell) {
			String cellValue = null;
			try {
				switch (cell.getCellType()) {
				case NUMERIC:
					if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

						cellValue = String.valueOf(cell.getNumericCellValue());
						if (DateUtil.isCellDateFormatted(cell)) {
							DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
							Date date = cell.getDateCellValue();
							cellValue = df.format(date);
						}
					}

					break;
				case STRING:
					cellValue = cell.getStringCellValue();
					break;
				case BOOLEAN:
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case FORMULA:
					cellValue = cell.getCellFormula();
				default:
					cellValue = "DEFAULT";
				}
			} catch (NullPointerException npe) {
				cellValue = " ";
			}

			return cellValue;
		}
}
