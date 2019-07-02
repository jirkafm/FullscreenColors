package com.github.jirkafm;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

class FullscreenColorsControl {

	private final Random random;
	private final Shell shell;

	public FullscreenColorsControl(Shell shell) {
		this.shell = shell;
		this.random = new Random();
	}

	public void init() {
		shell.getDisplay().addFilter(SWT.KeyDown, this::generateColor);
		shell.getDisplay().addFilter(SWT.KeyDown, this::exitSequenceCheck);
	}

	public void generateColor(Event event) {
		final int keyCode = event.keyCode;
		final int red = keyCode % 255;
		final int green = random.nextInt(255);
		final int blue = random.nextInt(255);
		shell.setBackground(new Color(shell.getDisplay(), red, green, blue));
	}

	private void exitSequenceCheck(Event event) {
		if (isExitKeySequence(event)) {
			shell.close();
		}
	}

	private boolean isExitKeySequence(Event event) {
		final int keyCode = event.keyCode;
		return isCtrlHold(event) && 'q' == keyCode;
	}

	private boolean isCtrlHold(Event event) {
		return SWT.CTRL == (event.stateMask & SWT.CTRL);
	}

}
