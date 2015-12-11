JCC = javac
JFLAGS = -g

default: ActionSquare.class Board.class Game.class GameBoardPanel.class PlainSquare.class Square.class SquarePanel.class

ActionSquare.class: ActionSquare.java
	$(JCC) $(JFLAGS) ActionSquare.java

Board.class: Board.java
	$(JCC) $(JFLAGS) Board.java

Game.class: Game.java
	$(JCC) $(JFLAGS) Game.java

GameBoardPanel.class: GameBoardPanel.java
	$(JCC) $(JFLAGS) GameBoardPanel.java

PlainSquare.class: PlainSquare.java
	$(JCC) $(JFLAGS) PlainSquare.java

Square.class: Square.java
	$(JCC) $(JFLAGS) Square.java

SquarePanel.class: SquarePanel.java
	$(JCC) $(JFLAGS) SquarePanel.java

clean:
	$(RM) *.class
