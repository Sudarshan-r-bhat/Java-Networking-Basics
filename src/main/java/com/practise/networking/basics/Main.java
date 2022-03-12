package com.practise.networking.basics;
/*import com.gistlabs.mechanize.*;
import com.gistlabs.mechanize.document.html.form.Form;
import com.gistlabs.mechanize.impl.MechanizeAgent;
import com.gistlabs.mechanize.document.*;
import com.gistlabs.mechanize.interfaces.document.Document;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Main.call_me();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void call_me() throws Exception
    {
        MechanizeAgent agent = new MechanizeAgent();
        Document page = agent.get("https://campuscommune.tcs.com/en-in/intro");
        Form form = page.form("form");
        form.get("user[name]").set("<username>");
        form.get("user[password]").set("<password>");
        Resource response = form.submit();
        assertTrue(response.getTitle().startsWith("mechanize java"));
        System.out.println(response.toString());

    }

}*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            Main.https_request();
        }
        catch(Exception e)
        {
            System.out.print(e.getCause().toString());
        }
        //System.out.println("Hello World!");
    }


    public static void https_request() throws Exception
    {
        String address = "https://campuscommune.tcs.com/en-in/intro";
        URL url = new URL(address);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        Map<String, String> params = new LinkedHashMap<>();
        // remember map is an Abstract interface. it is implemented by multiple classes like LinkedHashMap<>, TreeMap<>
        // username and password base64 encoded: Q1QyMDE4MjM4Mzk2NjpRd2VydHkxOTIxQEA= or
        // Q1QyMDE4MjM4Mzk2NlF3ZXJ0eTE5MjFAQA==
        StringBuilder postData = new StringBuilder();
        params.put("Authorization", "Basic Q1QyMDE4MjM4Mzk2NjpRd2VydHkxOTIxQEA=");
        params.put("User-Agent", "Mozilla/5.0");
        for(Map.Entry s : params.entrySet())
        {
            if (postData.length() != 0)
                postData.append('&');

            postData.append(URLEncoder.encode(String.valueOf(s.getKey()), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(s.getValue()),"UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println(response);

    }
}
