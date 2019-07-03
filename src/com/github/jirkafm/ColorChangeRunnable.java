package com.github.jirkafm;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;

public class ColorChangeRunnable implements Runnable {

	private Color color;
	private final Shell shell;

	public ColorChangeRunnable(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void run() {
		if (color != null) {
			shell.getDisplay().syncExec(() -> shell.setBackground(color));
			color = null;
		}
	}

	public void nextColor(Color color) {
		this.color = color;
	}

}
