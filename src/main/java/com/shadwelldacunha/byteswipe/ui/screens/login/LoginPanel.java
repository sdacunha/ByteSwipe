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

package com.shadwelldacunha.byteswipe.ui.screens.login;

import javax.swing.*;
import java.awt.*;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class LoginPanel extends JPanel {

    private final String[] protocols = { "FTP", "FTPS", "SFTP" };
    public LoginPanel() {
        setPreferredSize(new Dimension(250,250));
        setMinimumSize(new Dimension(200,200));

        setLayout(new GridBagLayout());
        protocols();
        port();
        username();
        password();
       login();
    }

    private void title() {
        JLabel title = new JLabel("Login");
        add(title);
    }

    private void protocols() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0.95;
        c.fill = GridBagConstraints.HORIZONTAL;
        JComboBox protocolSelector = new JComboBox(protocols);

        add(protocolSelector, c);
    }

    private void port() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0.05;
        c.fill = GridBagConstraints.HORIZONTAL;
        JTextField port = new JTextField(5);
        add(port,c);
    }

    private void username() {

        GridBagConstraints c = new GridBagConstraints();


        JTextField username = new JTextField();

        JLabel label = new JLabel("Username");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(label, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(username, c);
    }

    private void password() {

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Password");
        add(label, c);

        c.gridy = 4;
        c.gridwidth = 1;
        c.weightx = 0.95;
        JPasswordField password = new JPasswordField();
        add(password, c);

        c.gridx = 1;
        c.weightx = 0.05;
        JButton key = new JButton("Load key");
        add(key, c);

    }

    private void login() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton login = new JButton("Login");
        add(login, c);
    }

}
