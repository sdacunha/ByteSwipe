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
import com.shadwelldacunha.byteswipe.localization.MenuBarLocalization;
import com.shadwelldacunha.byteswipe.localization.WindowLocalization;
import com.shadwelldacunha.byteswipe.ui.components.Menu;
import com.shadwelldacunha.byteswipe.ui.components.MenuItem;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Enter class description here
 * <p>
 *
 * @author Shadwell Da Cunha
 * @since 1.0.0
 */
public class MenuBar extends JMenuBar {

    MenuBarLocalization msg;

    //TODO: Update and clean this
    public MenuBar() {
        msg = C10N.get(MenuBarLocalization.class);
        fileItems(new Menu(this, msg.file(), KeyEvent.VK_F, msg.fileDescription()));
        helpItems(new Menu(this, msg.help(), KeyEvent.VK_H, msg.fileDescription()));
    }


    private void fileItems(JMenu menu) {
        new MenuItem(menu, msg.exit(), KeyEvent.VK_E, KeyEvent.VK_F4, ActionEvent.ALT_MASK, msg.exitDescription(), (e) -> System.exit(0));
    }

    private void helpItems(JMenu menu) {
        new MenuItem(menu, msg.about(), KeyEvent.VK_A, -1, -1, msg.exitDescription(), (e) -> {

        });
    }
}
