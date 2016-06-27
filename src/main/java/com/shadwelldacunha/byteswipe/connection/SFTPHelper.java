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

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class SFTPHelper extends ProtocolHelper {
    JSch sftp;
    Session session;
    Channel channel;
    ChannelSftp c;

    public SFTPHelper(String host, String username, String password, int port) {
        super(host, username, password, port);
        //TODO: Implement proxy
        this.sftp = new JSch();
    }

    public SFTPHelper(String host, String username, File keyFile, int port) throws IOException {
        super(host, username, keyFile, port);
    }

    @Override
    public void connect() throws JSchException {
        if (session == null) session = sftp.getSession(username, host, port);
        if(session.getUserInfo() == null) {
            login();
        }
        session.connect();
        channel = session.openChannel("sftp");
        c = (ChannelSftp)channel;
        //TODO: Handle set hidden files through config
    }

    @Override
    public void login() throws JSchException {
        if (session == null) session = sftp.getSession(username, host, port);
        session.setUserInfo(userInfo);
    }

    @Override
    public void disconnect() {
        c.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    @Override
    public void ascii() {}

    @Override
    public void binary() {}

    @Override
    public void cd(String dir) throws SftpException {
        if(c.stat(dir).isLink()) {
            c.cd(c.readlink(dir));
        } else {
            c.cd(dir);
        }
    }

    @Override
    public void quit() {
        c.quit();
    }

    @Override
    public void close() {
        c.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    @Override
    public void del(String file) throws SftpException {
        c.rm(file);
    }

    @Override
    public Vector ls(String dir) throws SftpException {
        //TODO: Implement hidden files
        return c.ls(dir);
    }

    @Override
    public void get(String file, String dest) throws SftpException {
        if(c.stat(file).isLink()) {
            c.cd(c.readlink(file));
        } else {
            c.get(file,dest);
        }
    }

    @Override
    public void get(String file) throws Exception {
        get(file, localWorkingDir);
    }

    @Override
    public void put(String source, String dest) throws Exception {
        File sourceFile = new File(source);
        InputStream inputStream = new FileInputStream(sourceFile);
        c.put(inputStream, dest);
    }

    @Override
    public String pwd() throws Exception {
        return c.pwd();
    }

    @Override
    public void rmdir(String dir) throws Exception {
        c.rmdir(dir);
    }

    @Override
    public void mkdir(String dir) throws Exception {
        c.mkdir(dir);
    }

    @Override
    public void mdel(String[] files) throws Exception {
        for(String f : files) {
            del(f);
        }
    }

    @Override
    public void mget(String[] files, String dest) throws Exception {
        for(String f : files) {
            get(f, dest);
        }
    }

    @Override
    public void mget(String[] files) throws Exception {
        mget(files, localWorkingDir);
    }

    @Override
    public void chmod(String file, int val) throws Exception {
        c.chmod(val, file);
    }

    @Override
    public void rename(String oldFile, String newFile) throws SftpException {
        c.rename(oldFile, newFile);
    }


    @Override
    public void lcd(String dir) throws SftpException {
        this.localWorkingDir = dir;
        c.lcd(dir);
    }

    @Override
    public void lmkdir(String dir) throws Exception {
        //TODO: Add message to exception
        if(!new File(dir).mkdirs()) {
            throw new IOException();
        }
    }

    public void ln(String oldPath, String newPath) throws SftpException {
        c.symlink(oldPath,newPath);
    }

    private UserInfo userInfo = new UserInfo() {
        @Override
        public String getPassphrase() {
            return passPhrase;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean promptPassword(String message) {
            return false;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return false;
        }

        @Override
        public boolean promptYesNo(String message) {
            return false;
        }

        @Override
        public void showMessage(String message) {}
    };
}
