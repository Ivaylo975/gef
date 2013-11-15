package org.eclipse.gef4.swtfx.examples.snippets;

import javafx.embed.swt.FXCanvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import org.eclipse.gef4.geometry.euclidean.Angle;
import org.eclipse.gef4.geometry.planar.Ellipse;
import org.eclipse.gef4.geometry.planar.IShape;
import org.eclipse.gef4.geometry.planar.Pie;
import org.eclipse.gef4.geometry.planar.Polygon;
import org.eclipse.gef4.geometry.planar.Rectangle;
import org.eclipse.gef4.geometry.planar.RoundedRectangle;
import org.eclipse.gef4.swtfx.SwtFXCanvas;
import org.eclipse.gef4.swtfx.SwtFXScene;
import org.eclipse.gef4.swtfx.SwtFXShape;
import org.eclipse.gef4.swtfx.controls.SwtFXButton;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShapeTest {
	private static SwtFXScene createScene() {
		HBox hbox = new HBox();
		VBox col1 = new VBox();
		VBox col2 = new VBox();
		hbox.getChildren().addAll(col1, col2);
		HBox.setHgrow(col1, Priority.ALWAYS);
		HBox.setHgrow(col2, Priority.ALWAYS);

		col1.getChildren().addAll(
				new Button("abc"),
				shape(new Polygon(50, 0, 100, 100, 0, 100), 0, 1, 0),
				shape(new Pie(0, 0, 100, 100, Angle.fromDeg(15), Angle
						.fromDeg(120)), 0, 1, 1), new SwtFXButton("123"));

		col2.getChildren().addAll(shape(new Ellipse(0, 0, 60, 80), 1, 0, 0),
				shape(new Rectangle(0, 0, 100, 50), 0, 0, 1),
				new SwtFXButton("foobar"),
				shape(new RoundedRectangle(0, 0, 100, 100, 10, 10), 1, 0, 1));

		// create scene (and set scene size)
		return new SwtFXScene(hbox, 400, 300);
	}

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		FXCanvas canvas = new SwtFXCanvas(shell, SWT.NONE);

		SwtFXScene scene = createScene();
		canvas.setScene(scene);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private static <T extends IShape> SwtFXShape<T> shape(T shape, double r,
			double g, double b) {
		SwtFXShape<T> fxShape = new SwtFXShape<T>(shape);
		fxShape.setFill(new Color(r, g, b, 1));
		fxShape.setStroke(new Color(0, 0, 0, 1));
		return fxShape;
	}

}
