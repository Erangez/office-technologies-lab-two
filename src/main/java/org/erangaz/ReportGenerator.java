package org.erangaz;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportGenerator {
    public static void createReport(String pathOutput, String documentName, String[] valuesToPaste){
        try{
            pathOutput += documentName;
            String examplePath = "reports/examples/Реферат шаблон.docx";
            FileInputStream fis = new FileInputStream(examplePath);
            FileOutputStream fos = new FileOutputStream(pathOutput);
            XWPFDocument document = new XWPFDocument(fis);
            String[] placeHolders = PlaceHoldersFinder.findPlaceHolders(document);
            for (int i = 0; i < placeHolders.length; i++){
                DocumentFiller.fillDocument(document, placeHolders[i], valuesToPaste[i]);
            }
            document.write(fos);
            System.out.printf("Документ успешно создан и сохранен как: %s\n", pathOutput);
        } catch (FileNotFoundException e){
            System.out.printf("Файл не найден по пути: %s\n", pathOutput);
        } catch (IOException e) {
            System.out.println("Ошибка во время работы с файлом!");
        }
    }
}
