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

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class FTPHelper extends ProtocolHelper {
    private final FTPClient ftp;
    //TODO: Handle symlinks
    public FTPHelper(String host, String username, String password, int port) {
        super(host, username, password, port);
        //TODO: Handle proxy
        this.ftp = new FTPClient();
        //TODO: Handle set hidden files through config
        this.ftp.setListHiddenFiles(true);
    }

    @Override
    public void connect() throws IOException {
        ftp.connect(host);
    }

    @Override
    public void login() throws IOException {
        if (!ftp.login(username, password)) {
            disconnect();
        }

    }

    @Override
    public void disconnect() throws IOException {
        ftp.logout();
        ftp.disconnect();
    }

    @Override
    public void ascii() throws IOException {
        ftp.setFileType(FTP.ASCII_FILE_TYPE);
    }

    @Override
    public void binary() throws IOException {
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
    }

    @Override
    public void cd(String dir) throws IOException {
        ftp.changeWorkingDirectory(dir);
    }

    @Override
    public void lcd(String dir) {
        this.localWorkingDir = dir;
    }

    @Override
    public void lmkdir(String dir) throws IOException {
        //TODO: Add message to exception
        if(!new File(dir).mkdirs()) {
            throw new IOException();
        }
    }

    @Override
    public void quit() throws IOException {
        ftp.quit();
    }

    @Override
    public void close() throws IOException {
        ftp.disconnect();
    }

    @Override
    public void del(String file) throws IOException {
        ftp.deleteFile(file);
    }

    @Override
    public Vector ls(String dir) throws IOException {
        FTPFile[] files = ftp.listDirectories();
        return new Vector(Arrays.asList(files));
    }

    @Override
    public void get(String file, String dest) throws IOException {
        File destFile = new File(dest);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destFile));
        ftp.retrieveFile(file, outputStream);
        outputStream.close();
    }

    @Override
    public void get(String file) throws IOException {
        get(file, localWorkingDir);
    }

    @Override
    public void put(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        InputStream inputStream = new FileInputStream(sourceFile);
        ftp.appendFile(source, inputStream);
    }

    @Override
    public String pwd() throws IOException {
        return ftp.printWorkingDirectory();
    }

    @Override
    public void rmdir(String dir) throws IOException {
        ftp.rmd(dir);
    }

    @Override
    public void mkdir(String dir) throws IOException {
        ftp.mkd(dir);
    }

    @Override
    public void mdel(String[] files) throws IOException {
        for (String file : files) {
            del(file);
        }
    }

    @Override
    public void mget(String[] files, String dest) throws IOException {
        for (String file : files) {
            get(file, dest);
        }
    }

    public void mget(String[] files) throws IOException {
        mget(files, localWorkingDir);
    }

    @Override
    public void chmod(String file, int val) throws IOException {
        ftp.sendSiteCommand("chmod " + val + " " + file);
    }

    @Override
    public void rename(String oldFile, String newFile) throws Exception {
        ftp.rename(oldFile,newFile);
    }
}
