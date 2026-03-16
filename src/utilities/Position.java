package utilities;

public final class Position {
    private final int column;
    private final int row;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Position translate(int deltaColumn, int deltaRow) {
        return new Position(column + deltaColumn, row + deltaRow);
    }

    public boolean matches(Position other) {
        return column == other.column && row == other.row;
    }

    public void accept(IPositionVisitor visitor) {
        visitor.visit(column, row);
    }
}