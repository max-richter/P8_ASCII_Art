
public class Canvas {

	private final int width;  // width of the canvas
	private final int height; // height of the canvas

	private char [][] drawingArray; // 2D character array to store the drawing

	private final DrawingStack undoStack; // store previous changes for undo
	private final DrawingStack redoStack; // store undone changes for redo

	public Canvas(int width, int height) { 
		
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Height or width cannot be negative or zero");
		}
		
		this.width = width;
		this.height = height;
		
		drawingArray = new char[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				drawingArray[i][j] = ' ';
			}
		}
		this.undoStack = new DrawingStack();
		this.redoStack = new DrawingStack();
		
	} // Constructor. Throws IllegalArgumentException if width or height is 0 or negative
	// A Canvas is initially blank (use the space ' ' character)

	public void draw(int row, int col, char c) { 
		
		if ((row < 0 || row > height) || (col < 0 || col > width)) {
			throw new IllegalArgumentException("Position is outside of the canvas");
		}
		
		char oldArr = drawingArray[row][col];
		DrawingChange drawingChange = new DrawingChange(row, col, oldArr, c);
		undoStack.push(drawingChange);
		
		if (!redoStack.isEmpty()) {
			redoStack.pop();
		}
		drawingArray[row][col] = c;
		
	} // Draw a character at the given position
	// This method should throw an IllegalArgumentException if the drawing position is outside the canvas
	// If that position is already marked with a different character, overwrite it.
	// After making a new change, add a matching DrawingChange to the undoStack so that we can undo if needed.
	// After making a new change, the redoStack should be empty.

	public boolean undo() { 
		
		DrawingChange recentChange = undoStack.pop();
		draw(recentChange.x, recentChange.y, recentChange.prevChar);
		redoStack.push(recentChange);
		return true; // will return false if unable to undo change
	} // Undo the most recent drawing change. Return true if successful. False otherwise.
	// An undone DrawingChange should be added to the redoStack so that we can redo if needed.

	public boolean redo() { 
		
		DrawingChange recentUndoneChange = redoStack.pop();
		draw(recentUndoneChange.x, recentUndoneChange.y, recentUndoneChange.newChar);
		undoStack.push(recentUndoneChange);
		return true; // will return false if unable to redo change
		
	} // Redo the most recent undone drawing change. Return true if successful. False otherwise.
	// A redone DrawingChange should be added (back) to the undoStack so that we can undo again if needed.

	public String toString() { 
		String displayCanvas = "";
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				displayCanvas += drawingArray[i][j];
			}
			if (i != width - 1) {
				displayCanvas += System.lineSeparator();
			}
		}
		return displayCanvas;
	} // Return a printable string version of the Canvas.
	/* Format example: [_ is blank. Use System.lineSeparator() to put a newline character between rows]
	 * X___X
	 * _X_X_
	 * __X__
	 * _X_X_
	 * X___X
	 */

}
