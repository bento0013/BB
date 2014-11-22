/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.media;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;

/**
 *
 * @author bento
 */
public class MediaProcessCore {
    private static Log LOG = LogFactory.getLog(MediaProcessCore.class);

    public static void saveData(Media media,String filepath) throws Exception
    {
        try
        {
            saveStream(media, filepath);
        }
        catch(Exception e)
        {
            try
            {
                saveBytes(media, filepath);
            }
            catch(Exception e1)
            {
                try
                {
                    saveReader(media, filepath);
                }
                catch(Exception e2)
                {
                    try
                    {
                        saveString(media, filepath);
                    }
                    catch(Exception e3)
                    {
                        LOG.error(e3.getMessage(),e3);
                    }
                }
            }
        }
    }

    private static void saveStream(Media media,String filepath) throws Exception
    {
        InputStream inputStream = null;
        try
        {

            File file = new File(filepath);
            if(file.exists())
            {
                file.delete();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            inputStream = media.getStreamData();
            int read = 0;
            byte b[] = new byte[256];
            while((read = inputStream.read(b)) > 0)
            {
                fileOutput.write(b,0,read);
            }
            fileOutput.flush();
            fileOutput.close();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
    }

    private static void saveBytes(Media media,String filepath) throws Exception
    {
        InputStream inputStream = null;
        FileOutputStream fileOutput = null;
        try
        {

            File file = new File(filepath);
            if(file.exists())
            {
               file.delete();
            }
            fileOutput = new FileOutputStream(file);
            byte[] bytes = media.getByteData();                        
            fileOutput.write(bytes,0,bytes.length);
            fileOutput.flush();
            
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(fileOutput != null)
            {
                fileOutput.close();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
    }

    private static void saveString(Media media,String filepath) throws Exception
    {
        
        BufferedWriter writer =  null;
        try
        {

            File file = new File(filepath);
            if(file.exists())
            {
                file.delete();
            }
            FileWriter fileOutput = new FileWriter(file);
            String data = media.getStringData();
            writer = new BufferedWriter(fileOutput);
            writer.write(data);
            writer.flush();            
        }
        catch(Exception e)
        {            
            throw e;
        }
        finally
        {
            if(writer != null)
            {
                writer.close();
            }
        }
    }

    private static void saveReader(Media media,String filepath) throws Exception
    {

        BufferedWriter writer =  null;
        BufferedReader reader =  null;
        try
        {

            File file = new File(filepath);
            if(file.exists())
            {
                file.delete();
            }
            FileWriter fileOutput = new FileWriter(file);
            reader = new BufferedReader(media.getReaderData());
            writer = new BufferedWriter(fileOutput);
            String data = null;
            while( (data = reader.readLine()) != null)
            {
                writer.write(data);
            }
            
            writer.flush();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(writer != null)
            {
                writer.close();
            }

            if(reader != null)
            {
                reader.close();
            }
        }
    }
}
