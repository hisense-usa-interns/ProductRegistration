package com.hisense.productregistration.register;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * class for methods registering via given url api
 */
public class UrlRegister {

    private Context context;
    private static final String TAG = "UrlRegister";
    private static HttpURLConnection con;

    /**
     * constructor of urlRegister, can be called as new urlRegister(this) from upper class
     * @param context of class calling this method
     */
    public UrlRegister(Context context) {
        this.context = context;
    }

    /**
     * Use GET method to register given data to url and read the response
     * @param productType Televisions
     * @param model device model number
     * @param serial device serial number
     * @param first first name of user
     * @param last last name of user
     * @param zip zip code number
     * @param email email must fit the valid email syntax
     * @throws MalformedURLException
     * @throws IOException
     */
    public void getRequestToURL(String productType, String model, String serial, String first, String last, String zip, String email) throws MalformedURLException, IOException {
        String url = "https://hisense.stagingtank.com/api/v1/register?ProductType=" + productType + "&ModelNumber=" + model + "&SerialNumber=" + serial + "&FirstName=" + first + "&LastName=" + last + "&PostalZipCode=" + zip + "&Email=" + email;

        URL urlObj = new URL(url);
        con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("cache-control", "no-cache");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Host", "hisense.stagingtank.com");
        con.setRequestProperty("Postman-Token", "488907b4-946e-4d5a-9912-861d9337f0fb,cc876884-2353-454d-a9e9-90639b053df5");
        con.setRequestProperty("Cache-Control", "no-cache");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("User-Agent", "PostmanRuntime/7.15.2");

        int responseCode = con.getResponseCode();
        System.out.println("Sending 'GET' request to URL: " + url);
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }





}
