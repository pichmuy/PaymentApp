package com.teamresearch.apt50.Prints;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;


import com.teamresearch.apt50.Database.Configuration;
import com.teamresearch.apt50.Database.DBHelper;
import com.teamresearch.apt50.Database.Merchant;
import com.teamresearch.apt50.Database.Transaction;

import java.sql.SQLException;

import wangpos.sdk4.libbasebinder.Printer;


public class ToolsUtil {
    /**
     * @param context
     * @param printer
     */
    public static final int rowSize = 384;
    public static final int smallSize = 24 * 2;
    public static final int mediumSize = 18 * 2;
    public static final int largeSize = 12 * 2;
    public static final int extralargeSize = 8 * 2;

    /**

     *
     * @param context
     * @param printer
     */
    public static int printNormal(int ref,Context context, Printer printer) {
        int[] status = new int[1];
        int ret = -1;
        try {
            ret = printer.getPrinterStatus(status);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(status[0]!=0) return status[0];
        else {
            try {
                printer.printInit();
                printer.clearPrintDataCache();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            //clear print cache

            DBHelper dbHelper = new DBHelper(context);
            Merchant merchant = new Merchant();
            Configuration configuration = new Configuration();
            Transaction transaction = new Transaction();

            try {
                merchant = dbHelper.getById(Merchant.class, 1);
                configuration = dbHelper.getById(Configuration.class, 1);
                transaction = dbHelper.getById(Transaction.class, ref);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            String Body = transaction.command;
            String mediumSpline = "";
            for (int i = 0; i < mediumSize - 5; i++) {
                mediumSpline += "-";
            }

            try {

                // Set fixed length
                mediumSpline = "-----------------------------------------------";

                printer.printString(merchant.name, 25, Printer.Align.CENTER, true, false);
                printer.printString(merchant.address, 25, Printer.Align.CENTER, true, false);
                printer.printString(merchant.city + "," + merchant.state + " " + merchant.zipcode, 25, Printer.Align.CENTER, true, false);
                printer.printString((merchant.phone == null ? "" : merchant.phone) + "\n\n", 25, Printer.Align.CENTER, true, false);
                printer.printString(">>" + Body + " Receipt<<\n\n\n\n", 25, Printer.Align.CENTER, true, false);
                printer.printString(mediumSpline + "\n\n", 25, Printer.Align.LEFT, true, false);
                printer.printString("Order Number: " + transaction.orderID + "\nReference: " + ref + "\nOrder Time:" + transaction.trans_date, 25, Printer.Align.LEFT, true, false);
                printer.printString(mediumSpline + "\n", 25, Printer.Align.LEFT, true, false);
                printer.printString("D E T A I L S\n", 25, Printer.Align.CENTER, true, false);
                printer.printString(mediumSpline + "\n\n", 25, Printer.Align.LEFT, true, false);

                StringBuilder sbTable = new StringBuilder();
                sbTable.append("Qty: " + transaction.qty + "\n");
                sbTable.append("Amount: " + (transaction.amount == null ? "0.00" : transaction.amount) + "\n");
                sbTable.append("Card Type: " + (transaction.card_payment_type == null ? "" : transaction.card_payment_type) + "\n");
                sbTable.append("Transaction ID #: " + (transaction.getAuth_code() == null ? "" : transaction.getAuth_code()) + "\n\n");

                printer.printString(sbTable.toString(), 25, Printer.Align.LEFT, true, false);
                printer.printString(mediumSpline + "\n\n", 25, Printer.Align.LEFT, true, false);
                printer.printString("I agree to pay the above total amount according to the card issuer agreement.\n\n\n\n\n\n\n\n", 25, Printer.Align.CENTER, true, false);
                printer.printString("Signature:\n\n", 25, Printer.Align.LEFT, true, false);
                printer.printString("___________________________\n\n\n\n\n\n\n\n\n", 25, Printer.Align.LEFT, true, false);
                printer.printString("\n\nwww.teamsable.com\n\n\n\n\n", 25, Printer.Align.CENTER, true, false);
                printer.printPaper(100);
                printer.printFinish();


            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
    public static String getBlankBySize(int size) {
        String resultStr = "";
        for (int i = 0; i < size; i++) {
            resultStr += " ";
        }
        return resultStr;
    }
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }


}
