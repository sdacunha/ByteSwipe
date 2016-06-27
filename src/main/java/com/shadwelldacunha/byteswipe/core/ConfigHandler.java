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


import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class ConfigHandler {
    PropertiesConfiguration config;
    String file;

    public ConfigHandler(String file) {
        writeDefaultFile(file);
        setup(file);
        checkDefaults(file);
    }

    private void setup(String file) {
        this.file = file;
        try {
            config = new PropertiesConfiguration("byteswipe.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void writeDefaultFile(String file) {
        if(!new File(file).exists()) {
            try {
                Utilities.copyResource(file, file);
            } catch (IOException e) {
                //TODO: Handle config not saving
                //TODO: Log message
                e.printStackTrace();
            }
        }
    }

    private void checkDefaults(String file) {
        try {
            PropertiesConfiguration defConfig = new PropertiesConfiguration(Utilities.getResource("byteswipe.properties"));
            Iterator<String> defKeys = defConfig.getKeys();

            //Add missing keys
            while(defKeys.hasNext()) {
               String key = defKeys.next();
                if(!config.containsKey(key)){
                    config.addProperty(key, defConfig.getProperty(key));
                }
            }

            config.save();
        //TODO: Handle exceptions
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Configuration getConfig() {
        return this.config;
    }
}
