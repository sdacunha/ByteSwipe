/*
 * MIT License
 *
 * Copyright (c) 2016 Shadwell Da Cunha
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.shadwelldacunha.byteswipe.core;

import c10n.C10N;
import c10n.C10NConfigBase;
import c10n.annotations.DefaultC10NAnnotations;
import com.shadwelldacunha.byteswipe.ByteSwipe;
import org.apache.commons.logging.impl.Log4JLogger;

import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;


/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class Utilities {

    public static void setLookAndFeel() {
        //TODO: Handle exceptions gracefully
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void setLocalization() {
        C10N.configure(new C10NConfigBase(){
            @Override
            public void configure(){
                install(new DefaultC10NAnnotations());
                setLocaleProvider(() -> Locale.ENGLISH);
            }
        });
    }

    public static File getResource(String resource) throws URISyntaxException {
        ClassLoader classLoader = Utilities.class.getClassLoader();
        URL fileURL = classLoader.getResource(resource);
        return new File(fileURL.toURI());
    }

    public static void copyResource(String resource, String destination) throws IOException {
        ClassLoader classLoader = Utilities.class.getClassLoader();
        InputStream resStreamIn = classLoader.getResourceAsStream(resource);
        File resDestFile = new File(destination);
        OutputStream resStreamOut = new FileOutputStream(resDestFile);
        int readBytes;
        byte[] buffer = new byte[1024];
        while ((readBytes = resStreamIn.read(buffer)) > 0) {
            resStreamOut.write(buffer, 0, readBytes);
        }
        resStreamIn.close();
        resStreamOut.close();
    }

}
