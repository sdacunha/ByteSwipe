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

import com.jcraft.jsch.SftpException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;

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
public class FTPSHelper extends ProtocolHelper {
    private final FTPSClient ftps;
    //TODO: Handle symlinks
    public FTPSHelper(String host, String username, String password, int port) {
        super(host, username, password, port);
        //TODO: Handle proxy
        this.ftps = new FTPSClient();
        //TODO: Handle set hidden files through config
        this.ftps.setListHiddenFiles(true);
    }


    @Override
    public void connect() throws IOException {
        ftps.connect(host);
    }

    @Override
    public void login() throws IOException {
        if (!ftps.login(username, password)) {
            disconnect();
        }
    }

    @Override
    public void disconnect() throws IOException {
        ftps.logout();
        ftps.disconnect();

    }

    @Override
    public void ascii() throws IOException {
        ftps.setFileType(FTP.ASCII_FILE_TYPE);
    }

    @Override
    public void binary() throws IOException {
        ftps.setFileType(FTP.BINARY_FILE_TYPE);
    }

    @Override
    public void cd(String dir) throws IOException {
        ftps.changeWorkingDirectory(dir);
    }

    @Override
    public void lcd(String dir) throws SftpException {
        this.localWorkingDir = dir;
    }

    @Override
    public void lmkdir(String dir) throws Exception {
        //TODO: Add message to exception
        if(!new File(dir).mkdirs()) {
            throw new IOException();
        }
    }

    @Override
    public void quit() throws IOException {
        ftps.quit();
    }

    @Override
    public void close() throws IOException {
        ftps.disconnect();
    }

    @Override
    public void del(String file) throws IOException {
        ftps.deleteFile(file);
    }

    @Override
    public Vector ls(String dir) throws Exception {
        FTPFile[] files = ftps.listDirectories();
        return new Vector(Arrays.asList(files));
    }

    @Override
    public void get(String file, String dest) throws IOException {
        File destFile = new File(dest);
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destFile));
        ftps.retrieveFile(file, outputStream);
        outputStream.close();
    }

    public void get(String file) throws IOException {
        get(file, localWorkingDir);
    }

    @Override
    public void put(String source, String dest) throws IOException {
        File sourceFile = new File(source);
        InputStream inputStream = new FileInputStream(sourceFile);
        ftps.appendFile(source, inputStream);
    }

    @Override
    public String pwd() throws IOException {
        return ftps.printWorkingDirectory();
    }

    @Override
    public void rmdir(String dir) throws IOException {
        ftps.rmd(dir);
    }

    @Override
    public void mkdir(String dir) throws IOException {
        ftps.mkd(dir);
    }

    @Override
    public void mget(String[] files, String dest) throws IOException {
        for(String file : files) {
            get(file, dest);
        }
    }

    @Override
    public void mget(String[] files) throws IOException {
        mget(files, localWorkingDir);
    }

    @Override
    public void mdel(String[] files) throws Exception {
        for(String file : files) {
            del(file);
        }
    }

    @Override
    public void chmod(String file, int val) throws IOException {
        ftps.sendSiteCommand("chmod " + val + " " + file);
    }

    @Override
    public void rename(String oldFile, String newFile) throws Exception {
        ftps.rename(oldFile,newFile);
    }
}
