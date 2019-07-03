package com.github.jirkafm;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

class FullscreenColorsControl {

	private final Shell shell;
	private final ColorChangeRunnable colorChangeRunnable;

	private final Random random = new Random();
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	public FullscreenColorsControl(Shell shell) {
		this.shell = shell;
		this.colorChangeRunnable = new ColorChangeRunnable(shell);
	}

	public void init() {
		shell.getDisplay().addFilter(SWT.KeyDown, this::registerColorChange);
		shell.getDisplay().addFilter(SWT.KeyDown, this::exitSequenceCheck);
		scheduler.scheduleAtFixedRate(colorChangeRunnable, 1, 500, TimeUnit.MILLISECONDS);
	}

	public void registerColorChange(Event event) {
		final int keyCode = event.keyCode;
		final int red = keyCode % 255;
		final int green = random.nextInt(255);
		final int blue = random.nextInt(255);
		colorChangeRunnable.nextColor(new Color(shell.getDisplay(), red, green, blue));
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
