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

package com.shadwelldacunha.byteswipe.connection;

import com.shadwelldacunha.byteswipe.core.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public abstract class ProtocolHelper {

    protected String host;
    protected String username;
    protected String password;
    protected String passPhrase;
    protected int port;
    protected String localWorkingDir;

    public ProtocolHelper(String host, String username, String password, int port) {
        this.username = username;
        this.password = password;
        this.port = port;
        this.host = host;
    }

    public ProtocolHelper(String host, String username, File keyFile, int port) throws IOException {
        this.username = username;
        //TODO: Handle exception gracefully
        this.passPhrase = Utilities.readFileAsString(keyFile);
        this.port = port;
        this.host = host;
    }


    public String getLocalWorkingDir() {
        return localWorkingDir;
    }

    public abstract void connect() throws Exception;

    public abstract void login() throws Exception;

    public abstract void disconnect() throws Exception;

    public abstract void ascii() throws  Exception;

    public abstract void binary() throws Exception;

    public abstract void cd(String dir) throws Exception;

    public abstract void quit() throws Exception;

    public abstract void close() throws Exception;

    public abstract void del(String file) throws Exception;

    public abstract Vector ls(String dir) throws Exception;

    public abstract void get(String file, String dest) throws Exception;

    public abstract void get(String file) throws Exception;

    public abstract void lcd(String dir) throws Exception;

    public abstract void lmkdir(String dir) throws Exception;

    public abstract void put(String source, String dest) throws Exception;

    public abstract String pwd() throws Exception;

    public abstract void rmdir(String dir) throws Exception;;

    public abstract void mkdir(String dir) throws Exception;;

    public abstract void mdel(String[] files) throws Exception;;

    public abstract void mget(String[] files, String dest) throws Exception;

    public abstract void mget(String[] files) throws Exception;

    public abstract void chmod(String file, int val) throws Exception;

    public abstract void rename(String oldFile, String newFile) throws Exception;
}
