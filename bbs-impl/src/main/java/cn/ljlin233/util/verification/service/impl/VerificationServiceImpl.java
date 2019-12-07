package cn.ljlin233.util.verification.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.util.verification.dao.VerificationDao;
import cn.ljlin233.util.verification.entity.Verification;
import cn.ljlin233.util.verification.service.VerificationService;

/**
 * VerificationServiceImpl
 */
@Service
public class VerificationServiceImpl implements VerificationService {

    private int width = 120;

    private int height = 60;

    private Random random = new Random();

    private String codes = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    private Color bgColor = new Color(255, 255, 255);

    private Font font = new Font(null, Font.PLAIN, 60 - 10);

    private VerificationDao verificationDao;

    public VerificationServiceImpl() {
    }

    ;

    @Autowired
    public VerificationServiceImpl(VerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    @Override
    public Verification getVerification() {
        String verId = getVerificationId();
        String verCode = getVerificationCode();

        verificationDao.addVerification(verId, verCode);

        BufferedImage verImage = getVerificationImage(verCode);
        // image to base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String imageBase64 = "";

        try {
            ImageIO.write(verImage, "jpg", outputStream);
            imageBase64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Verification(verId, imageBase64);
    }

    @Override
    public boolean checkVerification(String verId, String verCode) {

        if (verId == null || verCode == null || verCode.length() != 4) {
            return false;
        }

        String verCode2 = verificationDao.getVerificationCode(verId);

        if (verCode2 == null) {
            return false;
        }

        if (verCode.compareToIgnoreCase(verCode2) == 0) {
            return true;
        }

        return false;
    }

    private String getVerificationId() {
        String verId = UUID.randomUUID().toString().replace("-", "");
        return verId;
    }

    private String getVerificationCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char c = getRandomChar();
            sb.append(c);
        }

        String verCode = sb.toString();
        return verCode;
    }

    private BufferedImage getVerificationImage(String verCode) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(this.bgColor);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < verCode.length(); i++) {
            char c = verCode.charAt(i);

            int x = i * width / 4;
            g.setColor(getRandomColor());
            g.setFont(font);
            g.drawString(String.valueOf(c), x, height - 5);
        }

        drawLine(image);

        return image;
    }

    private Color getRandomColor() {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    private char getRandomChar() {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    private void drawLine(BufferedImage image) {
        int lineNums = 60;
        Graphics g = image.getGraphics();
        for (int i = 0; i < lineNums; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
    }

}