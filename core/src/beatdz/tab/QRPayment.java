package beatdz.tab;

import beatdz.Binary;
import beatdz.Graphics;
import beatdz.LangLa_cy;
import beatdz.LangLa_dz;
import beatdz.LangLa_fo;
import beatdz.LangLa_jz;
import beatdz.MainScreen;
import beatdz.Utlis;
import beatdz.mFont;
import beatdz.mImage;
import beatdz.DataCenter;
import java.net.URLEncoder;

public class QRPayment extends LangLa_fo {

    private LangLa_dz btnDongY;
    private String qrUrl;
    private mImage qrImage;

    private String bankCode;
    private String accountNo;
    private String accountName;
    private int amount;
    private String addInfo;

    public QRPayment(MainScreen screen, String bankCode, String accountNo, String accountName, int amount, String addInfo) {
        this.a(230, 240);
        this.p = screen;
        this.r = false;
        this.cF = 2;

        this.bankCode = bankCode;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.amount = amount;
        this.addInfo = addInfo;

        this.loadQR();

        this.btnDongY = this.a(
                this.width / 2 - 50,
                this.height - 35,
                "Đồng ý",
                this,
                10001,
                13
        );
    }

    private void loadQR() {
        try {
            this.qrUrl = "https://img.vietqr.io/image/"
                    + this.bankCode + "-" + this.accountNo + "-compact2.png"
                    + "?amount=" + this.amount
                    + "&addInfo=" + encode(this.addInfo)
                    + "&accountName=" + encode(this.accountName);

            String key = "arr_qr_payment_"
                    + this.bankCode + "_"
                    + this.accountNo + "_"
                    + this.amount + "_"
                    + this.addInfo.replaceAll("[^a-zA-Z0-9]", "");

            Binary.write(key, Binary.b(this.qrUrl));
            this.qrImage = mImage.a(Binary.read(key), 0, 0);
        } catch (Exception e) {
            Utlis.println(e);
            this.qrImage = mImage.a(1, 1);
        }
    }

    private String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            return text;
        }
    }

    @Override
    public void a(Graphics g) {
        this.a(g, this.cx, this.cy, this.width, this.height, "Donate", (byte) 2, this.r);

        int qrSize = 150 * DataCenter.gI().zoomLevel;
        int qrX = this.width / 2;
        int qrY = 40 + qrSize / DataCenter.gI().zoomLevel;

        if (this.qrImage != null) {
            g.a(
                    this.qrImage,
                    0,
                    0,
                    this.qrImage.c,
                    this.qrImage.d,
                    0,
                    qrX,
                    qrY,
                    qrSize,
                    qrSize,
                    33
            );
        } else {
            mFont.b(mFont.d, g, "Đang tải QR...", this.width / 2, 120, 2, -1, -16777216);
        }

        this.b(g);
    }

    private void closePopup() {
        this.J();
    }

    @Override
    public void a(LangLa_jz var1, int var2, int var3) {
        if (var1.b == 10001) {
            closePopup();
        }
    }

    @Override
    public void a(int var1, Object var2, LangLa_cy var3) {
        if (var1 == 10001) {
            closePopup();
        }
    }

    @Override
    public void a(int var1, Object var2) {
    }

    @Override
    public void b() {
    }
}