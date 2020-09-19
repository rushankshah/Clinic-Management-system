package sample;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;



public class PDFTester {
    public void PDF_Generator(String name,String age, String gender, String date, String prescription) {

            final PDPage singlePage = new PDPage();
            final PDFont courierBoldFont = PDType1Font.COURIER_BOLD;
            final int fontSize = 12;
            try (final PDDocument document = new PDDocument())
            {
                document.addPage(singlePage);
                final PDPageContentStream contentStream = new PDPageContentStream(document, singlePage);
//                PDFont font = PDType0Font.load(document, new File("C:\\Users\\dw\\Desktop\\FZLTXHJW.TTF"));
                contentStream.beginText();
                contentStream.setFont(courierBoldFont, 20);
                contentStream.newLineAtOffset(175, 750);
                contentStream.showText("AGNIVESH AYURVEDIC CLINIC");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,12);
                contentStream.newLineAtOffset(0,715);
                contentStream.showText("Dr. Jayesh Bambhania       Timing  Monday-Saturday    Sunday       For appointment");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(0,700);
                contentStream.setFont(courierBoldFont, 10);
                contentStream.showText("                B.A.M.S.                   6:30am to 1:30pm    10am to 1pm      Contact:09757265688");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,8);
                contentStream.newLineAtOffset(0,685);
                contentStream.showText("CONSULTING PHYSICIAN-AYURVED         ");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,10);
                contentStream.newLineAtOffset(0,685);
                contentStream.showText("                                            3:00pm-8:30pm");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,8);
                contentStream.newLineAtOffset(0,685);
                contentStream.showText("                                                                                                 (with prior appointment only)");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,10);
                contentStream.newLineAtOffset(0,600);
                contentStream.showText("      NAME:"+name);
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(0,580);
                contentStream.showText("      AGE:"+age);
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(0,560);
                contentStream.showText("      GENDER:"+gender);
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(480,655);
                contentStream.showText("      DATE:"+date);
                contentStream.endText();
//                contentStream.beginText();
//                contentStream.setFont(courierBoldFont,fontSize);
//                contentStream.newLineAtOffset(0,520);
                int position = 540;
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,10);
                contentStream.newLineAtOffset(0,position);
                contentStream.showText("      PRESCRIPTION:");
                contentStream.endText();
                int length;
                int xpos = 0;

                char[] pres = prescription.toCharArray();
                int i = 0;
                length = pres.length;
                while (i<length)
                {
                    if(pres[i] == '\n')
                    {

                        position = position-15;
                        xpos=0;
                        contentStream.beginText();
                        contentStream.setFont(courierBoldFont,10);
                        contentStream.newLineAtOffset(xpos,position);
                        contentStream.showText("                     "+ pres[i + 1]);
                        contentStream.endText();
                        i=i+1;
                        xpos = xpos + 6;
                    }

                    else
                    {
                        contentStream.beginText();
                        contentStream.setFont(courierBoldFont,10);
                        contentStream.newLineAtOffset(xpos,position);
                        contentStream.showText("                     "+(pres[i]));
                        contentStream.endText();
                        xpos = xpos+6;
                    }
                    i++;
                }

//                contentStream.showText("      PRESCRIPTION:"+prescription.replace("\n", "<br>").replace("\r", "<br>"));
//                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,10);
                contentStream.newLineAtOffset(0,30);
                contentStream.showText("               14,Ruby Apartment, M.V. Road, Near Vishal Hall,Andheri(East),Mumbai 400 069.");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,8);
                contentStream.newLineAtOffset(0,20);
                contentStream.showText("                                              REGISTRATION NO:I-64337-A");
                contentStream.endText();
                contentStream.beginText();
                contentStream.setFont(courierBoldFont,10);
                contentStream.newLineAtOffset(0,5);
                contentStream.showText("                               agniveshayurvedicclinic@yahoo.com");
                contentStream.endText();
                PDImageXObject clinicpic=PDImageXObject.createFromFile("C:\\Users\\Rushank Shah\\Desktop\\Clinic Management System\\src\\Images\\clinicpic.png",document);
                contentStream.drawImage(clinicpic,90,740,50,50);
//                HashMap<Integer, String> hashPicture = new HashMap<>();
//                hashPicture.put(1,"/home/rushankshah65/Downloads/pdfresizer.com-pdf-resize.pdf");
//                Overlay overlay = new Overlay();
//                overlay.setInputPDF(document);
//                overlay.setOverlayPosition(Overlay.Position.BACKGROUND);
//                overlay.overlay(hashPicture);
                contentStream.drawLine(170,670,170,730);
                contentStream.drawLine(450,670,450,730);
                contentStream.close();  // Stream must be closed before saving document.
                document.save("D:\\PatientInformation.pdf");
            }
            catch (IOException ioEx)
            {
                System.err.println(
                        "Exception while trying to create simple document - " + ioEx);
            }
        }

}