package org.example;

import org.apache.commons.codec.DecoderException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.cert.CertificateException;
import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(CheckCertificate.class.getName());
    public static void main(String[] args) throws CertificateException, DecoderException, IOException {
    CheckCertificate c =new CheckCertificate();
        String path = "C:\\Users\\ASUS\\Downloads\\g2g_mofa_gov_sa";  // Path where the certificates are located.
       
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        logger.info("No of Certificates = " + listOfFiles.length);
        for (int i = 0; i < listOfFiles.length; i++) {

            String str = Files.readString(listOfFiles[i].toPath());
        String pathFile = String.valueOf(listOfFiles[i]);
          c.getCertInfo(str,pathFile);
           logger.info(c.toString());

        }
        }

    }
