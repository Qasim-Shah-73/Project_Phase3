package com.example.project_phase2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSerialize {


    public static String serialize(Serializable obj) throws IOException
    {
        if (obj == null) return "";
        try{
            ByteArrayOutputStream serialobj = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(serialobj);
            objStream.writeObject(obj);
            objStream.close();
            return encodeBytes(serialobj.toByteArray());
        }catch (Exception e)
        {throw new RuntimeException(e);}
    }

    public static Object deserialize(String str) throws IOException
    {
        if (str == null || str.length() == 0){return null;}
        try
        {
            ByteArrayInputStream serialobj = new ByteArrayInputStream(decodeBytes(str));
            ObjectInputStream objstream = new ObjectInputStream(serialobj);
            return objstream.readObject();
        }catch (Exception e)
        {throw new RuntimeException(e);}
    }

    private static byte[] decodeBytes(String str) {
        byte[] bytes = new byte[str.length()/2];
        for (int i = 0 ; i < str.length(); i += 2)
        {
            char c = str.charAt(i);
            bytes[i/2] = (byte) ((c - 'a') << 4);
            c = str.charAt(i+1);
            bytes[i/2] += (c- 'a');
        }
        return  bytes;
    }

    private static String encodeBytes(byte[] bytes) {
    StringBuffer str = new StringBuffer();
        for (int i = 0; i < bytes.length;i++)
        {
            str.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            str.append((char) (((bytes[i] ) & 0xF) + ((int) 'a')));
        }
        return str.toString();
    }

}
