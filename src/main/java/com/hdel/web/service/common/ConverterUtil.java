package com.hdel.web.service.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConverterUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConverterUtil.class);

    public static String xml2JsonString(String xml) throws Exception {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);

        return xmlJSONObj.toString();
    }

    public static HashMap<String, Object> jsonString2Map (String jsonString) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return (HashMap<String, Object>) mapper.readValue(jsonString, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List<String>> readToList(String path) {
        List<List<String>> list = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8");
            String line = "";

            while((line=br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<String>(Arrays.asList(token));
                list.add(tempList);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error(String.valueOf(e));
        } catch (IOException e) {
            LOGGER.error(String.valueOf(e));
        } finally {
            try {
                if(br != null) {br.close();}
            } catch (IOException e) {
                LOGGER.error(String.valueOf(e));
            }
        }
        return list;
    }

}
