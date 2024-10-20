package Ex2;

import java.util.Scanner;
import java.util.logging.Logger;

abstract class ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());

    public final void generateReport() {
        gatherData();
        formatData();
        createHeader();
        createBody();
        if (customerWantsSave()) {
            saveReport();
        } else {
            sendReport();
        }
    }

    protected abstract void gatherData();
    protected abstract void formatData();
    protected abstract void createHeader();
    protected abstract void createBody();
    protected abstract void saveReport();
    protected abstract void sendReport();

    protected boolean customerWantsSave() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сохранить отчет? (да/нет): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        boolean wantsSave = answer.equals("да");
        logger.info("Пользователь выбрал сохранение отчета: " + wantsSave);
        return wantsSave;
    }
}

class PdfReport extends ReportGenerator {
    @Override
    protected void gatherData() {
        System.out.println("Собираем данные для PDF отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматируем данные для PDF отчета...");
    }

    @Override
    protected void createHeader() {
        System.out.println("Создаем заголовок для PDF отчета...");
    }

    @Override
    protected void createBody() {
        System.out.println("Создаем тело PDF отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохраняем PDF отчет...");
    }

    @Override
    protected void sendReport() {
        System.out.println("Отправляем PDF отчет по электронной почте...");
    }
}

class ExcelReport extends ReportGenerator {
    @Override
    protected void gatherData() {
        System.out.println("Собираем данные для Excel отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматируем данные для Excel отчета...");
    }

    @Override
    protected void createHeader() {
        System.out.println("Создаем заголовок для Excel отчета...");
    }

    @Override
    protected void createBody() {
        System.out.println("Создаем тело Excel отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохраняем Excel отчет...");
    }

    @Override
    protected void sendReport() {
        System.out.println("Отправляем Excel отчет по электронной почте...");
    }
}

class HtmlReport extends ReportGenerator {
    @Override
    protected void gatherData() {
        System.out.println("Собираем данные для HTML отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматируем данные для HTML отчета...");
    }

    @Override
    protected void createHeader() {
        System.out.println("Создаем заголовок для HTML отчета...");
    }

    @Override
    protected void createBody() {
        System.out.println("Создаем тело HTML отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохраняем HTML отчет...");
    }

    @Override
    protected void sendReport() {
        System.out.println("Отправляем HTML отчет по электронной почте...");
    }
}

class CsvReport extends ReportGenerator {
    @Override
    protected void gatherData() {
        System.out.println("Собираем данные для CSV отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматируем данные для CSV отчета...");
    }

    @Override
    protected void createHeader() {
        System.out.println("Создаем заголовок для CSV отчета...");
    }

    @Override
    protected void createBody() {
        System.out.println("Создаем тело CSV отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохраняем CSV отчет...");
    }

    @Override
    protected void sendReport() {
        System.out.println("Отправляем CSV отчет по электронной почте...");
    }
}

public class Main2 {
    public static void main(String[] args) {
        ReportGenerator pdfReport = new PdfReport();
        pdfReport.generateReport();

        ReportGenerator excelReport = new ExcelReport();
        excelReport.generateReport();

        ReportGenerator htmlReport = new HtmlReport();
        htmlReport.generateReport();

        ReportGenerator csvReport = new CsvReport();
        csvReport.generateReport();
    }
}
