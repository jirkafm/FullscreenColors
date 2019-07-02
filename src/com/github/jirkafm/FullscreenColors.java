package com.github.jirkafm;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class FullscreenColors {

	private final Display display;

	public FullscreenColors(Display display) {
		this.display = display;
	}

	public static void main(String[] args) {
		final Display display = new Display();
		final FullscreenColors fullscreenColors = new FullscreenColors(display);
		fullscreenColors.run();
	}

	private void run() {
		final Shell shell = createFullscreenShell();
		final FullscreenColorsControl fullscreenColorsControl = new FullscreenColorsControl(shell);
		fullscreenColorsControl.init();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private Shell createFullscreenShell() {
		final Monitor primaryMonitor = display.getPrimaryMonitor();
		final Rectangle primaryMonitorBounds = primaryMonitor.getBounds();

		final Shell shell = new Shell(display);
		shell.setText("FullscreenColors");
		shell.setLayout(new FillLayout());
		shell.setLocation(primaryMonitorBounds.x, primaryMonitorBounds.y);
		shell.setSize(primaryMonitorBounds.width, primaryMonitorBounds.height);
		shell.setFullScreen(true);
		return shell;
	}

}
