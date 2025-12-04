package org.erangaz;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportGenerator {
    public static void createReport(String documentName, String pathOutput, String[] values){
        try{
            FileInputStream fis = new FileInputStream("reports/examples/Реферат шаблон.docx");
            pathOutput += documentName;
            FileOutputStream fos = new FileOutputStream(pathOutput);
            XWPFDocument document = new XWPFDocument(fis);
            String[] placeHolders = PlaceHoldersFinder.findPlaceHolders(document);
            boolean wrongText = false;
            for (int i = 0; i < placeHolders.length; i++){
                if (i == 7)
                    wrongText = !values[i].matches("[^\\dабийтмгкКБМГ ]+$");
                if (i == 0 || i == 1 || i == 4 || i == 5)
                    wrongText = !values[i].matches("^[А-Яа-яa-zA-Z,. ]+$");
                DocumentFiller.fillDocument(document, placeHolders[i], values[i], wrongText);
                wrongText = false;
            }
            document.write(fos);
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден!");
        } catch (IOException e){
            System.out.println("Ошибка при работе с файлом!");
        } finally {
            System.out.println("Файл успешно сохранен в " + pathOutput);
        }
    }
}
