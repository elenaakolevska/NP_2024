//package treta;
//
//import java.util.Objects;
//
//public class MovablePoint implements Movable {
//
//    public int x;
//    public int y;
//    public int xSpeed;
//    public int ySpeed;
//
//    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
//        this.x = x;
//        this.y = y;
//        this.xSpeed = xSpeed;
//        this.ySpeed = ySpeed;
//    }
//
//    public TYPE getType() {
//        return TYPE.POINT;
//    }
//
//    @Override
//    public void moveUp() throws ObjectCanNotBeMovedException {
//        if (y < 0 || y + ySpeed > MovablesCollection.y_MAX) {
//            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y));
//        }
//        y += ySpeed;
//    }
//
//    @Override
//    public void moveDown() throws ObjectCanNotBeMovedException {
//        if (y - ySpeed < 0 || y > MovablesCollection.y_MAX) {
//            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y));
//        }
//        y -= ySpeed;
//    }
//
//    @Override
//    public void moveLeft() throws ObjectCanNotBeMovedException {
//        if (x - xSpeed < 0 || x > MovablesCollection.x_MAX) {
//            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y));
//        }
//        x -= xSpeed;
//    }
//
//    @Override
//    public void moveRight() throws ObjectCanNotBeMovedException {
//        if (x < 0 || x + xSpeed > MovablesCollection.x_MAX) {
//            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, y));
//        }
//        x += xSpeed;
//    }
//
//    @Override
//    public int getCurrentXPosition() {
//        return x;
//    }
//
//    @Override
//    public int getCurrentYPosition() {
//        return x;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Movable point with coordinates (%d,%d)", x, y);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof MovablePoint)) return false;
//        MovablePoint that = (MovablePoint) o;
//        return x == that.x && y == that.y && xSpeed == that.xSpeed && ySpeed == that.ySpeed;
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y, xSpeed, ySpeed);
//    }
//}
