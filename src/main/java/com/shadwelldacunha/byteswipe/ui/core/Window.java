/*
 * MIT License
 *
 * Copyright (c) year Shadwell Da Cunha
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

package com.shadwelldacunha.byteswipe.ui.core;

import c10n.C10N;
import com.shadwelldacunha.byteswipe.core.ComponentLoader;
import com.shadwelldacunha.byteswipe.localization.WindowLocalization;
import com.shadwelldacunha.byteswipe.ui.components.FunctionBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class Window extends JFrame {

    private TabbedPane sessions;
    private FunctionBar functionBar;
    private ComponentLoader componentLoader;
    WindowLocalization msg;

    public Window() {
        super();
        this.sessions = new TabbedPane();
        this.functionBar = new FunctionBar();
        this.componentLoader = new ComponentLoader();
        this.msg =  C10N.get(WindowLocalization.class);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(windowListener);
        setLayout(new GridBagLayout());
        addComponents();
        setupDefaultFrame();
        setTitle(msg.title());
    }

    private void setupDefaultFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        menubar();
        functionBar();
        sessions();
    }

    //TODO: Improve this
    private void menubar() {
        setJMenuBar(new MenuBar());
    }

    private void functionBar() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(functionBar, c);
    }
    private void sessions() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(sessions, c);
    }

    WindowListener windowListener = new WindowListener() {
        public void windowOpened(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {}

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}

        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}

        public void windowDeactivated(WindowEvent e) {}
    };
}
