package org.example;

import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
public class CheckCertificate {

    private Date notAfter;
    private Date currentDate = new Date();
    private long daysDiff;
    private long timeDiff;
    private String serialNumber;
    private String certContent;
    private String resutl;
    private String path;
    static X509Certificate  myCert;
    Logger logger = Logger.getLogger(CheckCertificate.class.getName());





    public void getCertInfo(String certContent, String listOfFile) throws DecoderException {
        setCertContent(certContent);
        setPath(listOfFile);

            try {
                myCert = (X509Certificate) CertificateFactory
                        .getInstance("X509")
                        .generateCertificate(
                                // string encoded with default charset
                                new ByteArrayInputStream(getCertContent().getBytes())
                        );
            } catch (CertificateException e) {
                throw new RuntimeException(e);
            }

        setNotAfter(myCert.getNotAfter());
        setTimeDiff(Math.abs(getNotAfter().getTime() - getCurrentDate().getTime()));
        setDaysDiff(TimeUnit.DAYS.convert(getTimeDiff(), TimeUnit.MILLISECONDS));
        byte[] serialNumBA = myCert.getSerialNumber().toByteArray();
        setSerialNumber(encodeUsingApacheCommons(serialNumBA));
    }

    public static String encodeUsingApacheCommons(byte[] bytes)
            throws DecoderException {
        return Hex.encodeHexString(bytes);
    }
    @Override
    public String toString() {
        return "Serial Number :"+getSerialNumber()+" expiry date: "+getNotAfter()+" The days left at the end of the certification.: "+getDaysDiff()+" path: "+getPath();
    }
}
