package FileReaders;

import Models.Resident;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ResidentFileReader {

    public static ArrayList<Resident> getResidentsFromFile(String name) throws IOException {
        FileInputStream stream = new FileInputStream(name);

        Workbook wb = new XSSFWorkbook(stream);
        Sheet sheet = wb.getSheetAt(0);
        ArrayList<Resident> residentList = new ArrayList<Resident>();
        for (Row row: sheet) {
            Resident resident = new Resident();
            for (Cell cell: row) {
                switch (cell.getColumnIndex()) {
                    case 0 -> resident.setName(cell.getStringCellValue());
                    case 1 -> resident.setSurname(cell.getStringCellValue());
                    case 2 -> resident.setCountry(cell.getStringCellValue());
                    case 3 -> resident.setDormitory(cell.getStringCellValue());
                    case 4 -> resident.setRoom(cell.getStringCellValue());
                    case 5 -> resident.setDebtAmount((long)(cell.getNumericCellValue()));
                }
            }
            residentList.add(resident);
        }
        return residentList;
    }
}
