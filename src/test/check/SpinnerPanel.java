/*
 * Copyright (c) 2005-2008 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer. 
 *     
 *  o Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution. 
 *     
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of 
 *    its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission. 
 *     
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 */
package test.check;

import java.awt.*;

import javax.swing.*;

import org.pushingpixels.lafwidget.LafWidget;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import test.check.command.*;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Test application panel for testing {@link JSpinner} component.
 * 
 * @author Kirill Grouchnikov
 */
public class SpinnerPanel extends JPanel {
	/**
	 * Creates a test panel with spinners.
	 */
	public SpinnerPanel() {
		this.setLayout(new BorderLayout());

		FormLayout lm = new FormLayout("right:pref, 4dlu, left:pref:grow", "");
		DefaultFormBuilder builder = new DefaultFormBuilder(lm,
				new ScrollablePanel());
		builder.setDefaultDialogBorder();

		CreationCommand<Component> basicCr = new CreationCommand<Component>() {
			public Component create() {
				JSpinner basicSpinner = new JSpinner(new SpinnerNumberModel());
				return basicSpinner;
			}
		};
		CreationCommand<Component> dateCr = new CreationCommand<Component>() {
			public Component create() {
				JSpinner dateEnSpinner = new JSpinner(new SpinnerDateModel());
				return dateEnSpinner;
			}
		};
		CreationCommand<Component> weekdaysCr = new CreationCommand<Component>() {
			public Component create() {
				String weekdaysEn[] = new String[] { "Sunday", "Monday",
						"Tuesday", "Wednesday", "Thursday", "Friday",
						"Saturday" };
				JSpinner listEnSpinner = new JSpinner(new SpinnerListModel(
						weekdaysEn));
				return listEnSpinner;
			}
		};
		CreationCommand<Component> numberCr = new CreationCommand<Component>() {
			public Component create() {
				JSpinner numberEnSpinner = new JSpinner(new SpinnerNumberModel(
						0, 0, 100, 5));
				return numberEnSpinner;
			}
		};

		builder.appendSeparator("Enabled");
		addSpinner(builder, "Basic", basicCr, null);
		addSpinner(builder, "Date", dateCr, null);
		addSpinner(builder, "Weekdays", weekdaysCr, null);
		addSpinner(builder, "Weekdays select on focus", weekdaysCr,
				new ClientPropertyCommand(LafWidget.TEXT_SELECT_ON_FOCUS,
						Boolean.TRUE));
		addSpinner(builder, "Number", numberCr, null);
		addSpinner(builder, "Number flat", numberCr, new ClientPropertyCommand(
				SubstanceLookAndFeel.FLAT_PROPERTY, Boolean.TRUE));
		addSpinner(builder, "Number never", numberCr,
				new ClientPropertyCommand(
						SubstanceLookAndFeel.BUTTON_PAINT_NEVER_PROPERTY,
						Boolean.TRUE));
		addSpinner(builder, "Number pink", numberCr,
				new BackgroundColorCommand(new Color(255, 128, 128)));

		builder.appendSeparator("Disabled");
		addSpinner(builder, "Basic", basicCr, new DisableCommand());
		addSpinner(builder, "Date", dateCr, new DisableCommand());
		addSpinner(builder, "Weekdays", weekdaysCr, new DisableCommand());
		addSpinner(builder, "Weekdays select on focus", weekdaysCr,
				new ChainCommand<Component>(new ClientPropertyCommand(
						LafWidget.TEXT_SELECT_ON_FOCUS, Boolean.TRUE),
						new DisableCommand()));
		addSpinner(builder, "Number", numberCr, new DisableCommand());
		addSpinner(builder, "Number flat", numberCr,
				new ChainCommand<Component>(new ClientPropertyCommand(
						SubstanceLookAndFeel.FLAT_PROPERTY, Boolean.TRUE),
						new DisableCommand()));

		this.add(new JScrollPane(builder.getPanel()), BorderLayout.CENTER);
	}

	private void addSpinner(DefaultFormBuilder builder, String label,
			CreationCommand<Component> creationCmd,
			ConfigurationCommand<Component> configurationCmd) {

		Component comp = creationCmd.create();

		if (configurationCmd != null) {
			configurationCmd.configure(comp);
		}

		JLabel jl = new JLabel(label);
		builder.append(jl);
		builder.append(comp);
	}

}