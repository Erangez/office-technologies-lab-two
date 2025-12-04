package org.erangaz;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LabReport {
    public static void createLabReport(String documentName, String pathOutput, String[] values, String[] imagesData){
        try{
            FileInputStream fis = new FileInputStream("reports/examples/Структура отчета шаблон.docx");
            pathOutput += documentName;
            FileOutputStream fos = new FileOutputStream(pathOutput);
            XWPFDocument document = new XWPFDocument(fis);
            String[] placeHolders = PlaceHoldersFinder.findPlaceHolders(document);
            boolean wrongText = false;
            for (int i = 0; i < placeHolders.length; i++){
                if (placeHolders[i].contains("{{image}}")) {
                    DocumentFiller.fillImageData(document, imagesData);
                }
                else
                    if (i == 2 || i == 4|| i == 7 || i == 9)
                        wrongText = !values[i].matches("^\\d+$");
                    if (i == 3 || i == 5 || i == 6)
                        wrongText = !values[i].matches("^[А-Яа-я,. ]+$");
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
