//package treta;
//
//import java.util.Objects;
//
//public class MovableCircle implements Movable {
//
//    public int radius;
//    public MovablePoint center;
//
//    public MovableCircle(int radius, MovablePoint center) {
//        this.radius = radius;
//        this.center = center;
//    }
//    public TYPE getType(){
//        return TYPE.CIRCLE;
//    }
//
//    public int getRadius() {
//        return radius;
//    }
//
//    @Override
//    public void moveUp() throws ObjectCanNotBeMovedException {
//        center.moveUp();
//    }
//
//    @Override
//    public void moveDown() throws ObjectCanNotBeMovedException {
//        center.moveDown();
//    }
//
//    @Override
//    public void moveLeft() throws ObjectCanNotBeMovedException {
//        center.moveLeft();
//    }
//
//    @Override
//    public void moveRight() throws ObjectCanNotBeMovedException {
//        center.moveRight();
//    }
//
//    @Override
//    public int getCurrentXPosition() {
//        return center.getCurrentXPosition();
//    }
//
//    @Override
//    public int getCurrentYPosition() {
//        return center.getCurrentYPosition();
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Movable circle with center coordinates (%d,%d) and radius %d", center.x, center.y, radius);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof MovableCircle)) return false;
//        MovableCircle that = (MovableCircle) o;
//        return radius == that.radius && Objects.equals(center, that.center);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(radius, center);
//    }
//}
