package org.erangaz;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pathOutput, documentName, value;
        String[] values = new String[8];
        String[] fields = new String[]{"Авторы", "Правообладатель", "Программа", "Аннотация", "Тип ЭВМ", "Язык", "ОС", "Объем программы", "Полное название организации",
                "Название кафедры",
                "Номер работы",
                "Название дисциплины",
                "Номер группы",
                "ФИО студента",
                "ФИО преподавателя",
                "Год",
                "Цель работы",
                "Вариант задания",
                "Текст задания",
                "Решение",
                "Листинг программы",
                "Результат работы",
                "Вывод",
                "Название программа для ЭВМ или базы данных",
                "ФИО",
                "Адрес места жительства",
                "Серия и номер паспорта",
                "Дата выдачи паспорта",
                "Кем выдан паспорт",
                "Фамилия и инициалы",
                "Название программы ЭВМ",
                "Тип правообладателя (Физ. лицо/Юр. лицо)",
                "Данные правообладателя/заявителя (ФИО или наименование, ОГРН, ИНН, адрес)",
                "ФИО автора",
                "Дата рождения автора (ДД.ММ.ГГГГ)",
                "Гражданство автора",
                "Адрес постоянного жительства автора",
                "Доля авторства (%)",
                "Подпись автора (Инициалы)",
                "Должность представителя правообладателя",
                "Инициалы представителя правообладателя"};
        String[] allValues, bufferArray;
//      Реферат
        System.out.println("--------------- Реферат ---------------");
        System.out.println("Заполнение реферата.");
        System.out.print("Введите путь для сохранения: ");
        pathOutput = input.nextLine();
        System.out.print("Введите только имя документа без расширения: ");
        documentName = input.nextLine() + ".docx";
        System.out.println("Для заполнения полей документа используйте текст.");
        System.out.println("Неправильные поля отмечены красным цветом.");
        System.out.println("Поля для заполнения:\n" +
                "1. Авторы " +
                "2. Правообладатель " +
                "3. Программа " +
                "4. Аннотация " +
                "5. Тип ЭВМ " +
                "6. Язык " +
                "7. ОС " +
                "8. Объем программы");
        for (int i = 0; i < values.length; i++){
            System.out.printf("%d >> ", i + 1);
            value = input.nextLine();
            values[i] = value;
        }
        ReportGenerator.createReport(documentName, pathOutput, values);
        allValues = Arrays.copyOf(values, values.length);
        // Отчет
        System.out.println("--------------- Лабораторная работа ---------------");
        String[] imagesData = new String[0];
        values = new String[15];
        System.out.println("Заполнение отчета лабораторной работы.");
        System.out.print("Введите путь для сохранения: ");
        pathOutput = input.nextLine();
        System.out.print("Введите только имя документа без расширения: ");
        documentName = input.nextLine() + ".docx";
        System.out.println("""
                Для заполнения полей документа используйте текст. \n
                Неправильные поля отмечены красным цветом. \n
                Изображение должно быть в формате .png! \
                Если ваше изображение не в формате .png, напишите перед ним флаг --c \n
                Для вставки изображений и подписи к нему сначала напишите флаг --img. Для выхода введите --exit
                Например:
                """);
        System.out.println("--img\n/pictures/Анапа2010.png\nАнапа 2010\n--exit\n");
        System.out.println("Поля для заполнения:\n" +
                "1. Полное название организации " +
                "2. Название кафедры " +
                "3. Номер работы " +
                "4. Название дисциплины " +
                "5. Номер группы " +
                "6. ФИО студента " +
                "7. ФИО преподавателя " +
                "8. Год " +
                "9. Цель работы " +
                "10. Вариант задания " +
                "11. Текст задания " +
                "12. Решение " +
                "13. Листинг программы " +
                "14. Результат работы. " +
                "15. Вывод ");
        for (int i = 0; i < values.length; i++){
            System.out.printf("%d >> ", i + 1);
            value = input.nextLine();
            if (value.contains("--img")){
                int j = 0;
                while(!value.contains("--exit")){
                    imagesData = Arrays.copyOf(imagesData, imagesData.length + 1);
                    System.out.printf("%d [img] >> ", i + 1);
                    value = input.nextLine();
                    if (value.contains("--c"))
                        value = ImageConverter.getImage(value.substring(4));
                    imagesData[j] = value;
                    j++;
                }
                imagesData = Arrays.copyOf(imagesData, imagesData.length - 1);
                values[i] = "{{image}}";
            }
            else
                values[i] = value;
        }
        LabReport.createLabReport(documentName, pathOutput, values, imagesData);
        bufferArray = new String[allValues.length + values.length];
        System.arraycopy(allValues, 0, bufferArray, 0, allValues.length);
        System.arraycopy(values, 0, bufferArray, allValues.length, values.length);
        allValues = bufferArray;
        // Согласие на обработку персональных данных
        System.out.println("--------------- Согласие на обработку персональных данных ---------------");
        values = new String[7];
        System.out.println("Заполнение отчета согласия.");
        System.out.print("Введите путь для сохранения: ");
        pathOutput = input.nextLine();
        System.out.print("Введите только имя документа без расширения: ");
        documentName = input.nextLine() + ".docx";
        System.out.println("Для заполнения полей документа используйте текст.");
        System.out.println("Неправильные поля отмечены красным цветом.");
        System.out.println("Поля для заполнения:\n" +
                "1. Название программа для ЭВМ или базы данных " +
                "2. ФИО " +
                "3. Адрес места жительства " +
                "4. Серия и номер паспорта " +
                "5. Дата выдачи паспорта " +
                "6. Кем выдан паспорт " +
                "7. Фамилия и инициалы");
        for (int i = 0; i < values.length; i++){
            System.out.printf("%d >> ", i + 1);
            value = input.nextLine();
            values[i] = value;
        }
        PersonalDataConsentGenerator.createReport(documentName, pathOutput, values);
        bufferArray = new String[allValues.length + values.length];
        System.arraycopy(allValues, 0, bufferArray, 0, allValues.length);
        System.arraycopy(values, 0, bufferArray, allValues.length, values.length);
        allValues = bufferArray;
        System.out.println("--------------- Согласие на указание сведений об авторе ---------------");
        values = new String[12];
        String[] personInfo = new String[4];
        int personInfoType = 1;
        System.out.println("Заполнение отчета согласия.");
        System.out.print("Введите путь для сохранения: ");
        pathOutput = input.nextLine();
        System.out.print("Введите только имя документа без расширения: ");
        documentName = input.nextLine() + ".docx";
        System.out.println("Для заполнения полей документа используйте текст.");
        System.out.println("Неправильные поля отмечены красным цветом.");
        System.out.println("""
                Поля для заполнения:
                1. Название программы ЭВМ
                2. Выбор из нижеперечисленного для правообладателя или заявителя:
                \t2.1. Физ. Лицо -  указываются фамилия, имя, отчество (последнее – при наличии), место жительства
                \t2.2. Юр. Лицо -  наименование, место нахождения, основной государственный регистрационный номер (ОГРН)  и идентификационный номер налогоплательщика (ИНН)
                3. ФИО автора \
                4. День рождения \
                5. Месяц рождения \
                6. Год рождения \
                7. Гражданство \
                8. Место постоянного жительства, включая страну \
                9. Процент написания исходного текста программы (вводить без %) \
                10. Подпись автора (инициалы) \
                11. Должность правообладателя или представителя \
                12. Инициалы правообладателя или представителя""");
        for (int i = 0; i < values.length; i++){
            if (i == 1){
                System.out.println("Выберите из перечисленного (1, 2): ");
                System.out.print("2 >> ");
                personInfoType = input.nextInt();
                if (personInfoType != 1 && personInfoType != 2) {
                    System.out.println("Выбран неверный тип!");
                    System.out.println("В качестве выбора будет тип 1 - физ. лицо.");
                    personInfoType = 1;
                }
                System.out.println("""
                        Вводите данные исходя из ранее написанных вариантов.
                        2.1 (1) Фамилия \
                        2.1 (2) Имя \
                        2.1 (3) Отчество (если нет, то -) \
                        2.1 (4) Место жительства

                        2.2 (1) Наименование \
                        2.2 (2) Место нахождения \
                        2.2 (3) ОГРН \
                        2.2 (4) ИНН
                        """);
                input.nextLine();
                for (int j = 0; j < personInfo.length; j++){
                    System.out.printf("2.%d (%d) >> ", personInfoType, j);
                    personInfo[j] = input.nextLine();
                }
                System.out.println();
            }
            else {
                System.out.printf("%d >> ", i + 1);
                value = input.nextLine();
                values[i] = value;
            }
        }
        AuthorInfoConsentGenerator.createReport(documentName, pathOutput, values, personInfo, personInfoType);
        bufferArray = new String[allValues.length + values.length];
        System.arraycopy(allValues, 0, bufferArray, 0, allValues.length);
        System.arraycopy(values, 0, bufferArray, allValues.length, values.length);
        allValues = bufferArray;
        try{
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("dataValues.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT);
            csvPrinter.printRecord("Поле", "Значение");
            System.out.println("Значения: " + Arrays.toString(allValues));
            for (int i = 0; i < fields.length; i++){
                csvPrinter.printRecord(fields[i], allValues[i]);
                csvPrinter.flush();
            }
        } catch (IOException e){
            System.out.println("Ошибка при записи в dataValues.csv!");
        }
    }
}