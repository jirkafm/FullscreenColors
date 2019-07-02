package com.github.jirkafm;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

class FullscreenColorsControl implements Listener {

	private final Random random;
	private final Shell shell;

	public FullscreenColorsControl(Shell shell) {
		this.shell = shell;
		this.random = new Random();
	}

	public void init() {
		shell.getDisplay().addFilter(SWT.KeyDown, this);
	}

	@Override
	public void handleEvent(Event event) {
		final int keyCode = event.keyCode;
		final int red = keyCode % 255;
		final int green = random.nextInt(255);
		final int blue = random.nextInt(255);
		shell.setBackground(new Color(shell.getDisplay(), red, green, blue));
	}

}
