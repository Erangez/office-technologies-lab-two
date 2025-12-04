package org.erangaz;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AuthorInfoConsentGenerator {
    public static void createReport(String documentName, String pathOutput, String[] values, String[] personInfo, int personInfoType) {
        try{
            FileInputStream fis = new FileInputStream("reports/examples/Согласие на указание сведений об авторе шаблон.docx");
            pathOutput += documentName;
            FileOutputStream fos = new FileOutputStream(pathOutput);
            XWPFDocument document = new XWPFDocument(fis);
            String[] placeHolders = PlaceHoldersFinder.findPlaceHolders(document);
            boolean wrongText = false;
            for (int i = 0; i < placeHolders.length; i++){
                if (i == 1){
                    switch (personInfoType){
                        case 1:
                            for (int j = 0; j < personInfo.length - 1; j++){
                                wrongText = !personInfo[j].matches("^[а-яА-ЯеЁ\\- ]+$");
                            }
                            break;
                        case 2:
                            wrongText = (!personInfo[2].matches("^\\d{13}$") || !personInfo[3].matches("^\\d{10}$"));
                            break;
                    }
                values[i] = String.join(", ", personInfo);
                }
                if (i == 0 || i == 2 || i == 6 || i == 9 || i == 10 || i == 11)
                    wrongText = !values[i].matches("^[а-яА-ЯеЁ,. ]+$");
                if (i == 3) {
                    wrongText = !values[i].matches("^\\d+$");
                    if (!wrongText)
                        if (Integer.parseInt(values[i]) > 31 || Integer.parseInt(values[i]) < 1)
                            wrongText = true;
                }
                if (i == 4){
                    wrongText = !values[i].matches("^\\d+$");
                    if (!wrongText)
                        if (Integer.parseInt(values[i]) > 12 || Integer.parseInt(values[i]) < 1)
                            wrongText = true;
                }
                if (i == 5){
                    wrongText = !values[i].matches("^\\d+$");
                    if (!wrongText)
                        if (Integer.parseInt(values[i]) > 2026 || Integer.parseInt(values[i]) < 1916)
                            wrongText = true;
                }
                if (i == 8) {
                    wrongText = !values[i].matches("^\\d+$");
                    if (!wrongText)
                        values[i] += " %";
                }
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
