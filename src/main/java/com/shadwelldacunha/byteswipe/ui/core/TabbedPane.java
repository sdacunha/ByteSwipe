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

package com.shadwelldacunha.byteswipe.ui.core;

import c10n.C10N;
import com.shadwelldacunha.byteswipe.localization.TabbedPaneLocalization;
import com.shadwelldacunha.byteswipe.ui.components.NewTabComponent;
import com.shadwelldacunha.byteswipe.ui.components.TabComponent;
import com.shadwelldacunha.byteswipe.ui.screens.Login;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class TabbedPane extends JTabbedPane {

    private TabbedPaneLocalization msg;
    public TabbedPane() {
        msg = C10N.get(TabbedPaneLocalization.class);

        Pane initialPane = new Pane(new Login());
        addTab(msg.newSession(), initialPane);
        setTabComponentAt(0, new TabComponent(this));
        addTab(null, null);
        setTabComponentAt(getTabCount() - 1, new NewTabComponent(this));
        addMouseListener(mouseListener);
    }

    MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            if (getSelectedIndex() == getTabCount() - 1) {
                insertTab(msg.newSession(), null, new Pane(new Login()), "", getTabCount() - 1);
                setTabComponentAt(getTabCount() - 2, new TabComponent(TabbedPane.this));
                setSelectedIndex(getTabCount() - 2);
            }
        }

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}
    };

    public void setTitleOfCurrentTab(String title) {
        setTitleAt(getSelectedIndex(), title);
    }
}
