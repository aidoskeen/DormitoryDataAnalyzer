package FileReaders;

import Models.Payment;
import Models.Resident;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFileReader {

    public static ArrayList<Payment> getPaymentsFromFile(String name) throws IOException {
        FileInputStream stream = new FileInputStream(name);

        Workbook wb = new XSSFWorkbook(stream);
        Sheet sheet = wb.getSheetAt(1);
        ArrayList<Payment> paymentList = new ArrayList<>();
        for (Row row: sheet) {
            Payment payment = new Payment();
            for (Cell cell: row) {
                switch (cell.getColumnIndex()) {
                    case 0 -> payment.setPaymentType(cell.getStringCellValue());
                    case 1 -> payment.setPaymentMonth(cell.getStringCellValue());
                    case 2 -> payment.setPaymentMethod(cell.getStringCellValue());
                    case 3 -> payment.setPaymentAmount((long)(cell.getNumericCellValue()));
                }
            }
            paymentList.add(payment);
        }
        return paymentList;
    }
}
